package com.example.phonebook;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import com.example.phonebook.contactcopyendpoint.Contactcopyendpoint;
import com.example.phonebook.contactcopyendpoint.model.CollectionResponseContactCopy;
import com.example.phonebook.contactcopyendpoint.model.ContactCopy;
import com.example.phonebook.contactendpoint.Contactendpoint;
import com.example.phonebook.contactendpoint.model.CollectionResponseContact;
import com.example.phonebook.contactendpoint.model.Contact;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends Activity{

	
	private TextView welcomeTextView;
	private ListView listView;
	private String userName;
	private String userEmail;
	private String userFirstName;
	private String userLastName;
	private String userId;
	private String userKey;
	private String selectedContact;
	private String selectedContactCopy;
	//private Key contactKeyToEdit;
	private Long contactIdToRemove;
	AlertDialog.Builder removeAlert; //= new AlertDialog.Builder(this);
	
	
	//private ArrayList<Contact> contactArray = new ArrayList<>();
	private ArrayList<ContactCopy> contactCopyArray = new ArrayList<>();
	
	//private ArrayList<String> contactStringArray = new ArrayList<>(); //this will be the display
	private ArrayList<String> contactCopyStringArray = new ArrayList<>(); //this will be the display
	
	//private ArrayList<String> contactStringArraySession = new ArrayList<>(); //this will be the display
	private ArrayList<String> contactCopyStringArraySession = new ArrayList<>(); //this will be the display
	//CollectionResponseContact contactList;
	CollectionResponseContactCopy contactCopyList;
	SharedPreferences sharedpreferences;
	
	@SuppressWarnings("rawtypes")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {		    
		

		Log.i(WelcomeActivity.this.getLocalClassName(), "Begin onCreate Method");
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.welcome_user);
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		
		sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
		String sessionUserName = sharedpreferences.getString("userName", "No User Name in session ");
		//UserEndpoint u = new UserEndpoint();
		//userName = createUserActivityIntent.getStringExtra("userName");
		//userEmail = createUserActivityIntent.getStringExtra("userEmail");
		//userFirstName = createUserActivityIntent.getStringExtra("userFirstName");
		//userLastName = createUserActivityIntent.getStringExtra("userLastName");
		//userId = createUserActivityIntent.getStringExtra("userId");
		//userKey = createUserActivityIntent.getStringExtra("userKey");
		
		userName = sharedpreferences.getString("userName", "No User Name in session ");
		userEmail = sharedpreferences.getString("userEmail", "No User Email in session ");
		userFirstName = sharedpreferences.getString("userFirstName", "No User First Name in session ");
		userLastName = sharedpreferences.getString("userLastName", "No User Last Name in session ");
		userId = sharedpreferences.getString("userId", "No User Id in session ");
		userKey = sharedpreferences.getString("userKey", "No User Key in session ");
		
		
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) 
			new ListContactAsyncRetriever().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		else
			new ListContactAsyncRetriever().execute();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent createUserActivityIntent = getIntent();
		
		
		
		
		
		
		 // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        
        //createContactStringArray();
        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View", 
                                         "Adapter implementation",
                                         "Simple List View In Android",
                                         "Create List View Android", 
                                         "Android Example", 
                                         "List View Source Code", 
                                         "List View Array Adapter", 
                                         "Android Example List View" 
                                        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //  android.R.layout.simple_list_item_1, android.R.id.text1, contactStringArray);

        
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //		android.R.layout.simple_list_item_single_choice, contactStringArray);
        ArrayAdapter<String> adapterCopy = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_single_choice, contactCopyStringArray);
        
        // Assign adapter to ListView
        //listView.setAdapter(adapter); 
        listView.setAdapter(adapterCopy); 
		
        listView.setOnItemClickListener(new OnItemClickListener() {

        	   @Override
        	   public void onItemClick(AdapterView arg0, View view, int position,
        	     long arg3) {
        	    
        		   CheckedTextView textView = (CheckedTextView) view;
        		    for (int i = 0; i < listView.getCount(); i++) {
        		     textView= (CheckedTextView) listView.getChildAt(i);
        		     if (textView != null) {
        		    	 //textView.setTextColor(Color.BLACK);
        		    	 textView.setChecked(false);
        		     }
        		     
        		    }
        		    //listView.invalidate();
        		    textView = (CheckedTextView) view;
        		    if (textView != null) {
        		     	textView.setChecked(true);
        		    	//textView.setTextColor(Color.BLUE);
        		     	//selectedContact = textView.getText().toString();
        		     	selectedContactCopy = textView.getText().toString();
        		     	Log.i(WelcomeActivity.this.getLocalClassName(), selectedContactCopy +  " selected");
        		    }
        		    else
        		    	textView.setChecked(false);
        	   }
        });	
        
        
        
        
		welcomeTextView = (TextView) findViewById(R.id.textViewWelcome);
		welcomeTextView.setText("Welcome " + userFirstName + " " + userLastName + " "); //+ sessionUserName); //+ " " + userKey + " " + userId);
		Log.i(WelcomeActivity.this.getLocalClassName(), "End onCreate Method");
	}
	
	
	public void addContact(View view){
		Log.i(WelcomeActivity.this.getLocalClassName(), "Begin onCreate Method");
		Intent intent = new Intent(WelcomeActivity.this, ContactActivity.class);
		
		//intent.putExtra("userKey", userKey);
		//intent.putExtra("userId", userId);
		intent.putExtra("contactOperation", "add");
	    startActivity(intent);
	    WelcomeActivity.this.finish();
	    Log.i(WelcomeActivity.this.getLocalClassName(), "End onCreate Method");
	}
	
	//@SuppressLint("NewApi")
	//public void editContact(View view){
	//	if(selectedContact == null){
	//		Toast.makeText(getApplicationContext(), "No Contact Selected", Toast.LENGTH_SHORT).show();
	//	}
	//	else{
	//		Intent intent = new Intent(WelcomeActivity.this, ContactActivity.class);
		
	//		intent.putExtra("contactOperation", "edit");
	//		boolean foundContact = false;
			// here lets figure out which contact we are going to pass to the contact activity
	//		String[] figureOutWhoThisIs = selectedContact.split(" ");
	//		labelLoopOuter:
	//		for(int i = 0 ; i < contactStringArraySession.size(); i++){
	//			String tmpStringContact = contactStringArraySession.get(i);
	//			String[] tmpStringSplit = tmpStringContact.split("#");
	//			labelLoopIn:
	//			for(int j=0; j<3; j++){
				//while(figureOutWhoThisIs[i].equals(tmpStringSplit[i+1])){
	//				if( !figureOutWhoThisIs[j].equals(tmpStringSplit[j+1]) ) {
	//					foundContact = false;
	//					break labelLoopIn;
	//				}
	//				foundContact = true;
	//			}
	//			if(foundContact){
	//				intent.putExtra("contact",  tmpStringContact);
	//				break labelLoopOuter;
	//			}	
				
	//		}
			//TextView textView = (CheckedTextView) view;			
	//		sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);			
			
	//		Editor editor = sharedpreferences.edit();

	//		editor.putString("editContact", selectedContact);
	//		editor.commit();
			//intent.putExtra("userKey", userKey);
			//intent.putExtra("userId", userId);
			
			
	//		startActivity(intent);
	//	}
	//}
	 
	
	@SuppressLint("NewApi")
	public void editContactCopy(View view){
		Log.i(WelcomeActivity.this.getLocalClassName(), "Begin editContactCopy Method");
		if(selectedContactCopy == null){
			//Log.i(WelcomeActivity.this.getLocalClassName(), "End doInBackground Method");
			Toast.makeText(getApplicationContext(), "No Contact Selected", Toast.LENGTH_SHORT).show();
		}
		else{
			//Log.i(WelcomeActivity.this.getLocalClassName(), "End doInBackground Method");
			Intent intent = new Intent(WelcomeActivity.this, ContactActivity.class);
		
			intent.putExtra("contactOperation", "edit");
			boolean foundContactCopy = false;
			// here lets figure out which contact we are going to pass to the contact activity
			String[] figureOutWhoThisIs = selectedContactCopy.split(" ");
			labelLoopOuter:
			for(int i = 0 ; i < contactCopyStringArraySession.size(); i++){
				String tmpStringContact = contactCopyStringArraySession.get(i);
				String[] tmpStringSplit = tmpStringContact.split("#");
				labelLoopIn:
				for(int j=0; j<3; j++){
				//while(figureOutWhoThisIs[i].equals(tmpStringSplit[i+1])){
					if( !figureOutWhoThisIs[j].equals(tmpStringSplit[j+1]) ) {
						foundContactCopy = false;
						break labelLoopIn;
					}
					foundContactCopy = true;
				}
				if(foundContactCopy){
					intent.putExtra("contactCopy",  tmpStringContact);
					break labelLoopOuter;
				}	
				
			}
			//TextView textView = (CheckedTextView) view;			
			sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);			
			
			Editor editor = sharedpreferences.edit();

			editor.putString("editContact", selectedContactCopy);
			editor.commit();
			//intent.putExtra("userKey", userKey);
			//intent.putExtra("userId", userId);
			
			
			startActivity(intent);
			WelcomeActivity.this.finish();
			Log.i(WelcomeActivity.this.getLocalClassName(), "End editContactCopy Method");
		}
	}
	 
	
	public void removeAccount(View view){
		Log.i(WelcomeActivity.this.getLocalClassName(), "Begin removeAccount Method");
		if(selectedContactCopy == null){
			//Log.i(WelcomeActivity.this.getLocalClassName(), "End doInBackground Method");
			Toast.makeText(getApplicationContext(), "No Contact Selected", Toast.LENGTH_SHORT).show();
		}
		else{
			//Log.i(WelcomeActivity.this.getLocalClassName(), "End doInBackground Method");
			Log.i(WelcomeActivity.this.getLocalClassName(), "Remove Account " + selectedContactCopy);
			boolean foundContactCopy = false;
			// here lets figure out which contact we are going to pass to the contact activity
			String[] figureOutWhoThisIs = selectedContactCopy.split(" ");
			String tmpStringContact = "";
			labelLoopOuter:
			for(int i = 0 ; i < contactCopyStringArraySession.size(); i++){
				tmpStringContact = contactCopyStringArraySession.get(i);
				String[] tmpStringSplit = tmpStringContact.split("#");
				labelLoopIn:
				for(int j=0; j<3; j++){
				//while(figureOutWhoThisIs[i].equals(tmpStringSplit[i+1])){
					if( !figureOutWhoThisIs[j].equals(tmpStringSplit[j+1]) ) {
						foundContactCopy = false;
						break labelLoopIn;
					}
					foundContactCopy = true;
				}
				if(foundContactCopy){					
					break labelLoopOuter;
				}	
				
			}
			String[] contactArray = tmpStringContact.split("#");
			contactIdToRemove = Long.valueOf(contactArray[0]); 
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {	            
				
				@SuppressLint("NewApi")
				@Override
	            public void onClick(DialogInterface dialog, int which) {
	                switch (which){
	                case DialogInterface.BUTTON_POSITIVE:
	                    //Yes button clicked
	        			if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) 
	        				new DeleteContactAsyncRetriever().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	        			else
	        				new DeleteContactAsyncRetriever().execute();
	        			//onCreate(Bundle savedInstanceState);
	        			//onResume();
	        			try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	        			finish();
	        			startActivity(getIntent());
	                    break;

	                case DialogInterface.BUTTON_NEGATIVE:
	                    //No button clicked
	                    break;
	                }
	            }
	        };
	        
	        removeAlert = new AlertDialog.Builder(this);
	        removeAlert.setMessage("Remove " + contactArray[1] + " " + contactArray[2] + " ?").setPositiveButton("Yes", dialogClickListener)
	        .setNegativeButton("No", dialogClickListener).show();
		}
		Log.i(WelcomeActivity.this.getLocalClassName(), "End removeAccount Method");
		
	}
	
	
	@Override
	   protected void onResume() {
		   Log.i(WelcomeActivity.this.getLocalClassName(), "Begin onResume Method");
		   //sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	  //    if (sharedpreferences.contains("userName")){
	   //      Intent i = new Intent(this, WelcomeActivity.class);
	   //      startActivity(i);
	   //   
	   //   }
	      super.onResume();
	      Log.i(WelcomeActivity.this.getLocalClassName(), "End onResume Method");
	   }


	public void logOut(View view){
		   //sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
		  Log.i(WelcomeActivity.this.getLocalClassName(), "Begin logOut Method");
		  Editor editor = sharedpreferences.edit();
		  editor.clear();
		  editor.commit();
		  moveTaskToBack(true); 
		  WelcomeActivity.this.finish();
		  
		  Log.i(WelcomeActivity.this.getLocalClassName(), "End logOut Method");
	      //Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);	     
	      //startActivity(intent);
	  }
	  
	  //yes this should be the toString method in the contact class
	  //private void createContactStringArray(){
		  //Contact eachContact = new Contact();
	//	  contactArray = (ArrayList<Contact>) contactList.getItems();
	//	  for(int i = 0 ; i < contactArray.size() ; i++){
	//		  Contact tempContact = contactArray.get(i);
	//		  		if( tempContact.getUserId().compareTo(new Long(userId)) == 0){
			  			//change this to pipe delimiter
	//		  			contactStringArray.add(tempContact.getContactFirstName() + " " + tempContact.getContactLastName() + " " + tempContact.getContactPhoneNumber());
	//		  			contactStringArraySession.add(tempContact.getKey().getId() + "#" + tempContact.getContactFirstName() + "#"  +
	//		  											tempContact.getContactLastName() + "#" + tempContact.getContactPhoneNumber());
	//		  		}
			  		//else
			  		//	contactArray.remove(i);
	//		}				  
			  
	//  }
	  
	  //yes this should be the toString method in the contact class
	  private void createContactCopyStringArray(){
		  //Contact eachContact = new Contact();
		  Log.i(WelcomeActivity.this.getLocalClassName(), "Begin createContactCopyStringArray Method");
		  contactCopyArray = (ArrayList<ContactCopy>) contactCopyList.getItems();
		  Log.i(WelcomeActivity.this.getLocalClassName(), "The user id is " + userId );//+ "and contactCopyArray has " + contactCopyArray.size());
		  if(!contactCopyArray.isEmpty()){
		  	for(int i = 0 ; i < contactCopyArray.size() ; i++){
		  		ContactCopy tempContact = contactCopyArray.get(i);
			  		Log.d(WelcomeActivity.this.getLocalClassName(), "TEMP ID " + tempContact.getUserId());
			  			if( tempContact.getUserId().compareTo(new Long(userId)) == 0){
			  				//change this to pipe delimiter
			  				contactCopyStringArray.add(tempContact.getContactFirstName() + " " + tempContact.getContactLastName() + " " + tempContact.getContactPhoneNumber());
			  				contactCopyStringArraySession.add(tempContact.getId() + "#" + tempContact.getContactFirstName() + "#"  +
			  											tempContact.getContactLastName() + "#" + tempContact.getContactPhoneNumber());
			  			}
			  			//else
			  			//	contactArray.remove(i);
		  		}
	  	}
		  Log.i(WelcomeActivity.this.getLocalClassName(), "End createContactCopyStringArray Method");  
	  }
	  
	  /**
	   * AsyncTask for retrieving the list of places (e.g., stores) and updating the
	   * corresponding results list.
	   */
	  @SuppressLint("NewApi")
	private class ListContactAsyncRetriever extends AsyncTask<Void, Void, Void> {

	    
		@Override
	    protected Void doInBackground(Void... params) {
		//protected void onPreExecute(){
		  Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		  Log.i(WelcomeActivity.this.getLocalClassName(), "Begin doInBackground Method");
	//      Contactendpoint.Builder endpointBuilder = new Contactendpoint.Builder(
	//          AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);     
	//      endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);
	//      Contactendpoint endpoint = endpointBuilder.build();

	      Contactcopyendpoint.Builder endpointCopyBuilder = new Contactcopyendpoint.Builder(
		          AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
	      endpointCopyBuilder = CloudEndpointUtils.updateBuilder(endpointCopyBuilder);
	      Contactcopyendpoint endpointCopy = endpointCopyBuilder.build();
	      
	      try {
	  //  	  contactList = endpoint.listContact().execute();
	    	  
	    	  contactCopyList = endpointCopy.listContactCopy().execute();
	    	  Log.i(WelcomeActivity.this.getLocalClassName(), contactCopyList.size() + " users returned is null is " + contactCopyList.isEmpty());
	      } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        //result = null;
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      
	   //   createContactStringArray();
	      if(!contactCopyList.isEmpty())
	    	  createContactCopyStringArray();
	      Log.i(WelcomeActivity.this.getLocalClassName(), "End doInBackground Method");
	      //Loop thought the array and add it to the shared prefs
		  // MIght need to put this back
	      //Set<String> contactSet = new HashSet<String>(contactStringArray);
		  //Editor editor = sharedpreferences.edit();
		  //editor.putStringSet("contacts", contactSet);
		  //editor.commit();
	      //return contactList;
		return null;
	    }
		
		//protected Void doInBackground(Void... params) {
		//	return null;

		//}
	  }
	  
	  
	  protected class DeleteContactAsyncRetriever extends AsyncTask<Void, Void, Void> {

		    @Override
		    protected Void doInBackground(Void... params) {

		      Log.i(WelcomeActivity.this.getLocalClassName(), "Begin doInBackground Method");	
		      Contactcopyendpoint.Builder endpointCopyBuilder = new Contactcopyendpoint.Builder(
		          AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
		      endpointCopyBuilder = CloudEndpointUtils.updateBuilder(endpointCopyBuilder);
		      Contactcopyendpoint endpointCopy = endpointCopyBuilder.build();
	      
		      try {
		    	  //  	  contactList = endpoint.listContact().execute();
		    	  endpointCopy.removeContactCopy(contactIdToRemove).execute();
		      } catch (IOException e) {
		    	  // TODO Auto-generated catch block
		    	  e.printStackTrace();
		    	  //result = null;
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
		      Log.i(WelcomeActivity.this.getLocalClassName(), "End doInBackground Method");
		      return null;
		   }
		  } 
	  
	  
}
