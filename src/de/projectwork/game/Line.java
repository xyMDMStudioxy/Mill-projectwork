package de.projectwork.game;

/**
 * Eine Linie repräsentiert immer 3 nebeneinanderliegende Felder 
 * die eine Mühle bilden können.
 * Für Feld Informationen siehe: {@link de.projectwork.game.Field}
 */
public class Line {

	private Field field1;
	private Field field2;
	private Field field3;
	
	public Line(Field f1, Field f2, Field f3) {
		this.field1 = f1;
		this.field2 = f2;
		this.field3 = f3;
	}
	
	/**
	 * Prüft ob ein Spieler eine Mühle mit dem letzten gesetzten/verschobenen
	 * Spielstein gelegt hat.
	 * @return true = Mühle wurde gesetzt/verschoben.
	 * @return false = Keine Mühle wurde gesetzt/verschoben.
	 */
	public boolean checkMill() {
		/**
		 * Prüft ob Spieler weiß (getWhichPlayer() == 1) eine Mühle gelegt hat.
		 */
		if (field1.isOccupied() && field2.isOccupied() && field3.isOccupied() &&
				field1.getWhichPlayer() == 1 && field2.getWhichPlayer() == 1 && field3.getWhichPlayer() == 1) {
			System.out.println("Spieler Weiß hat eine Mühle");		// TODO Ausgabe durch Funktion ersetzen.
			return true;
		}
		/**
		 * Prüft ob Spieler schwarz (getWhichPlayer() == 2) eine Mühle gelegt hat.
		 */
		else if (field1.isOccupied() && field2.isOccupied() && field3.isOccupied() &&
				field1.getWhichPlayer() == 2 && field2.getWhichPlayer() == 2 && field3.getWhichPlayer() == 2) {
			System.out.println("Spieler Schwarz hat eine Mühle");	// TODO Ausgabe durch Funktion ersetzen.
			return true;
		/**
		 * Kein Spieler hat eine Mühle gelegt.
		 */
		} else {
			return false;
		}
	}
}