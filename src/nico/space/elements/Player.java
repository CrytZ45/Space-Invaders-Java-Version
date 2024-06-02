package nico.space.elements;

import java.awt.Rectangle;

import nico.space.Reference;
import nico.space.utils.ResourceManager;

public class Player extends ScreenElement {

	public final int shotDelay = 10;
	private boolean superShot;
	
	private int lives;
	
	/**Creates player object and set its position*/
	public Player() {
		posX = Reference.windowWidth/2;
		posY = Reference.windowHeight-100;
		lives = 3;
		died = false;
		superShot = false;
	}
	
	
	/**Changes player position
	 * @param xDir - The number of pixels to move the player*/
	public void move(int xDir) {
		posX+=xDir;
	}
	
	
	/**Getter Method*/
	public int getLives() {return lives;}
	
	/**Increases lives amount by 1*/
	public void addLife() {
		if(lives < 9) lives++;
	}
	
	/**Decreases lives amount by 1*/
	public void takeLife() {lives--;}
	
	
	/**Setter Method*/
	public void setSuperShot(boolean superShot) {this.superShot=superShot;}
	
	/**Getter Method*/
	public boolean hasSuperShot() {return superShot;}

	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(posX, posY, ResourceManager.player.getWidth()*3, ResourceManager.player.getHeight()*3);
	}

	
}
