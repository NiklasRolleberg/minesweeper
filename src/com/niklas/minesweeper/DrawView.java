package com.niklas.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View{
    Paint paint = new Paint();
    
    int width;
    int height;
    int linesX;
    int linesY;
    int dist;
    int maxsizeX;
    int maxsizeY;
    
    
    String t;
    int r; //mina/antal minor runt
    int s; //state

    public DrawView(Context context) {
        super(context);  
        
        width=data.width;
        linesX=data.linesX;
        linesY=data.linesY;
        dist= width/linesX;
        data.dist=dist;
        maxsizeX=dist*linesX;
        maxsizeY=dist*linesY;
        data.maxX=maxsizeX;
        data.maxY=maxsizeY;
        
        /*if (dist*linesX > width){
        	linesX--;
        }
        if (dist*linesY > height){
        	linesY--;
        }*/
    }

    @Override
    public void onDraw(Canvas canvas) { 	
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setTextSize(dist);
        
        for (int x = 0; x <linesX; x++) {
        	for (int y =0; y<linesY; y++){
        		
        		r=data.field[x][y];
        		s=data.state[x][y];
        		if (s==1){
        			paint.setColor(Color.BLACK);
            		canvas.drawRect(x*dist, y*dist, (x+1)*dist, (y+1)*dist, paint);
        		}
        		
        		else if (s==2){
        			if (r==10){
        				if (data.win==false){
        				paint.setColor(Color.RED);
        				}
        				else{
        					paint.setColor(Color.GREEN);
        				}
                		canvas.drawRect(x*dist, y*dist, (x+1)*dist, (y+1)*dist, paint);
        			}
     			
        			else{
        				paint.setColor(Color.GRAY);
        				canvas.drawRect(x*dist, y*dist, (x+1)*dist, (y+1)*dist, paint);
        				if (r!=0){
        					t=Integer.toString(r);
        					paint.setColor(Color.RED);
        					canvas.drawText(t,(dist/10)+x*dist,-(dist/10)+dist+y*dist, paint);
        				}
        			}
        		}
        		else if (s==3){
					paint.setColor(Color.YELLOW);
            		canvas.drawRect(x*dist, y*dist, (x+1)*dist, (y+1)*dist, paint);
    			}
        	}
        }
        paint.setColor(Color.WHITE);
        for (int i = 0; i <=linesX ; i++) {
        	canvas.drawLine(i*dist, 0, i*dist, maxsizeY, paint);
        }     
        for (int i = 0; i <=linesY ; i++) {
        	canvas.drawLine(0 ,i*dist,maxsizeX,i*dist, paint);
        }

    }

}