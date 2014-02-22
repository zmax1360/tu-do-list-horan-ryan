package edu.towson.cis.cosc490.horan.lab4;

import edu.towson.cis.cosc490.jdehlinger.lab3solution.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewItemActivity extends Activity {

	EditText editTextMessage;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        editTextMessage=(EditText)findViewById(R.id.editText1);
    }
    
	public void addItem(View V)
	{
		// get the Entered  message
		String item = editTextMessage.getText().toString();
		Intent intentMessage = new Intent();
		
		// Debugging Toast
		Toast.makeText( AddNewItemActivity.this, 
						"Add new item: "+item, 
						Toast.LENGTH_LONG).show();
		
		// put the message in Intent
		intentMessage.putExtra("ITEM", item);

		setResult(2,intentMessage);
	
        finish();
	}
    
}
