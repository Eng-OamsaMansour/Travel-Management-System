package TMSpack;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;


public class LogIn {
    person  p;

public LogIn(){
    p=new person();

}
public String login(String user_name , String password){
    boolean user_sucsses = false;
    ArrayList<String> USERNAMEs = new ArrayList<String>();
    try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("c##TMS");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            Statement stmt = con.createStatement();
            
            ResultSet result = stmt.executeQuery("select * from PIRSON");
             while (result.next()) {
                USERNAMEs.add(result.getString(6));
            }
            con.close();
       boolean user_found = false;
       for (int i = 0; i < USERNAMEs.size(); i++) {

                if (USERNAMEs.get(i).equals(user_name)) {

                    user_sucsses = true;
                    user_found = true;
                    break;
                }
            }
      if (user_found) {
                user_sucsses = true;
                OracleDataSource Trip_ods = new OracleDataSource();
                Trip_ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                Trip_ods.setUser("c##TMS");
                Trip_ods.setPassword("123456");
                Connection Tcon = Trip_ods.getConnection();
                Statement Tstmt = Tcon.createStatement();
                ResultSet user_result = Tstmt.executeQuery("select * from PIRSON where USER_NAME '\"+user_name +\" ;");
                while (user_result.next()) {
                    USERNAMEs.add(user_result.getString(6));
                }
                Tcon.close();
                
               
                    if (user_result.equals(password)) {
                        user_sucsses = true;
                        
                     
                    }
        String[] splited_id;
            splited_id = user_result.split("U" , 2);
        String id_num_part = splited_id[1];         
      }
    }
        catch (SQLException ex) {          
            
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);          
        }
    
              
              
            
       /* }catch(SQLException x){
   x.printStackTrace();
}       catch (ClassNotFoundException ex) {
            Logger.getLogger(p.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            
         
    
    Driver driver = new oracle.jdbc.driver.OracleDriver();
    
    try{
        
        DriverManager.registerDriver(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "c##TMS", "123456");
 
    
        }catch(SQLException x){
   x.printStackTrace();
} catch (ClassNotFoundException ex) {
            Logger.getLogger(p.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void signup(person newPERSON){
   ArrayList<String> IDs = new ArrayList<String>();
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("c##TMS");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from PIRSON");
            while (result.next()) {
                IDs.add(result.getString(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        String graterid = IDs.get(0);
        for (int i = 0; i < IDs.size(); i++) {
            int condition = graterid.compareTo(IDs.get(i));
            if (condition < 0) {
                graterid = IDs.get(i);
            }
        }
         Driver driver = new oracle.jdbc.driver.OracleDriver();
        try {
            DriverManager.registerDriver(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "c##TMS", "123456");           
            /*(TRIP_ID, DATE_, PRICE, TRIP_LOCATION, TRIP_HOTEL, TRANSPORTATION_WAY, TRIP_PATH_DESCRIPTION, TRIP_KIND, MAX_BOOKING_NUM, CURRENT_BOOKING_NUMBER)*/          
            PreparedStatement stmt = con.prepareStatement("insert into PIRSON values(?,?,?,?,?)");
            stmt.setString(1,newPERSON.getFname);
            stmt.setDate(2, newPERSON.getLname);
            stmt.setInt(3, newPERSON.getGovID);
            stmt.setString(4, newPERSON.getAge);
            stmt.setString(5, newPERSON.getPhonenum);
            
            stmt.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
        } catch (Exception ex) {            
           System.out.print(ex.toString());
        }            
            
}

public boolean forget_password(String username, String gve_id,String password){
    boolean user_sucsses = false;
     boolean id_sucsses = false;  
        ArrayList<String> user = new ArrayList<String>();
        ArrayList<String> Gve_IDs = new ArrayList<String>();
    try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("c##TMS");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            Statement stmt = con.createStatement();
             ResultSet user_result = stmt.executeQuery("select * from PIRSON");
            while (user_result.next()) {
                user.add(user_result.getString());
            }
            con.close();
            boolean user_found = false;
            for (int i = 0; i < user.size(); i++) {

                if (user.get(i).equals(username)) {

                    user_sucsses = true;
                    user_found = true;
                    break;
                }
            }
            if (user_found) {
                user_sucsses = true;
                OracleDataSource user_ods = new OracleDataSource();
                user_ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                user_ods.setUser("c##TMS");
                user_ods.setPassword("123456");
                Connection Tcon = user_ods.getConnection();
                Statement Tstmt = Tcon.createStatement();
                ResultSet id_result = Tstmt.executeQuery("select * from PIRSON where USER_NAME '\"+username +\" ;");
                while (id_result.next()) {
                    Gve_IDs.add(id_result.getString(row));
                }
                Tcon.close();
                boolean id_found = false;
                for (int i = 0; i < Gve_IDs.size(); i++) {
                    if (Gve_IDs.get(i).equals(gve_id)) {
                        id_sucsses=true;
                        id_found = true;
                        break;
                    }
                }
            con.setAutoCommit(false);
            con.commit();
            con.close();
            }
    } catch (SQLException ex) {
            Logger.getLogger(person.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.print(user_sucsses +"\n"
                + id_sucsses + "\n");
    if (user_sucsses == true && id_sucsses == true ) {
            Driver driver = new oracle.jdbc.driver.OracleDriver();
            try{
                 OracleDataSource Current_Password_ds = new OracleDataSource();
                Current_Password_ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                Current_Password_ds.setUser("c##TMS");
                Current_Password_ds.setPassword("123456");
                Connection pass_num_con = Current_Password_ds.getConnection();
                Statement pass_num_stmt = pass_num_con.createStatement();
                ResultSet pass_num_result = pass_num_stmt.executeQuery("SELECT CURRENT_PASSWORD FROM PIRSON WHERE PASSWORD_ = '"+password+"' ");
                pass_num_result.next();
                 Driver pass_num_driver = new oracle.jdbc.driver.OracleDriver();
                DriverManager.registerDriver(pass_num_driver);
                String url1 = "jdbc:oracle:thin:@localhost:1521:xe";
                Connection pass_num_con2 = DriverManager.getConnection(url1, "c##TMS", "123456");
                Statement stmt1 = pass_num_con2.createStatement();
                String strstm = "UPDATE TRIP SET CURRENT_PASSWORD_NUMBER = " + pass_num_result.getBigDecimal(1).add(BigDecimal.ONE) + " WHERE PASSWORD = '"+password+"' ";
                stmt1.execute(strstm);
                pass_num_con2.setAutoCommit(false);
                pass_num_con2.commit();
                pass_num_con2.close();
               pass_num_con.close();
                DriverManager.registerDriver(driver);
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                Connection con = DriverManager.getConnection(url, "c##TMS", "123456");
                PreparedStatement stmt = con.prepareStatement("insert into PIRSON values(?)");
                stmt.setString(1, password);
                stmt.executeQuery();
                con.setAutoCommit(false);
                con.commit();
                con.close();
            } catch (SQLException ex) {                
                Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
            
                
            
    

     
    
}

public void logout(){
    
    
    
}






}
