package system;

import game.Game;
import game.PlayerData;

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
		this.curLevel = game.getCurLevel();
		this.pData=game.getPlayerData();

	}

	public void loadGame(Game game) {
		game.setCurLevel(curLevel);
		game.setPlayerData(this.pData);
	}
}
