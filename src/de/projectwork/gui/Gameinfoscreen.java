package de.projectwork.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.projectwork.game.Game;
import de.projectwork.game.Player;

/**
 * Repräsentiert den rechten Bildschirmteil des Spielbildschirms.
 * Dieser Bildschirmteil stellt die Spielinformationen und die
 * Informationen über die beiden Spieler dar.
 */
public class Gameinfoscreen extends JPanel {

	private static final long serialVersionUID = -2395440719298208450L;

	private static Font font = new Font("Times New Roman", Font.BOLD, 18);
	
	public int boardOffset = 40;
	
	private Game game;
	
	private Player playerBlack;
	private Player playerWhite;
	
	private Gameboardscreen gameboardscreen;

	public Gameinfoscreen(Gameboardscreen gameboardscreen, Game game, Player playerWhite, Player playerBlack) {
		this.gameboardscreen = gameboardscreen;
		this.game = game;
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Die Informationen über das Spiel und die beiden Spieler werden angezeigt.
	 * Der Bildschirm und die enthaltenen Komponenten passen sich dynamisch an.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		setBounds(gameboardscreen.getBoardSize() + 20, 0 , gameboardscreen.getBoardSize(), gameboardscreen.getBoardSize());  // TODO 500 ersetzen durch Variablen.
		g.setFont(font);
		
		/**
		 * Anzahl der bisher gespielten Runden wird ausgegeben.
		 */
		g.drawString("Runde: " + game.getRoundCounter(), boardOffset, boardOffset);
		
		/**
		 * Werte des wei�en Spielers werden angezeigt: Anzahl verbleibender Spielsteine
		 */
		g.drawString("Spieler Weiss: " , boardOffset, boardOffset + 50);
		g.drawString("Anzahl Spielsteine: " + playerWhite.getCounterGamestone(), boardOffset, boardOffset + 80);
		
		/**
		 * Werte des schwarzen Spielers werden angezeigt: Anzahl verbleibender Spielsteine
		 */
		g.drawString("Spieler Schwarz: " , boardOffset, boardOffset + 250);
		g.drawString("Anzahl Spielsteine: " + playerBlack.getCounterGamestone(), boardOffset, boardOffset + 280);
	}
}