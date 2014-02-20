package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
	
	
	public BufferedImageLoader l;
	
	public static enum GameState{
		PLAY,
		WAIT,
		LOAD,
	};
	public static GameState gState = GameState.WAIT;
	
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
				musicPlayer.stop();
				musicOn=false;
				setWait();
				GameSystem.state=GameSystem.STATE.MENU;
				return;
			}
			if(enemyCount==0){
				curLevel++;
				setWait();
			}
			if(curLevel>lastStage){
				musicPlayer.stop();
				musicOn=false;
				setWait();
				curLevel=1;
				GameSystem.state=GameSystem.STATE.MENU;
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
			musicPlayer.playBattleMusic();
			musicOn=true;
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
			g.drawImage(background, 0, 0, null);
			c.render(g);
			e.render(g);
			event1.render(g);
			event2.render(g);
			}
		
	}
	public void timeStop(){
		timeStop = true;
	}
	public void stopTick(){
		stopTick = true;
	}
	public void pauseMusic(){
		musicPlayer.stop();
	}
	public void resumeMusic(){
		musicPlayer.resume();
	}
	public void removeTimedEvents(){
		resumeMusic();
		stopTick = false;
		timeStop = false;
	}
}
