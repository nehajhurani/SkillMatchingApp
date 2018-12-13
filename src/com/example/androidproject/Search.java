package com.example.androidproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Search extends Activity {
	
	TextView t1,t2;
	Button b1;
	String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		b1=(Button)findViewById(R.id.button1);
		
		username=getIntent().getStringExtra("uname");
        ArrayList<String> names = getIntent().getStringArrayListExtra("names");
        ArrayList<String> phone = getIntent().getStringArrayListExtra("phone");
        ArrayList<String> email = getIntent().getStringArrayListExtra("email");
        ArrayList<String> workplace = getIntent().getStringArrayListExtra("workplace");
        
        t1.setText("Name  |  Email  |  Phone Number  |  Working Place \n");
		
		for(int i=0; i < names.size(); i++){
			 
			 t2.setText(t2.getText() + "\n" + names.get(i) +" , "+email.get(i) +" , "+phone.get(i) +" , "+workplace.get(i) + "\n");
			 }
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(Search.this,Login_entry.class);
				i.putExtra("fname",username);
				startActivity(i);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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
