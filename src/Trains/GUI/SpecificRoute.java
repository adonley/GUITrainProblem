package Trains.GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextPane;

import Trains.Controllers.Controller;

public class SpecificRoute extends GUI {

	private final JButton submit;
	private final JButton instructions;
	private final JTextPane input;
	private Controller control = Controller.getInstance();
	
	public SpecificRoute() {
		submit = new JButton("Submit");
		instructions = new JButton("Instructions");
		input = new JTextPane();
		
		// Make the font size larger for the input field
		// TODO make this global since it isthe same in two classes
		Font font = new Font(Font.SANS_SERIF,Font.BOLD,24);
		input.setFont(font);
	}
	
	public void show() {
		// So we don't have any layout collisions
		constraints = new GridBagConstraints();
		
		// Remove everything that was on that pane
		frame.getContentPane().removeAll();
		
		// Instructions
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		layout.setConstraints(instructions, constraints);
		frame.add(instructions);
		
		// Input Field
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		layout.setConstraints(input, constraints);
		frame.add(input);
		
		// Submit Button
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		layout.setConstraints(submit, constraints);
		frame.add(submit);
		
		// Refresh the frame
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
	
	public class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			control.parseRoute(input.getText().toString().toUpperCase());
		}
		
	}
	
}
