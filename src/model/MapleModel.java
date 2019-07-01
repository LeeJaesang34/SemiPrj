package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.Maple;
	
   public class MapleModel {
    Connection con;  
    	public MapleModel() throws Exception {
    		con=DBcon.getConnection();
    	}
   
   public void insertMaple(Maple vo) throws Exception {
      con.setAutoCommit(false);//�ڵ�Ŀ���� ����
      
      //DB�� insert ��Ű��
      String sql1="INSERT INTO MINFO(MNUMBER,MJOB,MNAME,MGENDER,MLEVEL,MDETAIL)\r\n" +
    		  "VALUES(SEO_M_INFO.NEXTVAL,?,?,?,?,?)";
      PreparedStatement ps1=con.prepareStatement(sql1);
      ps1.setString(1, vo.getMjob());
      ps1.setString(2, vo.getMname());
      ps1.setString(3, vo.getMgender());     
      ps1.setString(4, vo.getMlevel());
      ps1.setString(5, vo.getMdetail());
      int r1=ps1.executeUpdate();//sql����
      
      if(r1!=1) {//�Ѱ��� ������н�
         con.rollback();
         System.out.println("�ѹ�");
      }
      con.commit();
      ps1.close();
      
      con.setAutoCommit(true);//�ڵ�Ŀ�� ��ȯ
     }
   public ArrayList searchMaple(int idx, String str) throws Exception {
         //�˻����
         String[] key= {"mname","mjob"};
         String sql="select MNUMBER, MJOB, MNAME, MGENDER, MLEVEL " +
               "FROM MINFO " +
               "where "+key[idx]+" Like '%"+str+"%'";
         PreparedStatement ps=con.prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         ArrayList data=new ArrayList();
         while(rs.next()) {
            ArrayList temp=new ArrayList();
            temp.add(rs.getString("MNUMBER"));
            temp.add(rs.getString("MJOB"));
            temp.add(rs.getString("MNAME"));
            temp.add(rs.getString("MGENDER"));
            temp.add(rs.getString("MLEVEL"));
            data.add(temp);//arraylist=arraylist�� �߰�
         }
         rs.close();
         ps.close();
         
         return data;
      }
public Maple selectbyPk(int no) throws Exception {
	//JTable���� Ŭ���� ���ڵ��� ������ Maple Ÿ������ �����ؼ� return
	Maple vo=new Maple();
	String sql="SELECT * FROM MINFO WHERE MNUMBER="+no;
	PreparedStatement ps=con.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
	while (rs.next()) {
		vo.setMnumber(Integer.parseInt(rs.getString("MNUMBER")));
		vo.setMjob(rs.getString("MJOB"));
		vo.setMname(rs.getString("MNAME"));
		vo.setMgender(rs.getString("MGENDER"));
		vo.setMlevel(rs.getString("MLEVEL"));
		vo.setMdetail(rs.getString("mdetail"));
		
	}
	rs.close();
	ps.close();
	
	return vo;
}
public void modifyMaple(Maple vo)  throws Exception {
	//������ �����۾�
	String sql="UPDATE MINFO SET MJOB=?,MNAME=?,MGENDER=?,MLEVEL=?,MDETAIL=?\r\n" + 
			"WHERE MNUMBER=?";
	PreparedStatement ps=con.prepareStatement(sql);
	ps.setString(1,vo.getMjob() );
	ps.setString(2,vo.getMname());
	ps.setString(3,vo.getMgender() );
	ps.setString(4,vo.getMlevel() );
	ps.setString(5, vo.getMdetail());
	ps.setInt(6,vo.getMnumber());
	
	ps.executeUpdate();//����
	ps.close();
	 
}
public void deleteMaple(Maple vo) throws Exception {
	String sql1="DELETE FROM MINFO WHERE MNUMBER=?";
	
	PreparedStatement ps1=con.prepareStatement(sql1);
	ps1.setInt(1,vo.getMnumber());
	
	
	//sql����
	int r1=ps1.executeUpdate();
	
	if (r1!=1) {
		con.rollback();
	}
	con.commit();
	
	ps1.close();
	;
	con.setAutoCommit(true);
	}
   
}