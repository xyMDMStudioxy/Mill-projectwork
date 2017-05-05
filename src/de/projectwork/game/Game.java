package de.projectwork.game;

import javax.swing.JLabel;

/**
 * Repr�sentiert ein M�hle Spiel.
 */
public class Game {
	
	private int roundCounter;
	
	public Game() {
		this.roundCounter = 0;
	}
	
	/**
	 * Gibt zur�ck welcher Spieler gerade am Zug ist.
	 * @return 1 = wei�er Spieler
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
	 * Setzt den Runden Z�hler und �berpr�ft ob Phase 1 (Setzphase)
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