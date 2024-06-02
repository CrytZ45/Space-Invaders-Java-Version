package nico.space;

import javax.swing.JFrame;

import nico.space.gui.GameBoard;

public class Main {

	private static JFrame window;
	private static GameBoard board;
	
	public static void main(String[] args) {
		
		createFrame();
		createGameBoard();
	}
	
	/**Creates a JFrame object*/
	private static void createFrame() {
		System.out.println("[Main]: Creating Frame");
		
		window = new JFrame(" Java Space Invaders");
		window.setVisible(true);
		window.setBounds(100, 100, Reference.windowWidth, Reference.windowHeight);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**Creates a GameBoard object and adds it to the JFrame*/
	private static void createGameBoard() {
		System.out.println("[Main]: Creating game board");
		
		board = new GameBoard();
		window.add(board);
		board.requestFocusInWindow();
	}
}
