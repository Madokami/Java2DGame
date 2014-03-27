package game;
faofijewoifjaowiejp
import gameObject.Brick;
import gameObject.Controller;
import gameObject.Enemy;
import gameObject.Enemy_1_1;
import gameObject.Enemy_1_2;
import gameObject.Enemy_Boss_1;
import gameObject.HitableBrick;
import gameObject.PlaceHolder;
import gameObject.Player_Homura;
import gameObject.Player_Kyouko;
import gameObject.Player_Madoka;
import gameObject.Player_Mami;
import gameObject.Player_Sayaka;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URLDecoder;

import system.BufferedImageLoader;
import system.GameSystem;

public class LevelLoader {
	Game game;
	BufferedImageLoader loader;
	String title;
	private Image gif;
	private String path;
	private long renderStageStart;
	private int duration;
	
	public LevelLoader(Game game){
		this.game=game;
		loader = new BufferedImageLoader();
		//path = getClass().getResource("/witch1.gif").getFile();
		//path = URLDecoder.decode(path);
		//gif = Toolkit.getDefaultToolkit().createImage(path);
		gif=loader.loadGif("/witch1.gif");
	}
	public synchronized void load(){
		int stage = game.getCurLevel();
		reset();
		if(stage==1){
			stage1();
		}
		else if(stage==2){
			stage2();
		}
		else if(stage==3){
			stage3();
		}
		updateList();
	}
	public void stage1(){
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		//created the player depending on the player chosen state
		createPlayer(7,11);
		for(int i=0;i<GameSystem.GRIDW;i=i+3){
			game.getController().addEntity(new Enemy_1_1(i,0,game));
			game.setEnemyCount(game.getEnemyCount() + 1);
		}
		//adding bricks
		for(int i=2;i<GameSystem.GRIDW-2;i++){
			for(int j=2;j<8;j++){
			game.getController().addEntity(new HitableBrick(i,j,game));
			}
		}
	}
	
	
	public void stage2(){
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		/*
		createPlayer(1,2);
		for(int i=4;i<GameSystem.GRIDW-4;i++){
			game.c.addEntity(new AdelbertMini(i,3,game));
			game.enemyCount++;
			game.c.addEntity(new Brick(i,GameSystem.GRIDH-2,game));
		}
		*/
		int[][] data = new int[][]{
				{2,1,2,2,2,2,2,2,2,2},
				{2,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,2},
				{2,3,3,3,3,0,12,0,0,2},
				{2,2,0,0,12,12,12,2},
				{2,0}
		};
		game.setEnemyCount(1);
		this.loadFromArray(data);
	}
	public void stage3(){
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		createPlayer(10,12);
		game.getController().addEntity(new Enemy_Boss_1(10,2,game));
		game.setEnemyCount(game.getEnemyCount() + 1);
		/*
		for(int i=4;i<GameSystem.GRIDW-4;i++){
			game.c.addEntity(new AdelbertMini(i,3,game));
			game.enemyCount++;
		}
		*/
	}
	
	public void reset(){
		game.setVictory(false);
		game.setController(new Controller(game));
		game.setPlayerIsAlive(true);
		game.setEnemyCount(0);
	}
	public void updateList(){
		game.setBombList(game.getController().getBList());
		game.setEnemyList(game.getController().getEList());
		game.setBrickList(game.getController().getBrickList());
		game.setPlaceHolderList(game.getController().getPlaceHolderList());	
		game.setPowerUpList(game.getController().getPList());
		game.setFireList(game.getController().fireList);
		game.setProjectileList(game.getController().getProjectileList());
		
		game.setWallArray(game.getController().getWallArray());
		game.setBombArray(game.getController().getBombArray());
	}
	
	public void renderStart(int duration){
		this.renderStageStart = System.currentTimeMillis();
		this.duration = duration;
	}
	
	public void render(Graphics g){
		title = "Stage".concat(" ").concat(Integer.toString(game.getCurLevel()));
		g.drawImage(gif, 100, 100, null);
		g.setFont(new Font("arial",Font.BOLD,32));
		g.setColor(Color.RED);
		g.drawString(title, 270, 240);
		if(System.currentTimeMillis()-renderStageStart>duration){
			Game.gState=Game.GameState.PLAY;
		}
	}
	private void createPlayer(int i, int j) {
		if(game.cChosen==Game.CHARACTER.MADOKA){
			game.getController().createPlayer(new Player_Madoka(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.HOMURA){
			game.getController().createPlayer(new Player_Homura(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.SAYAKA){
			game.getController().createPlayer(new Player_Sayaka(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.MAMI){
			game.getController().createPlayer(new Player_Mami(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.KYOUKO){
			game.getController().createPlayer(new Player_Kyouko(i,j,game));
		}
		game.setPlayer(game.getController().getPlayer());
	}
	private void loadFromArray(int[][] mapData){
		for(int i=0;i<mapData.length;i++){
			for(int j=0;j<mapData[i].length;j++){
				if(mapData[i][j]==1){
					createPlayer(j+1,i+1);
				}
				else if(mapData[i][j]==11){
					addEnemy(new Enemy_1_1(j+1,i+1,game));
				}
				else if(mapData[i][j]==12){
					addEnemy(new Enemy_1_2(j+1,i+1,game));
					
				}
				else if(mapData[i][j]==10){
					addEnemy(new Enemy_Boss_1(j+1,i+1,game));
				}
				else if(mapData[i][j]==2){
					addBrick(new HitableBrick(j+1,i+1,game));
				}
				else if(mapData[i][j]==3){
					addPlaceHolder(new PlaceHolder(j+1,i+1,game));
				}
			}
		}
	}
	private void addEnemy(Enemy e){
		game.getController().addEntity(e);
		game.setEnemyCount(game.getEnemyCount() + 1);
	}
	private void addBrick(HitableBrick b){
		game.getController().addEntity(b);
	}
	private void addPlaceHolder(PlaceHolder b){
		game.getController().addEntity(b);
	}
}
