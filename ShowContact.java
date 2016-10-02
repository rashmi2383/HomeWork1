package com.example.call;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ShowContact extends ActionBarActivity {
	ListView lv;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_contact);

		
		db = openOrCreateDatabase("TestDB", MODE_PRIVATE, null); 
		db.execSQL("create table if not exists user(name text,mobile number(10))"); 
	    
		lv = (ListView)findViewById(R.id.listView1);
		
		Cursor c = db.rawQuery("select * from user",null);
		List<String> ls = new ArrayList<String>();
		while(c.moveToNext()){
			ls.add(c.getString(1).toString()+"\n"+c.getString(0).toString());
		}
		
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ls);
		ad.setDropDownViewResource(android.R.layout.simple_list_item_1);
	    
		lv.setAdapter(ad);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				String str = lv.getItemAtPosition(position).toString();
				String s = str.substring(str.length()-10).toString();
				Intent in = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+s));
				startActivity(in);
						
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			call();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	 public void call(){
	    	
	    	LinearLayout ll = new LinearLayout(this);
	    	ll.setOrientation(LinearLayout.VERTICAL);

	    	AlertDialog.Builder alert = new AlertDialog.Builder(this);
	    	alert.setTitle("Add New Contact");
	    	
	    	TextView tv = new TextView(this);
	    	tv.setText("Enter Contact Name");
	    	final EditText ed = new EditText(this);
	    	ed.setHint("Name");
	    	
	    	TextView tv1 = new TextView(this);
	    	tv1.setText("Enter Mobile Number");
	    	final EditText ed1 = new EditText(this);
	    	ed1.setHint("Mobile Number");
	    	
	    	 
	    	ll.addView(tv);
	    	ll.addView(ed);
	    	ll.addView(tv1);
	    	ll.addView(ed1);
	    	alert.setView(ll);
	    	
	    	
	    	alert.setPositiveButton("Submit",
	    			new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							db.execSQL("insert into user values('"+ed1.getText().toString()+"','"+ed.getText().toString()+"')");
							Toast.makeText(getBaseContext(), "Contact Added",2000).show();
							Intent intent = new Intent(ShowContact.this,ShowContact.class);
							startActivity(intent);
							
						}
					});
	    	
	    	alert.setNegativeButton("Cancel",
	    			new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int arg1) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
	    	
	    		alert.show();
	    	
	    }


	
}
