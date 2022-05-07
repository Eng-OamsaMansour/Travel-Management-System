
import javax.swing.*;


public class person {
private String Fname;
private String Lname;
private String GovID;
private int age;
private String phonenum;
private String username;
private String password;


public person() {

}

public person(String Fname, String Lname, String GovID, int age, String phonenum, String username, String password) {
	this.Fname = Fname;
	this.Lname = Lname;
	this.GovID = GovID;
	this.age = age;
	this.phonenum = phonenum;
	this.username = username;
	this.password = password;

}


public void set_Fname(String n) {
	Fname=n;
}

public String get_Lname() {
	return Lname;
}

public void set_ID(String id) {

	GovID=id;
}

public String get_ID() {
	return GovID;
}

public void set_age(int a) {	
	age=a;
}

public int get_age() {
	return age;
}

public void set_phonenum(String num) {
	phonenum=num;
}

public String get_phonenum() {
	return phonenum;
}


}
