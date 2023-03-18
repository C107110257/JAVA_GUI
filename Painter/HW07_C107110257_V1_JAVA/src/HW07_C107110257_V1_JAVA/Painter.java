package HW07_C107110257_V1_JAVA;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.*;
import javax.swing.*;
//------------------------------------------------------------
public class Painter extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton color_button;
	private JPanel r_panel;
	private JPanel draw_panel;
	private JRadioButton[] r_bs;
	private	String[] rb_ns= {"Line","Rect","Round Rect","Fill Rect"};
	private Color color=Color.WHITE;
	private int dm;
	ArrayList<shape> objects = new ArrayList<>();
	int[] x=new int[2];
	int[] y=new int[2];
	public Painter(){
	super("C107110257_Painter");
	//------------------------------------------------------------選形狀
	r_panel = new JPanel();
	r_panel.setLayout(new GridLayout(2,2));
	r_bs = new JRadioButton[rb_ns.length];
	for(int count=0; count < rb_ns.length; count++){
		r_bs[count] = new JRadioButton(rb_ns[count]);
		r_panel.add(r_bs[count]);
	}/* End of for-loop */
	add(r_panel, BorderLayout.NORTH);
	//------------------------------------------------------------畫板
	draw_panel = new LineRectOvalsJPanel();
	add(draw_panel, BorderLayout.CENTER);
	//------------------------------------------------------------選顏色
	color_button = new JButton("Color Chooser");
	add(color_button, BorderLayout.SOUTH);
	//------------------------------------------------------------圓點互斥
	ButtonGroup buttonGroup = new ButtonGroup();
	for(int i=0; i < r_bs.length; i++){
		buttonGroup.add(r_bs[i]);
	}/* End of for-loop */
	//------------------------------------------------------------畫板動作
	ColorBtnHandler handler = new ColorBtnHandler();
	color_button.addActionListener(handler);
	//------------------------------------------------------------滑鼠動作
	MouseHandler mousehandler = new MouseHandler();
	draw_panel.addMouseListener(mousehandler);
	
	R_B_Handler rhandler =new R_B_Handler();
	for(int count=0; count < rb_ns.length; count++){
		r_bs[count].addItemListener(rhandler);
	}
}
//------------------------------------------------------------選色實作
	private class ColorBtnHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			Color c= JColorChooser.showDialog(Painter.this, "Color Chooser", color);
			if(c!=Color.WHITE)
				color=c;
		}
	}
//------------------------------------------------------------滑鼠實作
	public class MouseHandler implements MouseListener{
		public void mouseClicked(MouseEvent event){}
		public void mousePressed(MouseEvent event){
			x[0]=event.getX();
			y[0]=event.getY();
		}
		public void mouseReleased(MouseEvent event){
			x[1]=event.getX();
			y[1]=event.getY();
			shape ashape =new shape(dm,color,x,y);
			objects.add(ashape);
			draw_panel.repaint();
		}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}
	}
//------------------------------------------------------------畫板實作
	private class LineRectOvalsJPanel extends JPanel{
		private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics gp){
				super.paintComponent(gp);
				this.setBackground(Color.WHITE);
				for(int i=0;i<objects.size();i++) {
					objects.get(i).paint(gp);
				}
		}
	}
//------------------------------------------------------------
	public class shape implements Serializable{
		private static final long serialVersionUID = 1L;
		int r,g,b;
		int s;
		int[] xv =new int[2];
		int[] yv =new int[2];
		public shape(int s,Color color,int[] pt1,int[] pt2) {
			this.s=s;
			r=color.getRed();
			g=color.getGreen();
			b=color.getBlue();
			xv[0]=pt1[0]; xv[1]=pt1[1];
			yv[0]=pt2[0]; yv[1]=pt2[1];
		}
		//------------------------------------------------------------
		public void paint(Graphics gp) {
			gp.setColor(new Color(r,g,b));
			int left,top,width,height;
			width = Math.abs(xv[0]-xv[1]);
			height = Math.abs(yv[0]-yv[1]);
			if(xv[0]<xv[1])
				left=xv[0];
			else
				left=xv[1];
			if(yv[0]<yv[1])
				top=yv[0];
			else
				top=yv[1];
			switch(s) {
			case 0:
				gp.drawLine(xv[0], yv[0], xv[1], yv[1]);
				break;
			case 1:
				gp.drawRect(left, top, width, height);
				break;
			case 2:
				gp.drawRoundRect(left, top, width, height,25, 25);
				break;
			case 3:
				gp.fillRect(left, top, width, height);
				break;
			default:
				break;
			}
		}
		//------------------------------------------------------------
	}
//------------------------------------------------------------
	public class R_B_Handler implements ItemListener{
		public void itemStateChanged(ItemEvent ievent){
			if(r_bs[1].isSelected()) 
				dm=1;
			else if(r_bs[2].isSelected()) 
				dm=2;
			else if(r_bs[3].isSelected()) 
				dm=3;
			else
				dm=0;
		}
	}
//------------------------------------------------------------
}
