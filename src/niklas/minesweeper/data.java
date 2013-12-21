
package niklas.minesweeper;

import java.io.Serializable;



@SuppressWarnings("serial")
public class data implements Serializable{
	
	public static int width;
	public static int height;
	public static int linesX;
	public static int linesY;
	public static float dist;
	public static float aspect;
	static int minor;
	public static int maxX;
	public static int maxY;
	
	public static int[][] field;
	public static int[][] state;
	
	public static boolean show=false;
	public static boolean win=false;
	    
		
	public data(int w,int h, int l, int m) {
		width=w;
		height=h;
		
		
		minor=m;
		aspect= ((float)height/(float)width);
		
		linesX=l;
		linesY=(int) (l*aspect);
		
		field=new int[linesX][linesY];
		state=new int[linesX][linesY];
	}
	
	public static void OpenAround(int x,int y){
		if (x<0 || y<0  || x>=linesX || y>=linesY){
			return;
		}
		if (state[x][y]==2) {
			return;
		}
		state[x][y]=2;
		if (field[x][y]==0){			
			OpenAround(x+1,y);
			OpenAround(x-1,y);
			OpenAround(x,y+1);
			OpenAround(x,y-1);
		}
	}
	
	
	public static int Around(int x, int y){
		int ar=0;
		int[] xpos={x-1,x,x+1};
		int[] ypos={y-1,y,y+1};
		
		for(int i=0; i<3;i++){
			for(int j=0;j<3;j++){
				if (xpos[i]<linesX && xpos[i]>=0 && ypos[j]<linesY && ypos[j]>=0 && field[xpos[i]][ypos[j]]==10){
					ar++;
				}
			}
		}
		
		return ar;
	}
	
	public static boolean vinstkoll(){
		int klararutor=0;
		int flaggor=0;
		int Rflaggor=0;
		for (int i=0;i<linesX;i++){
			for (int j=0;j<linesY;j++){
				if (state[i][j]==2 && field[i][j]!=10){
					klararutor++;
				}
				if (state[i][j]==3){
					flaggor++;
					if (field[i][j]==10){
						Rflaggor++;
					}
				}
			}
		}
		
		if (Rflaggor==minor && flaggor==Rflaggor){
			return true;
		}
		
		if (klararutor >= (linesX*linesY)-minor){
			return true;
		}
		return false;
	}
	
	
	public static  void ShowAll(){
		show=true;
		for (int i=0;i<linesX;i++){
			for (int j=0;j<linesY;j++){
				state[i][j]=2;
			}
		}
	}
	
	
	public static boolean changestate(int x,int y,boolean flag){
		
		int i = (int) (x/dist); 
		int j = (int) (y/dist); 
		
		if (i>linesX-1){
			return false;
		}
		if (j>linesY-1){
			return false;
		}
		
		if(flag){
			if (state[i][j]==3){
				state[i][j]=1;
				return false;
			}
			else if(state[i][j]==1){
				state[i][j]=3;
				return false;
			}
		}
		
		//state[i][j]=2;
		if (state[i][j]!=3){
			OpenAround(i,j);
			if (field[i][j]==10){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		
	}
	
	public static void createField(){
		
		show=false;
		win=false;
		
		for (int i = 0; i <linesX;i++) {
        	for (int j =0; j<linesY; j++){
        		field[i][j]=0;
        		state[i][j]=1;
        	}
        }
		int t=0;
		while (t<minor){
			int platsX=(int) (linesX*Math.random());
			int platsY=(int) (linesY*Math.random());
			if (field[platsX][platsY]!=10){
				field[platsX][platsY]=10;
				t++;
			}
		}
		
		for (int i = 0; i <linesX;i++) {
        	for (int j =0; j<linesY; j++){
        		if (field[i][j]!=10){
        			field[i][j]=Around(i,j);
        		}
        	}
        }
		
	}
}