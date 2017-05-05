package de.projectwork.game;

import javax.swing.JLabel;

/**
 * Repräsentiert ein Mühle Spiel.
 */
public class Game {
	
	private int roundCounter;
	
	public Game() {
		this.roundCounter = 0;
	}
	
	/**
	 * Gibt zurück welcher Spieler gerade am Zug ist.
	 * @return 1 = weißer Spieler
	 * @return 0 = schwarzer Spieler
	 */
	public int player() {
		if (roundCounter % 2 == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/*********************************************
	 * Getter und Setter Methoden der Klasse Game.
	 *********************************************/
	
	public int getRoundCounter() {
		return roundCounter;
	}
	
	/**
	 * Setzt den Runden Zähler und überprüft ob Phase 1 (Setzphase)
	 * abgeschlossen ist.
	 * @param lRoundCounter
	 */
	public void setRoundCounter(JLabel lRoundCounter) {
		roundCounter++;
		lRoundCounter.setText("Runde: " + getRoundCounter());
		if (roundCounter == 18) {
			// TODO Phase 2 beginnt.
		}
	}
}