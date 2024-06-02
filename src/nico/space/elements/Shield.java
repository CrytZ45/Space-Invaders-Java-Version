package nico.space.elements;

import java.awt.Rectangle;

import nico.space.utils.ResourceManager;

public class Shield extends ScreenElement {

	private int damageCount;
	private Type type;
	
	/**Creates a new shield
	 * @param posX - The x position
	 * @param posY - The y position
	 * @param type - The shape of the shield*/
	public Shield(int posX, int posY, Type type) {
		this.posX=posX;
		this.posY=posY;
		damageCount = 3;
		this.type=type;
	}
	
	/**Getter Method*/
	public Type getType() {return type;}
	/**Getter Method*/
	public int getDamage() {return damageCount;}
	
	/**Decreases damageCount by 1*/
	public void damage() {damageCount--;}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(posX, posY, ResourceManager.shieldSquareDmg0.getWidth()*3, ResourceManager.shieldSquareDmg0.getHeight()*3);
	}
	
	public enum Type{
		SQUARE,
		TRIANGLE1,
		TRIANGLE2,
		TRIANGLE3,
		TRIANGLE4
	}
}
