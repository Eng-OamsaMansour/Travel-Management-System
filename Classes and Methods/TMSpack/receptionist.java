package Classes and Methods.TMSpack;


import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;
import java.util.*;

public class receptionist extends person {

	private String receptionistID;
    person p;

public receptionist(){

}

public receptionist(String Fname,String Lname, String ID, int age, String phonenum, String username, String password){
p.setFname(Fname);
p.setLname(Lname);
p.setGovID(ID);
p.setAge(age);
p.setPhonenum(phonenum);
p.setUsername(username);
p.setPassword(password);

}


    public void set_receptionistID(String id) {
            receptionistID=id;
        }
    
    public String get_receptionistID() {
        return receptionistID;
    }
    
    
    public void add_receptionist(receptionist R){
    
    ArrayList<String> ids= new ArrayList<String>();
    
    try{
        OracleDataSource o = new OracleDataSource();
        o.setURL("jdbc:oracel:thin:@localhost:1521:xe");
        o.setUser("c##TMS");
        o.setPassword("123456");
        Connection c = o.getConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select * from PIRSON");
        while(rs.next()) {  
        ids.add( rs.getString(3));
        }
        c.close();
    }
    catch (Exception e){
        JOptionPane.showMessageDialog(null, e.toString());
    }
    String g = ids.get(0);
    for(int i=0; i<ids.size();i++){
        int condition = g.compareTo(ids.get(i));
        if (condition<0){
            g = ids.get(i);
        }
    }
    String [] spilt_id = g.split("R", 2);
    String id_n = spilt_id[1];
    int id_int = Integer.parseInt(id_n);
    id_int++;
    String id ="0";
    id="R"+Integer.toString(id_int);
    
         Driver driver = new oracle.jdbc.driver.OracleDriver();
   try{
            DriverManager.registerDriver(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "c##TMS", "123456");           
            /*(FIRST_NAME ,LAST_NAME ,SYS_ID ,GOV_ID ,AGE ,USER_NAME ,PASSWORD ,PHONENUMBER)*/          
            PreparedStatement stmt = con.prepareStatement("insert into PIRSON values(?,?,?,?,?,?,?,?)");
            stmt.setString(1, R.getFname());
            stmt.setString(2, R.getLname());
            stmt.setString(3,id);
            stmt.setString(4, R.getGovID());
            stmt.setInt(5, R.getAge());
            stmt.setString(6, R.getUsername());
            stmt.setString(7, R.getPassword());
            stmt.setString(8, R.getPhonenum());
            stmt.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
        }
    
    catch (Exception e){
        JOptionPane.showMessageDialog(null, e.toString());
    }
   }

   
   public void delete_receptionist(String id){
        
    try{
         OracleDataSource ods = new OracleDataSource();
         ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
         ods.setUser("c##TMS");
         ods.setPassword("123456");
         Connection con = ods.getConnection();
         Statement stmt = con.createStatement();
         stmt.executeQuery("DELETE FROM PIRSON WHERE SYS_ID ='" + id + "'");
         con.setAutoCommit(false);
         con.commit();
         con.close();
    }
 catch (Exception e){
     JOptionPane.showMessageDialog(null, e.toString());
 }
}




    

    
    
}
