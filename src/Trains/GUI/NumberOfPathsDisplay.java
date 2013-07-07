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

public class NumberOfPathsDisplay extends GUI {

	private final JButton submit;
	private final JButton instructions;
	private final JButton newSolution;
	private final JButton differentSolution;
	private final JTextPane input;
	private final JScrollPane panel;
	private final JTextField output;
	private Controller control = Controller.getInstance();
	
	public NumberOfPathsDisplay() {
		
		UIDefaults defaults = javax.swing.UIManager.getDefaults();
		
		submit = new JButton("Submit");
		instructions = new JButton("Instructions");
		differentSolution = new JButton("Back");
		newSolution = new JButton("Create New List");
		
		input = new JTextPane();
		
		output = new JTextField() {

			private static final long serialVersionUID = -7610718763057746401L;

			@Override
			public void setBorder(Border border) { 
				// No more border for this component
			}
		};
		
		output.setEditable(false);
		output.setBackground(defaults.getColor(frame));
		
		panel = new JScrollPane(output);
		panel.setBackground(defaults.getColor(frame));
		
		// Make the font size larger for the input field
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
		instructions.addActionListener(new InstructionsListener());
		layout.setConstraints(instructions, constraints);
		frame.add(instructions);
		
		// Create new list
		constraints.gridx = 1;
		newSolution.addActionListener(new NewGraphListener());
		layout.setConstraints(newSolution,constraints);
		frame.add(newSolution);

		// Different Solution
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.EAST;
		differentSolution.addActionListener(new BackListener());
		layout.setConstraints(differentSolution,constraints);
		frame.add(differentSolution);
		
		
		// Input Field
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		constraints.anchor = GridBagConstraints.CENTER;
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
		submit.addActionListener(new SubmitListener());
		frame.add(submit);
		
		// Answer Box
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		layout.setConstraints(panel, constraints);
		frame.add(panel);
		
		// Refresh the frame
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
	
	public void updateAnswer(String answer) {
		UIDefaults defaults = javax.swing.UIManager.getDefaults();
		output.setBackground(defaults.getColor(frame));
		output.setText(answer);
		output.repaint();
	}
	
	public class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			control.numberOfPaths();
		}
		
	}
	
	public class BackListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			control.ChangeToSelectSolutionType();
		}
		
	}
	
	public class NewGraphListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			control.resetDatabase();
			control.ChangeToGetNodes();
		}
		
	}
	
	public class InstructionsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			control.ChangeToNumberOfPathInstructions();
		}
		
	}
	
}
