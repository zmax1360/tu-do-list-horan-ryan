package edu.towson.cis.cosc490.horan.lab4;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import edu.towson.cis.cosc490.jdehlinger.lab3solution.R;

public class MainActivity extends ListActivity {
    ArrayAdapter<String> mAdapter;
   

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu(getListView());
        // Set up ListView example
        ArrayList<String> items = new ArrayList<String>();
        items.add("Finish Lab 4");
        
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                items);
        setListAdapter(mAdapter);
        
        
        
        ListView listView = getListView();
        // Create a ListView-specific touch listener. ListViews are given special treatment because
        // by default they handle touches for their list items... i.e. they're in charge of drawing
        // the pressed state (the list selector), handling list item clicks, etc.
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        listView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mAdapter.remove(mAdapter.getItem(position));
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });
        listView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        listView.setOnScrollListener(touchListener.makeScrollListener());
        
        
        
        
    }
    
    // Method to handle the Click Event on GetMessage Button
 	public void addNewItem(View V)
 	{
 	    Intent intentAddNewItem = new Intent(this,AddNewItemActivity.class);
 	    startActivityForResult(intentAddNewItem, 2);
 	}
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        
      }
        
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()) {
        case R.id.item1:
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.putExtra("sms_body", "TU-Do List Item: " + mAdapter.getItem((int)info.id));
                smsIntent.putExtra("address", "8675309");
                smsIntent.setType("vnd.android-dir/mms-sms");
                startActivity(smsIntent);
            return true;
        case R.id.item2:
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, "cosc490spring2014@gmail.com");
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "New TU-DO Item");
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mAdapter.getItem((int)info.id));

                startActivity(emailIntent);
            return true;
        default:
            return super.onContextItemSelected(item);
        }
    }
 	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
 		super.onActivityResult(requestCode, resultCode, data);
 		
        if(null != data) {	 	
        	String item = data.getStringExtra("ITEM");
        	String priority = data.getStringExtra("PRIORITY");
        	String finished = data.getStringExtra("FINISHED");
        	if(finished.equalsIgnoreCase("TRUE"))
                mAdapter.add("FINISHED: " + item);
            else
                mAdapter.add(priority + ": " + item);
        }
        }
 	}

