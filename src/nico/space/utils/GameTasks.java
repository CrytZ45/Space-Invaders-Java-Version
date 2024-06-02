package nico.space.utils;

import java.util.ArrayList;
import java.util.Random;

import nico.space.Reference;
import nico.space.elements.Alien;
import nico.space.elements.AlienShip;
import nico.space.elements.Player;
import nico.space.elements.Projectile;
import nico.space.elements.Shield;
import nico.space.elements.Shield.Type;
import nico.space.gui.GameBoard;

public class GameTasks {

	private static int alienTimeDelay = 0;
	private static int playerTimeDelay = 0;
	private static int superShotDelay = 0;
	
	/**Increases by 1 alienTimeDelay and playerTimeDelay*/
	public static void changeDelays() {
		alienTimeDelay++;
		playerTimeDelay++;
		if(Reference.player.hasSuperShot()) superShotDelay++;
	}
	
	/**Getter Method*/
	public static int getAlienDelay() {return alienTimeDelay;}
	/**Getter Method*/
	public static int getPlayerDelay() {return playerTimeDelay;}
	/**Sets playerTimeDelay to 0*/
	public static void resetPlayerDelay() {playerTimeDelay=0;}
	
	/**Initializes Reference.aliens[][]*/
	public static void initAliens() {
		Reference.aliens = new Alien[Reference.alienColumns][Reference.alienRows];
		for(int i=0; i<Reference.alienColumns; i++) {
			for(int j=0; j<Reference.alienRows; j++) {
				Reference.aliens[i][j] = new Alien(200+i*45, 50+j*45);
			}
		}
		alienTimeDelay = 0;
	}
	
	/**Makes all aliens move*/
	public static void moveAllAliens() {
		//Moves all aliens in a direction
		if(alienTimeDelay==Alien.getMotionDelay()) {
			moveAllAliensSideways();
			alienTimeDelay=0;
		}
		
		//Checks if the last alien needs to change direction
		if(Reference.aliens[0][0].getDir()>0) {
			if(Reference.aliens[Reference.alienColumns-1][Reference.alienRows-1].checkDir())
				moveAllAliensDown();
		}
		else {
			if(Reference.aliens[0][0].checkDir())
				moveAllAliensDown();
		}
	}
	
	/**Calls alien[i][j].moveSideways() on every alien*/
	private static void moveAllAliensSideways() {
		for(int i=0; i<Reference.alienColumns; i++) {
			for(int j=0; j<Reference.alienRows; j++) {
				try {
					Reference.aliens[i][j].moveSideways();
				} catch (NullPointerException e) {
					continue;
				}
			}
		}
		Alien.changeFrame();
	}
	
	/**Calls alien[i][j].moveDownwards() on every alien*/
	private static void moveAllAliensDown() {
		for(int i=0; i<Reference.alienColumns; i++) {
			for(int j=0; j<Reference.alienRows; j++) {
				try {
					Reference.aliens[i][j].moveDownwards();
				} catch (NullPointerException e) {
					continue;
				}
			}
		}
	}
	
	/**Move all the projectiles on the screen*/
	public static void movePrjs() {
		for(int i=0; i<Reference.projectiles.size(); i++) {
			Reference.projectiles.get(i).move();
			
			//Deletes projectile if outside of the screen
			if(Reference.projectiles.get(i).getPosY()<0 || Reference.projectiles.get(i).getPosY()>Reference.windowHeight-80) {
				Reference.projectiles.remove(i);
			}
		}
	}
	
	/**Chooses a random alien and shots if is a valid one*/
	public static void alienShoot() {
		Random rand = new Random();
		int col = rand.nextInt(Reference.alienColumns*4);
		int row = rand.nextInt(Reference.alienRows*4);
		
		//Return if the chosen alien is dead or if choice is not valid
		if(col>=Reference.alienColumns || row>=Reference.alienRows) return;
		if(Reference.aliens[col][row].isDead()) return;
		
		//If the alien is the last of the column it shots
		if(row == 4) {
			Reference.projectiles.add(new Projectile(Reference.aliens[col][row].getPosX()+15, Reference.aliens[col][row].getPosY()+25, 2));
			return;
		}
		
		//If there is no alien below it shots
		if(Reference.aliens[col][row+1].isDead()) {
			Reference.projectiles.add(new Projectile(Reference.aliens[col][row].getPosX()+15, Reference.aliens[col][row].getPosY()+25, 2));
		}
	}
	
	/**Checks collision between an alien and a projectile*/
	public static boolean checkCollisionAlienPrj() {
		for(int i=0; i<Reference.alienColumns; i++) {
			for(int j=0; j<Reference.alienRows; j++) {
				for(int k=0; k<Reference.projectiles.size(); k++) {
					
					if(Reference.projectiles.get(k).getBoundingBox().intersects(Reference.aliens[i][j].getBoundingBox()) && !Reference.aliens[i][j].isDead()) {
						
						Reference.aliens[i][j].setDead(true);
						Reference.projectiles.remove(k);
						
						switch(Reference.map.getAlienType(i, j)) {
						case 0:
							GameBoard.addScore(10); break;
						case 1:
							GameBoard.addScore(20); break;
						case 2:
							GameBoard.addScore(30); break;
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**Checks collision between the player and a projectile
	 * @return true if the player was shot*/
	public static boolean checkCollisionPlayerPrj() {
		for(int i=0; i<Reference.projectiles.size(); i++) {
			if(Reference.projectiles.get(i).getBoundingBox().intersects(Reference.player.getBoundingBox())) {
				Reference.projectiles.remove(i);
				Reference.player.takeLife();
				return true;
			}
		}
		return false;
	}
	
	/**Checks collision between shields and projectiles*/
	public static void checkCollisionShieldPrj() {
		for(int i=0; i<Reference.shields.length; i++) {
			for(int j=0; j<Reference.projectiles.size(); j++) {
				if(Reference.projectiles.get(j).getBoundingBox().intersects(Reference.shields[i].getBoundingBox()) && Reference.shields[i].getDamage()>0) {
					Reference.shields[i].damage();
					Reference.projectiles.remove(j);
				}
			}
		}
	}
	
	/**Checks if the aliens position is low enough in the world to cause game over*/
	public static boolean checkAliensLanded() {
		for(int i=0; i<Reference.aliens.length; i++) {
			for(int j=0; j<Reference.aliens[i].length; j++) {
				if(Reference.aliens[i][j].getPosY() > 350 && !Reference.aliens[i][j].isDead()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**Generates a random number and if it is 0 it creates a red ship*/
	public static void redShipFlight() {
		Random rand = new Random();
		int r = rand.nextInt(750);
		
		if(Reference.redShip == null && r == 0) {
			Reference.redShip = new AlienShip();
			System.out.println("[GameTask]: Creating Red Ship");
		}
			
		if(Reference.redShip != null) {
			Reference.redShip.move();
			if(Reference.redShip.getPosX() < 0)
				Reference.redShip = null;
		}
	}
	
	public static void checkCollisionRedshipPrj() {
		for(int i=0; i<Reference.projectiles.size(); i++) {
			if(Reference.redShip !=null) {
				if(Reference.projectiles.get(i).getBoundingBox().intersects(Reference.redShip.getBoundingBox())) {
					Reference.redShip = null;
					Reference.player.setSuperShot(true);
				}
			}
		}
		if(superShotDelay == 350) Reference.player.setSuperShot(false);
	}

	/**Re-initializes every object*/
	public static void restart() {
		//init player
		Reference.player = new Player();
				
		//init aliens
		GameTasks.initAliens();
		
		//init projectiles list
		Reference.projectiles = new ArrayList<Projectile>();
		
		//init shields
		Reference.shields = new Shield[] {
				new Shield(70, 400, Type.TRIANGLE1),
				new Shield(100, 400, Type.SQUARE),
				new Shield(130, 400, Type.SQUARE),
				new Shield(160, 400, Type.TRIANGLE2),
				new Shield(70, 430, Type.SQUARE),
				new Shield(100, 430, Type.TRIANGLE3),
				new Shield(130, 430, Type.TRIANGLE4),
				new Shield(160, 430, Type.SQUARE),
				
				new Shield(270, 400, Type.TRIANGLE1),
				new Shield(300, 400, Type.SQUARE),
				new Shield(330, 400, Type.SQUARE),
				new Shield(360, 400, Type.TRIANGLE2),
				new Shield(270, 430, Type.SQUARE),
				new Shield(300, 430, Type.TRIANGLE3),
				new Shield(330, 430, Type.TRIANGLE4),
				new Shield(360, 430, Type.SQUARE),
				
				new Shield(470, 400, Type.TRIANGLE1),
				new Shield(500, 400, Type.SQUARE),
				new Shield(530, 400, Type.SQUARE),
				new Shield(560, 400, Type.TRIANGLE2),
				new Shield(470, 430, Type.SQUARE),
				new Shield(500, 430, Type.TRIANGLE3),
				new Shield(530, 430, Type.TRIANGLE4),
				new Shield(560, 430, Type.SQUARE),
				
				new Shield(670, 400, Type.TRIANGLE1),
				new Shield(700, 400, Type.SQUARE),
				new Shield(730, 400, Type.SQUARE),
				new Shield(760, 400, Type.TRIANGLE2),
				new Shield(670, 430, Type.SQUARE),
				new Shield(700, 430, Type.TRIANGLE3),
				new Shield(730, 430, Type.TRIANGLE4),
				new Shield(760, 430, Type.SQUARE)
		};
	}

	/**Sorts Reference.highscores*/
	public static void sortHighscores() {
		int appo;
		
		for(int i=0; i<Reference.highscores.length-1; i++) {
			for(int j=i+1; j<Reference.highscores.length; j++) {
				if(Reference.highscores[j]>Reference.highscores[i]) {
					appo = Reference.highscores[i];
					Reference.highscores[i] = Reference.highscores[j];
					Reference.highscores[j] = appo;
				}
			}
		}
		
	}
}
