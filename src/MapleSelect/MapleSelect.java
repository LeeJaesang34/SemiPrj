package MapleSelect;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import view.MapleView;
import view.MapleView2;
//입고준완료
public class MapleSelect extends JFrame{
   MapleView maple;
   MapleView2 maple2;
   
   public MapleSelect() {
	  Toolkit kit =Toolkit.getDefaultToolkit();//툴킷생성
	  Dimension screenSize = kit.getScreenSize();
	  Image img = kit.getImage("icon.png");
	  setIconImage(img);
	  
      maple=new MapleView();//객체생성
      maple2=new MapleView2();
      //탭추가
      JTabbedPane pane=new JTabbedPane();
      
      pane.add("캐릭터정보입력", maple);
      pane.add("캐릭터사진", maple2);
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