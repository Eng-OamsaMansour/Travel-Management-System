package TMSpack;

import java.util.ArrayList;

public class Locations {
	private String country;
	private String city;
	private ArrayList<Hotel> avilable_hotels = new ArrayList<Hotel>();

	public Locations(String country,String city,ArrayList<Hotel> avilable_hotels) {
		this.avilable_hotels = avilable_hotels;
		this.city = city;
		this.country = country;
	}
    
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ArrayList<Hotel> getAvilable_hotels() {
		return avilable_hotels;
	}

	public void setAvilable_hotels(ArrayList<Hotel> avilable_hotels) {
		this.avilable_hotels = avilable_hotels;
	}
	
	
}
