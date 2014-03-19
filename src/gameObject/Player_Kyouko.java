package gameObject;

import system.IntToImage;
import game.Game;

public class Player_Kyouko extends Player{
	private int upAttackCounter=10;
	private boolean upAttacking;
	public Player_Kyouko(int x, int y, Game game) {
		super(x, y, game);
		upAttackGif=loader.loadGif("/image/spriteSheet/actors/player/kyouko/ky_up_attack.gif");
		moveRightGif=loader.loadGif("/image/spriteSheet/actors/player/kyouko/ky_walkRight2.gif");
		moveLeftGif=loader.loadGif("/image/spriteSheet/actors/player/kyouko/ky_walkLeft.gif");
		moveUpGif=moveRightGif;
		moveDownGif=moveLeftGif;
		
		
		animationParameters.setWalkGif(loader.loadGif("/image/spriteSheet/actors/player/kyouko/run.gif"));
		animationParameters.setStandGif(loader.loadGif("/image/spriteSheet/actors/player/kyouko/stand.gif"));
		animationParameters.setDamagedGif(loader.loadGif("/image/spriteSheet/actors/player/kyouko/damaged.gif"));
		animationParameters.setDeathGif(loader.loadGif("/image/spriteSheet/actors/player/homura/dead.gif"));
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
	public void tick(){
		super.tick();
		Animate.animateWithGif(this);
		
	}
	public void useUltimate(){
		upAttackCounter=0;
	}
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}

}
