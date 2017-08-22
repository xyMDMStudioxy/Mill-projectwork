package de.projectwork.game;

import javax.swing.JLabel;

/**
 * Repräsentiert ein Mühle Spiel.
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
	 * @return 1 = weißer Spieler
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
	 * Gibt zurück welcher Spieler gerade am Zug ist.
	 * @return 1 = weißer Spieler || 2 = schwarzer Spieler
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
	 * Es wird die aktuelle Spielphase bestimmt.
	 * @param gamePhase 1 = Setzphase || 2 = Verschiebenphase || 3 = Springphase
	 */
	public int getGamePhase() {
		return gamePhase;
	}
	
	/**
	 * Setzt den Runden Zähler und überprüft ob Phase 1 (Setzphase)
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
}