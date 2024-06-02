package nico.space.utils;

import java.awt.Graphics2D;

import nico.space.Reference;
import nico.space.elements.Alien;

public class MapGenerator {

	private int map[][];
	
	public MapGenerator() {
		map = new int[][] {{2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0},
						   {2, 1, 1, 0, 0}};
	}
	
	/**Draws images of the aliens*/
	public void draw(Graphics2D g2d) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				
				if(!Reference.aliens[i][j].isDead()) {
					switch(map[i][j]) {
					
					case 0:
						if(Alien.getFrame() == 0)
							g2d.drawImage(ResourceManager.alien0, Reference.aliens[i][j].getPosX(), Reference.aliens[i][j].getPosY(), ResourceManager.alien0.getWidth()*3, ResourceManager.alien0.getHeight()*3, null);
						else
							g2d.drawImage(ResourceManager.alien0_1, Reference.aliens[i][j].getPosX(), Reference.aliens[i][j].getPosY(), ResourceManager.alien0_1.getWidth()*3, ResourceManager.alien0_1.getHeight()*3, null);
						break;
					
					case 1:
						if(Alien.getFrame() == 0)
							g2d.drawImage(ResourceManager.alien1, Reference.aliens[i][j].getPosX(), Reference.aliens[i][j].getPosY(), ResourceManager.alien1.getWidth()*3, ResourceManager.alien1.getHeight()*3, null);
						else
							g2d.drawImage(ResourceManager.alien1_1, Reference.aliens[i][j].getPosX(), Reference.aliens[i][j].getPosY(), ResourceManager.alien1_1.getWidth()*3, ResourceManager.alien1_1.getHeight()*3, null);
						break;
					
					case 2:
						g2d.drawImage(ResourceManager.alien2, Reference.aliens[i][j].getPosX(), Reference.aliens[i][j].getPosY(), ResourceManager.alien2.getWidth()*3, ResourceManager.alien2.getHeight()*3, null);
						break;
					}
				}
				else {
					if(Reference.aliens[i][j].getDeathDelay() > 0) {
						g2d.drawImage(ResourceManager.explosion, Reference.aliens[i][j].getPosX(), Reference.aliens[i][j].getPosY(), ResourceManager.explosion.getWidth()*3, ResourceManager.explosion.getHeight()*3, null);
						Reference.aliens[i][j].decreaseDeathDelay();
					}
				}
			}
		}
	}
	
	/**Returns the type of alien at a certain position
	 * @param x - The x coordinate of the position
	 * @param y - The y coordinate of the position*/
	public int getAlienType(int x, int y) {return map[x][y];}
}
