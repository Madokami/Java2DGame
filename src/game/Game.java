package game;

import game.GameSystem.STATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game {
	public Player p;
	public boolean playerIsAlive = true;
	public LinkedList<FriendlyInterface> fi;
	public LinkedList<EnemyInterface> ei;
	public LinkedList<WallInterface> wi;
	public LinkedList<Fire> fireList;
	public LinkedList<PowerUps> powerUpList;
	public Controller c; 
	public Explode e;
	public int curLevel;
	public LevelLoader loader;
	public BufferedImage background;
	public boolean playing;
	public Music musicPlayer;
	public int enemyCount;
	public int lastStage=2;
	public boolean musicOn;
	public int duration = 4000;
	public BufferedImage soulGem1;
	public BufferedImage soulGem2;
	public BufferedImage soulGem3;
	public BufferedImage soulGem4;

	
	
	
	//handles ticking problems with respect to bomb explosion sound.
	//"time stop" ability + mass bombing leads to serious lag
	public int timePastSinceLastExplode=0;
	public boolean explosionPlayed;
	
	
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
		this.sys = sys;
		musicPlayer = new Music();
		playing = false;
		loader = new LevelLoader(this);
		c = new Controller();
		e = new Explode(this);
		curLevel=1;
		musicOn=false;
		event1 = new TimedEvent(this);
		event2= new TimedEvent(this);
		l = new BufferedImageLoader();
		//car = l.loadImage("/car.gif");
		cutIn = l.loadImage("/homuraCutIn.png");
		soulGem1= l.loadImage("/image/soulGemQuaterDark.png");
		soulGem2= l.loadImage("/image/soulGemHalfDark.png");
		soulGem3= l.loadImage("/image/soulGemThreeQuaterDark.png");
		soulGem4= l.loadImage("/image/soulGemFullDark.png");

	}

	public void loadLevel(){
		loader.load(this);
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
			if(enemyCount==0){
				curLevel++;
				setWait();
			}
			if(curLevel>lastStage){
				setWait();
				curLevel=1;
				goToDeath();
				return;
			}
		}
		if(Game.gState==Game.GameState.PLAY){
			event1.tick();
			event2.tick();
			if(timeStop){
				if(stopTick){
					return;
				}
				p.tick();
				return;
			}
			c.tick();
			e.tick();
		}
		if(!musicOn){
			playMusic();
		}
		if(explosionPlayed){
			if(timePastSinceLastExplode<10){
				timePastSinceLastExplode++;
				
			}
			else{
				musicPlayer.reloadExplosion();
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
			g.drawImage(background, 0, 0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT-96, null);
			c.render(g);
			e.render(g);
			event1.render(g);
			event2.render(g);
			renderPlayerSoul(g);
			renderPlayerHealth(g);
			renderPlayerMana(g);
			renderSoulGemState(g);
			}
		
	}
	public void timeStop(){
		timeStop = true;
	}
	public void stopTick(){
		stopTick = true;
	}
	public void pauseMusic(){
		musicPlayer.stopMusic();
	}
	public void resumeMusic(){
		musicPlayer.resumeMusic();
	}
	public void removeTimedEvents(){
		resumeMusic();
		stopTick = false;
		timeStop = false;
	}

	public void keyPressed(int key) {
		if(key==KeyEvent.VK_P){
			GameSystem.state=STATE.PAUSE;
		}
		if(gState==GameState.PLAY){
			if(key==KeyEvent.VK_C){
				if(p.hasUltimate()){
					p.useUltimate();
				}
			}
			else if(key==KeyEvent.VK_Z){
				c.addEntity(new Bomb(p.xGridNearest,p.yGridNearest,this));
				
			}
		}
		if(!p.finishingMove){
			if(key==KeyEvent.VK_RIGHT){
				p.moveRight();
			}
			else if(key==KeyEvent.VK_LEFT){
				p.moveLeft();
			}
			else if(key==KeyEvent.VK_UP){
				p.moveUp();
			}
			else if(key==KeyEvent.VK_DOWN){
				p.moveDown();	
			}
			
		}
		
	}

	public void keyReleased(int key) {
		if(!p.finishingMove){
			if(key==KeyEvent.VK_RIGHT){
				if(p.direction.equals("right")){
					p.moveStop();
					//game.p.movable=false;
					p.moveToNext(p.nextX,p.nextY);
				}
			}
			else if(key==KeyEvent.VK_LEFT){
				if(p.direction.equals("left")){
				p.moveStop();
				//game.p.movable=false;
				p.moveToNext(p.nextX,p.nextY);
				}
			}
			else if(key==KeyEvent.VK_UP){
				if(p.direction.equals("up")){
				p.moveStop();
				//game.p.movable=false;
				p.moveToNext(p.nextX,p.nextY);
				}
			}
			else if(key==KeyEvent.VK_DOWN){
				if(p.direction.equals("down")){
				p.moveStop();
				//game.p.movable=false;
				p.moveToNext(p.nextX,p.nextY);
				}
			}
		}
		
	}
	public void goToMenu(){
		stopMusic();
		Menu.mState=Menu.MENUSTATE.MAIN;
		GameSystem.state=GameSystem.STATE.MENU;
	}
	public void goToDeath(){
		stopMusic();
		Menu.mState=Menu.MENUSTATE.DEATH;
		GameSystem.state=GameSystem.STATE.MENU;
	}
	public void playMusic(){
		musicPlayer.playBattleMusic();
		musicOn=true;
	}
	public void stopMusic(){
		musicPlayer.stopMusic();
		musicOn=false;
	}
	public void renderPlayerSoul(Graphics g){
		g.drawImage(p.soulGem,0,GameSystem.ABSHEIGHT-p.soulGem.getHeight(),null);
		g.setFont(new Font("arial",Font.PLAIN,15));
		g.drawString((Integer.toString((int)p.soul)), 30, GameSystem.ABSHEIGHT-p.soulGem.getHeight()+50);
	}
	public void renderPlayerHealth(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(100, GameSystem.ABSHEIGHT-80, 200, 20);
		g.setColor(Color.GREEN);
		g.fillRect(100, GameSystem.ABSHEIGHT-80, (int) (p.hp*2), 20);
		g.setColor(Color.WHITE);
		g.drawRect(100, GameSystem.ABSHEIGHT-80, 200, 20);
	}
	public void renderPlayerMana(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(100, GameSystem.ABSHEIGHT-50, 200, 20);
		g.setColor(Color.BLUE);
		g.fillRect(100, GameSystem.ABSHEIGHT-50, (int) (p.mp*2), 20);
		g.setColor(Color.WHITE);
		g.drawRect(100, GameSystem.ABSHEIGHT-50, 200, 20);
	}
	public void renderSoulGemState(Graphics g){
		if(1-p.soul/p.maxSoul>=0.25&&1-p.soul/p.maxSoul<0.5){
			g.drawImage(soulGem1, 0, GameSystem.ABSHEIGHT-p.soulGem.getHeight(),null);
		}
		else if(1-p.soul/p.maxSoul>=0.5&&1-p.soul/p.maxSoul<0.75){
			g.drawImage(soulGem2, 0, GameSystem.ABSHEIGHT-p.soulGem.getHeight(),null);
		}
		else if(1-p.soul/p.maxSoul>=0.75&&1-p.soul/p.maxSoul<1){
			g.drawImage(soulGem3, 0, GameSystem.ABSHEIGHT-p.soulGem.getHeight(),null);
		}
		else if(1-p.soul/p.maxSoul==1){
			g.drawImage(soulGem4, 0, GameSystem.ABSHEIGHT-p.soulGem.getHeight(),null);
		}
	}
}
