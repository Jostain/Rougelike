package GameEngine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Announcer extends JPanel {
	ArrayList<Announcement> array = new ArrayList<>();
	public Announcer()
	{
		 setLayout(null);
	        Dimension d = new Dimension(1024,200);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
	        
	}
	public void broadcast(int sprite, int type)
	{
	   	
	}
	private class Announcement {
		private int sprite = 3;
		private int type = 0;

	}
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        

	    }
}
