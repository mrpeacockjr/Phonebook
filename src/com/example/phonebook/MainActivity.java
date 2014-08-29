package com.example.phonebook;

import java.io.IOException;
import java.util.ArrayList;







import com.example.phonebook.userendpoint.Userendpoint;
import com.example.phonebook.userendpoint.model.CollectionResponseUser;
import com.example.phonebook.userendpoint.model.User;
import com.example.utils.Validator;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson.JacksonFactory;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	  
	  private EditText username=null;
	  private EditText password=null;
	  private TextView newUserLink=null;
	  private Button loginButton;
	  private Button newUserButton; 
	  
	  public static final String MyPREFERENCES = "MyPrefs" ;
	  SharedPreferences sharedpreferences;
	  
	  CollectionResponseUser userList;
	  User result;
	  
		
	
	  @SuppressLint("NewApi")
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
		Log.i(MainActivity.this.getLocalClassName(), "Begin onCreate Method");  
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    //new FindUSerAsyncRetriever().execute();
	    
	    
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) 
			new ListUserAsyncRetriever().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		else
			new ListUserAsyncRetriever().execute();
	    
	    //new UserTask().execute();
	    //password.setText("");  
		
	      username = (EditText)findViewById(R.id.editText1);
	      password = (EditText)findViewById(R.id.editText2);
	      loginButton = (Button)findViewById(R.id.button1);
	      newUserButton = (Button)findViewById(R.id.button2);
	      Log.i(MainActivity.this.getLocalClassName(), "End onCreate Method");
	  }
	  
	   @Override
	   protected void onResume() {
		   Log.i(MainActivity.this.getLocalClassName(), "Begin onResume Method");
		   sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	  //    if (sharedpreferences.contains("userName")){
	   //      Intent i = new Intent(this, WelcomeActivity.class);
	   //      startActivity(i);
	   //   
	   //   }
	      super.onResume();
	      Log.i(MainActivity.this.getLocalClassName(), "End onResume Method");
	   }
	  
	  
	   @SuppressLint("NewApi")
	public void login(View view){
		   
		   //Log.i(MainActivity.this.getLocalClassName(), "Begin login Method with userId " + currentUser.getUserId().toString());
		   //if(username.getText().toString().equals("admin") && 
		   //   password.getText().toString().equals("admin")){
		   User currentUser = Validator.validateLogin(username.getText().toString(), password.getText().toString(), userList);
		   Log.i(MainActivity.this.getLocalClassName(), "Begin login Method with userId " + currentUser.getUserId().toString());
		   if(currentUser != null){
		   //if(currentUser.getFirstName() != null || currentUser.getFirstName().isEmpty()) {	  			  
		      Toast.makeText(getApplicationContext(), "Redirecting...", 
		      Toast.LENGTH_SHORT).show();
		      
		     
		      
		      Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
		      intent.putExtra("userName", currentUser.getUserName());
		      intent.putExtra("userEmail", currentUser.getUserEmail());
		      intent.putExtra("userFirstName", currentUser.getUserFirst());
		      intent.putExtra("userLastName", currentUser.getUserLastName());
		      intent.putExtra("userId", currentUser.getUserId().toString());
		      intent.putExtra("userKey", currentUser.getKey().getId().toString());
		      
		      Editor editor = sharedpreferences.edit();
		      editor.putString("userName", currentUser.getUserName());
		      editor.putString("userEmail", currentUser.getUserEmail());
		      editor.putString("userFirstName", currentUser.getUserFirst());
		      editor.putString("userLastName", currentUser.getUserLastName());
		      editor.putString("userKey", currentUser.getKey().getId().toString());
		      editor.putString("userId", currentUser.getUserId().toString());
		      //currentUser.getKey().
		      editor.commit();
		      
		      //intent.putExtra("userName", "testOne");
		      //intent.putExtra("userEmail", "test1@email.com");
		      //intent.putExtra("userFirstName", "test");
		      //intent.putExtra("userLastName", "one");
		      //intent.putExtra("userId", "1");
		      //intent.putExtra("userKey", "6685030696878080");
		      
		      
		      startActivity(intent);
		      MainActivity.this.finish();
		      
		   }	
		   else{
		      Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
		      //attempts.setBackgroundColor(Color.RED);	
		      //counter--;
		      //attempts.setText(Integer.toString(counter));
		      //if(counter==0){
		      //   login.setEnabled(false);
		   }
		   Log.i(MainActivity.this.getLocalClassName(), "End login Method");
	  }
	   
	   public void newAccount(View view){
		   Log.i(MainActivity.this.getLocalClassName(), "Begin newAccount Method");
		   Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
		   startActivity(intent);
		   Log.i(MainActivity.this.getLocalClassName(), "End newAccount Method");
	   }
	  
	  
	  @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
		  Log.i(MainActivity.this.getLocalClassName(), "Begin onCreateOptionsMenu Method");  
	    getMenuInflater().inflate(R.menu.main, menu);
	    Log.i(MainActivity.this.getLocalClassName(), "End onCreateOptionsMenu Method");
	    return true;
	  }
	  
	  //public void logout(){
	//	  p
	 //s }

	  /**
	   * AsyncTask for retrieving the list of places (e.g., stores) and updating the
	   * corresponding results list.
	   */
	  private class ListUserAsyncRetriever extends AsyncTask<Void, Void, CollectionResponseUser> {

	    @Override
	    protected CollectionResponseUser doInBackground(Void... params) {
	    	
	    	Log.i(MainActivity.this.getLocalClassName(), "Begin doInBackground Method");
	      Userendpoint.Builder endpointBuilder = new Userendpoint.Builder(
	          AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
	     
	      endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);
	      Userendpoint endpoint = endpointBuilder.build();

	      try {
	        userList = endpoint.listUser().execute();
	      } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        //result = null;
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      Log.i(MainActivity.this.getLocalClassName(), "End doInBackground Method");
	      return userList;
	    }
	  }  
	  
	  
	  
//	  /**
//	   * AsyncTask for retrieving the list of places (e.g., stores) and updating the
//	   * corresponding results list.
//	   */
//	  private class FindUSerAsyncRetriever extends AsyncTask<Void, Void, User> {
//
//	    @Override
//	    protected User doInBackground(Void... params) {
//
//	    	//insertCard();
//	    	
//	      long findThisUser = -1;
//	      findThisUser = 6227633859723264L;
//	    	
//	      Userendpoint.Builder endpointBuilder = new Userendpoint.Builder(
//	          AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
//	     
//	      endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);
//	      Userendpoint endpoint = endpointBuilder.build();
//
//	      try {
//	        result = endpoint.getUser(findThisUser).execute();
//	      } catch (IOException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	        result = null;
//	      } catch (Exception e) {
//	    	  e.printStackTrace();
//	      }
//	      return result;
//	    }
//	  }  
//	  
//	  /**
//	   * AsyncTask for calling Mobile Assistant API for checking into a place (e.g., a store)
//	   */
//	  private class UserTask extends AsyncTask<Void, Void, Void> {
//
//	    /**
//	     * Calls appropriate CloudEndpoint to indicate that user checked into a place.
//	     *
//	     * @param params the place where the user is checking in.
//	     */
//	    @Override
//	    protected Void doInBackground(Void... params) {
//	    	
//	    	
//	      User currentUser = new User();
//	      
//	      // Set the ID of the store where the user is. 
//	      // This would be replaced by the actual ID in the final version of the code. 
//	      //currentUser.setUserName("StoreNo123");
//
//	      Userendpoint.Builder builder = new Userendpoint.Builder(
//	          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
//	          null);
//	          
//	      builder = CloudEndpointUtils.updateBuilder(builder);
//
//	      Userendpoint endpoint = builder.build();
//	      
//
//	      try {	    	 
//	        endpoint.insertUser(currentUser).execute();
//	       
//	      } catch (IOException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	      }
//
//	      return null;
//	    }
//	  } 
	}
