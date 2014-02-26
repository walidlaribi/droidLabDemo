/* Tahwissa application
 * 
 * This app has been developed during DroidLab Tlemcen which has taken place in Tlemcen from Feb. 20th to Feb. 22rd
 * 
 * The purpose of this app is to display information about hotels, restaurants and cool places in Tlemcen.
 * It contains three tabs (Hotels, Restaurants and Endroits (places), each tab contains a list of elements that the user can
 * click to perform an action (call the hotel, show it on map or send an email.
 * 
 * Credits: - Fethi Dilmi
 * 			- Mohamed Amine Aboura
 * 			- Walid Laribi
 * 
 * Feel free to use this code without any restrictions or limitations
 */

package com.example.tahwissa;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

//We declare our Class and indicate that it is an activity
public class TahwissaMain extends Activity implements OnItemClickListener {
	
	//This is an array of "Elements", each "Element" is an instance of the class "Element". In this case, a hotel.
	Element[] tableauHotels = 
		{
			//An Element's constructor takes 3 parameters which are the name, rating and phone number
			new Element("Aghadir", 4, "0550123456"),
			new Element("Ibis", 3, "0551123456"),
			new Element("Renaissance", 4, "0552123456"),
			new Element("Zianides", 5, "0553123456")
		};
	
	//Same thing for the restaurants, each restaurant is defined as an instance of "Element"
	Element[] tableauRestaurants = 
		{
			new Element("BelleCaféteria", 2, "0660123456"),
			new Element("Orange", 4, "0661123456"),
			new Element("Paris Caféteria", 3, "0662123456"),
			new Element("Fella Caféteria", 4, "0663123456")
		};
	
	//Same thing for the places (Endroits), each place is defined as an instance of "Element"
	Element[] tableauEndroits = 
		{
			new Element("Lalla Setti", 4, "0770123456"),
			new Element("ElMeshwar", 3, "0771123456"),
			new Element("Lorit", 4, "0772123456"),
    		new Element("Mansoura", 5, "0773123456"),
		};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tahwissa_main);
		
		//We instantiate the TabHost which is going to host our tabs
		TabHost monTab = (TabHost) findViewById(android.R.id.tabhost);
		
		//Setup the tabhost defined above
		monTab.setup();
		
		//Each tab is created and added to the tabhost object
		TabSpec mesSpec = monTab.newTabSpec("tabHotels");
		//Set the content of the tabhost to be the LinearLayout we created in activity_tahwissa_main.xml
		mesSpec.setContent(R.id.tabHotels);
		//Set the title of our tab to "Hotels"
		mesSpec.setIndicator("Hotels");
		//Add the tab to the tabhost object we created above
		monTab.addTab(mesSpec);
		
		//Follow the same directives for the restaurants
		mesSpec = monTab.newTabSpec("tabRestaurants");
		mesSpec.setContent(R.id.tabRestaurants);
		mesSpec.setIndicator("Restaurants");
		monTab.addTab(mesSpec);
		
		//Follow the same directives for the places (Endroits) 
		mesSpec = monTab.newTabSpec("tabEndroits");
		mesSpec.setContent(R.id.tabEndroits);
		mesSpec.setIndicator("Endroits");
		monTab.addTab(mesSpec);
		
		//Instantiate our list views that will contain our items as the following
		ListView listHotels = (ListView) findViewById(R.id.listHotels);
		ListView listRestaurants = (ListView) findViewById(R.id.listRestaurants);
		ListView listEndroits = (ListView) findViewById(R.id.listEndroits);
		
		//Create an instance of the ElementAdapter class defined in the project and bind the threes arrays to the listview_item
		ElementAdapter hotelsAdapter = new ElementAdapter(this, 
                R.layout.listview_item, tableauHotels);
		
		ElementAdapter restaurantsAdapter = new ElementAdapter(this, 
                R.layout.listview_item, tableauRestaurants);
		
		ElementAdapter endroitsAdapter = new ElementAdapter(this, 
                R.layout.listview_item, tableauEndroits);
		
		//We set the adapters for each list to the adapters that we created
		listHotels.setAdapter(hotelsAdapter);
		listRestaurants.setAdapter(restaurantsAdapter);
		listEndroits.setAdapter(endroitsAdapter);
		
		//We create a onClick listener for our listHotels listView to listen to click events
		//When a user click on an item from the list, we should perform a call to that item's phone number
		//You can change this action to show a map or send email just by changing the data model of the Element class
		//(Add geo coordinates and/or email addresses)
		//Then use one of the methods below to make the desired work: showOnMap(geoLocation), senEmail(emailAddress)
		listHotels.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
				//We get the phone number from our array using the position of the element being clicked
				dialPhoneNumber("" + tableauHotels[position].phoneNumber);
            }
		});
		
		//Same thing as listHotels
		listRestaurants.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
				dialPhoneNumber("" + tableauRestaurants[position].phoneNumber);
            }
		});
		
		//Same thing as listHotels and listRestaurants 
		listEndroits.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
				dialPhoneNumber("" + tableauEndroits[position].phoneNumber);
            }
		});
	}
	
	//This method is used to call a phone number using the phone's dialer application
	void dialPhoneNumber(String phoneNumber) {
		//First, we instantiate a new intent with the ACTION_DIAL mode
	    Intent intent = new Intent(Intent.ACTION_DIAL);
	    //We pare the URI for the phone number and pass it to our intent
	    intent.setData(Uri.parse("tel:" + phoneNumber));
	    //We start the activity to launch the dialer app
	    if (intent.resolveActivity(getPackageManager()) != null) {
	        startActivity(intent);
	    }    
	}
	
	//This method is used to send an email using the phone's email application
	public void SendEmail(String emailAddress) {
		String subject = "Put sample subject here";
		String text = "Put sample body here";
		
		Intent intent = new Intent(Intent.ACTION_SENDTO);
	    intent.setData(Uri.parse("mailto:")); // only email apps should handle this   
	    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
	    intent.putExtra(Intent.EXTRA_TEXT, text);
	    intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
	    if (intent.resolveActivity(getPackageManager()) != null) {
	        startActivity(intent);
	    }
	}
	
	//This method is used to show an Element on the map using the phone's maps application
	void showOnMap(String uri) {
		//uri must be something like this: "geo:47.6,-122.3?z=17";
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tahwissa_main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}

}
