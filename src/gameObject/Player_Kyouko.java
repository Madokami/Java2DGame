package gameObject;

import system.IntToImage;
import game.Game;

public class Player_Kyouko extends Player{
	private int upAttackCounter=10;
	private boolean upAttacking;
	public Player_Kyouko(int x, int y, Game game) {
		super(x, y, game);

		run=new ImageSequence("/image/spriteSheet/actors/player/kyouko/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/player/kyouko/stand",8);
		damage=new ImageSequence("/image/spriteSheet/actors/player/kyouko/damage",4);		
		dead=new ImageSequence("/image/spriteSheet/actors/player/kyouko/dead",7);
		dead.scale(1.3);
		dead.setX(dead.getX()+15);
		dead.setY(dead.getY()-10);
		sequence.startSequence(stand);
		//moveUpGif=loader.loadGif("/image/spriteSheet/actors/player/kyouko/ky_jump.gif");
		//moveDownGif=loader.loadGif("/image/spriteSheet/actors/player/kyouko/ky_jump.gif");
		
		ssWidth=120;
		ssHeight=60;
		
		pVoice=new KyVoice();
		ssX=4;
		ssY=1;
		ss=SpriteData.char3;
		//image=ss.grabImage(ssX, ssY, ssWidth, ssHeight);
	
		soulGemSprite=SpriteData.gem_kyouko;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.kyStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
	}
	
	public void useUltimate(){
		upAttackCounter=0;
	}
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}

}
