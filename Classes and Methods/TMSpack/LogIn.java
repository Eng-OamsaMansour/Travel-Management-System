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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;


public class LogIn {
    person  p;

public LogIn(){
    p=new person();

}
public static String login(String user_name, String password) {
    ArrayList<String> username = new ArrayList<String>();
    boolean user_found = false;
    int user_index = -1;
    String base_password = "-1000";
    try {
        OracleDataSource user_ods = new OracleDataSource();
        user_ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
        user_ods.setUser("c##TMS");
        user_ods.setPassword("123456");
        Connection con = user_ods.getConnection();
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("select * from PIRSON");
        while (result.next()) {
            username.add(result.getString(6));
        }
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
    }
    for (int i = 0; i < username.size(); i++) {
        if (username.get(i).equals(user_name)) {
            user_found = true;
            user_index = i;
            break;
        }
    }
    if (user_found) {
        try {
            OracleDataSource user_ods = new OracleDataSource();
            user_ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            user_ods.setUser("c##TMS");
            user_ods.setPassword("123456");
            Connection con = user_ods.getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select PASSWORD_ from PIRSON where USER_NAME = '"+user_name+"'");
            result.next();
            base_password = result.getString(1);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(base_password.equals(password)){
            if(user_name.contains("U")){
                return "user";
            }else{
                if(user_name.contains("R")){
                    return "Resip";
                }else{
                    return "manager";
                }
            }
        }else{
            return "wrongpass";
        }

    }else{
        return "wronguser";
    }

}

public void signup(person newPERSON){
   ArrayList<String> IDs = new ArrayList<String>();
   String id ="0";
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("c##TMS");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from PIRSON");
            while (result.next()) {
                IDs.add(result.getString(3));
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
            String [] spilt_id = graterid .split("U", 2);
            String id_n = spilt_id[1];
            int id_int = Integer.parseInt(id_n);
            id_int++;
            
            id="U"+Integer.toString(id_int);
            
        }
         Driver driver = new oracle.jdbc.driver.OracleDriver();
        try {
            DriverManager.registerDriver(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "c##TMS", "123456");                   
            PreparedStatement stmt = con.prepareStatement("insert into PIRSON values(?,?,?,?,?,?,?)");
            stmt.setString(1,newPERSON.getFname());
            stmt.setString(2, newPERSON.getLname());
            stmt.setString(3,id);
            stmt.setString(4, newPERSON.getGovID());
            stmt.setInt(5, newPERSON.getAge());
            stmt.setString(6, newPERSON.getPhonenum());
            stmt.setString(7, newPERSON.getUsername());
            stmt.setString(8, newPERSON.getPassword());


            
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
                user.add(user_result.getString(6));
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
                ResultSet id_result = Tstmt.executeQuery("select USER_NAME, GOV_ID from PIRSON where USER_NAME = '\"+username +\" ;");
                while (id_result.next()) {
                    
                    Gve_IDs.add(id_result.getString(4));
                    Gve_IDs.add(id_result.getString(6));
                    
                   
                    
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
                Logger.getLogger(person.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
            
                
            
    

     
    
}







}
