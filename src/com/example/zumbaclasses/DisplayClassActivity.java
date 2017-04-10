package com.example.zumbaclasses;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.zumbaclasses.ClassListActivity.DownloadHtmlTask;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayClassActivity extends Activity {
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_class);
		Intent intent = getIntent();
        //String fullMessage = String.format("{0} {1}", "your word:" , MainActivity.EXTRA_MESSAGE);
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // Create the text view
        textView = (TextView) findViewById(R.id.textView1);
        try {
			String  urlAddress;
			//urlAddress = "http://www.studio-b.co.il/studiob-schedule.aspx?schid=7/4/2014&schsid=6/4/2014";
			urlAddress = String.format("http://maymor.zumba.com/come-and-dance-with-me");
			Toast.makeText(DisplayClassActivity.this, urlAddress,
					Toast.LENGTH_LONG).show();

			URL url = new URL(urlAddress);
			new DownloadHtmlStodioBTask().execute(url);
		} catch (Exception e) { // TODO Auto-generated catch block
			Toast.makeText(DisplayClassActivity.this, e.getMessage(),
					Toast.LENGTH_LONG).show();
		}

		Toast.makeText(DisplayClassActivity.this, "done http req",
				Toast.LENGTH_LONG).show();


        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_class, menu);
		return true;
	}
	public class DownloadHtmlStodioBTask extends AsyncTask<URL, Void, String> {

		String result ="fail";

		protected String doInBackground(URL... urls) {

			
			String zumbaInfo = null ;
			try 
			{
				
				Source source=new Source(urls[0]);
				List <Element> spans = source.getAllElements(HTMLElementName.SPAN);
				
				for (int i=0 ; i< spans.size() ; i++) 
				{
				
					String elementString = spans.get(i).toString();
				if(elementString.contains("zumba"))
				{
					List <Element> classDetails = spans.subList(i -1, i+2);
					zumbaInfo = classDetails.toString();
					
				}
					
			}		
				result = zumbaInfo;
			//	result = spans.toString();
			}
				
			catch (Exception e)
			{
				String message =e.getMessage();
				result= message;
			}
			return result;
		}

		protected void onPostExecute(String result) {

			textView.setTextSize(20);
	        textView.setText(String.format(result));
	        
	        textView.setTextColor(Color.RED);
	        // Set the text view as the activity layout
	        setContentView(textView);
		}

	}


}
