package com.uchicom.tgo;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TrainFrame frame = new TrainFrame();
			frame.setVisible(true);
		});
	}
}
