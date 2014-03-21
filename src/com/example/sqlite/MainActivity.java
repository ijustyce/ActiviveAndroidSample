package com.example.sqlite;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Category restaurants = new Category();
		restaurants.name = "Restaurants";
		restaurants.save();
		
		Item item = new Item();
	    item.category = restaurants;
	    item.name = "Outback Steakhouse";
	    item.save();
	    
	    item = new Item();
	    item.category = restaurants;
	    item.name = "Red Robin";
	    item.save();

	    item = new Item();
	    item.category = restaurants;
	    item.name = "Olive Garden";
	    item.save();
	    
	    ActiveAndroid.beginTransaction();
	    try {
	            for (int i = 0; i < 100; i++) {
	                Item item1 = new Item();
	                item1.name = "Example " + i;
	                item1.save();
	            }
	            ActiveAndroid.setTransactionSuccessful();
	    }
	    finally {
	            ActiveAndroid.endTransaction();
	    }
	    
	    Item.delete(Item.class, 1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	@Table(name = "haha")
	public class Category extends Model { 
	    @Column(name = "Name")
	    public String name;
	}

	@Table(name = "sms")
	public class Item extends Model {
	    @Column(name = "Name")
	    public String name;

	    @Column(name = "Category")
	    public Category category;
	}

}
