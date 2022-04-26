package Classes and Methods.TMSpack;

import javax.swing.*;

import TMSpack.person;
public class user extends person {

	private String userID;


public user(String userID){
    this.userID=userID;
}
public void set_userID(String id) {
	int dig = id.length();
	if(dig==10)
	userID=id;
	else
		JOptionPane.showMessageDialog(null, " Your ID number is UNCORRECT, Please enter your information again !! ");
}

public String get_userID() {
	return userID;
}	


    
}

    

