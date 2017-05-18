package de.projectwork.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Startbildschirm des Spiels.
 */
public class Startscreen implements ActionListener {

	private int startscreenWidth = 600;
	private int startscreenHeight = 400;
	
	private int bWidth = 150;
	private int bHeight = 30;
	
	private Image iStartscreen;
	
	private JLabel lStartscreen;
	
	private JButton bGameStart;
	private JButton bGameEnd;
	
	protected JFrame fStartscreen;

	/**
	 * Einstiegspunkt des Startbildschirms und der gesamten Anwendung.
	 */
	public static void main(String[] args) {
		try {
			Startscreen window = new Startscreen();
			window.fStartscreen.setVisible(true);
		} catch (Exception e) {
			System.err.println("Startbildschirm kann nicht gestartet werden!");
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt den Startbildschirm.
	 */
	public Startscreen() {
		initialize();
	}

	/**
	 * Initialisiert den Startbildschirm und seine enthaltenen
	 * Komponenten.
	 */
	private void initialize() {
		/**
		 * Frame des Startbildschirms wird erstellt und mittig auf dem
		 * Bildschirm ausgerichtet. Wird der X Button gedrückt wird die
		 * Anwendung komplett beendet. Das Fenster kann nicht vergrößert
		 * werden.
		 */
		fStartscreen = new JFrame();
		fStartscreen.setBounds(0, 0, startscreenWidth, startscreenHeight);
		fStartscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fStartscreen.setLocationRelativeTo(null);
		fStartscreen.setResizable(false);
		
		/**
		 *  Spiel starten Button wird erstellt und dem Frame hinzugefügt
		 *  und mittig ausgerichtet.
		 */
		bGameStart = new JButton("Spiel starten");
		fStartscreen.getContentPane().add(bGameStart);
		bGameStart.setBounds(startscreenWidth/2 - bWidth/2, startscreenHeight/2 - bHeight/2 - 40, bWidth, bHeight);
		bGameStart.setActionCommand("gameStart");
		bGameStart.addActionListener(this);
		
		/**
		 *  Spiel beenden Button wird erstellt und dem Frame hinzugefügt
		 *  und mittig unter Spiel starten Button ausgerichtet.
		 */
		bGameEnd = new JButton("Spiel beenden");
		fStartscreen.getContentPane().add(bGameEnd);
		bGameEnd.setBounds(startscreenWidth/2 - bWidth/2, startscreenHeight/2 - bHeight/2 + 10, bWidth, bHeight);
		bGameEnd.setActionCommand("gameEnd");
		bGameEnd.addActionListener(this);
		
		/**
		 * Label mit Holz Hintergrund wird dem Frame hinzugefügt.
		 */
		lStartscreen = new JLabel();
		iStartscreen = new ImageIcon(this.getClass().getResource("/titelBild.png")).getImage();
		lStartscreen.setIcon(new ImageIcon(iStartscreen));
		lStartscreen.setBounds(0, 0, startscreenWidth, startscreenHeight);
		fStartscreen.getContentPane().add(lStartscreen);
		
		/**
		 * Layout des Startbildschirms wird gesetzt, damit Buttons richtig
		 * angezeigt werden.
		 */
		fStartscreen.getContentPane().setLayout(null);
	}

	/**
	 * Steuert die Button Ereignisse des Startbildschirms.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("gameStart".equals(e.getActionCommand())) {
			@SuppressWarnings("unused")
			Gamescreen window = new Gamescreen();
			fStartscreen.setVisible(false);
			fStartscreen.dispose();
		}
		else if ("gameEnd".equals(e.getActionCommand())) {
			System.exit(1);
		}
	}
}