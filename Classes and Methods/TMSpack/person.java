
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

public String getFname() {
	return Fname;
}

public void setFname(String n) {
	Fname=n;
}

public String getLname() {
	return Lname;
}

public void setLname(String Lname) {
	this.Lname = Lname;
}

public void setGovID(String id) {

	GovID=id;
}

public String getGovID() {
	return GovID;
}

public void setAge(int a) {	
	age=a;
}

public int getAge() {
	return age;
}

public void setPhonenum(String num) {
	phonenum=num;
}

public String getPhonenum() {
	return phonenum;
}


public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


}
