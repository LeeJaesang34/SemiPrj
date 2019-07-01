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
   JTable tableMaple;// jtable view����

   // �߰�
   MapleModel model;
   MapleTableModel tbModelMaple;//jTable�𵨰���
   
   public MapleView() {
      addLayout();// ȭ�鼳��
      // �ʱ�ȭ �۾�
      initStyle();
      eventProc();
      // db����
      connectDB();// ȣ��
   }

   void initStyle() {
   tfMnum.setEditable(false);// ��Ȱ��ȭ
      
   }

   private void connectDB() {
      try {
         model =new MapleModel();
         System.out.println("�����ÿ��Ἲ��");
      } catch (Exception e) {
         System.out.println("�����ÿ������");
      }
   }

   public void eventProc() {
      // ���ű� ����
      bMapleDelete.addActionListener(this);
      bMapleInsert.addActionListener(this);
      bMapleModify.addActionListener(this);
      tfMapleSearch.addActionListener(this);
      //JTable�� ������ ����
      tableMaple.addMouseListener(new MouseAdapter() {
         @Override
       public void mouseClicked(MouseEvent e) {
            int row=tableMaple.getSelectedRow();
            int col=0;
            //Ŭ�����ڵ� ĳ���ͼ��� ���
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
      // ���� ������ ����
	  tfMnum = new JTextField();
	  tfMname = new JTextField();
      tfMlevel = new JTextField();

      String[] cbJanreStr = { "����", "����", "�ü�", "������", "����" };
      comMjob = new JComboBox(cbJanreStr);
      String[] cbJanreStr1 = { "����", "����"};
      comMgender = new JComboBox(cbJanreStr1);
      taMContent = new JTextArea();


      // ��ư
      bMapleInsert = new JButton("�Է�");
      bMapleModify = new JButton("����");
      bMapleDelete = new JButton("����");

      String[] cbMapleSearch = { "�г���", "����" };
      comMapleSearch = new JComboBox(cbMapleSearch);
      tfMapleSearch = new JTextField(15);
      //���߿� 
      tbModelMaple=new MapleTableModel();
      tableMaple=new JTable(tbModelMaple);
      tableMaple.setModel(tbModelMaple);
      
      // ȭ�鱸��
      // west�ǳ� ����
      JPanel p_west = new JPanel();
      p_west.setLayout(new BorderLayout());

      // ���� ���
      JPanel p_west_center = new JPanel();
      p_west_center.setLayout(new BorderLayout());

      // ���� ��� ����
      JPanel p_west_center_north = new JPanel();
      p_west_center_north.setLayout(new GridLayout(5, 2));
      p_west_center_north.add(new JLabel("ĳ���ͼ���"));
      p_west_center_north.add(tfMnum);

      p_west_center_north.add(new JLabel("����"));
      p_west_center_north.add(comMjob);

      p_west_center_north.add(new JLabel("�г���"));
      p_west_center_north.add(tfMname);

      p_west_center_north.add(new JLabel("����"));
      p_west_center_north.add(comMgender);

      p_west_center_north.add(new JLabel("����"));
      p_west_center_north.add(tfMlevel);

      // ���� ��� ���
      JPanel p_west_center_center = new JPanel();
      p_west_center_center.setLayout(new BorderLayout());

      p_west_center_center.add(new JLabel("����"), BorderLayout.WEST);
      p_west_center_center.add(taMContent, BorderLayout.CENTER);

      // ���� ��� �ǳڿ� �ΰ��� �ǳ��߰�
      p_west_center.add(p_west_center_north, BorderLayout.NORTH);
      p_west_center.add(p_west_center_center, BorderLayout.CENTER);

      p_west_center.setBorder(new TitledBorder("ĳ���������Է�"));// ��輱 �����

      // ���� �Ʒ�
      JPanel p_west_south = new JPanel();
      p_west_south.setLayout(new GridLayout(2, 1));

      // ���� �Ʒ��� ���� �ǳ�
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

      // east �ǳ� ����
      JPanel p_east = new JPanel();
      p_east.setLayout(new BorderLayout());

      // �������� ����
      JPanel p_east_north = new JPanel();
      p_east_north.add(comMapleSearch);
      p_east_north.add(tfMapleSearch);
      // ��輱 �����
      p_east_north.setBorder(new TitledBorder("ĳ���Ͱ˻�"));
      p_east.add(p_east_north, BorderLayout.NORTH);
      p_east.add(new JScrollPane(tableMaple), BorderLayout.CENTER);

      setLayout(new GridLayout(1, 2));
      add(p_west);
      add(p_east);
   }
   class MapleTableModel extends AbstractTableModel{
	  //jTable�� �����ϱ����� ������ ����
      ArrayList data=new ArrayList();
      String [] columnNames={"ĳ���ͼ���","����","�г���","����","����"};
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
      if (evt==bMapleInsert) {//�Է¹�ư�� ����
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
      JOptionPane.showMessageDialog(null, "�����Ϸ�");
      tfMnum.setText(null);
      tfMname.setText(null);
      tfMlevel.setText(null);
      taMContent.setText(null);
   } catch (Exception e1) {
	  JOptionPane.showMessageDialog(null,"��������");
      e1.printStackTrace();
   }//�����۾��޼ҵ� ȣ��
     
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
      JOptionPane.showMessageDialog(null, "�����Ϸ�");
      tfMnum.setText(null);
      tfMname.setText(null);
      tfMlevel.setText(null);
      taMContent.setText(null);
    } catch (Exception e1) {
    	JOptionPane.showMessageDialog(null,"��������");
    	e1.printStackTrace();
   }//�����۾� �޼ҵ�ȣ��
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
	  //Ŭ�� ���ڵ带 textfield�� �Ѹ���
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
            JOptionPane.showMessageDialog(null, "�Է¿Ϸ�");
            //textâ ���������
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
            JOptionPane.showMessageDialog(null, "��������");
            e1.printStackTrace();
         }
         
   
      }
      
   }