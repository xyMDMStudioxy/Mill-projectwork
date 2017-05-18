package de.projectwork.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.projectwork.game.Game;
import de.projectwork.game.Player;
import java.awt.Dimension;

/**
 * Gamebildschirm des Spiels.
 */
public class Gamescreen {
	
	public Game game;
	
	private Player playerBlack;
	private Player playerWhite;
	
	protected JFrame fGamescreen;
	protected JDialog fGameEndscreen;

	public Gamescreen() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initialize(); 
	    	}
		});
	}

	/**
	 * Initialisiert den Gamebildschirm und seine enthaltenen
	 * Komponenten. Der Gamebildschirm besteht aus zwei kleineren
	 * Bildschirmen. Der rechte ist der Gameboardscreen. Der linke
	 * ist der Gameinfoscreen.
	 */
	private void initialize() {
		/**
		 * Erstellt ein neues Spiel und zwei neue Spieler
		 */
		createNewGame();
		createPlayer();
		/**
		 * Frame des Gamebildschirms wird erstellt. Wird der X Button gedrückt wird die
		 * Anwendung komplett beendet. Das Fenster passt sich automatisch dem Bildschirm an.
		 */
		fGamescreen = new JFrame();
		fGamescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gameboardscreen gameboardscreen = new Gameboardscreen(fGamescreen, game);
		fGamescreen.getContentPane().add(gameboardscreen);
		fGamescreen.setMinimumSize(new Dimension(800, 500));
		fGamescreen.pack();
		fGamescreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Gameinfoscreen gameinfoscreen = new Gameinfoscreen(gameboardscreen, game, playerWhite, playerBlack);
		fGamescreen.getContentPane().add(gameinfoscreen);
		
		fGamescreen.setVisible(true);
	}
	
	/**
	 * Erstellt ein neues Spiel und initialisiert die Variablen.
	 */
	public void createNewGame() {
		game = new Game();
	}
	
	/**
	 * Erstellt zwei neue Spieler (schwarz und weiß).
	 */
	public void createPlayer() {
		playerBlack = new Player(false);
		playerWhite = new Player(true);
	}
}