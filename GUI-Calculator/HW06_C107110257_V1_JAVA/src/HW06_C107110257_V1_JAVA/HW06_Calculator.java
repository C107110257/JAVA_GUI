package HW06_C107110257_V1_JAVA;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HW06_Calculator extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JButton[] num1buttons,num2buttons;
	private JRadioButton d_r_button,h_r_button;
	private String names1[] = {"0", "=", "Clr", "+", "1", "2","3","-","4","5","6","*","7","8","9","/"};
	private String names2[] = {"A","B","C","D","E","F"};
	private JPanel panel0,panel1,panel2,panel3,panel4;
	private String str1,str2,instring="",outstring="",s;
	public HW06_Calculator() {
		
		super("小算盤_two-integer operation_C107110257");
		panel0=new JPanel();
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		
		GridLayout fout=new GridLayout(1,1);
		panel1.setLayout(fout);
		text = new JTextField("輸入規則:【數字】【 運算符號 】【數字】【=】   (A~F於十進位無動作)");
		panel1.add(text);
		
		GridLayout num1layout=new GridLayout(4, 4);
		panel2.setLayout(num1layout);
		num1buttons = new JButton[names1.length];
		for(int i=0; i < names1.length; i++){
			num1buttons[i] = new JButton(names1[i]);
			panel2.add(num1buttons[i]);
		}
		
		GridLayout num2layout=new GridLayout(1, 6);
		panel3.setLayout(num2layout);
		num2buttons = new JButton[names2.length];
		for(int i=0; i < names2.length; i++){
			num2buttons[i] = new JButton(names2[i]);
			panel3.add(num2buttons[i]);
		}
		
		GridLayout rlayout=new GridLayout(1, 2);
		panel4.setLayout(rlayout);
		d_r_button=new JRadioButton("十進位",true);
		h_r_button=new JRadioButton("十六進位");
		panel4.add(d_r_button);
		panel4.add(h_r_button);
		
		
		ButtonGroup buttonGroup = new ButtonGroup();//處理互斥
		buttonGroup.add(d_r_button);
		buttonGroup.add(h_r_button);

		
		GridLayout complex_south=new GridLayout(2, 1);//將(A~F)(菜單)合併
		panel0.setLayout(complex_south);
		panel0.add(panel3);
		panel0.add(panel4);
		
		setLayout(new BorderLayout());//最後依序加入表面
		add(panel1,BorderLayout.NORTH);
		add(panel2,BorderLayout.CENTER);
		add(panel0,BorderLayout.SOUTH);
		
		
		input actionlistener = new input();
		for(int i=0; i < names1.length; i++){
			num1buttons[i].addActionListener(actionlistener);
		}
		for(int i=0; i < names2.length; i++){
			num2buttons[i].addActionListener(actionlistener);
		}
	}// End of Constructure
	public String compute(String op,int n1,int n2) {
		switch(op) {
		case "+":
			if(h_r_button.isSelected())
				return String.valueOf(Integer.toHexString(n1+n2));
			else
				return String.valueOf(n1+n2);
		case "-":
			if(h_r_button.isSelected())
				return String.valueOf(Integer.toHexString(n1-n2));
			else
				return String.valueOf(n1-n2);
		case "*":
			if(h_r_button.isSelected())
				return String.valueOf(Integer.toHexString(n1*n2));
			else
				return String.valueOf(n1*n2);
		case "/":
			if(h_r_button.isSelected())
				return String.valueOf(Integer.toHexString(n1/n2));
			else
				return String.valueOf(n1/n2);
		default:
			return "";
		}
	}
	private class input implements ActionListener{
		public void actionPerformed(ActionEvent b_event) {
			for(int i=0;i<names1.length;i++) {	
				if(b_event.getSource() == num1buttons[i]) {
					if(names1[i] == "+"||(names1[i] == "-"&& instring !="") ||names1[i] == "*"||names1[i] == "/") {// +*/
						s=names1[i];
						str1=instring;
						outstring+=names1[i];
						text.setText(new String(outstring));
						instring="";
					}else if(i == 1) {//=
						str2=instring;
						if(h_r_button.isSelected()) {
							text.setText(new String(instring=outstring=compute(s,Integer.parseInt(str1, 16),Integer.parseInt(str2, 16))));
						}else{
							text.setText(new String(instring=outstring=compute(s,Integer.parseInt(str1, 10),Integer.parseInt(str2, 10))));
						}
						s="";
					}else if(i == 2) {//clr
						str1=str2=instring=s=outstring="";
						text.setText(new String("已全部清除"));
					}else {
						instring+=names1[i];
						outstring+=names1[i];
						text.setText(new String(outstring));
					}
				}
			}
			if(h_r_button.isSelected()) {
				for(int i=0;i<names2.length;i++) {
					if(b_event.getSource() == num2buttons[i]) {
						instring+=names2[i];
						outstring+=names2[i];
						text.setText(new String(outstring));
					}
				}
			}
		}// End of actionPerformed
	}// End of input implements ActionListener
}//End of HW06_Calculator extends JFrame
