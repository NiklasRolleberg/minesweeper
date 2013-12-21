package com.niklas.minesweeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Launcher extends Activity {
    /** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        
		final int Width  = getWindowManager().getDefaultDisplay().getWidth();
		final int Height = getWindowManager().getDefaultDisplay().getHeight();
        
        
        final EditText b = (EditText)findViewById(R.id.editText1);
        final EditText m = (EditText)findViewById(R.id.editText2);
        
        
        
        Button start = (Button) findViewById(R.id.button1);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	String textB=b.getText().toString();
            	String textM=m.getText().toString();
            	
            	int br = Integer.parseInt(textB);
            	int mi = Integer.parseInt(textM);
            	
            	if (br < 4){
            		br=4;
            	}
            	if (br > 30){
            		br=30;
            	}
            	if (mi < 3){
            		mi=3;
            	}
            	if (mi > br*br){
            		mi=(br*br)-4;
            	}
            	@SuppressWarnings("unused")
				data data=new data(Width,Height,br,mi);
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }
	protected void onDestroy()
	{
    	System.exit(0);
		// TODO Auto-generated method stub
	}
}
