package game;

import game.Game.CHARACTER;
import game.Game.GameState;
import game.GameSystem.STATE;

import java.io.Serializable;
import java.util.LinkedList;

public class GameData implements Serializable{
	/*
	public LinkedList<FriendlyInterface> fi;
	public LinkedList<EnemyInterface> ei;
	public LinkedList<WallInterface> wi;
	public LinkedList<Fire> fireList;
	public LinkedList<PowerUps> powerUpList;
	public Controller c; 
	*/
	public int curLevel;
	public int enemyCount;
	public PlayerData pData;
	
	/*
	public boolean[][] wallArray;
	public LinkedList<Fire> f;
	private LinkedList<WallInterface> w = new LinkedList<WallInterface>();
	*/
	
	public void updateGameData(Game game){
		this.curLevel = game.curLevel;
		this.pData=game.pData;

	}

	public void loadGame(Game game) {
		game.curLevel = curLevel;
		game.pData=this.pData;
	}
}
