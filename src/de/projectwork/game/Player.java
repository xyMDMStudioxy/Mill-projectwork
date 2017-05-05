package de.projectwork.game;

import javax.swing.JLabel;

/**
 * Repräsentiert einen Spieler.
 */
public class Player {

	private int counterGamestone;
	private boolean white;
	
	private final Gamestone gamestone[] = new Gamestone[9];
	
	public Player(boolean white) {
		this.white = white;
		this.counterGamestone = 9;
		for (int i = 0; i < counterGamestone; i++) {
			gamestone[i] = new Gamestone(white);
		}
	}
	
	/***********************************************
	 * Getter und Setter Methoden der Klasse Player.
	 ***********************************************/
	
	public int getCounterGamestone() {
		return counterGamestone;
	}
	
	/**
	 * Gibt zurück ob der Spieler weiß oder schwarz ist.
	 * @return true = weißer Spieler
	 * @return false = schwarzer Spieler
	 */
	public boolean getWhite() {
		return white;
	}
	
	/**
	 * Für jeden gesetzten Stein wird der Spielsteinzähler um eins verringert, wenn
	 * keine Spielsteine mehr vorhanden sind kann nicht mehr gesetzt werden.
	 * @param lCounterGamestone
	 */
	public void setCounterGamestone(JLabel lCounterGamestone) {
		if (counterGamestone > 0) {
			counterGamestone--;
			lCounterGamestone.setText("Anzahl Spielsteine: " + getCounterGamestone());
		} else {
			counterGamestone = 0;
		}
	}
}