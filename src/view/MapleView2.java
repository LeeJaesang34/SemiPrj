package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.w3c.dom.css.RGBColor;

public class MapleView2 extends Panel implements ActionListener{

	ImageIcon img=new ImageIcon("���.jpg");
	ImageIcon img2=new ImageIcon("�޸�.jpg");
	ImageIcon img3=new ImageIcon("�ƶ�.jpg");
	ImageIcon img4=new ImageIcon("����.jpg");
	ImageIcon img5=new ImageIcon("����.jpg");
	ImageIcon img6=new ImageIcon("����.jpg");
	ImageIcon img7=new ImageIcon("����.jpg");
	ImageIcon img8=new ImageIcon("����.jpg");
	
	JLabel dual;
	
	JButton b1;
	JButton b2; 
	JButton b3;
	JButton b4 ;
	JButton b5 ;
	JButton b6 ;
	JButton b7 ;
	JButton b8 ;
	
	public MapleView2() {
	      b1=new JButton("���");
	      b2=new JButton("�޸�");
	      b3=new JButton("�ƶ�");
	      b4 = new JButton("����");
	      b5 = new JButton("����");
	      b6 = new JButton("����");
	      b7 = new JButton("����");
	      b8 = new JButton("����");
	      
	      
	      b1.addActionListener(this);
	      b2.addActionListener(this);
	      b3.addActionListener(this);
	      b4.addActionListener(this);
	      b5.addActionListener(this);
	      b6.addActionListener(this);
	      b7.addActionListener(this);
	      b8.addActionListener(this);
	      dual=new JLabel();
	      //��ư�� ���� ���� ������
	      b1.setBackground(Color.red);
	      b2.setBackground(Color.blue);
	      b3.setBackground(Color.cyan);
	      b4.setBackground(Color.LIGHT_GRAY);
	      b5.setBackground(Color.magenta);
	      b6.setBackground(Color.orange);
	      b7.setBackground(Color.YELLOW);
	      b8.setBackground(Color.green);

	      JPanel p_west = new JPanel();
		p_west.setLayout(new GridLayout(4,2));

		p_west.add(b1);  //�����̳ʿ� ������Ʈ �߰�
		p_west.add(b2);	
		p_west.add(b3);	
		p_west.add(b4);	
		p_west.add(b5);
		p_west.add(b6);
		p_west.add(b7);
		p_west.add(b8);
	

		JPanel panel=new JPanel();
		setLayout(new GridLayout(1, 1));
		panel.add(dual);
		
		setLayout(new GridLayout(2, 1));
		add(p_west);
		add(panel);
	
		setVisible(true); //ȭ�鿡 ���̱�

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		if (evt==b1) {
			dual.setIcon(img);	
		}if(evt==b2){
			dual.setIcon(img2);
		}if(evt==b3){
			dual.setIcon(img3);
		}if(evt==b4){
			dual.setIcon(img4);
		}if(evt==b5){
			dual.setIcon(img5);
		}if(evt==b6){
			dual.setIcon(img6);
		}if(evt==b7){
			dual.setIcon(img7);
		}if(evt==b8){
			dual.setIcon(img8);
			
		}
	
		
	}
}