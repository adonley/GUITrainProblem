package Trains.GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIDefaults;
import javax.swing.border.Border;

import Trains.Controllers.Controller;

public class GetNodeInstructions extends GUI {

	private final JButton differentSolution;
	private final JScrollPane panel;
	private final JTextPane output;
	private Controller control = Controller.getInstance();
	
	public GetNodeInstructions() {
		
		UIDefaults defaults = javax.swing.UIManager.getDefaults();
		
		differentSolution = new JButton("Back");
		
		output = new JTextPane() {

			private static final long serialVersionUID = -7610718763057746401L;

			@Override
			public void setBorder(Border border) { 
				// No more border for this component
			}
		};
		
		// Make the font size larger for the input field
		Font font = new Font(Font.SANS_SERIF,Font.BOLD,24);
		output.setEditable(false);
		output.setFont(font);
		output.setText("A valid graph is entered as follows:\n\n"
				+ "AB5, BC4, CD8\nDC8, DE6, AD5\nCE2, EB3, AE7\n\n"
				+ "This first letter represents the beginning station, the second character represents the ending station "
				+ "and the integer represents the length of the track. The stations can be separated with commas or newline characters (but don't use both for one entry). All other white space is ignored."
				+ "\n\nA file containing the same formatting can be read in by click on 'Choose File'");
		output.setBackground(defaults.getColor(frame));
		
		panel = new JScrollPane(output);
		panel.setBackground(defaults.getColor(frame));

	}
	
	public void show() {
		// So we don't have any layout collisions
		constraints = new GridBagConstraints();
		
		// Remove everything that was on that pane
		frame.getContentPane().removeAll();
		
		// Back Button
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 2;
		constraints.anchor = GridBagConstraints.WEST;
		differentSolution.addActionListener(new BackListener());
		layout.setConstraints(differentSolution,constraints);
		frame.add(differentSolution);
		
		// Instructions to the User
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(panel, constraints);
		frame.add(panel);
		
		// Refresh the frame
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
	
	public class BackListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			control.ChangeToGetNodes();
		}
		
	}
	
}

