package Classes and Methods.TMSpack;

import TMSpack.person;

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

public class manager extends person{

    private String managerID;
    person p;

    public manager(){
        
    }
    
    public manager(String Fname,String Lname, String ID, int age, String phonenum, String username, String password){
        p.setFname(Fname);
        p.setLname(Lname);
        p.setGovID(ID);
        p.setAge(age);
        p.setPhonenum(phonenum);
        p.setUsername(username);
        p.setPassword(password);
        
    }
    
    public void set_managerID(String id) {
            managerID=id;
    }
    
    public String get_managerID() {
        return managerID;
    }	
    
    
public void add_manager(manager M){
    
    ArrayList<String> ids= new ArrayList<String>();
    
    try{
        oracleDataSource o = new OracleDataSource();
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
    String [] spilt_id = g.split("M", 2);
    String id_n = spilt_id[1];
    int id_int = Integer.parseInt(id_n);
    id_int++;
    String id ="0";
    id="M"+Integer.toString(id_int);
    
    
         Driver driver = new oracle.jdbc.driver.OracleDriver();
   try{
            DriverManager.registerDriver(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "c##TMS", "123456"); 
            /*(FIRST_NAME ,LAST_NAME ,SYS_ID ,GOV_ID ,AGE ,USER_NAME ,PASSWORD ,PHONENUMBER)*/          
           PreparedStatement stmt = con.prepareStatement("insert into PIRSON values(?,?,?,?,?,?,?,?)");
            stmt.setString(1, M.getFname());
            stmt.setString(2, M.getLname());
            stmt.setString(3,id);
            stmt.setString(4, M.getGovID());
            stmt.setInt(5, M.getAge());
            stmt.setString(6, M.getUsername());
            stmt.setString(7, M.getPassword());
            stmt.setString(8, M.getPhonenum());
            stmt.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
        }
    
    catch (Exception e){
        JOptionPane.showMessageDialog(null, e.toString());
    }
}

    
    
public void delete_manager(String id){
        
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
