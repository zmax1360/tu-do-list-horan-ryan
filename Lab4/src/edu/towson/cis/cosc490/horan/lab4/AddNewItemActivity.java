package edu.towson.cis.cosc490.horan.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import edu.towson.cis.cosc490.jdehlinger.lab3solution.R;

public class AddNewItemActivity extends Activity {

	EditText editTextMessage;
	RadioButton HRB;
	RadioButton MRB;
	RadioButton LRB;
	CheckBox FCB;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        editTextMessage=(EditText)findViewById(R.id.editText1);
        HRB = (RadioButton)findViewById(R.id.radio0);
        MRB = (RadioButton)findViewById(R.id.radio1);
        LRB = (RadioButton)findViewById(R.id.radio2);
        FCB = (CheckBox)findViewById(R.id.checkBox1);
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
        
        // put radio button in Intent
        if(HRB.isChecked()){
        	intentMessage.putExtra("PRIORITY", "High");
        } else if(MRB.isChecked()){
        	intentMessage.putExtra("PRIORITY", "Medium");
        } else {
        	intentMessage.putExtra("PRIORITY", "Low");
        }
        
        // put check box in Intent
        if(FCB.isChecked()){
        	intentMessage.putExtra("FINISHED", "TRUE");
        } else {
        	intentMessage.putExtra("FINISHED", "FALSE");
        }
        setResult(2,intentMessage);
    	
        finish();
////////////////////////////////////////////////////////////////////////////////////////
	}
    
}
