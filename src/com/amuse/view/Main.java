package com.amuse.view;

import java.awt.EventQueue;

import com.amuse.model.User;

public class Main {
	private static String token = "ab2db7600e498b986c69b15c3266b477e799a9d7";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm(new User(token));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
