package com.example.androidproject;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_entry extends Activity {

	
	TextView t1;
	EditText e1;
	Button b1;
	String name;
	
	SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_entry);
		
		t1=(TextView)findViewById(R.id.textView1);
		
		e1=(EditText)findViewById(R.id.editText1);
		
		b1=(Button)findViewById(R.id.button1);
		
		dbhelper = new SQLiteDBHelper(this);
        db = dbhelper.getReadableDatabase();
		
		Intent i=getIntent();
		name=i.getStringExtra("fname");
		t1.setText("Welcome "+name+" !!");
		
		
       b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String search=e1.getText().toString();
				cursor = db.rawQuery("SELECT *FROM "+SQLiteDBHelper.TABLE_NAME+" WHERE "+SQLiteDBHelper.COLUMN_SKILL+"=?",new String[] {search});
                ArrayList<String> names=new ArrayList<String>();
                ArrayList<String> phone=new ArrayList<String>();
                ArrayList<String> email=new ArrayList<String>();
                ArrayList<String> workplace=new ArrayList<String>();
				
					while (cursor.moveToNext()) {
 
                        
                        //Retrieving User FullName and Email after successfull login and passing to LoginSucessActivity
                        String _fname = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_FULLNAME));
                        String _pnumb = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_MOBILE));
                        String _mail = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_EMAIL));
                        String _wplace = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_WORK_PLACE));
                        
                        names.add(_fname);
                        phone.add(_pnumb);
                        email.add(_mail);
                        workplace.add(_wplace);
                        
                        /*Intent i=new Intent(Home.this,Nlogin_entry.class);     
                        i.putExtra("fname", _fname);
                        startActivity(i);
 
                        //Removing MainActivity[Login Screen] from the stack for preventing back button press.
                        finish();*/
                        
                        
                    }
					
					if(cursor.getCount()<=0) {
 
                        //I am showing Alert Dialog Box here for alerting user about wrong credentials
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Login_entry.this);
                        builder.setTitle("Alert");
                        builder.setMessage("No teacher with searched skill is registered.");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
 
                                dialogInterface.dismiss();
 
                            }
                        });
 
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //-------Alert Dialog Code Snippet End Here
                    }
				
			
				if(!(names.isEmpty()))
				{
					Intent i=new Intent(Login_entry.this,Search.class);
				   i.putStringArrayListExtra("names", names);
				   i.putStringArrayListExtra("phone", phone);
				   i.putStringArrayListExtra("email", email);
				   i.putStringArrayListExtra("workplace", workplace);
				   i.putExtra("uname",name);
				   startActivity(i);
				   finish();
				}
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_entry, menu);
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
