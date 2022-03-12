/*
2020-05-11
Lukas Jonca 
Version 1
Line segment class used for generating and finding intersections of lines
 */


//import java packages
package rayCasting;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import rayCasting.GUI.*;
//import rayCasting.GUI.Move;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class lineSegment extends JFrame implements ActionListener{
	public static int x1, x2, x3, x4;
	public static int y1, y2, y3, y4;
	
	lineSegment(){
		
		//declare JMenuBar, JMenu, JMenuItem
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		
		//setup GUI board
		Board gameBoard = new Board();
		gameBoard.setLayout (new BorderLayout());
		this.setContentPane(gameBoard);
		
		//Initialize and setup the JMenuBar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Generate random coordinates
		x1 = (int) (Math.random() * (700 - 0 + 1));
		x2 = (int) (Math.random() * (700 - 0 + 1));
		x3 = (int) (Math.random() * (700 - 0 + 1));
		x4 = (int) (Math.random() * (700 - 0 + 1));
		y1 = (int) (Math.random() * (700 - 0 + 1));
		y2 = (int) (Math.random() * (700 - 0 + 1));
		y3 = (int) (Math.random() * (700 - 0 + 1));
		y4 = (int) (Math.random() * (700 - 0 + 1));
		
		
		
	}
	
	
	public class Board extends JPanel{
		
		 /**********
		 *name: paintComponent
		 *date:2020-05-12
		 *description:The following method is used to display the board
		 *Input: generated coordinates
		 *Output: displays line and intersection
		 ***************************/
			public void paintComponent(Graphics g) {
				
				
				//initialize variables
				double t, u;
				
				//set color
				g.setColor(Color.green);
				
				//label points
				g.drawString("1", x1, y1);
				g.drawString("2", x2, y2);
				g.drawString("3", x3, y3);
				g.drawString("4", x4, y4);
				
				//set color
				g.setColor(Color.white);	
				
				//draw lines
				g.drawLine(x1, y1, x2, y2);
				g.drawLine(x3, y3, x4 , y4);
					
				//find t and u
				t = findT(x1, x2, x3, x4, y1, y2, y3, y4);
				u = findU(x1, x2, x3, x4, y1, y2, y3, y4);
				
				//find x and y intersections
				int xInter = IntersectionX(x1, x2, x3, x4, y1, y2, y3, y4);
				int yInter = IntersectionY(x1, x2, x3, x4, y1, y2, y3, y4);
				
				//print out
				System.out.println("t: " + t + " u: " + u);
				
				//if lines intersect
				if (t>0 && t<1 && u>0 && u<1) {
					
					//drwa oval
					g.fillOval(xInter - 5,yInter-5, 10,10);
				}
			}
	}
	
	/**********
	*name:intersectionX
	*date:2020-05-11
	*description:The following method is used to calculate the X intersection,
	*Input: Coordinates.
	*Output:X coordinate.
	***************************/
	int IntersectionX(int x1, int x2, int x3, int x4,int y1, int y2,int y3,int y4) {
		
		//initialize variables
		int numerator;
		int denominator;
		int pX;
		
	
		//calculate
		numerator = (int)((x1*y2 - y1*x2)*(x3 -x4) - (x1 - x2)*(x3*y4 - y3*x4));
		denominator = (int)((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
		
		//if denominator = 0 return o
		if(denominator== 0) {
			return 0;
		}
		
		//calculate coordinate
		pX = numerator/denominator;
		
		//return x
		return pX;
		
	}
	
	/**********
	*name:findT
	*date:2020-05-11
	*description:The following method is used to calculate T.
	*Input: Coordinates.
	*Output:T double value.
	***************************/
	double findT(int x1, int x2, int x3, int x4,int y1, int y2,int y3,int y4) {
		
		//calculate 
		double numerator = (x1 - x3)*(y3 - y4) - (y1 - y3)*(x3 - x4);
		double denominator = (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4);
		
		//if denominator = 0 return 2
		if (denominator == 0) {
			return 2;
		}
		
		//calculate t
		double t = numerator/denominator;
		
		//return t value
		return t;
	}
	
	/**********
	*name:findU
	*date:2020-05-11
	*description:The following method is used to calculate U.
	*Input: Coordinates.
	*Output: U double value.
	***************************/
	double findU(int x1, int x2, int x3, int x4,int y1, int y2,int y3,int y4) {
		
		//calculate 
		double numerator = (x1 - x2)*(y1 - y3) - (y1 -y2)*(x1-x3);
		double denominator = (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4);
		
		//if denominator = 0 return 2
		if (denominator == 0) {
			return 2;
		}
		
		//calculate u
		double u = -numerator/denominator;
		
		//return u
		return u;
	}
	
	/**********
	*name:intersectionY
	*date:2020-05-11
	*description:The following method is used to calculate the Y intersection,
	*Input: Coordinates.
	*Output:Y coordinate.
	***************************/
	int IntersectionY(int x1, int x2, int x3, int x4,int y1, int y2,int y3,int y4) {
		
		//initialize variables
		int numerator;
		int denominator;
		int pY;
		
		//calculate 
		numerator = (int)((x1*y2 - y1*x2)*(y3 -y4) - (y1 - y2)*(x3*y4-y3*x4));
		denominator = (int)((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4));
		
		//if denominator = 0 return 0
		if(denominator== 0) {
			return 0;
		}
		
		
		//calculate point
		pY = numerator/denominator;
		
		//return y
		return pY;
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
