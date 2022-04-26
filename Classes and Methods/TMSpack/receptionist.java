package Classes and Methods.TMSpack;

import javax.swing.*;
public class receptionist extends person {

	private String receptionistID;


    public void set_receptionistID(String id) {
        int dig = id.length();
        if(dig==10)
            receptionistID=id;
        else
            JOptionPane.showMessageDialog(null, " Your ID number is UNCORRECT, Please enter your information again !! ");
    }
    
    public String get_managerID() {
        return receptionistID;
    }	
    
    public void add_receptionist() {
        
    }
    
    public void delete_receptionist() {
        
    }
    
    
}
