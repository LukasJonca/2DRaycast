/*
2020-03-20
Lukas Jonca 
Version 1
Raycast main menu for picking demo
 */

//import java packages
package rayCasting;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import java.io.*;

public class MainMenu extends JFrame implements ActionListener{

	MainMenu(){
		
		//declare JMenuBar, JMenu, JMenuItem
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		
		//Initialize and setup the JMenuBar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//call display method
		display();
	}

	
	public void display(){
		
		//menu
		JButton Button;
		JPanel bottomPane;
		
		//initialize bottom pane
		bottomPane = new JPanel ();
		bottomPane.setLayout (new FlowLayout(5));
		
		//setup content pane
		Container contentPane = getContentPane();
		contentPane.removeAll();
		JPanel myJPanel = new JPanel();
		
	
		//Initialize top panel
		JPanel topPanel = new JPanel();
		
		
		//setup layout
		topPanel.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		topPanel.setBorder(new LineBorder(Color.GRAY, 2));
		bottomPane.setBorder(new LineBorder(Color.GRAY, 2));
		
		
		//add layout
		contentPane.add(Box.createRigidArea(new Dimension(1,5)));
		
				
		//add text
		JLabel L1 = new JLabel("Raycasting", JLabel.CENTER);
		L1.setFont(new Font ("Arial", Font.PLAIN, 24));
		L1.setForeground(Color.white);
		contentPane.add(L1, BorderLayout.NORTH);
		
		//add button pane
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		contentPane.setBackground(Color.black);
		buttonPane.setBackground(Color.black);
		
		
		//add single player button in center of screen
		buttonPane.add(Box.createRigidArea(new Dimension(0, 40)));
		Button = new JButton ("Line Segment");	
		Button.setAlignmentX(CENTER_ALIGNMENT);
		Button.addActionListener (this);
		Button.setBackground(Color.white);
		
		buttonPane.add(Button);
		
		//add two player button to center screen
		buttonPane.add(Box.createRigidArea(new Dimension(0, 40)));
		Button = new JButton ("2D-Raycast");	
		Button.setAlignmentX(CENTER_ALIGNMENT);
		Button.addActionListener (this);
		Button.setBackground(Color.white);
		buttonPane.add(Button);
				
		//add continue game button to center screen
		buttonPane.add(Box.createRigidArea(new Dimension(0, 40)));
		Button = new JButton ("Load Saved");	
		Button.setAlignmentX(CENTER_ALIGNMENT);
		Button.addActionListener (this);
		Button.setBackground(Color.white);
		buttonPane.add(Button);
		
				
		//add to content pane
		contentPane.add(buttonPane);
		
		//validate
		validate();
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		
		if(event.equals("Line Segment")) {
			
			try {
				//initialize and display line segment GUI
				lineSegment RaycastGUI = new lineSegment();
				RaycastGUI.setTitle("2D-Raycasting");
				RaycastGUI.setSize(700,700);
				RaycastGUI.setVisible(true);
				RaycastGUI.setBackground(Color.black);
				RaycastGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}catch(Exception E) {
				System.out.println("error launching");
			}
			
		}
		
		if(event.equals("2D-Raycast")) {
			try {
				//initialize and display 2D raycast GUI
				GUI RaycastGUI = new GUI();
				RaycastGUI.setTitle("2D-Raycasting");
				RaycastGUI.setSize(700,700);
				RaycastGUI.setVisible(true);
				RaycastGUI.setBackground(Color.black);
				RaycastGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}catch(Exception E) {
				System.out.println("error launching");
			}
		}
		
		if(event.equals("Load Saved")) {
			
			try {
				//initialize and display main menu GUI
				GUI RaycastGUI = new GUI();
				RaycastGUI.setTitle("2D-Raycasting");
				RaycastGUI.setSize(700,700);
				RaycastGUI.setVisible(true);
				RaycastGUI.setBackground(Color.black);
				RaycastGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				//load saved player
				RaycastGUI.player = RaycastGUI.loadPlayer("player.ser");
			}catch(Exception E) {
				System.out.println("error launching");
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Launching Main Menu");
		
		//initialize and display main menu GUI
		MainMenu homeGUI = new MainMenu();
		homeGUI.setTitle("Raycasting");
		homeGUI.setSize(700,500);
		homeGUI.setVisible(true);
		homeGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	

}
