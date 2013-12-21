package com.niklas.minesweeper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements OnTouchListener, OnClickListener, OnLongClickListener{
	   DrawView drawView;
	   boolean fail=false;
	   int Width;
	   int Height;
	   
	   int x;
	   int y;

	   int oldX;
	   int oldY;
	    @SuppressWarnings("deprecation")
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			Width  = getWindowManager().getDefaultDisplay().getWidth();
			Height = getWindowManager().getDefaultDisplay().getHeight();
	        
	        data.createField();
	        drawView = new DrawView(this);
	        drawView.setOnTouchListener(this);
	        drawView.setOnClickListener(this);
	        drawView.setOnLongClickListener(this);
	        drawView.setBackgroundColor(Color.GRAY);
	        setContentView(drawView);
	    }

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			x = (int)event.getX();
	        y = (int)event.getY();
	  
	       return false;
	   }

		@Override
		public void onClick(View v) {
			//System.out.println("Click");
			if (Math.abs(x-oldX)>=3 && Math.abs(y-oldY)>=3){
				
			fail=data.changestate(x,y,false);
	        drawView.invalidate();
	        
	        if (fail){
	        	data.ShowAll();
	        	data.win=false;
	        	drawView.invalidate();
	        }
	        
	        boolean vinst = data.vinstkoll();
	        if (vinst && data.show == false){
	        	data.ShowAll();
	        	data.win=true;
	        	drawView.invalidate();
	        }	
			}
			oldX=x;
			oldY=y;
		}

		@Override
		public boolean onLongClick(View v) {
			//System.out.println("LongClick");
			fail=data.changestate(x,y,true);
			
			boolean vinst = data.vinstkoll();
	        if (vinst && data.show == false){
	        	data.ShowAll();
	        	data.win=true;
	        }	
			drawView.invalidate();
			oldX=x;
			oldY=y;
			return false;
		}
}
