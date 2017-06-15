package de.projectwork.game;

import javax.swing.JLabel;

/**
 * Repr�sentiert ein M�hle Spiel.
 */
public class Game {
	
	private int roundCounter;
	private int gamePhase;
	private int currentPlayer;
	
	public Game() {
		this.roundCounter = 0;
		this.gamePhase = 1;
		this.currentPlayer = 1;
	}

	/**
	 * Wechselt den Spieler.
	 * @return 1 = wei�er Spieler
	 * @return 2 = schwarzer Spieler
	 */
	public void changeCurrentPlayer() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
		} else {
			currentPlayer = 1;
		}
	}
	
	/*********************************************
	 * Getter und Setter Methoden der Klasse Game.
	 *********************************************/
	
	/**
	 * Gibt zur�ck welcher Spieler gerade am Zug ist.
	 * @return 1 = wei�er Spieler || 2 = schwarzer Spieler
	 */
	public int getCurrentPlayer() {
		if (currentPlayer == 1) {
			return 1;
		} else {
			return 2;
		}
	}

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
	}
	
	public void setRoundCounter(int roundCounter) {
		roundCounter++;
		this.roundCounter = roundCounter;
	}
	
	/**
	 * Es wird eine neue Spielphase gesetzt.
	 * @param gamePhase 1 = Setzphase || 2 = Verschiebenphase || 3 = Springphase
	 */
	public void setGamePhase(int gamePhase) {
		this.gamePhase = gamePhase;
	}
	
	/**
	 * Es wird die aktuelle Spielphase bestimmt.
	 * @param gamePhase 1 = Setzphase || 2 = Verschiebenphase || 3 = Springphase
	 */
	public int getGamePhase() {
		return gamePhase;
	}
}