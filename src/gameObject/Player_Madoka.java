package gameObject;

import system.IntToImage;
import game.Game;

public class Player_Madoka extends Player{

	public Player_Madoka(int x, int y, Game game) {
		super(x, y, game);
		pVoice=new MdVoice();
		bombLength = 3;
		bombStrength= 20;
		hp=100;
		ssX=7;
		ssY=1;
		ss=SpriteData.char1;
		animationParameters.setWalkGif(loader.loadGif("/image/spriteSheet/actors/player/madoka/run.gif"));
		animationParameters.setStandGif(loader.loadGif("/image/spriteSheet/actors/player/madoka/stand.gif"));
		animationParameters.setDamagedGif(loader.loadGif("/image/spriteSheet/actors/player/madoka/damaged.gif"));
		animationParameters.setDeathGif(loader.loadGif("/image/spriteSheet/actors/player/madoka/dead.gif"));
		
		//standGif=loader.loadGif("/image/spriteSheet/mdStand3.gif");
		//ssWidth=38;
		//ssHeight=50;
		//this.renderYShift=-10;
		/*
		ss=SpriteData.mdRunning;
		ssWidth=36;
		ssHeight=48;
		ssX=1;
		ssY=1;
		collisionWidth=32;
		collisionHeight=32;
		frames=8;
		*/
		
		image=ss.grabImage(ssX, ssY, ssWidth, ssHeight);
		
		soulGemSprite=SpriteData.gem_madoka;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.mdStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
		// TODO Auto-generated constructor stub
	}
	
	public void useUltimate(){
		game.getController().addEntity(new Projectile_PinkArrow(xGridNearest,yGridNearest,game,this));
	}
	public void useAbility1(){
		
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
