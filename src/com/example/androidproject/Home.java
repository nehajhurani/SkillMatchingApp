package com.example.androidproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import android.widget.Toast;

public class Home extends Activity {

	EditText e1;
	Button b1,b2,b3;
	TextView t1;
	SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;
    
	
	//SQLiteDBHelper sqlhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		e1=(EditText)findViewById(R.id.editText1);
		
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		
		t1=(TextView)findViewById(R.id.textView2);
		
		dbhelper = new SQLiteDBHelper(this);
        db = dbhelper.getReadableDatabase();
 
		
		
        t1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Home.this,Login.class);
				startActivity(i);
				
			}
		});
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Home.this,Registration.class);
				startActivity(i);
				
			}
		});
		
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Home.this,Registration_student.class);
				startActivity(i);
				
			}
		});
 b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String search=e1.getText().toString();
				cursor = db.rawQuery("SELECT *FROM "+SQLiteDBHelper.TABLE_NAME+" WHERE "+SQLiteDBHelper.COLUMN_SKILL+"=?",new String[] {search});
                ArrayList<String> names=new ArrayList<String>();
				
                while(cursor.moveToNext()){
 
                        
                        //Retrieving User FullName and Email after successfull login and passing to LoginSucessActivity
                        String _fname = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_FULLNAME));
                        
                        names.add(_fname);
                        
                        /*Intent i=new Intent(Home.this,Nlogin_entry.class);     
                        i.putExtra("fname", _fname);
                        startActivity(i);
 
                        //Removing MainActivity[Login Screen] from the stack for preventing back button press.
                        finish();*/
                       
                    }
					
					if(cursor.getCount()<=0) {
 
                        //I am showing Alert Dialog Box here for alerting user about wrong credentials
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
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
				   Intent i=new Intent(Home.this,Nlogin_entry.class);
				   i.putStringArrayListExtra("search_res", names);
				   startActivity(i);
				   finish();
				}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
