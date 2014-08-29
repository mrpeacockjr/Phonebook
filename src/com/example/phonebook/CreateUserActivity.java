package com.example.phonebook;


import java.io.IOException;

import com.example.phonebook.userendpoint.Userendpoint;
import com.example.phonebook.userendpoint.model.User;
import com.example.utils.Validator;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson.JacksonFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUserActivity extends Activity{
	
	private EditText userid=null;
	private EditText username=null;
	private EditText firstname=null;
	private EditText lastname=null;
	private EditText email=null;
	private EditText password=null;
	private EditText verifyPassword=null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		    
		
		super.onCreate(savedInstanceState);  
		Intent createUserActivityIntent = getIntent();
		setContentView(R.layout.new_user);
	}
	
	
	public void createAccount(View view){
			
		username = (EditText)findViewById(R.id.editText1);
		firstname = (EditText)findViewById(R.id.editText2);
		lastname = (EditText)findViewById(R.id.editText3);
		email = (EditText)findViewById(R.id.editText4);
		password = (EditText)findViewById(R.id.EditText01);
		verifyPassword = (EditText)findViewById(R.id.EditText02);
		
		// this if statment will be replaced by a call to the validator class
		if( ! Validator.validateUserName(username.getText().toString()).equalsIgnoreCase(Validator.VALID)){		
			Toast.makeText(getApplicationContext(), Validator.validateUserName(username.getText().toString()), 
					Toast.LENGTH_SHORT).show();
		}else if( ! Validator.validateFirstName(firstname.getText().toString()).equalsIgnoreCase(Validator.VALID)){		
			Toast.makeText(getApplicationContext(), Validator.validateFirstName(firstname.getText().toString()), 
					Toast.LENGTH_SHORT).show();
		}else if( ! Validator.validateLastName(lastname.getText().toString()).equalsIgnoreCase(Validator.VALID)){		
			Toast.makeText(getApplicationContext(), Validator.validateLastName(lastname.getText().toString()), 
					Toast.LENGTH_SHORT).show();				
		}else if( ! Validator.validateEmail(email.getText().toString()).equalsIgnoreCase(Validator.VALID)){		
			Toast.makeText(getApplicationContext(), Validator.validateEmail(email.getText().toString()), 
					Toast.LENGTH_SHORT).show();				
		}else if( ! Validator.validatePassword(password.getText().toString()).equalsIgnoreCase(Validator.VALID)){		
			Toast.makeText(getApplicationContext(), Validator.validatePassword(password.getText().toString()), 
					Toast.LENGTH_SHORT).show();			
		}else if(! password.getText().toString().equals(verifyPassword.getText().toString() )){ 
			Toast.makeText(getApplicationContext(), "Passwords do no match", Toast.LENGTH_SHORT).show();
		}else{
			new UserTask().execute();
			
			//theTask.insertUser(addUser);		
			Intent intent = new Intent(CreateUserActivity.this, WelcomeActivity.class);
			startActivity(intent);
			CreateUserActivity.this.finish();
		}
		
	}
	
	  public void goHome(View view) 
	  {
	      Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);	      
	      startActivity(intent);
	      CreateUserActivity.this.finish();
	  }
	  
	  /**
	   * AsyncTask for calling Mobile Assistant API for checking into a place (e.g., a store)
	   */
	  private class UserTask extends AsyncTask<Void, Void, Void> {
	  		  
		  @Override
		    protected Void doInBackground(Void... params) {
		      User currentUser = new User();
		      
		      currentUser.setUserName(username.getText().toString());
		      currentUser.setFirstName(firstname.getText().toString());
		      currentUser.setUserFirst(firstname.getText().toString());
		      currentUser.setLastName(lastname.getText().toString());
		      currentUser.setUserLastName(lastname.getText().toString());		      
		      currentUser.setUserEmail(email.getText().toString());
		      currentUser.setUserPassword(password.getText().toString());		     

		      Userendpoint.Builder builder = new Userendpoint.Builder(
		          AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		          null);
		          
		      builder = CloudEndpointUtils.updateBuilder(builder);
		      Userendpoint endpoint = builder.build();
		      
		      try {	    	 
		        endpoint.insertUser(currentUser).execute();
		      } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }

		      return null;
		    }
		  
	  }
	
}
