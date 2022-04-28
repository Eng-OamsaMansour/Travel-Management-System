package Classes and Methods.TMSpack;

import TMSpack.person;

import javax.swing.*;

public class manager extends person{

    private String managerID;


    public void set_managerID(String id) {
        int dig = id.length();
        if(dig==10)
            managerID=id;
        else
            JOptionPane.showMessageDialog(null, " Your ID number is UNCORRECT, Please enter your information again !! ");
    }
    
    public String get_managerID() {
        return managerID;
    }	
    
    public void add_manager() {
        
    }
    
    public void delete_manager() {
        
    }
    
}
