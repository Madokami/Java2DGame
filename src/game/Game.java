package game;

import game.GameObject.ORIENTATION;
import game.GameSystem.STATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.LinkedList;

public class Game {
	public GameTimer timer;
	
	public GameData gData;
	public Player p;
	public BufferedImage background;
	public boolean playing;
	public boolean musicOn;
	public int duration = 3000;
	public BufferedImage hpGauge;

	public boolean playerIsAlive = true;
	public LinkedList<Bomb> bList;
	public LinkedList<Enemy> eList;
	public LinkedList<Brick> brickList;
	public LinkedList<Fire> fireList;
	public LinkedList<PowerUps> powerUpList;
	public LinkedList<Projectile> projectileList;
	
	public Controller c; 
	public int curLevel;
	public LevelLoader loader;
	public int lastStage=3;
	public int enemyCount;
	public boolean victory;
	
	private int shift = 428;
	
	public PlayerData pData;
	
	
	//handles ticking problems with respect to bomb explosion sound.
	//"time stop" ability + mass bombing leads to serious lag
	public int timePastSinceLastExplode=0;
	public static boolean explosionPlayed;
	
	
	public long renderStageStart;
	
	private boolean timeStop;
	private boolean stopTick;
	
	public TimedEvent event1; 
	public TimedEvent event2;
	
	GameSystem sys;
	BufferedImage cutIn;
	
	public static enum CHARACTER{
		MADOKA,
		HOMURA,
		SAYAKA,
		MAMI,
		KYOUKO,
	};
	
	public BufferedImageLoader l;
	
	public static enum GameState{
		PLAY,
		WAIT,
		LOAD,
	};
	public static GameState gState = GameState.WAIT;
	public static CHARACTER cChosen = CHARACTER.HOMURA;
	
	public Game(GameSystem sys){
		timer = new GameTimer();
		this.sys = sys;
		playing = false;
		loader = new LevelLoader(this);
		c = new Controller(this);
		curLevel=1;
		musicOn=false;
		event1 = new TimedEvent(this);
		event2= new TimedEvent(this);
		l = new BufferedImageLoader();
		//car = l.loadImage("/car.gif");
		cutIn = l.loadImage("/homuraCutIn.png");
		hpGauge = l.loadImage("/image/hpGauge.png");
		
		pData = new PlayerData();
		pData.loadDefaultValues();
		gData = new GameData();
		

	}

	public void loadLevel(){
		loader.load();
	}
	public void tick(){
		if(isWaiting()){
			loadLevel();
			renderStageTitle(duration);
			playing=true;
		}
		if(!isWaiting()){
			if(!playerIsAlive){
				setWait();
				goToDeath();
				return;
			}
			if(victory){
				curLevel++;
				if(curLevel>lastStage){
					curLevel=1;
				}
				p.updatePlayerData();
				gData.updateGameData(this);
				goToScore();
				saveGame();
				return;
			}
			
		}
		if(Game.gState==Game.GameState.PLAY){
			timer.tick();
			event1.tick();
			event2.tick();
			checkVictoryCondition();
			if(stopTick){
				return;
			}
			if(timeStop){
				if(stopTick){
					return;
				}
				p.tick();
				return;
			}
			c.tick();
		}
		if(explosionPlayed){
			if(timePastSinceLastExplode<10){
				timePastSinceLastExplode++;
				
			}
			else{
				GameSystem.musicPlayer.reloadExplosion();
				explosionPlayed=false;
				timePastSinceLastExplode=0;
			}
		}

		
	}
	public void renderStageTitle(int duration){
		loader.renderStart(duration);
		gState=GameState.LOAD;
	}
	public void updatePlaying(){
		if(!playerIsAlive){
			playing=false;
		}
	}
	public boolean isWaiting(){
		if(gState==GameState.WAIT){
			return true;
		}
		return false;
	}
	public void setWait(){
		gState=GameState.WAIT;
	}
	public void render(Graphics g){
	
			if(Game.gState==Game.GameState.LOAD){
				loader.render(g);
			}
			else if(Game.gState==Game.GameState.PLAY){
			g.drawImage(background, 0, 0,GameSystem.GAME_WIDTH+10,GameSystem.GAME_HEIGHT, null);
			c.render(g);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, GameSystem.GAME_HEIGHT, GameSystem.GAME_WIDTH+10, 106);
			event1.render(g);
			event2.render(g);
			renderPlayerHealth(g);
			renderPlayerMana(g);
			p.renderExp(g);;
			p.renderPlayerStatus(g);
			p.renderPlayerLevel(g);
			p.renderSoulGem(g);
			
			timer.render(g);
			}
		
	}
	public void timeStop(){
		timeStop = true;
	}
	public void stopTick(){
		stopTick = true;
	}
	public void removeTimedEvents(){
		stopTick = false;
		timeStop = false;
	}

	public void keyPressed(int key) {
		if(key==KeyEvent.VK_P){
			GameSystem.state=STATE.PAUSE;
		}
	if(gState==GameState.PLAY){
			if(key==KeyEvent.VK_C){
				p.useUltimate();
			}
			else if(key==KeyEvent.VK_Z){
				c.addEntity(new Bomb(p.xGridNearest,p.yGridNearest,this));
				
			}
			else if(key==KeyEvent.VK_X){
				p.kickBomb();
			}
				if(key==KeyEvent.VK_RIGHT){
					p.buttonReleased=false;
					p.moveRight();
				}
				else if(key==KeyEvent.VK_LEFT){
					p.buttonReleased=false;
					p.moveLeft();
				}
				else if(key==KeyEvent.VK_UP){
					p.buttonReleased=false;
					p.moveUp();
				}
				else if(key==KeyEvent.VK_DOWN){
					p.buttonReleased=false;
					p.moveDown();	
				}
			
		}
		
	}

	public void keyReleased(int key) {
	if(key==KeyEvent.VK_RIGHT&&p.orientation==ORIENTATION.RIGHT){
		p.buttonReleased=true;
	}
	else if(key==KeyEvent.VK_LEFT&&p.orientation==ORIENTATION.LEFT){
		p.buttonReleased=true;
	}
	else if(key==KeyEvent.VK_UP&&p.orientation==ORIENTATION.UP){
		p.buttonReleased=true;
	}
	else if(key==KeyEvent.VK_DOWN&&p.orientation==ORIENTATION.DOWN){
		p.buttonReleased=true;
	}
	/*
	if(gState==GameState.PLAY){
		if(!p.finishingMove){
			if(key==KeyEvent.VK_RIGHT){
				if(p.direction.equals("right")){
					p.buttonPressed=false;
					p.moveStop();
					//game.p.movable=false;
					//p.moveToNext(p.nextX,p.nextY);
				}
			}
			else if(key==KeyEvent.VK_LEFT){
				if(p.direction.equals("left")){
					p.buttonPressed=false;
					p.moveStop();
				//game.p.movable=false;
				//p.moveToNext(p.nextX,p.nextY);
				}
			}
			else if(key==KeyEvent.VK_UP){
				if(p.direction.equals("up")){
					p.buttonPressed=false;
					p.moveStop();
				//game.p.movable=false;
				//p.moveToNext(p.nextX,p.nextY);
				}
			}
			else if(key==KeyEvent.VK_DOWN){
				if(p.direction.equals("down")){
					p.buttonPressed=false;
					p.moveStop();
				//game.p.movable=false;
				//p.moveToNext(p.nextX,p.nextY);
				}
			}
		}
		}

		*/
	}
	
	public void goToMenu(){
		GameSystem.turnOffBgm();
		GameSystem.turnOnBgm("/sound/music/theme1.wav");	
		Menu.mState=Menu.MENUSTATE.MAIN;
		GameSystem.state=GameSystem.STATE.MENU;
	}
	public void goToDeath(){
		GameSystem.turnOffBgm();
		GameSystem.turnOnBgm("/sound/music/theme2.wav");
		Menu.mState=Menu.MENUSTATE.DEATH;
		GameSystem.state=GameSystem.STATE.MENU;
	}
	public void goToScore(){
		GameSystem.turnOffBgm();
		GameSystem.turnOnBgm("/sound/music/theme1.wav");	
		Menu.mState=Menu.MENUSTATE.SCORE;
		GameSystem.state=GameSystem.STATE.MENU;
	}
	public void renderPlayerHealth(Graphics g){
		g.drawImage(hpGauge, 90, GameSystem.ABSHEIGHT-75, null);
		g.setColor(Color.GREEN);
		g.fillRect(138, GameSystem.ABSHEIGHT-62, (int) (p.hp/p.maxHp*120), 7);
		g.setColor(Color.WHITE);
		g.drawRect(138, GameSystem.ABSHEIGHT-62, 120,7);
	}
	public void renderPlayerMana(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(118, GameSystem.ABSHEIGHT-27, (int) (p.mp/p.maxMp*120), 7);
		g.setColor(Color.WHITE);
		g.drawRect(118, GameSystem.ABSHEIGHT-27,120,7);
	}
	
	public void checkVictoryCondition(){
		if(enemyCount<=0){
			victory=true;
		}
	}
	
	public void saveGame(){
		try
	      {
			//String path = getClass().getResource("bin/save/game.ser").toString();
			//path = URLDecoder.decode(path);
			//File newFile = new File(path);"C:/Users/Attack on Majou/workspace/Java2DGame/res/save/game.ser"
			 GameData saveData = gData;
			 saveData.updateGameData(this);
			//game.p.pData.upDatePlayerData(game.p);
	         FileOutputStream fileOut = new FileOutputStream("system/save/game.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         //game.pData.upDatePlayerData(game.p);
	         out.writeObject(saveData);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /save/game.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
}
