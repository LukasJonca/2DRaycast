/*
2020-05-21
Lukas Jonca 
Version 1
the player class is used to house all major variables of the program
 */

//import java packages
package rayCasting;
import java.io.Serializable;
import java.awt.Color;
import java.util.ArrayList;

public class Player implements Serializable{
	
	//initialize variables
	int playerX;
	int playerY;
	int width =10;
	public ArrayList<Border> walls = new ArrayList<Border>();
	public static ArrayList<Ray> rays = new ArrayList<Ray>();
	public int degree = 0;
	public int FOV = 90;
	
	//player constructor
	Player(){
		
		//set player coordinates
		playerX = 350;
		playerY = 350;
		
		//initialize variables
		double rad; 
		double m;
		int LineY2;
		
		//for loop
		for(int i = 0; i < 90; i++) {
			      
			//calculate radians then slope
			rad = Math.toRadians(i);
			//System.out.println("rads: "+rad);
			m = Math.tan(rad);
			//System.out.println("slope: " +m);
			LineY2 =  (int) (m*7000)+playerY;
			
			rays.add(new Ray(playerX,playerY,7000,LineY2, m, Color.white));

		}
		rays.add(new Ray(playerX,playerY,350, 7000, 0, Color.red));
		
		for(int i = 91; i < 180; i++) {
			
			//calculate radians then slope
			rad = Math.toRadians(i); 
			//System.out.println("rads: "+rad);
			m = Math.tan(rad);
			//System.out.println("slope: " +m);
			LineY2 =  (int) (m*-7000)+playerY;
			
			rays.add(new Ray(playerX,playerY,-7000,LineY2, m, Color.white));
			
		}
		for(int i = 180; i < 270; i++) {
			
			//calculate radians then slope
			rad = Math.toRadians(i); 
			//System.out.println("rads: "+rad);
			m = Math.tan(rad);
			//System.out.println("slope: " +m);
			LineY2 =  (int) (m*-7000)+350;
			//int LineX2 = -700;
			
			rays.add(new Ray(playerX,playerY,-7000,LineY2, m, Color.white));
			//fill rectangle
		   // g.fillRect(180, 0 ,390,920);

		    //set color dark grey
		}
		rays.add(new Ray(playerX,playerY,350, -7000, 0, Color.white));
		for(int i =271; i < 360; i++) {
			
			//calculate radians then slope
			rad = Math.toRadians(i); 
			//System.out.println("rads: "+rad);
			m = Math.tan(rad);
			//System.out.println("slope: " +m);
			LineY2 =  (int) (m* 7000)+playerY;
			
			rays.add(new Ray(playerX,playerY,7000,LineY2, m, Color.white));
			
		}
		
		for(int i = 0; i <= 10; i++) {
			
			//generat random walls
			int randomX1 = (int) (Math.random() * (700 - 0 + 1));
			int randomX2 = (int) (Math.random() * (700 - 0 + 1));
			int randomY1 = (int) (Math.random() * (700 - 0 + 1));
			int randomY2 = (int) (Math.random() * (700 - 0 + 1));
			
			//add wall
			walls.add(new Border(randomX1, randomX2, randomY1, randomY2));
			
			
			//walls for testing
			//walls.add(new Border(100, 600, 600, 600));
			
			//walls.add(new Border(100, 100, 600, 100));
			
			//walls.add(new Border(100, 100, 100, 600));
			
			//walls.add(new Border(600, 100, 600, 600));
			
			//walls.add(new Border(100, 100, 600, 600));
			
		}
	
	}
	
	//method for getting playerX
	int getPlayerX() {
		return playerX;
	}
	
	//method for getting playerY
	int getPlayerY() {
		return playerY;
	}
	
	//method for getting rays
	static Ray getRays(int i) {
		return rays.get(i);
	}
	
	
}
