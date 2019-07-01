package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.MapleModel;
import model.vo.Maple;

public class MapleView extends Panel implements ActionListener {

   JTextField tfMnum, tfMname,tfMlevel;
   JComboBox comMjob, comMgender;
   JTextArea taMContent;

   

   JButton bMapleInsert, bMapleModify, bMapleDelete;

   JComboBox comMapleSearch;
   JTextField tfMapleSearch;
   JTable tableMaple;// jtable view역할

   // 추가
   MapleModel model;
   MapleTableModel tbModelMaple;//jTable모델관련
   
   public MapleView() {
      addLayout();// 화면설계
      // 초기화 작업
      initStyle();
      eventProc();
      // db변경
      connectDB();// 호출
   }

   void initStyle() {
   tfMnum.setEditable(false);// 비활성화
      
   }

   private void connectDB() {
      try {
         model =new MapleModel();
         System.out.println("메이플연결성공");
      } catch (Exception e) {
         System.out.println("메이플연결실패");
      }
   }

   public void eventProc() {
      // 수신기 부착
      bMapleDelete.addActionListener(this);
      bMapleInsert.addActionListener(this);
      bMapleModify.addActionListener(this);
      tfMapleSearch.addActionListener(this);
      //JTable에 리스너 부착
      tableMaple.addMouseListener(new MouseAdapter() {
         @Override
       public void mouseClicked(MouseEvent e) {
            int row=tableMaple.getSelectedRow();
            int col=0;
            //클릭레코드 캐릭터순서 얻기
            String data= (String) tableMaple.getValueAt(row, col);
            int no=Integer.parseInt(data);
            
            try {
            Maple vo=model.selectbyPk(no);
            selectbyPk(vo);
         } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         }
         
   });
   }

   
   private void addLayout() {
      // 탭의 디자인 구성
	  tfMnum = new JTextField();
	  tfMname = new JTextField();
      tfMlevel = new JTextField();

      String[] cbJanreStr = { "전사", "도적", "궁수", "마법사", "해적" };
      comMjob = new JComboBox(cbJanreStr);
      String[] cbJanreStr1 = { "남자", "여자"};
      comMgender = new JComboBox(cbJanreStr1);
      taMContent = new JTextArea();


      // 버튼
      bMapleInsert = new JButton("입력");
      bMapleModify = new JButton("수정");
      bMapleDelete = new JButton("삭제");

      String[] cbMapleSearch = { "닉네임", "직업" };
      comMapleSearch = new JComboBox(cbMapleSearch);
      tfMapleSearch = new JTextField(15);
      //나중에 
      tbModelMaple=new MapleTableModel();
      tableMaple=new JTable(tbModelMaple);
      tableMaple.setModel(tbModelMaple);
      
      // 화면구성
      // west판넬 구성
      JPanel p_west = new JPanel();
      p_west.setLayout(new BorderLayout());

      // 왼쪽 가운데
      JPanel p_west_center = new JPanel();
      p_west_center.setLayout(new BorderLayout());

      // 왼쪽 가운데 위쪽
      JPanel p_west_center_north = new JPanel();
      p_west_center_north.setLayout(new GridLayout(5, 2));
      p_west_center_north.add(new JLabel("캐릭터순서"));
      p_west_center_north.add(tfMnum);

      p_west_center_north.add(new JLabel("직업"));
      p_west_center_north.add(comMjob);

      p_west_center_north.add(new JLabel("닉네임"));
      p_west_center_north.add(tfMname);

      p_west_center_north.add(new JLabel("성별"));
      p_west_center_north.add(comMgender);

      p_west_center_north.add(new JLabel("레벨"));
      p_west_center_north.add(tfMlevel);

      // 왼쪽 가운데 가운데
      JPanel p_west_center_center = new JPanel();
      p_west_center_center.setLayout(new BorderLayout());

      p_west_center_center.add(new JLabel("설명"), BorderLayout.WEST);
      p_west_center_center.add(taMContent, BorderLayout.CENTER);

      // 왼쪽 가운데 판넬에 두개의 판넬추가
      p_west_center.add(p_west_center_north, BorderLayout.NORTH);
      p_west_center.add(p_west_center_center, BorderLayout.CENTER);

      p_west_center.setBorder(new TitledBorder("캐릭터정보입력"));// 경계선 만들기

      // 왼쪽 아래
      JPanel p_west_south = new JPanel();
      p_west_south.setLayout(new GridLayout(2, 1));

      // 왼쪽 아래에 사용될 판넬
      JPanel p_west_south_1 = new JPanel();
      p_west_south_1.setLayout(new FlowLayout());

      JPanel p_west_south_2 = new JPanel();
      p_west_south_2.setLayout(new GridLayout(1, 3));
      p_west_south_2.add(bMapleInsert);
      p_west_south_2.add(bMapleModify);
      p_west_south_2.add(bMapleDelete);

      p_west_south.add(p_west_south_1);
      p_west_south.add(p_west_south_2);

      p_west.add(p_west_center, BorderLayout.CENTER);
      p_west.add(p_west_south, BorderLayout.SOUTH);

      // east 판넬 구성
      JPanel p_east = new JPanel();
      p_east.setLayout(new BorderLayout());

      // 오른쪽의 위쪽
      JPanel p_east_north = new JPanel();
      p_east_north.add(comMapleSearch);
      p_east_north.add(tfMapleSearch);
      // 경계선 만들기
      p_east_north.setBorder(new TitledBorder("캐릭터검색"));
      p_east.add(p_east_north, BorderLayout.NORTH);
      p_east.add(new JScrollPane(tableMaple), BorderLayout.CENTER);

      setLayout(new GridLayout(1, 2));
      add(p_west);
      add(p_east);
   }
   class MapleTableModel extends AbstractTableModel{
	  //jTable를 구성하기위한 데이터 세팅
      ArrayList data=new ArrayList();
      String [] columnNames={"캐릭터순서","직업","닉네임","성별","레벨"};
      @Override
      public int getColumnCount() {
         return columnNames.length;
      }

      @Override
      public int getRowCount() {
         return data.size();
      }

      @Override
      public Object getValueAt(int row, int col) {
         ArrayList temp=(ArrayList) data.get(row);
         return temp.get(col);
      }
      public String getColumnName(int col) {
         return columnNames[col];
      }
      
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      Object evt=e.getSource();
      if (evt==bMapleInsert) {//입력버튼이 눌리
            insertMaple();
      }else if (evt==tfMapleSearch) {
         searchMaple();
      }else if (evt==bMapleModify) {
         modify();
      }else if (evt==bMapleDelete) {
         delete();      
      }
   }
   
private void delete() {
	Maple vo=new Maple();
	vo.setMnumber(Integer.parseInt(tfMnum.getText()));
    vo.setMname(tfMname.getText());
    vo.setMgender((String)comMgender.getSelectedItem());
    vo.setMlevel(tfMlevel.getText());
    vo.setMjob((String)comMjob.getSelectedItem());
     try {
      model.deleteMaple(vo);
      JOptionPane.showMessageDialog(null, "삭제완료");
      tfMnum.setText(null);
      tfMname.setText(null);
      tfMlevel.setText(null);
      taMContent.setText(null);
   } catch (Exception e1) {
	  JOptionPane.showMessageDialog(null,"삭제실패");
      e1.printStackTrace();
   }//수정작업메소드 호출
     
}

private void modify() {
   Maple vo=new Maple();
     vo.setMnumber(Integer.parseInt(tfMnum.getText()));
     vo.setMjob((String)comMjob.getSelectedItem());
     vo.setMname(tfMname.getText());
     vo.setMgender((String)comMgender.getSelectedItem());
     vo.setMlevel(tfMlevel.getText());
     vo.setMdetail(taMContent.getText());
    try {
      model.modifyMaple(vo);
      JOptionPane.showMessageDialog(null, "수정완료");
      tfMnum.setText(null);
      tfMname.setText(null);
      tfMlevel.setText(null);
      taMContent.setText(null);
    } catch (Exception e1) {
    	JOptionPane.showMessageDialog(null,"수정실패");
    	e1.printStackTrace();
   }//수정작업 메소드호출
}

private void searchMaple() {
   int idx=comMapleSearch.getSelectedIndex();
    String str=tfMapleSearch.getText();
    
    try {
       ArrayList data=model.searchMaple(idx, str);
       tbModelMaple.data=data;
     tableMaple.setModel(tbModelMaple);
       tbModelMaple.fireTableDataChanged();
       
       
    } catch (Exception e1) {
       // TODO Auto-generated catch block
       e1.printStackTrace();
    }
}
   void selectbyPk(Maple vo) {
	  //클릭 레코드를 textfield에 뿌리기
	  tfMnum.setText(String.valueOf(vo.getMnumber()));
	  tfMname.setText(vo.getMname());
	  comMgender.setSelectedItem(vo.getMgender());
      tfMlevel.setText(vo.getMlevel());
      taMContent.setText(vo.getMdetail());
      comMjob.setSelectedItem(vo.getMjob());
      
   }
   private void insertMaple() {   
         Maple vo=new Maple();
         vo.setMname(tfMname.getText());
         vo.setMjob((String) comMjob.getSelectedItem());
         vo.setMgender((String)comMgender.getSelectedItem());
         vo.setMlevel(tfMlevel.getText());
         vo.setMdetail(taMContent.getText());
         
         
         try {
        	 model.insertMaple(vo);
            JOptionPane.showMessageDialog(null, "입력완료");
            //text창 내용지우기
            tfMnum.setText(null);
            tfMlevel.setText(null);
            tfMname.setText(null);
            taMContent.setText(null);
            //////
//            ArrayList data=model.selectAll();
//			tbModelMaple.data=data;//VideoTableModel
////			tableMaple.setModel(tbModelMaple);
//			tbModelMaple.fireTableDataChanged();
         } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "생성실패");
            e1.printStackTrace();
         }
         
   
      }
      
   }