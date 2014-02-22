package game;

import game.GameSystem.STATE;
import game.Menu.MENUSTATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MenuDeath {
	private BufferedImage mdSelectOn,mdSelectOff,hoSelectOn,hoSelectOff,saSelectOn,saSelectOff,kySelectOn,kySelectOff,maSelectOn,maSelectOff;
	private int cSelectIndex;
	private int cSelectHeight,cSelectWidth;
	double yShift;
	private boolean shiftingDown;
	private BufferedImageLoader loader;
	private Music musicPlayer;
	private boolean musicOn;
	
	public static enum DEATH{
		RESTART,
		BACKTOMENU,
	}
	
	public static DEATH dSelected = DEATH.RESTART;
	
	public MenuDeath(){
		loader = new BufferedImageLoader();
		musicPlayer = new Music();
		mdSelectOn = loader.loadImage("/image/mdSelectOn.png");
		mdSelectOff = loader.loadImage("/image/mdSelectOff.png");
		hoSelectOn = loader.loadImage("/image/hoSelectOn.png");
		hoSelectOff = loader.loadImage("/image/hoSelectOff.png");
		maSelectOn = loader.loadImage("/image/maSelectOn.png");
		maSelectOff = loader.loadImage("/image/maSelectOff.png");
		saSelectOn = loader.loadImage("/image/saSelectOn.png");
		saSelectOff = loader.loadImage("/image/saSelectOff.png");
		kySelectOn = loader.loadImage("/image/kySelectOn.png");
		kySelectOff = loader.loadImage("/image/kySelectOff.png");
		cSelectIndex=(GameSystem.ABSWIDTH-5*mdSelectOn.getWidth())/6;
		cSelectWidth=mdSelectOn.getWidth();
		cSelectHeight = mdSelectOn.getHeight();
		yShift=1;
		shiftingDown=true;
		musicOn=false;
	}
	public void tick(){
		if(!musicOn){
			turnOnBgm("/sound/death0.wav");
		}
	}
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GameSystem.ABSWIDTH+10, GameSystem.ABSHEIGHT+10);
		g.setFont(new Font("arial",Font.BOLD,45));
		g.setColor(Color.BLACK);
		g.drawString("Game Over", GameSystem.ABSWIDTH/3, 100);
		g.setFont(new Font("arial",Font.ITALIC,30));
		g.drawString("Restart Level",GameSystem.ABSWIDTH/2,300);
		g.drawString("Back to Menu",GameSystem.ABSWIDTH/2,400);
		renderSelected(g);
	}
	private void renderSelected(Graphics g) {
			if(dSelected==DEATH.RESTART){
				g.setColor(Color.RED);
				g.drawString("Restart Level",GameSystem.ABSWIDTH/2,300);
			}
			else if(dSelected==DEATH.BACKTOMENU){
				g.setColor(Color.RED);
				g.drawString("Back to Menu",GameSystem.ABSWIDTH/2,400);
			} 		
	}
	
	public void keyPressed(int key) {
		if(key==KeyEvent.VK_DOWN){
			if(dSelected==DEATH.RESTART){
				dSelected=DEATH.BACKTOMENU;
			}
		}
		else if(key==KeyEvent.VK_UP){
			if(dSelected==DEATH.BACKTOMENU){
				dSelected=DEATH.RESTART;
			}	
		}
		else if(key==KeyEvent.VK_Z){
			if(dSelected==DEATH.BACKTOMENU){
				backToMenu();
			}
			else if(dSelected==DEATH.RESTART){
				toGame();
			}
		}
	}
	
	private void backToMenu() {
		turnOffBgm();
		Menu.mState=MENUSTATE.MAIN;
	}
	private void toGame() {
		turnOffBgm();
		Menu.turnOffBgm();
		GameSystem.state=STATE.GAME;
	}
	public void turnOnBgm(String url){
		musicOn=true;
		musicPlayer.playMusic(url);
	}
	public void turnOffBgm(){
		musicOn=false;
		musicPlayer.stopMusic();
	}
	public void playSwitch(){
		musicPlayer.playVoice("/sound/switch1.wav");
	}
	public void playConfirm(){
		musicPlayer.playVoice("/sound/choice2.wav");
	}
	public void playCancel(){
		musicPlayer.playVoice("/sound/cancel2.wav");
	}
}
