package nico.space.elements;

import java.awt.Rectangle;

public abstract class ScreenElement {

	protected int posX;
	protected int posY;
	
	protected boolean died;
	
	/**Getter Method*/
	public int getPosX() {return posX;}
	/**Getter Method*/
	public int getPosY() {return posY;}
	
	/**Getter Method
	 * @return True if alien is dead*/
	public boolean isDead() {return died;}
	
	/**Setter Method
	 * @param died - True if the alien is dead*/
	public void setDead(boolean died) {this.died=died;}
	
	/**Gets the rectangle around this element*/
	public abstract Rectangle getBoundingBox();
}
