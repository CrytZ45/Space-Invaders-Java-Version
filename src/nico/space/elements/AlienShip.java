package nico.space.elements;

import java.awt.Rectangle;

import nico.space.utils.ResourceManager;

public class AlienShip extends ScreenElement {

	/**Creates an alien ship object at position x=900 y=20*/
	public AlienShip() {
		this.posX = 900;
		this.posY = 20;
	}
	
	/**Moves the ship left of 10 pixels*/
	public void move() {
		this.posX-=10;
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(posX, posY, ResourceManager.redShip.getWidth()*3, ResourceManager.redShip.getHeight()*3);
	}

}
