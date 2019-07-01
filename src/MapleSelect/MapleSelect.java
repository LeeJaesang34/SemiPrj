package MapleSelect;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import view.MapleView;
import view.MapleView2;
//�԰��ؿϷ�
public class MapleSelect extends JFrame{
   MapleView maple;
   MapleView2 maple2;
   
   public MapleSelect() {
	  Toolkit kit =Toolkit.getDefaultToolkit();//��Ŷ����
	  Dimension screenSize = kit.getScreenSize();
	  Image img = kit.getImage("icon.png");
	  setIconImage(img);
	  
      maple=new MapleView();//��ü����
      maple2=new MapleView2();
      //���߰�
      JTabbedPane pane=new JTabbedPane();
      
      pane.add("ĳ���������Է�", maple);
      pane.add("ĳ���ͻ���", maple2);
      pane.setSelectedIndex(0);
      setTitle("MapleStory");
      add("Center",pane);
      setSize(800, 600);
      setVisible(true);
   }
   
   public static void main(String[] args) {
      
      new MapleSelect();
   }
   
}