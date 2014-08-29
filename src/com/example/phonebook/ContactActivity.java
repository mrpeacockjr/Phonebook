package com.example.phonebook;

import java.io.ByteArrayInputStream;
import java.io.IOException;












import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import com.example.phonebook.contactcopyendpoint.Contactcopyendpoint;
import com.example.phonebook.contactcopyendpoint.model.ContactCopy;
import com.example.phonebook.contactendpoint.Contactendpoint;
import com.example.phonebook.contactendpoint.model.Contact;
import com.example.phonebook.contactendpoint.model.Key;
import com.example.phonebook.userendpoint.Userendpoint;
import com.example.phonebook.userendpoint.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ContactActivity extends Activity{
	
	private TextView welcomeTextView;
	private EditText firstname=null;
	private EditText lastname=null;
	private EditText phoneNumber=null;
	private ListView listView ;
	private Button actionButton;
	
	private String userId;
	private String userKey;
	private User currentUser;
	private Contact currentContact;
	private ContactCopy currentContactCopy;
	
	private Long editContactId;
	private String editContactFirstName;
	private String editContactLastName;
	private String editContactPhoneNumber;
	SharedPreferences sharedpreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		    
		
		super.onCreate(savedInstanceState);
		Log.i(ContactActivity.this.getLocalClassName(), "End doInBackground Method");
		Intent createUserActivityIntent = getIntent();
		setContentView(R.layout.contact);
		sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
		
		//userKey = createUserActivityIntent.getStringExtra("userKey");
		//userId = createUserActivityIntent.getStringExtra("userId");
		
		userKey = sharedpreferences.getString("userKey", "No User Key in session ");
		userId = sharedpreferences.getString("userId", "No User Id in session ");
		String contactOperation = createUserActivityIntent.getStringExtra("contactOperation");
		
		welcomeTextView = (TextView) findViewById(R.id.textView1);
		if( contactOperation.equalsIgnoreCase("add") ){
			welcomeTextView.setText("Add a new contact " + userId);
			actionButton = (Button)findViewById(R.id.button1);
			actionButton.setText("Add Contact");
			
		}	
		else if( contactOperation.equalsIgnoreCase("edit") ){
			
			//String contactString = createUserActivityIntent.getStringExtra("contact");
			//String contactString = createUserActivityIntent.getStringExtra("editContact");
			String contactString = createUserActivityIntent.getStringExtra("contactCopy");
			String[] contactArray = contactString.split("#");
						
			editContactId = new Long(contactArray[0]);
			editContactFirstName = contactArray[1];
			editContactLastName = contactArray[2];
			editContactPhoneNumber = contactArray[3];
			
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) 
				new FindContactAsyncRetriever().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			else
				new FindContactAsyncRetriever().execute();
			
			//String contactOld = sharedpreferences.getString("editContact", "No Contact in session ");
			welcomeTextView.setText("Edit contact " + editContactFirstName + " " + editContactLastName + " ");// + key.getId());
			actionButton = (Button)findViewById(R.id.button1);
			actionButton.setText("Edit Contact");
			
			firstname = (EditText)findViewById(R.id.editText1);
			firstname.setText(editContactFirstName);
			
			lastname = (EditText)findViewById(R.id.editText2);
			lastname.setText(editContactLastName);
			
			phoneNumber = (EditText)findViewById(R.id.editText3);
			phoneNumber.setText(editContactPhoneNumber);
			
		}
		Log.i(ContactActivity.this.getLocalClassName(), "End doInBackground Method");
	}
	
	@SuppressLint("NewApi")
	public void actionContact(View view){
		Log.i(ContactActivity.this.getLocalClassName(), "End doInBackground Method");
		Intent intent = new Intent(ContactActivity.this, WelcomeActivity.class);
		firstname = (EditText)findViewById(R.id.editText1);
		lastname = (EditText)findViewById(R.id.editText2);
		phoneNumber = (EditText)findViewById(R.id.editText3);
		if(actionButton.getText().toString().equalsIgnoreCase("Add Contact")){
			if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) 
				new InsertContactTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			else
				new InsertContactTask().execute();	
		}
		else if(actionButton.getText().toString().equalsIgnoreCase("Edit Contact")){
			//currentContact.seeditContactId
			//currentContact.setContactFirstName(firstname.getText().toString());
			//currentContact.setContactLastName(lastname.getText().toString());			
			//currentContact.setContactPhoneNumber(phoneNumber.getText().toString());
			
			currentContactCopy.setContactFirstName(firstname.getText().toString());
			currentContactCopy.setContactLastName(lastname.getText().toString());			
			currentContactCopy.setContactPhoneNumber(phoneNumber.getText().toString());
						
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) 
				new EditContactTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			else
				new EditContactTask().execute();	
		}
		moveTaskToBack(true); 		  
		startActivity(intent);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i(ContactActivity.this.getLocalClassName(), "End doInBackground Method");
		ContactActivity.this.finish();
	}
	
//	@SuppressLint("NewApi")
//	public void addContact(View view){
//		firstname = (EditText)findViewById(R.id.editText1);
//		lastname = (EditText)findViewById(R.id.editText2);
//		phoneNumber = (EditText)findViewById(R.id.editText3);
//
//		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) 
//			new InsertContactTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//		else
//			new InsertContactTask().execute();	
//		
//	    Intent intent = new Intent(ContactActivity.this, WelcomeActivity.class);	     
//	    startActivity(intent);
//	}
//	

	
	  public void goHome(View view) {
		  Log.i(ContactActivity.this.getLocalClassName(), "End doInBackground Method");
	      Intent intent = new Intent(ContactActivity.this, WelcomeActivity.class);	     
	      startActivity(intent);
	      ContactActivity.this.finish();
	      Log.i(ContactActivity.this.getLocalClassName(), "End doInBackground Method");
	  }
	  
	  //public void goWelcome(View view) {
	  //    Intent intent = new Intent(ContactActivity.this, WelcomeActivity.class);	     
	  //    startActivity(intent);
	 // }
	  
	  @SuppressLint("NewApi")
	public void logOut(View view){

		  Log.i(ContactActivity.this.getLocalClassName(), "End doInBackground Method");
		  SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
		  Editor editor = sharedpreferences.edit();
		  editor.clear();
		  editor.commit();
		  //moveTaskToBack(true); 
		  //WelcomeActivity.this.finishFromChild(getParent());;
		  //ContactActivity.this.finishFromChild(getParent());
		  //ContactActivity.this.finishAffinity();
	      Intent intent = new Intent(ContactActivity.this, MainActivity.class);	  
	      //intent.putExtra("LogOut", "logout);
	      startActivity(intent);
	      moveTaskToBack(true); 
		  ContactActivity.this.finish();
		  Log.i(ContactActivity.this.getLocalClassName(), "End doInBackground Method");
	  }

	  /**
	   * AsyncTask for calling Mobile Assistant API for checking into a place (e.g., a store)
	   */
	  private class EditContactTask extends AsyncTask<Void, Void, Void> {
	  		  
		  @Override
		    protected Void doInBackground(Void... params) {
		      //Contact newContact = new Contact();

		      //newContact.setUserId(editContactId);
		      //newContact.setKey(new Key())
		      //newContact.setContactFirstName(editContactFirstName);
		      //newContact.setContactLastName(editContactLastName);
		      //newContact.setContactPhoneNumber(editContactPhoneNumber);
			  Log.i(ContactActivity.this.getLocalClassName(), "Begin EditContactTask doInBackground Method");
			  Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		      Contactendpoint.Builder builder = new Contactendpoint.Builder(
		          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		          null);	          		     		      
		      builder = CloudEndpointUtils.updateBuilder(builder);
		      Contactendpoint endpoint = builder.build();
		      
		      			
		      Contactcopyendpoint.Builder builderCopy = new Contactcopyendpoint.Builder(
		          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		          null);	          		     		      
		      builderCopy = CloudEndpointUtils.updateBuilder(builderCopy);
		      Contactcopyendpoint endpointCopy = builderCopy.build();
		      
		      try {	    	 		       
		        //endpoint.updateContact(currentContact).execute();
		        endpointCopy.updateContactCopy(currentContactCopy).execute();
		      } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
		      Log.i(ContactActivity.this.getLocalClassName(), "End EditContactTask doInBackground Method");
		      return null;
		    }
		  
	  }

	  
	  
	  /**
	   * AsyncTask for calling Mobile Assistant API for checking into a place (e.g., a store)
	   */
	  private class InsertContactTask extends AsyncTask<Void, Void, Void> {
	  		  
		  @Override
		    protected Void doInBackground(Void... params) {
			  Log.i(ContactActivity.this.getLocalClassName(), "Begin InsertContactTask doInBackground Method");
		      Contact newContact = new Contact();
		      ContactCopy newContactCopy = new ContactCopy();

		      newContact.setUserId(new Long(userId));
		      newContact.setContactFirstName(firstname.getText().toString());
		      newContact.setContactLastName(lastname.getText().toString());
		      newContact.setContactPhoneNumber(phoneNumber.getText().toString());
		      
		      newContactCopy.setUserId(new Long(userId));
		      newContactCopy.setContactFirstName(firstname.getText().toString());
		      newContactCopy.setContactLastName(lastname.getText().toString());
		      newContactCopy.setContactPhoneNumber(phoneNumber.getText().toString());
	     

		      Contactendpoint.Builder builder = new Contactendpoint.Builder(
		          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		          null);
		      
		      Contactcopyendpoint.Builder builderCopy = new Contactcopyendpoint.Builder(
			          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			          null);
		          		     
		      
		      builder = CloudEndpointUtils.updateBuilder(builder);
		      Contactendpoint endpoint = builder.build();
		      
		      builderCopy = CloudEndpointUtils.updateBuilder(builderCopy);
		      Contactcopyendpoint endpointCopy = builderCopy.build();
		      
		      try {	    	 
		        endpoint.insertContact(newContact).execute();
		        endpointCopy.insertContactCopy(newContactCopy).execute();
		      } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
		      Log.i(ContactActivity.this.getLocalClassName(), "End InsertContactTask doInBackground Method");
		      return null;
		    }
		  
	  }
		  
		  /**
		   * AsyncTask for retrieving the list of places (e.g., stores) and updating the
		   * corresponding results list.
		   */
		  private class FindContactAsyncRetriever extends AsyncTask<Void, Void, ContactCopy> {
		  //private class FindContactAsyncRetriever extends AsyncTask<Void, Void, Contact> {	 
			  
		    @Override
		    //protected Contact doInBackground(Void... params) {
		    protected ContactCopy doInBackground(Void... params) {
	    	//insertCard();
		    	
		      //long findThisContact = -1;
		      //findThisContact = 6227633859723264L;
		    	Log.i(ContactActivity.this.getLocalClassName(), "Begin FindContactAsyncRetriever doInBackground Method");
		    	Thread.currentThread().setPriority(Thread.MAX_PRIORITY);	
		      Contactendpoint.Builder builder = new Contactendpoint.Builder(
			          AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
		     
		      builder = CloudEndpointUtils.updateBuilder(builder);
		      Contactendpoint endpoint = builder.build();
		      
		      
		    	
			      Contactcopyendpoint.Builder builderCopy = new Contactcopyendpoint.Builder(
				          AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
			     
			      builderCopy = CloudEndpointUtils.updateBuilder(builderCopy);
			      Contactcopyendpoint endpointCopy = builderCopy.build();
	
		      try {
		    	  currentContact = endpoint.getContact(editContactId).execute();
		    	  currentContactCopy = endpointCopy.getContactCopy(editContactId).execute();
		      } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		        //currentContact = null;
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
		      //return currentContact;
		      Log.i(ContactActivity.this.getLocalClassName(), "End FindContactAsyncRetriever doInBackground Method");
		      return currentContactCopy;
		    }
		  }  
		  
	  

	  
}
