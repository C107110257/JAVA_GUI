package HW05_C107110257_V1_JAVA;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import HW05_C107110257_V1_JAVA.Math;

public class GCD_LCM_RP extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel label_n1;
	private JLabel label_n2;
	private JLabel label_sol;
	
	private JTextField text_n1;
	private JTextField text_n2;
	private JTextField text_sol;
	
	private JButton start_b;//�}�l�p��
	private JRadioButton gcd_b;//�̤j���]
	private JRadioButton lcm_b;//�̤p����
	private JRadioButton coprime_b;//����
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	
	private int op;

	public GCD_LCM_RP() {//�غc��
		super("C107110257_HW05");
	
		
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		
		
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		
		//---------------------------------------------------------�Ĥ@��
		label_n1 = new JLabel("Number 1:");
		label_n2 = new JLabel("Number 2:");
		text_n1 = new JTextField(8);
		text_n2 = new JTextField(8);
		panel1.add(label_n1);
		panel1.add(text_n1);
		panel1.add(label_n2);
		panel1.add(text_n2);
		
		
		//---------------------------------------------------------�ĤG��
		gcd_b = new JRadioButton("�̤j���]��");
		lcm_b = new JRadioButton("�̤p������");
		coprime_b = new JRadioButton("����");
		panel2.add(gcd_b); 
		panel2.add(lcm_b);
		panel2.add(coprime_b);
		
		
		//---------------------------------------------------------�ĤT��
		start_b = new JButton("�}�l�p��");
		label_sol=new JLabel("���G:");
		text_sol = new JTextField(16);
		panel3.add(start_b);
		panel3.add(label_sol);
		panel3.add(text_sol);
		
		
		//---------------------------------------------------------��
		setLayout(new GridLayout(3,1));
		add(panel1);
		add(panel2);
		add(panel3);
		//---------------------------------------------------------���s������
		ButtonGroup b_Group = new ButtonGroup();
		b_Group.add(gcd_b);
		b_Group.add(lcm_b);
		b_Group.add(coprime_b);
		
		//---------------------------------------------------------�B�z�ﶵ
		RadioButtonHandler R_B_Handler = new RadioButtonHandler();
		gcd_b.addItemListener(R_B_Handler);
		lcm_b.addItemListener(R_B_Handler);
		coprime_b.addItemListener(R_B_Handler);
		
		//---------------------------------------------------------�B�z�}�l��
		MouseButtonHandler M_B_Hander =new MouseButtonHandler();
		start_b.addActionListener(M_B_Hander);
		
	}//end GCD_LCM_RP construct
	private class RadioButtonHandler implements ItemListener{//��@�I��
		public void itemStateChanged(ItemEvent i_event){
			if(i_event.getSource() == gcd_b) {
				op=1;
			}else if(i_event.getSource() == lcm_b) {
				op=2;
			}else if(i_event.getSource() == coprime_b) {
				op=3;
			}
		}
	}//end RadioButtonHandler
	private class MouseButtonHandler implements ActionListener{//��@�I���}�l
		public void actionPerformed(ActionEvent a_event) {
			Math m =new Math(text_n1.getText(),text_n2.getText());
			switch(op) {
			case 1:
				text_sol.setText(new String(m.GCD()));
				break;
			case 2:
				text_sol.setText(new String(m.LCM()));
				break;
			case 3:
				text_sol.setText(new String(m.IS()));
				break;
			default:
				text_sol.setText(new String("�п��\"�B��Ҧ�\""));
			}
		}
	}//end MouseButtonHandler
}//end class
