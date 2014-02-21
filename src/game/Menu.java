package game;

import game.GameSystem.STATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
//Note: if the Menu class get too fat with information and gets confusing, you should create
//additional classes
public class Menu {
	//you can delete these 3 Rectangle objects. They are only used to track the position of text
	//However, I have changed menu input from mouse to keyboard because keyboard is a lot easier to code.
	public static Rectangle playButton = new Rectangle(GameSystem.WIDTH/2 + 120, 150,100,50);
	public static Rectangle helpButton = new Rectangle(GameSystem.WIDTH/2 + 120, 250,100,50);
	public static Rectangle quitButton = new Rectangle(GameSystem.WIDTH/2 + 120, 350,100,50);
	
	//the bufferedImageLoader is something that I made to save steps in loading images.
	BufferedImageLoader loader;
	BufferedImage start;
	BufferedImage menu;
	BufferedImage help;
	Game game;
	MouseInput in;
	Image gif;
	
	public Music musicPlayer;
	private boolean musicOn;
	
	//enum: only 1 state can be true at a time.
	//used to track state of game and change what gets rendered;
	public static enum MENUSTATE{
		MAIN,
		CHOOSECHAR,
		SAVE,
		LOAD,
		SETTING
	};
	
	//determines what menu option is currently selected by the user
	//"selected" options will light up
	public static enum SELECTED{
		STORY,
		ARCADE,
	};
	
	public static enum CHARACTER{
		MADOKA,
		HOMURA
	};
	
	//Initializes the two states.
	public static SELECTED selected = SELECTED.STORY;
	public static MENUSTATE mState = MENUSTATE.MAIN;
	public static CHARACTER cSelected = CHARACTER.MADOKA;
	
	public Menu(){
		loader = new BufferedImageLoader();
		//sample usage
		//NOTE: use loader.loadGif(path) to load .gif, or else doesn't work.
		start = loader.loadImage("/homu.png");
		menu = loader.loadImage("/menu.png");
		help = loader.loadImage("/help.png");
		//example .gif loading
		gif = loader.loadGif("/homura.gif");
		musicPlayer = new Music();
	}
	
	//this method is called automatically by GameSystem
	//use this method to update variables continuously
	//this method will be called exactly 60 times per second.
	public void tick(){
		if(!musicOn){
			//turnOnBgm also sets musicOn=true
			turnOnBgm();
		}
	}
	
	//this method draws stuff. this method will only activate when GameSystem.state is set to MENU
	//this method will be called continuously. 
	//NOTE: if you need to update a variable for example: update a position variable every time the game loop finish, do it in tick()
	// because tick() is designed to be ran 60 times per second, and if it lags during intense times, 
	//it will be ran bonus times later to make up for the lack of update.
	// on the other hand, the render method has no restrictions on how many times it's ran per second.
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Font f1 = new Font("arial",Font.BOLD,50);
		g.setFont(f1);
		g.setColor(Color.BLACK);
		
		g.drawImage(menu, 0, 0,GameSystem.ABSWIDTH,GameSystem.ABSHEIGHT, null);
		//could omit the width and height in drawString or drawImage
		g.drawString("Homuhomu Booom", GameSystem.WIDTH/3, 100);
		g.drawString("Story", playButton.x-5, playButton.y+47);
		g.drawString("Arcade", helpButton.x-2, helpButton.y+47);
		g.drawString("Quit", quitButton.x, quitButton.y+43);
		//make sure renderSelected is under renderCurrentState or else you won't see the changes
		renderCurrentState(g);
		renderSelected(g);
	}
	
	//this method simply filters what to draw out based on the current Menu.SELECTED state
	public void renderSelected(Graphics g){
		if(mState==MENUSTATE.MAIN){
			if(selected==SELECTED.STORY){
				g.setColor(Color.RED);
				g.drawString("Story", playButton.x-5, playButton.y+47);
			}
			else if(selected==SELECTED.ARCADE){
				g.setColor(Color.RED);
				g.drawString("Arcade", helpButton.x-2, helpButton.y+47);
			}
		}
		if(mState==MENUSTATE.CHOOSECHAR){
			if(cSelected == CHARACTER.MADOKA){
				g.setFont(new Font("arial",Font.ITALIC,25));
				g.setColor(Color.RED);
				g.drawString("Madoka", 150, 200);
			}
			else if(cSelected == CHARACTER.HOMURA){
				g.setFont(new Font("arial",Font.ITALIC,25));
				g.setColor(Color.RED);
				g.drawString("Homura", 150, 250);
			}
		}
	}
	//change what is render based on the current MENUSTATE
	public void renderCurrentState(Graphics g){
		if(mState == MENUSTATE.MAIN){
			return;
		}
		else if(mState == MENUSTATE.CHOOSECHAR){
			g.setFont(new Font("arial",Font.ITALIC,25));
			g.setColor(Color.BLACK);
			g.drawString("Madoka", 150, 200);
			g.drawString("Homura", 150, 250);
		}
	}
	
	//detects user keyboard input. the parameter "key" is passed down from GameSystem.keyPressed
	public void keyPressed(int key) {
		if(key==KeyEvent.VK_X){
			Menu.mState=Menu.MENUSTATE.MAIN;
		}
		//when "down" is pressed
		else if(key==KeyEvent.VK_DOWN){
			//if the current selected mode is story
			if(mState==MENUSTATE.MAIN){
				if(Menu.selected==Menu.SELECTED.STORY){
					//change the current selected mode to arcade
					Menu.selected=Menu.SELECTED.ARCADE;
					//hopefully find a better sound effect. this one is too long
					musicPlayer.playVoice("/sound/soundEffect1.wav");
				}
			}
			//in choose character mode
			else if(mState==MENUSTATE.CHOOSECHAR){
				if(cSelected == CHARACTER.MADOKA){
					cSelected=CHARACTER.HOMURA;
				}
			}
		}
		else if(key==KeyEvent.VK_UP){
			if(mState==MENUSTATE.MAIN){
				if(Menu.selected==Menu.SELECTED.ARCADE){
					Menu.selected=Menu.SELECTED.STORY;
					musicPlayer.playVoice("/sound/soundEffect1.wav");
				}
			}
			else if(mState==MENUSTATE.CHOOSECHAR){
				if(cSelected == CHARACTER.HOMURA){
					cSelected=CHARACTER.MADOKA;
				}
			}
		}
		//user has pressed "z"
		else if(key==KeyEvent.VK_Z){
			if(mState==MENUSTATE.MAIN){
				//if currently story mode is selected
				if(Menu.selected==Menu.SELECTED.STORY){
					toStoryMode();
				}
				//similarly if arcade mode is selected
				else if(Menu.selected==Menu.SELECTED.ARCADE){
					//change Menu state to CHOOSECHAR state.
					Menu.mState=Menu.MENUSTATE.CHOOSECHAR;
				}
			}
			else if(mState==MENUSTATE.CHOOSECHAR){
				if(cSelected == CHARACTER.MADOKA){
					Game.cChosen=Game.CHARACTER.MADOKA;
				}
				else if(cSelected == CHARACTER.HOMURA){
					Game.cChosen=Game.CHARACTER.HOMURA;
				}
				toGameMode();
			}
		}
		
	}
	private void toStoryMode() {
		turnOffBgm();
		GameSystem.state=STATE.STORY;
	}

	private void toGameMode() {
		turnOffBgm();
		GameSystem.state=STATE.GAME;
	}

	//2 ways to play bgm
	//first one plays the default bgm
	public void turnOnBgm(){
		musicOn=true;
		musicPlayer.playMusic("/sound/ariaOnG.wav");
	}
	//this will play the .wav file idicated given the url
	public void turnOnBgm(String url){
		musicOn=true;
		musicPlayer.playMusic(url);
	}
	public void turnOffBgm(){
		musicOn=false;
		musicPlayer.stopMusic();
	}
}
