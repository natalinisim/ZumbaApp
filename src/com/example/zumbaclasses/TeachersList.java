package com.example.zumbaclasses;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TeachersList extends Activity {
	  private ListView mainListView ;  
	  private ArrayAdapter<String> listAdapter ; 
	
	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachers_list);
		mainListView = (ListView) findViewById( R.id.teachersListView);  
		  
	    // Create and populate a List of planet names.  
	    String[] teachers = new String[] {"מאי מור" ,"דניאל קזס" ,"מריו גוטרייז"};    
	   
	      
	    // Create ArrayAdapter using the planet list.  
	    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, teachers);  
	      
	    // Set the ArrayAdapter as the ListView's adapter.  
	    mainListView.setAdapter( listAdapter );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teachers_list, menu);
		return true;
	}

}
