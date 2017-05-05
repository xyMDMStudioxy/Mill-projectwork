package de.projectwork.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import de.projectwork.game.Field;
import de.projectwork.game.Game;
import de.projectwork.game.Line;
import de.projectwork.game.Player;
import java.awt.Dimension;

/**
 * Gamebildschirm des Spiels.
 */
public class Gamescreen implements ActionListener {
	
	private String font = "Courier New";
	
	private Game game;
	
	private Player playerBlack;
	private Player playerWhite;
	
	private final Field field[] = new Field[24];
	
	private final Line line[] = new Line[16];

	private JLabel lPlayerWhite;
	private JLabel lPlayerBlack;
	private JLabel lCounterGamestoneBlack;
	private JLabel lCounterGamestoneWhite;
	private JLabel lRoundCounter;
	
	//private JButton bGameEnd;			// TODO Button Spiel beenden implementieren
	
	protected JFrame fGamescreen;
	protected JDialog fGameEndscreen;

	/**
	 * Erstellt ein neues Spiel und zwei neue Spieler
	 */
	public Gamescreen() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initialize(); 
	    	}
		});
		createNewGame();
		createPlayer();
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

	/**
	 * Initialisiert den Gamebildschirm und seine enthaltenen
	 * Komponenten.
	 */
	private void initialize() {
		/**
		 * Frame des Gamebildschirms wird erstellt. Wird der X Button gedrückt wird die
		 * Anwendung komplett beendet. Das Fenster passt sich automatisch dem Bildschirm an.
		 */
		fGamescreen = new JFrame();
		fGamescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createFields();
		Gameboardscreen gameboardscreen = new Gameboardscreen(fGamescreen, field);
		fGamescreen.getContentPane().add(gameboardscreen);
		fGamescreen.setMinimumSize(new Dimension(600, 500));
		fGamescreen.pack();
		fGamescreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		/**
		 * Anzahl der bisher gespielten Runden wird ausgegeben.
		 */
		lRoundCounter = new JLabel("Runde: " + game.getRoundCounter());
		fGamescreen.getContentPane().add(lRoundCounter);
		lRoundCounter.setBounds(750, 20, 180, 30);
		lRoundCounter.setFont(new Font(font, Font.BOLD, 24));
		
		/**
		 * Werte des wei�en Spielers werden angezeigt: Anzahl verbleibender Spielsteine
		 */
		lPlayerWhite = new JLabel("Spieler Weiss: ");
		fGamescreen.getContentPane().add(lPlayerWhite);
		lPlayerWhite.setBounds(750, 70, 180, 30);
		lPlayerWhite.setFont(new Font(font, Font.BOLD, 16));
		lCounterGamestoneWhite = new JLabel("Anzahl Spielsteine: " + playerWhite.getCounterGamestone());
		fGamescreen.getContentPane().add(lCounterGamestoneWhite);
		lCounterGamestoneWhite.setBounds(750, 100, 180, 30);
		lCounterGamestoneWhite.setFont(new Font(font, Font.BOLD, 12));
		
		/**
		 * Werte des schwarzen Spielers werden angezeigt: Anzahl verbleibender Spielsteine
		 */
		lPlayerBlack = new JLabel("Spieler Schwarz: ");
		fGamescreen.getContentPane().add(lPlayerBlack);
		lPlayerBlack.setBounds(750, 270, 180, 30);
		lPlayerBlack.setFont(new Font(font, Font.BOLD, 16));
		lCounterGamestoneBlack = new JLabel("Anzahl Spielsteine: " + playerBlack.getCounterGamestone());
		fGamescreen.getContentPane().add(lCounterGamestoneBlack);
		lCounterGamestoneBlack.setBounds(750, 300, 180, 30);
		lCounterGamestoneBlack.setFont(new Font(font, Font.BOLD, 12));
		
		/**
		 *  Spiel beenden Button wird erstellt und dem Frame hinzugef�gt.
		 */
		/*bGameEnd = new JButton("Spiel beenden");
		fGamescreen.getContentPane().add(bGameEnd);
		bGameEnd.setBounds(750, 500, 150, 30);
		bGameEnd.setActionCommand("gameEnd");
		bGameEnd.addActionListener(this);*/
	
		fGamescreen.setVisible(true);
		createLines();
	}
	
	/**
	 * Spielfeld mit Felder wird aufgebaut und die Positionen
	 * der Felder werden in Prozent übergeben.
	 * siehe Beschreibung von Field: {@link de.projectwork.game.Field#Field(int, int)})	
	 */
	public void createFields() {
		field[0] = new Field(0, 0);
		field[1] = new Field(45, 0);
		field[2] = new Field(90, 0);
		field[3] = new Field(15, 15);
		field[4] = new Field(45, 15);
		field[5] = new Field(75, 15);
		field[6] = new Field(30, 30);
		field[7] = new Field(45, 30);
		field[8] = new Field(60, 30);
		field[9] = new Field(0, 45);
		field[10] = new Field(15, 45);
		field[11] = new Field(30, 45);
		field[12] = new Field(60, 45);
		field[13] = new Field(75, 45);
		field[14] = new Field(90, 45);
		field[15] = new Field(30, 60);
		field[16] = new Field(45, 60);
		field[17] = new Field(60, 60);
		field[18] = new Field(15, 75);
		field[19] = new Field(45, 75);
		field[20] = new Field(75, 75);
		field[21] = new Field(0, 90);
		field[22] = new Field(45, 90);
		field[23] = new Field(90, 90);
	}
	
	/**
	 * Linien werden aus Feldern erstellt.
	 * siehe Beschreibung von Line: {@link de.projectwork.game.Line}
	 */
	public void createLines() {
		line[0] = new Line(field[0], field[1], field[2]);
		line[1] = new Line(field[3], field[4], field[5]);
		line[2] = new Line(field[6], field[7], field[8]);
		line[3] = new Line(field[9], field[10], field[11]);
		line[4] = new Line(field[12], field[13], field[14]);
		line[5] = new Line(field[15], field[16], field[17]);
		line[6] = new Line(field[18], field[19], field[20]);
		line[7] = new Line(field[21], field[22], field[23]);
		line[8] = new Line(field[0], field[9], field[21]);
		line[9] = new Line(field[1], field[4], field[7]);
		line[10] = new Line(field[2], field[14], field[23]);
		line[11] = new Line(field[3], field[10], field[18]);
		line[12] = new Line(field[5], field[13], field[20]);
		line[13] = new Line(field[6], field[11], field[15]);
		line[14] = new Line(field[8], field[12], field[17]);
		line[15] = new Line(field[16], field[19], field[22]);
	}
	
	/**
	 * TODO Close Gamescreen
	 */
	public void closeGamescreen() {
		this.fGamescreen.setVisible(false);
		this.fGamescreen.dispose();
	}

	/**
	 * Steuert die Button Ereignisse des Spielbildschirms.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("gameEnd".equals(e.getActionCommand())) {
			GameEndscreen window = new GameEndscreen();
			window.setVisible(true);
		}
		/*for (int i = 0; i < field.length; i++) {
			String buttonnr = String.valueOf(i);
			if (buttonnr.equals(e.getActionCommand()) && field[i].isOccupied() == false) {
				game.setRoundCounter(lRoundCounter);
				if (game.player() == 1) {
					playerWhite.setCounterGamestone(lCounterGamestoneWhite);
					field[i].setWhichPlayer(game);
				} else {
					playerBlack.setCounterGamestone(lCounterGamestoneBlack);
					field[i].setWhichPlayer(game);
				}
				//field[i].setGamestone(game.getRoundCounter(), game);
				for (int j = 0; j < line.length; j++) {
					line[j].checkMill();
				}
			}
		}*/
	}
}