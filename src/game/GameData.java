package game;

import game.Game.CHARACTER;
import game.Game.GameState;
import game.GameSystem.STATE;

import java.io.Serializable;
import java.util.LinkedList;

public class GameData implements Serializable{
	public boolean playerIsAlive;
	/*
	public LinkedList<FriendlyInterface> fi;
	public LinkedList<EnemyInterface> ei;
	public LinkedList<WallInterface> wi;
	public LinkedList<Fire> fireList;
	public LinkedList<PowerUps> powerUpList;
	public Controller c; 
	*/
	public int curLevel;
	public int lastStage;
	public int enemyCount;
	public GameState gState;
	public CHARACTER cChosen;
	
	public STATE state;
	
	public PlayerData pData;
	
	/*
	public boolean[][] wallArray;
	public LinkedList<Fire> f;
	private LinkedList<WallInterface> w = new LinkedList<WallInterface>();
	*/
	
	public void updateGameData(Game game){
		this.playerIsAlive=game.playerIsAlive;
		this.curLevel = game.curLevel;
		this.lastStage = game.lastStage;
		this.enemyCount = game.enemyCount;
		this.gState = game.gState;
		this.cChosen = game.cChosen;
		state = GameSystem.state;
		this.pData=game.pData;

	}

	public void loadGame(Game game) {
		game.playerIsAlive=playerIsAlive;
		game.curLevel = curLevel;
		game.lastStage = lastStage;
		game.enemyCount = enemyCount;
		//Game.gState=gState;
		Game.cChosen=cChosen;
		GameSystem.state=state;
		game.pData=this.pData;
	}
}
