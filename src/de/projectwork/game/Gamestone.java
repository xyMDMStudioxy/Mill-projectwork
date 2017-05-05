package de.projectwork.game;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Repräsentiert einen Spielstein.
 */
public class Gamestone {

	private boolean white;
	private Image image;
	
	public Gamestone(boolean white) {
		this.white = white;
		if (white) {
			image = new ImageIcon(this.getClass().getResource("/whiteGamestone.png")).getImage();
		}
		else {
			image = new ImageIcon(this.getClass().getResource("/blackGamestone.png")).getImage();
		}
	}
	
	/**************************************************
	 * Getter und Setter Methoden der Klasse Gamestone.
	 **************************************************/
	
	public Image getImage() {
		return image;
	}
	
	/**
	 * Gibt zurück ob der Spielstein weiß oder schwarz ist.
	 * @return true = weißer Spielstein
	 * @return false = schwarzer Spielstein
	 */
	public boolean getWhite() {
		return white;
	}
	
	public void setWhite(boolean white) {
		this.white = white;
	}

}