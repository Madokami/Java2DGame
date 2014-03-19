package gameObject;

import game.Game;

import java.awt.Graphics;
import java.awt.Image;

import system.GameSystem;
import system.IntToImage;

public class Player_Homura extends Player{
	Image damaged;
	public Player_Homura(int x, int y, Game game) {
		
		super(x, y, game);
		
		this.moveRightGif=loader.loadGif("/image/spriteSheet/actors/player/homura/run.gif");
		this.moveLeftGif=loader.loadGif("/image/spriteSheet/actors/player/homura/run.gif");
		this.standGif=loader.loadGif("/image/spriteSheet/actors/player/homura/stand.gif");
		damaged = loader.loadGif("/image/spriteSheet/actors/player/homura/dead.gif");
		
		animationParameters.setWalkGif(loader.loadGif("/image/spriteSheet/actors/player/homura/run.gif"));
		animationParameters.setStandGif(loader.loadGif("/image/spriteSheet/actors/player/homura/stand.gif"));
		animationParameters.setDamagedGif(loader.loadGif("/image/spriteSheet/actors/player/homura/damaged.gif"));
		animationParameters.setDeathGif(loader.loadGif("/image/spriteSheet/actors/player/homura/dead.gif"));
		//this.moveRightGif=loader.loadGif("/image/spriteSheet/actors/player/homura/run.gif");
		pVoice=new HoVoice();
		hp=100;
		bombStrength = 20;
		bombLength = 3;
		ssX=1;
		ssY=1;
		ss=SpriteData.char2;
		ssStand=SpriteData.hoStand;
		this.renderYShift=0;
		image=ss.grabImage(ssX, ssY, ssWidth, ssHeight);
		image=damaged;
		
		soulGemSprite=SpriteData.gem_homura;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.hoStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		super.tick();
		Animate.animateWithGif(this);
	}
	public void render(Graphics g){
		super.render(g);
	}
	public void useUltimate(){
		if(mp-50<0){
			return;
		}
		else{
			mp=mp-50;
			pVoice.playUltimateSound();
			game.event1.startEvent(2000, "timeStopCutIn");
			game.event2.startEvent(5000, "timeStop");
			GameSystem.musicPlayer.playSwoosh();
		}
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
	

}
