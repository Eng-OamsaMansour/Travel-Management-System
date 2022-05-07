package TMSpack;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;

public class Feedback {
    private String feedback_text;
	private int feedback_ID;

    public Feedback(String feedback_text,int feedback_ID){
      this.feedback_ID=feedback_ID;
      this.feedback_text=feedback_text;

    }
    public int getFeedbackId() {
        return feedback_ID;
      }
      public void setFeedbackId(int feedback_ID) {
        this.feedback_ID = feedback_ID;
      }
      public String getFeedbackText() {
        return feedback_text;
      }

      public void setFeedbackText(String feedback_text) {
        this.feedback_text = feedback_text;
      }

      public void addFeedback(Feedback newUSER_FEEDBACK) {
        ArrayList<String> IDs = new ArrayList<String>();
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("c##TMS");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from USER_FEEDBACK");
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
        String[] splited_id = graterid.split("F", 2);
        String id_num_part = splited_id[1];
        int INT_ID = Integer.parseInt(id_num_part);
        INT_ID++;
        String id = "0";
        id = "F"+Integer.toString(INT_ID);
         Driver driver = new oracle.jdbc.driver.OracleDriver();
        try {
            DriverManager.registerDriver(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "c##TMS", "123456");           
                     
            PreparedStatement stmt = con.prepareStatement("insert into USER_FEEDBACK values(?,?,?)");
            stmt.setString(1, id);
            stmt.setString(2, newUSER_FEEDBACK.feedback_ID);
            stmt.setString(3, newUSER_FEEDBACK.feedback_text);
            
            stmt.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
        } catch (Exception ex) {            
           System.out.print(ex.toString());
        }            
    }
    public void Delete_Feedback(String feedback_ID) {
      try {
          OracleDataSource ods = new OracleDataSource();
          ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
          ods.setUser("c##TMS");
          ods.setPassword("123456");
          Connection con = ods.getConnection();
          Statement stmt = con.createStatement();
          stmt.executeQuery("DELETE FROM USER_FEEDBACK WHERE USER_ID ='" + feedback_ID + "'");
          con.setAutoCommit(false);
          con.commit();
          con.close();
      } catch (SQLException ex) {
          Logger.getLogger(Feedback.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }




    

