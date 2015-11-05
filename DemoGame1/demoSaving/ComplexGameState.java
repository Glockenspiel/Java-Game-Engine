package demoSaving;

import saving.GameState;
import framework.Level;
import framework.Vector;

public class ComplexGameState extends GameState{

	private int playerX, playerY, playerHealth, playerMoney;
	
	
	public ComplexGameState(Level currentLevel, Vector playerPos, int playerHealth, int playerMoney) {
		super(currentLevel);
		playerX = playerPos.intX();
		playerY = playerPos.intY();
		this.playerHealth = playerHealth;
		this.playerMoney = playerMoney;
	}
	
	public Vector getPlayerPos(){
		return new Vector(playerX, playerY);
	}
	
	public int getHealth(){
		return playerHealth;
	}
	
	public int getMoney(){
		return playerMoney;
	}
}
