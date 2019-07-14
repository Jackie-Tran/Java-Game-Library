package com.mikejack.engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;

public class Window {

	private JFrame frame;

	public Window(String title, int width, int height, int scale, GameContainer gc) {
		frame = new JFrame(title);
		Dimension s = new Dimension(width * scale, height * scale);
		frame.setPreferredSize(s);
		frame.setMaximumSize(s);
		frame.setMinimumSize(s);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(gc);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.toFront();
		frame.setState(Frame.NORMAL);
		frame.requestFocus();
	}

	public JFrame getFrame() {
	    return frame;
	}
	
	
}
