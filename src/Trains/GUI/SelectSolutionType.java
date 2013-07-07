package Trains.GUI;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Trains.Controllers.Controller;

public class SelectSolutionType extends GUI {
	
	private final JButton shortestRoute;
	private final JButton distanceRoute;
	private final JButton numberOfRoutes;
	private Controller control = Controller.getInstance();
	
	public SelectSolutionType() {
		shortestRoute =  new JButton("Find Shortest Route Between Stations");
		distanceRoute =  new JButton("Compute the Distance of a Specific Route");
		numberOfRoutes = new JButton("Find the Number of Routes to a Station with Distance with Distance < Max");
	}
	
	public void show() {
		
		// So we don't have any layout collisions
		constraints = new GridBagConstraints();
		
		// Remove everything that was on that pane
		frame.getContentPane().removeAll();
		
		// Shortest Route Button
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		layout.setConstraints(shortestRoute, constraints);
		shortestRoute.addActionListener(new ShortestRouteListener());
		frame.add(shortestRoute);
		
		// Distance of a Route Button
		constraints.gridy = 1;
		layout.setConstraints(distanceRoute, constraints);
		distanceRoute.addActionListener(new DistanceListener());
		frame.add(distanceRoute);
		
		// Number of Routes Button
		constraints.gridy = 2;
		layout.setConstraints(numberOfRoutes, constraints);
		numberOfRoutes.addActionListener(new NumberOfPathsListener());
		frame.add(numberOfRoutes);
		
		// Refresh the frame
		frame.invalidate();
		frame.validate();
		frame.repaint();
		
	}
	
	public class DistanceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			control.ChangeToSpecificRoute();
		}
		
	}
	
	public class ShortestRouteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			control.ChangeToShortestRoute();
		}
		
	}
	
	public class NumberOfPathsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			control.ChangeToNumberOfPaths();
		}
		
	}

}
