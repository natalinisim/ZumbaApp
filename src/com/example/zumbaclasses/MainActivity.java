package com.example.zumbaclasses;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.TEACHER";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addListenerOnButton() {

		ImageButton mayMorImageButton = (ImageButton) findViewById(R.id.ib);
		ImageButton marioImageButton = (ImageButton) findViewById(R.id.iMarioButton);
		Button nextClassButton = (Button) findViewById(R.id.nextClass);

		final String mayMorName = (String) mayMorImageButton.getTag();
		final String marioName = (String) marioImageButton.getTag();
		final String nextClass = (String) nextClassButton.getTag();
		View.OnClickListener handler = new View.OnClickListener() {
			public void onClick(View v) {

				switch (v.getId()) {

				case R.id.ib:
					final Intent mayIntent = new Intent(MainActivity.this,
							ClassListActivity.class);
					Toast.makeText(MainActivity.this, mayMorName,
							Toast.LENGTH_SHORT).show();

					// String message = editText.getText().toString();
					mayIntent.putExtra(EXTRA_MESSAGE, mayMorName);
					startActivity(mayIntent);
					break;

				case R.id.iMarioButton:
					final Intent marioIntent = new Intent(MainActivity.this,
							ClassListActivity.class);
					Toast.makeText(MainActivity.this, marioName,
							Toast.LENGTH_SHORT).show();

					// String message = editText.getText().toString();
					marioIntent.putExtra(EXTRA_MESSAGE, marioName);
					startActivity(marioIntent);
					break;
			
				case R.id.nextClass:
					final Intent nextClassIntent = new Intent(
							MainActivity.this, DisplayClassActivity.class);
					Toast.makeText(MainActivity.this, nextClass,
							Toast.LENGTH_SHORT).show();

					// String message = editText.getText().toString();
					nextClassIntent.putExtra(EXTRA_MESSAGE, nextClass);
					startActivity(nextClassIntent);
					break;

				}
			}

		};
		findViewById(R.id.ib).setOnClickListener(handler);
		findViewById(R.id.iMarioButton).setOnClickListener(handler);
		findViewById(R.id.nextClass).setOnClickListener(handler);
	}
}
