package com.example.class_notes;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et = (EditText) findViewById(R.id.input_text);
        final TextView tv = (TextView) findViewById(R.id.output_text);
        et.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s) {
				tv.setText(s);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				tv.setText(s);
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				tv.setText(s);
			}});
    }
    
    public void showToast(View view){
    	Toast.makeText(this, "My first Toast!", Toast.LENGTH_SHORT).show();
    }
}
