package org.venipedia;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class WaitingWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaitingWindow window = new WaitingWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WaitingWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblHangOnA = new JLabel("Hang on a sec, downloading article names...");
		GridBagConstraints gbc_lblHangOnA = new GridBagConstraints();
		gbc_lblHangOnA.insets = new Insets(0, 0, 5, 0);
		gbc_lblHangOnA.gridx = 0;
		gbc_lblHangOnA.gridy = 0;
		frame.getContentPane().add(lblHangOnA, gbc_lblHangOnA);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 1;
		frame.getContentPane().add(progressBar, gbc_progressBar);
		progressBar.setIndeterminate(true);
		
		frame.pack();
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	
	public void kill(){
		setVisible(false);
		frame.dispose();
	}

}
