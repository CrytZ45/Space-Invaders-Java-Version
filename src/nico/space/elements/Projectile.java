package nico.space.elements;

import java.awt.Rectangle;

public class Projectile extends ScreenElement {
	
	private int yDir;
	
	/**Creates a projectile object
	 * @param posX - Position on X coordinate
	 * @param posY - Position on Y coordinate
	 * @param yDir - Negative if moving up, positive if moving down*/
	public Projectile(int posX, int posY, int yDir) {
		this.posX=posX;
		this.posY=posY;
		this.yDir=yDir;
	}
	
	/**Moves one projectile depending on its yDir*/
	public void move() {
		this.posY+=yDir;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(posX, posY, 2, 10);
	}
}
