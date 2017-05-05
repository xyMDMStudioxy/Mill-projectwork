package de.projectwork.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameEndscreen extends JDialog implements ActionListener {

	private static final long serialVersionUID = 4604740785683010475L;
	
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	
	private static GameEndscreen dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new GameEndscreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GameEndscreen() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bYes = new JButton("Ja");
				bYes.setActionCommand("Yes");
				buttonPane.add(bYes);
				getRootPane().setDefaultButton(bYes);
			}
			{
				JButton bNo = new JButton("Nein");
				bNo.setActionCommand("No");
				buttonPane.add(bNo);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Yes".equals(e.getActionCommand())) {
			
		}
		else if ("No".equals(e.getActionCommand())) {
			dialog.setVisible(false);
		}
	}

}