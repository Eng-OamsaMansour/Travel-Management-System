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
}
