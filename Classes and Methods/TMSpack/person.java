package TMSpack;
import javax.swing.*;


public class person {
private String name;
private String ID;
private String age;
private String phonenum;



public person(String name, String ID, String age, String phonenum){

	this.name=name;
	this.ID=ID;
	this.age=age;
	this.phonenum=phonenum;

}

public void set_name(String n) {
	name=n;
}

public String get_name() {
	return name;
}

public void set_ID(String id) {
	int dig = id.length();  // to check the number of digits in ID number 
	if(dig==10)
	ID=id;
	else
		JOptionPane.showMessageDialog(null, " Your ID number is UNCORRECT, Please enter your information again !! ");
}

public String get_ID() {
	return ID;
}

public void set_age(String a) {
	
	if (Integer.parseInt(a)>=18)	
	age=a;
	else 
		JOptionPane.showMessageDialog(null, " Your AGE is UNDER 18 , Please enter your information again !! ");	
}

public String get_age() {
	return age;
}

public void set_phonenum(String num) {
	int N = String.valueOf(num).length(); // to check the number of digits in phone number 
	if(N==5)
	phonenum=num;
	else
		JOptionPane.showMessageDialog(null, " Your PHONE NUMBER is less than 10 digit , Please enter your information again !! ");
}

public String get_phonenum() {
	return phonenum;
}


}
