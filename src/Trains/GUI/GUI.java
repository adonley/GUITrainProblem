package Trains.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

/**
 * Base class for GUI components in the Train problem solution.
 * @author Andrew Donley
 *
 */
public class GUI {
	
	/**
	 * Static JFrame to be shared throughout all GUI classes.
	 */
	static JFrame frame;
	
	static GridBagLayout layout;

	/**
	 * Static GridBagConstraints to be shared through all GUI classes for
	 * rendering new panes.	
	 */
	static GridBagConstraints constraints;
	
	/**
     * Create the GUI and show it.
     */
    public static void createAndShowGUI() {
    	
        //Create and set up the window.
        frame = new JFrame("Trains - Andrew Donley");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layout = new GridBagLayout();
        frame.setLayout(layout);
        frame.setPreferredSize(new Dimension(700,500));
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Initializes 'constraints' variable.
     */
    public GUI() {
    	//TODO Might want to remake this so when other GUI components call super
    	//  contraints does not get remade.
    	constraints = new GridBagConstraints();
    }

}