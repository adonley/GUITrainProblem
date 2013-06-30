package Trains.GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIDefaults;
import javax.swing.border.Border;

import Trains.Controllers.Controller;

/**
 * Creates the view to enable the user to input a list of train stations
 * to compute upon.
 * @author Andrew Donley
 *
 */
public class GetNodes extends GUI {
	
	private final JButton submitList;
	private final JButton submitFile;
	private final JTextPane input;
	private final JTextField directions;
	private final JScrollPane inputScroller;
	private Controller control = Controller.getInstance();
	

	/**
	 * Constructor calling the super constructor and setting some values for
	 * the GridBagLayout. Also, initializes the components to be displayed
	 * on the GetNodes page.
	 */
	public GetNodes() {
		super();
		
		// Need this for getting the system background color
		UIDefaults defaults = javax.swing.UIManager.getDefaults();
		
		submitList = new JButton("Submit List");
		submitFile = new JButton("Choose File");
		
		// Create a TextField so we can center the text
		//   don't want a border here.
		directions = new JTextField() {

			@Override
			public void setBorder(Border border) { 
				// No more border for this component
			}
		};
		input = new JTextPane();
		
		// Make the font size larger for the input field
		Font font = new Font(Font.SANS_SERIF,Font.BOLD,24);
		input.setFont(font);
		
		// Create directions and make them uneditable and centered
		directions.setHorizontalAlignment(JTextField.CENTER);
		directions.setEditable(false);
		directions.setBackground(defaults.getColor(frame));
		directions.setText("Input a Train List Manually or Choose a Train File to Submit");
		inputScroller = new JScrollPane(input);
		
		//chooser = new JFileChooser();
	}
	
	/**
	 * Displays the list view with an option to choose a file for input
	 */
	public void show() {
		
		// Just so we don't have any layout collisions
		constraints = new GridBagConstraints();
		frame.invalidate();
		
		// Remove everything from previous
		frame.getContentPane().removeAll();
		
		// Directions
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		layout.setConstraints(directions, constraints);
		frame.add(directions);
		
		// Select File
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		layout.setConstraints(submitFile, constraints);
		submitFile.addActionListener(new ChooseListener());
		frame.add(submitFile);
		
		// User Input
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		constraints.ipadx = 0;
		constraints.ipady = 0;
		layout.setConstraints(inputScroller, constraints);
		frame.add(inputScroller);
		
		// Submit User Input
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.ipadx = 0;
		constraints.ipady = 0;
		layout.setConstraints(submitList, constraints);
		// Add actionlistener for click
		submitList.addActionListener(new SubmitListener());
		frame.add(submitList);

		frame.validate();
		frame.repaint();
		
	}
	
	/**
	 * Simple action listener that passes the user created list to the controller
	 * @author Andrew Donley
	 *
	 */
	public class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get text from the input box
			// Parse the string
			if(control.parseString(input.getText()));
		}
		
	}
	
	/**
	 * Simple implementation of an actionlistener that sends a signal to the controller to change views
	 * @author Andrew Donley
	 *
	 */
	public class ChooseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Change view to the file chooser view
			control.ChangeToFileChooser();
		}
		
	}
	
}