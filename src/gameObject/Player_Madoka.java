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
		
		run=new ImageSequence("/image/spriteSheet/actors/player/madoka/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/player/madoka/stand",8);
		stand.scale(1.25);
		stand.setY(stand.getY()-10);
		damage=new ImageSequence("/image/spriteSheet/actors/player/madoka/damage",4);
		dead=new ImageSequence("/image/spriteSheet/actors/player/madoka/dead",11);
		sequence.startSequence(stand);
		
	}
	
	public void useUltimate(){
		if(this.mp>50){
			game.getController().addEntity(new Projectile_PinkArrow(xGridNearest,yGridNearest,game,this));
			mp-=50;
		}
	}
	public void useAbility1(){
		
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
