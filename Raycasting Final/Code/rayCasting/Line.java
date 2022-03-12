/*
2020-05-21
Lukas Jonca 
Version 1
the line class is an abstract class used to create rays and borders
 */

//import packagaes
package rayCasting;

import java.awt.Color;
import java.io.Serializable;

public abstract class Line implements Serializable{
	
	//coordinate variables for line
	int X1;
	int X2;
	int Y1;
	int Y2;
	
	Color color;
	
	double slope;
	
	
	//method for getting X1
	int getX1(){
		return X1;
	}
	
	//method for getting X2
	int getX2(){
		return X2;
	}
	
	//method for getting Y1
	int getY1(){
		return Y1;
	}
	
	//method for getting Y2
	int getY2(){
		return Y2;
	}
	
	//method for getting color
	Color getColor() {
		return color;
	}
	
	//method for getting slope
	double getM() {
		return slope;
	}
}
