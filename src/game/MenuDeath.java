package game;

import game.GameSystem.STATE;
import game.Menu.MENUSTATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MenuDeath{
	private BufferedImageLoader loader;
	private BufferedImage dBackground, dText;
	public static enum DEATH{
		RESTART,
		BACKTOMENU,
	}
	
	public static DEATH dSelected = DEATH.RESTART;
	
	public MenuDeath(){
		loader = new BufferedImageLoader();
		dBackground = loader.loadImage("/image/gameOverBg.png");
		dText = loader.loadImage("/image/gameOverText.png");
	}
	public void tick(){
		GameSystem.turnOnBgm("/sound/death0.wav");	
	}
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.drawImage(dBackground,0, 0, GameSystem.ABSWIDTH+10, GameSystem.ABSHEIGHT+10,null);
		g.setFont(new Font("arial",Font.BOLD,45));
		g.setColor(Color.BLACK);
		g.drawImage(dText, GameSystem.ABSWIDTH-GameSystem.ABSWIDTH/3-110, 100,null);
		g.setFont(new Font("arial",Font.ITALIC,30));
		g.drawString("Restart Level",GameSystem.ABSWIDTH/2+100,300);
		g.drawString("Back to Menu",GameSystem.ABSWIDTH/2+100,400);
		renderSelected(g);
	}
	public void renderSelected(Graphics g) {
			if(dSelected==DEATH.RESTART){
				g.setColor(Color.RED);
				g.drawString("Restart Level",GameSystem.ABSWIDTH/2+100,300);
			}
			else if(dSelected==DEATH.BACKTOMENU){
				g.setColor(Color.RED);
				g.drawString("Back to Menu",GameSystem.ABSWIDTH/2+100,400);
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
				Menu.backToMenu();
			}
			else if(dSelected==DEATH.RESTART){
				Menu.toGameMode();
			}
		}
	}
	
	
	
	
}
