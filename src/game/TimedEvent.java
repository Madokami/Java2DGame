package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URLDecoder;

public class TimedEvent {
	private long startTime;
	private long duration;
	private String eventName;
	private Game game;
	
	
	
	private BufferedImageLoader loader;
	private BufferedImage homuraCutIn;
	private BufferedImage homuraCutInBg;
	private BufferedImage background;
	private BufferedImage cutInLarge;
	private BufferedImage madokaCutIn;
	private BufferedImage madokaCutInLarge;
	private BufferedImage pinkStripes;
	private Image gif;
	
	private double translate;
	private double imageTranslate;
	
	private boolean translateRight;
	private boolean hasEvent;
	
	public TimedEvent(Game game){
		this.game = game;
		loader = new BufferedImageLoader();
		homuraCutIn = loader.loadImage("/homuraCutIn.png");
		homuraCutInBg = loader.loadImage("/homuraCutInBg.png");
		background = loader.loadImage("/blueStripesTrans.png");
		cutInLarge= loader.loadImage("/homuraCutInLarge.png");
		gif = loader.loadGif("/image/particle.gif");
		pinkStripes=loader.loadImage("/image/pinkStripesTransparent.png");
		madokaCutIn=loader.loadImage("/image/madokaCutIn.png");
		madokaCutInLarge=loader.loadImage("/image/madokaCutInLarge.png");
		translate=1;
		imageTranslate=0;
		translateRight=true;
		hasEvent=false;
	}
	
	public void tick(){
		if(!hasEvent()){
			return;
		}
		if(System.currentTimeMillis()-startTime>duration){
			removeEvent();
			game.removeTimedEvents();
			return;
		}
		if(eventName.equals("timeStop")){
			game.timeStop();
		}
		else if(eventName.equals("timeStopCutIn")){
			game.stopTick();
			translateImage(background);
			translate = translate*1.13;
			if(translate>=100){
				translate=100;
			}
			//game.pauseMusic();
		}
		else if(eventName.equals("madokaCutIn")){
			game.stopTick();
			translateImage(background);
			translate = translate*1.13;
			if(translate>=100){
				translate=100;
			}
		}
		
	}
	
	public void render(Graphics g){
		if(System.currentTimeMillis()-startTime>duration){
			translate = 1;
			imageTranslate=1;
			
			return;
		}
		//renders timeStop cutIn picture
		if(eventName.equals("timeStopCutIn")){
			//g.drawImage(homuraCutInBg,5,5,GameSystem.ABSWIDTH,GameSystem.ABSHEIGHT,null);
			//g.setColor(Color.WHITE);
			//g.fillRect(0, 0, 700, 700);
			//g.drawImage(homuraCutIn,(int) (100-translate),0,null);
			g.drawImage(cutInLarge, (int) (-400+translate), -200, null);
			g.drawImage(background, (int) (-1*imageTranslate-20),0,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial",Font.ITALIC,32));
			g.drawImage(homuraCutIn,(int) (100-translate),0,null);
			g.drawString("TIME STOP", 50, 400);
		}
		else if(eventName.equals("madokaCutIn")){
			g.drawImage(madokaCutInLarge, (int) (-400+translate), -200, null);
			g.drawImage(pinkStripes, (int) (-1*imageTranslate-20),0,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial",Font.ITALIC,32));
			g.drawImage(madokaCutIn,(int) (100-translate),0,null);
			g.drawString("", 50, 400);
		}
	}
	
	public void startEvent(long duration, String key){
		this.duration = duration;
		this.startTime=System.currentTimeMillis();
		eventName=key;
		hasEvent=true;
	}
	
	private void translateImage(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		if(translateRight){
			imageTranslate=imageTranslate+80;
			if(width-imageTranslate>800){
				return;
			}
			else{
				translateRight=false;
			}
		}
		else{
			imageTranslate=imageTranslate-80;
			if(width-imageTranslate<width-100){
				return;
			}
			else{
				translateRight=true;
			}
		}
	}
	private boolean hasEvent(){
		return this.hasEvent;
	}
	private void removeEvent(){
		hasEvent=false;
	}
}
