package com.example.call;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class Call extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

       SQLiteDatabase db = openOrCreateDatabase("DatabaseName", MODE_PRIVATE, null); 
      
       Button b = (Button)findViewById(R.id.button1);
       b.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent in = new Intent(Call.this,NewContact.class);
			startActivity(in);
			
		}
       });
       
       Button b1 = (Button)findViewById(R.id.button2);
       b1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent in = new Intent(Call.this,ShowContact.class);
			startActivity(in);
			
		}
       });
       
       
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.call, menu);
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
