package nico.space;

import java.util.ArrayList;

import nico.space.elements.Alien;
import nico.space.elements.AlienShip;
import nico.space.elements.Player;
import nico.space.elements.Projectile;
import nico.space.elements.Shield;

import nico.space.utils.MapGenerator;
import nico.space.utils.ResourceManager;

public class Reference {
	
	public static final int windowWidth = 900;
	public static final int windowHeight = 600;
	
	public static Player player;
	public static Alien aliens[][];
	public static MapGenerator map;
	public static ArrayList<Projectile> projectiles;
	public static Shield shields[];
	public static AlienShip redShip;
	
	public static final int alienRows = 5;
	public static final int alienColumns = 11;
	
	public static int[] highscores = ResourceManager.readHighscores();
}
