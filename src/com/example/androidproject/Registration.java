package com.example.androidproject;




import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends Activity {

	EditText e1,e2,e3,e4,e5,e6;
	Button b1;
	
	SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		openHelper = new SQLiteDBHelper(this);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);
		e6=(EditText)findViewById(R.id.editText6);
		b1=(Button)findViewById(R.id.button1);
		

		
		
		db = openHelper.getWritableDatabase();
		
		
		
		
		
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				String fullname = e1.getText().toString();
				String mobile = e2.getText().toString();
                String email = e3.getText().toString();
                String pass = e4.getText().toString();
                String workplace=e5.getText().toString();
                String role="Teacher";
                String education=null;
                String skill=e6.getText().toString();
                
 
                //Calling InsertData Method - Defined below
                InsertData(role,fullname, mobile,email, pass, workplace,education,skill);
 
                //Alert dialog after clicking the Register Account
                final AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                builder.setTitle("Information");
                builder.setMessage("Your Account is Successfully Created.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
 
                        //Finishing the dialog and removing Activity from stack.
                        dialogInterface.dismiss();
                        finish();
                    }
                });
 
                AlertDialog dialog = builder.create();
                dialog.show();
 
            }
        });
				
 
    }
 
	 //Inserting Data into database - Like INSERT INTO QUERY.
    public void InsertData(String role,String fullName, String mobile,String email, String password, String workplace,String education,String skill  ) {
 
    	List<String> result = Arrays.asList(skill.split("\\s*,\\s*"));
    	for(String s : result)
    	
    	{
    		ContentValues values = new ContentValues();
            values.put(SQLiteDBHelper.COLUMN_ROLE,role);
            values.put(SQLiteDBHelper.COLUMN_FULLNAME,fullName);
            values.put(SQLiteDBHelper.COLUMN_MOBILE,mobile);
            values.put(SQLiteDBHelper.COLUMN_EMAIL,email);
            values.put(SQLiteDBHelper.COLUMN_PASSWORD,password);
            values.put(SQLiteDBHelper.COLUMN_WORK_PLACE,workplace);
            values.put(SQLiteDBHelper.COLUMN_EDUCATION,education);
            values.put(SQLiteDBHelper.COLUMN_SKILL,s);
            long id = db.insert(SQLiteDBHelper.TABLE_NAME,null,values);
    	}
 
    }
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
