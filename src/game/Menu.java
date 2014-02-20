package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class Menu {
	public static Rectangle playButton = new Rectangle(GameSystem.WIDTH/2 + 120, 150,100,50);
	public static Rectangle helpButton = new Rectangle(GameSystem.WIDTH/2 + 120, 250,100,50);
	public static Rectangle quitButton = new Rectangle(GameSystem.WIDTH/2 + 120, 350,100,50);
	BufferedImageLoader loader;
	BufferedImage start;
	BufferedImage menu;
	BufferedImage help;
	Game game;
	MouseInput in;
	
	public static enum MENUSTATE{
		MAIN,
		CHOOSECHAR,
		SAVE,
		LOAD,
		SETTING
	};
	public static enum SELECTED{
		STORY,
		ARCADE,
	};
	
	public static SELECTED selected = SELECTED.STORY;
	public static MENUSTATE mState = MENUSTATE.MAIN;
	
	public Menu(){
		loader = new BufferedImageLoader();
		start = loader.loadImage("/homu.png");
		menu = loader.loadImage("/menu.png");
		help = loader.loadImage("/help.png");
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Font f1 = new Font("arial",Font.BOLD,50);
		g.setFont(f1);
		g.setColor(Color.BLACK);
		g.drawImage(menu, 0, 0,GameSystem.WIDTH*GameSystem.SCALE,GameSystem.HEIGHT*GameSystem.SCALE, null);

		//g.drawImage(start,Game.WIDTH/2 + 120, 150,100,50,null);
		//g.drawImage(help, Game.WIDTH/2 + 120, 250,100,50, null);
		
		g.drawString("Homuhomu Booom", GameSystem.WIDTH/3, 100);
		//g2d.draw(playButton);
		//g2d.draw(helpButton);
		//g2d.draw(quitButton);
		g.drawString("Story", playButton.x-5, playButton.y+47);
		g.drawString("Arcade", helpButton.x-2, helpButton.y+47);
		g.drawString("Quit", quitButton.x, quitButton.y+43);
		renderSelected(g);
		renderCurrentState(g);
	}
	public void renderSelected(Graphics g){
		if(selected==SELECTED.STORY){
			g.setColor(Color.RED);
			g.drawString("Story", playButton.x-5, playButton.y+47);
		}
		else if(selected==SELECTED.ARCADE){
			g.setColor(Color.RED);
			g.drawString("Arcade", helpButton.x-2, helpButton.y+47);
		}
	}
	public void renderCurrentState(Graphics g){
		if(mState == MENUSTATE.MAIN){
			return;
		}
		else if(mState == MENUSTATE.CHOOSECHAR){
			g.fill3DRect(160, 200, 200, 50, true);
		}
	}
}
