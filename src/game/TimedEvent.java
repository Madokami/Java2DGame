package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import system.BufferedImageLoader;
import system.GameSystem;
import system.SpecialEffects;

public class TimedEvent {
	private long startTime;
	private long duration;
	private String eventName;
	private Game game;
	private SpecialEffects effect;
	
	
	private BufferedImageLoader loader;
	private BufferedImage homuraCutIn;
	private BufferedImage homuraCutInBg;
	private BufferedImage background;
	private BufferedImage cutInLarge;
	private BufferedImage madokaCutIn;
	private BufferedImage madokaCutInLarge;
	private BufferedImage pinkStripes;
	private BufferedImage blueBgTrans,blueBlob,whiteStripes,whiteStripes2,saCutIn1,saCutIn2;
	private Image gif;
	
	private double translate;
	private double imageTranslate;
	
	private boolean translateRight;
	private boolean hasEvent;
	private Random rand;
	
	public TimedEvent(Game game){
		this.game=game;
		loader = new BufferedImageLoader();
		homuraCutIn = loader.loadImage("/homuraCutIn.png");
		homuraCutInBg = loader.loadImage("/homuraCutInBg.png");
		background = loader.loadImage("/blueStripesTrans.png");
		cutInLarge= loader.loadImage("/homuraCutInLarge.png");
		gif = loader.loadGif("/image/particle.gif");
		pinkStripes=loader.loadImage("/image/pinkStripesTransparent.png");
		madokaCutIn=loader.loadImage("/image/madokaCutIn1.png");
		madokaCutInLarge=loader.loadImage("/image/madokaCutInLarge1.png");
		blueBgTrans = loader.loadImage("/image/blueBgTrans.png");
		blueBlob = loader.loadImage("/image/blueSlash.png");
		whiteStripes = loader.loadImage("/image/whiteStripes.png");
		whiteStripes2=loader.loadImage("/image/whiteStripes2.png");
		saCutIn1=loader.loadImage("/image/saUlt1.png");
		saCutIn2=loader.loadImage("/image/saUlt2.png");
		translate=1;
		imageTranslate=0;
		translateRight=true;
		hasEvent=false;
		rand=new Random();
		effect=new SpecialEffects();
	}
	
	public void tick(){
		if(!hasEvent()){
			return;
		}
		if(System.currentTimeMillis()-startTime>duration){
			removeEvent();
			game.removeTimedEvents();
			if(eventName.equals("sayakaCutIn")){
				translate = 1;
				imageTranslate=1;
				startEvent(1200,"sayakaCutIn2");
			}
			else if(eventName.equals("sayakaCutIn2")){
				translate = 1;
				imageTranslate=1;
				effect.startFadeWhite();
				startEvent(3000,"sayakaCutIn3");
			}
			return;
		}
		effect.tick();
		if(eventName.equals("timeStop")){
			game.timeStop();
		}
		else if(eventName.equals("timeStopCutIn")){
			game.stopTick();
			translateImage(background);
			translate = translate*1.23;
			if(translate>=100){
				translate=100;
			}
			//game.pauseMusic();
		}
		else if(eventName.equals("madokaCutIn")){
			game.stopTick();
			translateImage(background);
			translate = translate*1.23;
			if(translate>=100){
				translate=100;
			}
		}
		else if(eventName.equals("sayakaCutIn")){
			game.stopTick();
			translateImage(background);
			translate = translate*1.23;
			if(translate>=100){
				translate=100;
			}
		}
		else if(eventName.equals("sayakaCutIn2")){
			game.stopTick();
			translateImage(background);
			translate = translate*1.23;
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
		effect.render(g);
		if(eventName.equals("timeStopCutIn")){
			//g.drawImage(homuraCutInBg,5,5,GameSystem.ABSWIDTH,GameSystem.ABSHEIGHT,null);
			//g.setColor(Color.WHITE);
			//g.fillRect(0, 0, 700, 700);
			//g.drawImage(homuraCutIn,(int) (100-translate),0,null);
			g.drawImage(cutInLarge, (int) (-1*GameSystem.ABSWIDTH/2+translate), -1*GameSystem.ABSHEIGHT/3, null);
			g.drawImage(background, (int) (-1*imageTranslate-20),0,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial",Font.ITALIC,32));
			g.drawImage(homuraCutIn,(int) (GameSystem.ABSWIDTH/3-translate),0,null);
			g.drawString("TIME STOP", 50, 400);
		}
		else if(eventName.equals("madokaCutIn")){
			g.drawImage(madokaCutInLarge, (int) (-1*GameSystem.ABSWIDTH/2+translate+100), -1*GameSystem.ABSHEIGHT/3, null);
			g.drawImage(pinkStripes, (int) (-1*imageTranslate-20),0,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("arial",Font.ITALIC,32));
			g.drawImage(madokaCutIn,(int) (GameSystem.ABSWIDTH/3-translate+100),0,null);
			g.drawString("", 50, 400);
		}
		else if(eventName.equals("sayakaCutIn")){
			//Graphics2D g2D = (Graphics2D)g;
			//g.drawImage(blueBlob,rand.nextInt(GameSystem.ABSWIDTH)-blueBlob.getWidth()/2,rand.nextInt(GameSystem.ABSHEIGHT)-blueBlob.getWidth()/2,null);
			//g.drawImage(background, (int) (-1*imageTranslate-20),0,null);
			g.drawImage(blueBgTrans, 0,0,null);
			createStripes(g);
			g.drawImage(saCutIn1,(int) (GameSystem.ABSWIDTH/4-translate+100),(int) (-1*translate/3),null);
			//createStripes(g);
			//createStripes(g);
			//g.drawImage(blueBlob,rand.nextInt(GameSystem.ABSWIDTH),rand.nextInt(GameSystem.ABSHEIGHT),null);
			//g.drawImage(blueBlob,rand.nextInt(GameSystem.ABSWIDTH),rand.nextInt(GameSystem.ABSHEIGHT),null);
			
		}
		else if(eventName.equals("sayakaCutIn2")){
			g.drawImage(blueBgTrans, 0,0,null);
			createStripes(g);
			g.drawImage(saCutIn2,(int) (-100+translate),(int) (120+translate/3),null);
		}
	}
	public void createStripes(Graphics g){
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
			g.drawImage(whiteStripes,rand.nextInt(GameSystem.ABSWIDTH)-whiteStripes.getWidth()/2,(int) rand.nextInt(GameSystem.ABSHEIGHT+100)/2,null);
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
			imageTranslate=imageTranslate+80*2;
			if(width-imageTranslate>1000){
				return;
			}
			else{
				translateRight=false;
			}
		}
		else{
			imageTranslate=imageTranslate-80*2;
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
