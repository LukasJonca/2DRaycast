/*
2020-05-11
Lukas Jonca 
Version 1
The GUI class used for visualizing and calculating the 2D ray cast
 */


//import java packages
package rayCasting;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import java.io.Serializable;
import jdk.dynalink.beans.StaticClass;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class GUI extends JFrame implements ActionListener{
	
	//initialize player
	public Player player = new Player();
	
	//initialize coordinates
	public static int x1, x2, x3, x4;
	public static int y1, y2, y3, y4;

	GUI(){
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
		 
		//set up men bar option
		menu = new JMenu("File");
		menuBar.add(menu);
		
		//set up save option
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		//set up load option
		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		//initialize key press
		Press press = new Press();
		this.addKeyListener(press);
	}
	
	
	
	/**********
	*name: saveGame
	*date:2020-05-11
	*description:The following method is used to save the player.
	*Input: player objects 
	*Output: saved player object in serialized file
	***************************/
	public void savePlayer() {
		
		
	    //Creates a File output
	    ObjectOutputStream output = null;
	    
	    
	   
	   //try for errors
	    try {
	    	
	    	//set output to file
	    	FileOutputStream fileOut = new FileOutputStream("player.ser");
			
			//output object player to file
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(player);
			
			//close file
			out.close();
			fileOut.close();
			
			//closes the file and prints changes saved
			System.out.println("Changes saved!");
			
		//catch for errors
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			//print errors
			JOptionPane.showMessageDialog(rootPane, "Error in saving");
			e.printStackTrace();
		}
	   
	   
	    
	    
	}
	
	/**********
	*name:loadPlayer
	*date:2020-05-11
	*description:The following method is used to load previous player objects from file.
	*Input: filename.
	*Output: player objects.
	***************************/
	public Player loadPlayer(String filename) {
		
		//initialize stream and board variables
		ObjectInputStream input = null;
		Player playerLoaded = null;
		
		//try block
		try {
			
			//initialize stream
			input = new ObjectInputStream(new FileInputStream(filename));
			
			//load board
			playerLoaded = (Player) input.readObject();
			
			//close stream
			input.close();
			
		} catch (IOException | ClassNotFoundException e) {
			
			//print out errors
			JOptionPane.showMessageDialog(rootPane, "Error in loading");
			e.printStackTrace();
		}
		
		//return loaded board
		return playerLoaded;
	}
	
	
	/**********
	*name:actionPerformed
	*date:2020-05-11
	*description:The following method is used when an action on the GUI is performed.
	*Input: action performed.
	*Output: file.
	***************************/
	public void actionPerformed(ActionEvent e) {
		
		//get action performed
		String event = e.getActionCommand();
		
		//if save pressed
		if(event.equals("Save")) {
			
			//save player to file
			savePlayer();
		}
		
		//if load pressed
		if(event.equals("Load")) {
			
			//load player
			Player load = loadPlayer("player.ser");
			player = load;
			
  		}
		
	}
	
	
	

	//Key listener class
	public class Press implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		/**********
		*name:keyPressed
		*date:2020-05-11
		*description:The following method is used when a key is pressed.
		*Input: key pressed.
		*Output: Movement.
		***************************/
		public void keyPressed(KeyEvent e) {
			
			//System.out.println(e.getKeyCode());
			
			//initialize velocity
			int velocity = 2;
			
			//set speeds
			int speedX = (int)(velocity*Math.cos(Math.toRadians(player.degree)));
			int speedY = (int)(velocity*Math.sin(Math.toRadians(player.degree)));
			
			//if right key pressed
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				//try block
				try {
					
					// player degree is less than 360
					if(player.degree + 1< 360) {
						
						//increment degree
						player.degree++;
					}else {
						
						//else reset degree
						player.degree = 0;
					}
					
					//if degree is greater than 0
					if(player.degree-1 > 0) {
						Player.getRays(player.degree-1).color = Color.white;
					}
					
					//set color
					//Player.getRays(player.degree).color = Color.green;
					
					
					//System.out.println(player.degree);
				}catch(Exception E) {
					E.printStackTrace();
				}
			}
			
			//if left key pressed
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				
				//try block
				try {
					
					//if degree is greater than 0
					if(player.degree >= 0) {
						
						//decrement degree
						player.degree--;
					}else {
						
						//else reset degree
						player.degree = 360;
					}
					
					//if player degrees less than 360
					if(player.degree+1 < 360) {
						Player.getRays(player.degree+1).color = Color.white;
					}
					
					//set color
					//Player.getRays(player.degree).color = Color.green;
					
					//System.out.println(player.degree);
				}catch(Exception E) {
					E.printStackTrace();
				}
			}
			
			//if up key pressed
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				
				//increment coordinates
				player.playerY+=speedY;
				player.playerX+=speedX;
			}
			
			//if down key pressed
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				
				//decrement coordinates
				player.playerY-=speedY;
				player.playerX-=speedX;
			}
			
				//for loop
				for(int i = 0; i < 360; i++){
					
					//reset color
					Player.getRays(i).color = Color.white;
				}
				
				//for loop
				for(int i= 0; i < player.FOV/2; i++) {
					
					//try block
					try {
						
						//if player degree is less than 360
						if(player.degree + i < 360) {
							
							//set color
							Player.getRays(0).color = Color.green;
							Player.getRays(player.degree+i).color = Color.red;
						}else {
							
							//else set color	
							player.getRays(player.FOV/2 - i).color = Color.red;
						}
						
						//if color is greater than 0
						if(player.degree - i > 0) {
							
							//set color
							Player.getRays(0).color = Color.red;
							Player.getRays(player.degree-i).color = Color.green;
						}else {
								
							//else set color
							player.getRays(360-(player.FOV/2 - i)).color = Color.green;
						}
						
						//if player FOV does not go past 0 or 360
						if(player.degree - player.FOV/2 > 0 && player.degree + player.FOV/2 < 360) {
							
							//player color white
							player.getRays(0).color = Color.white;
						}
							
						//set color
						player.getRays(player.degree).color = Color.blue;
					}catch(Exception E) {
						E.printStackTrace();
					}
				}
				
			//initialize variables
			int xInter;
			int yInter;
			double t;
			double u;
			
			//for loop 
			for(int i = 0; i <= player.rays.size()-1; i++) {
				
				//set rays X1, Y1 to player coordinates
				player.getRays(i).X1 = player.playerX;
				player.getRays(i).Y1 = player.playerY;
				
				//if i is between 0 and 90
				if (i >= 0 && i < 90) {
					
					//set ray coordinates
					player.getRays(i).X2 = 7000;
					player.getRays(i).Y2 =  (int) (player.getRays(i).getM()*7000+ player.playerY);
				}
				
				//set ray 90 coordinates
				player.getRays(90).X2 = player.playerX;
				player.getRays(90).Y2 = 7000;
		
				//if i is between 90 and 180
				if (i > 90 && i < 180) {
					
					//set ray coordinates
					player.getRays(i).X2 = -7000;
					player.getRays(i).Y2 =  (int) (player.getRays(i).getM()*-7000+ player.playerY);
				}
				
				//set ray 180 coordinates
				player.getRays(180).X2 = -7000;
				player.getRays(180).Y2 = player.playerY;
				
				//if i is between 180 and 270
				if (i > 180 && i < 270) {
					
					//set ray coordinates
					player.getRays(i).X2 = -7000;
					player.getRays(i).Y2 =  (int) (player.getRays(i).getM()*-7000+ player.playerY);
				}
				
				//set ray 270 coordinates
				player.getRays(270).X2 = player.playerX;
				player.getRays(270).Y2 = -7000;
			
				//if i is between 270 and 360
				if (i > 270 && i < 360) {
					
					//set ray coordinates
					player.getRays(i).X2 = 7000;
					player.getRays(i).Y2 =  (int) (player.getRays(i).getM()*7000+ player.playerY);
				}
				
				
			}
			
		
			//for loop j
			for(int j = 0; j <= player.rays.size()-1; j++) {
				
				//for loop i
				for(int i = 0; i <= player.walls.size() -1; i++) {
					
						// set coordinates
						x1 = player.walls.get(i).getX1();
						x2 = player.walls.get(i).getX2();
						y1 = player.walls.get(i).getY1();
						y2 = player.walls.get(i).getY2();
						x3 = player.getPlayerX();
						x4 = player.getRays(j).getX2();
						y3 = player.getPlayerY();
						y4 = player.getRays(j).getY2();
						
						//find t and u
						t = findT(x1, x2, x3, x4, y1, y2, y3, y4);
						u = findU(x1, x2, x3, x4, y1, y2, y3, y4);
					
				//System.out.println("t: " + t +  " u: " + u);
				
				//if line segments intersect
				if (t > 0 && t < 1 && u > 0 && u < 1){
					
					//try block
					try {
						
						//find intercepts
						xInter = (int) (x1 + t*(x2 -x1));
						yInter = (int) (y1 + + t*(y2-y1));
						
						//set ray coordinates
						player.getRays(j).X2 = xInter;
						player.getRays(j).Y2 = yInter;
					}catch(Exception E) {
						E.printStackTrace();
					}
					
				}
					
			}
				
		}
			
			//refresh the screen
			repaint();
			
	}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
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
	
	
	//board class extending JPanel
	public class Board extends JPanel{	
		
		
			/**********
			*name: paintComponent
			*date:2020-03-22
			*description:The following method is used to display the board
			*Input: user click
			*Output: calls upon various methods
			***************************/
			public void paintComponent(Graphics g) {
				
				//initialize coordinates
				int X1;
				int X2;
				int Y1;
				int Y2;
			   
				
				
				//System.out.println("size: " + player.rays.size());
				
				//for loop
				for(int i = 0; i <= player.rays.size() -1; i++) {
					
					//retrieve color and coordinates
					g.setColor(player.getRays(i).getColor());
					X1 = player.getRays(i).getX1();
					X2 = player.getRays(i).getX2();
					Y1 = player.getRays(i).getY1();
					Y2 = player.getRays(i).getY2();
	
					//draw line
					g.drawLine(X1,Y1, X2,Y2);
					
				}
				
				
				//used for debugging and testing
				//g.setColor(Color.yellow);
				//g.drawString("1", x1, y1);
				//g.drawString("2", x2, y2);
				//g.drawString("3", x3, y3);
				//g.drawString("4", x4, y4);
				//g.fillOval(player.getPlayerX()-5, player.getPlayerY()-5, 10, 10);
				
				//set color
				g.setColor(Color.white);
				
				//for loop
				for(int i = 0; i <= player.walls.size() -1; i++) {
					
					//try block 
					try {
						
						//draw line
						g.drawLine(player.walls.get(i).getX1(), player.walls.get(i).getY1(),player.walls.get(i).getX2(),player.walls.get(i).getY2());
					}catch(Exception E) {
						
					}
				}
				
			
				
			}
	}
	
	
	
}
