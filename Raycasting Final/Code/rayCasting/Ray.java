/*
2020-05-21
Lukas Jonca 
Version 1
The ray class houses variables used for drawing rays and calculating
 */

//import packages
package rayCasting;

import java.awt.Color;
import java.io.Serializable;

public class Ray extends Line{

	//ray constructor method
	Ray(int x1, int y1, int x2, int y2, double m, Color c){
		
		//set coordinates
		X1 = x1;
		X2 = x2;
		Y1 = y1;
		Y2 = y2;
		
		//set slope
		slope = m;
		
		//set color
		color = c;
	}
	
	
	
}
