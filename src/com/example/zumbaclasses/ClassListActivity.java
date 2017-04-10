package com.example.zumbaclasses;

import net.htmlparser.jericho.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ClassListActivity extends Activity {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_list);
		Intent intent = getIntent();
		String teacherName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		listView = (ListView) findViewById(R.id.list);

		// SetList(teacherName);

		try {
			String  urlAddress;
			if(teacherName.equals("maymor"))
			{
			urlAddress = String.format("http://%s.zumba.com/come-and-dance-with-me",teacherName);
			}
			else
			{
			urlAddress = String.format("http://%s.zumba.com/classes",teacherName);
			}
			Toast.makeText(ClassListActivity.this, urlAddress,
					Toast.LENGTH_LONG).show();

			URL url = new URL(urlAddress);
			new DownloadHtmlTask().execute(url);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		Toast.makeText(ClassListActivity.this, "done http req",
				Toast.LENGTH_LONG).show();

	}

	public void SetList(String[] classesList) {

		ArrayAdapter<String> adapter;
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				classesList);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				int itemPosition = position;

				// ListView Clicked item value
				String itemValue = (String) listView
						.getItemAtPosition(position);

				// Show Alert
				Toast.makeText(
						getApplicationContext(),
						"Position :" + itemPosition + "  ListItem : "
								+ itemValue, Toast.LENGTH_LONG).show();

			}

		});

	}

	public class DownloadHtmlTask extends AsyncTask<URL, Void, String []> {

		String [] result ={"fail"};

		protected String []doInBackground(URL... urls) {

			//Downloader d = new Downloader();
			List<String> zumbaInfo=new ArrayList<String>();
			try 
			{
				//result = d.getHtml(urls[0]);
				Source source=new Source(urls[0]);
				List <Element> spans = source.getAllElements(HTMLElementName.SPAN);
				
				for (int i=0 ; i< spans.size() ; i++) 
				{
				
					String elementString = spans.get(i).toString();
				if(elementString.contains("day"))
				{
					List <Element> classDetails = spans.subList(i, i+3);
					zumbaInfo.add(classDetails.toString());
					
				}
					
			}		
				result = zumbaInfo.toArray(new String [zumbaInfo.size()]);
			//	result = spans.toString();
			}
				
			catch (Exception e)
			{
				String message =e.getMessage();
				result= new String [] {message};
			}
			return result;
		}

		protected void onPostExecute(String []result) {

			SetList(result);
		}

	}

}