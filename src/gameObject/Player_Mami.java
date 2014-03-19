package gameObject;

import system.IntToImage;
import game.Game;

public class Player_Mami extends Player{

	public Player_Mami(int x, int y, Game game) {
		super(x, y, game);
		pVoice=new MaVoice();
		ssX=1;
		ssY=1;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, ssWidth, ssHeight);
		
		animationParameters.setWalkGif(loader.loadGif("/image/spriteSheet/actors/player/mami/run.gif"));
		animationParameters.setStandGif(loader.loadGif("/image/spriteSheet/actors/player/mami/stand.gif"));
		animationParameters.setDamagedGif(loader.loadGif("/image/spriteSheet/actors/player/mami/damaged.gif"));
		animationParameters.setDeathGif(loader.loadGif("/image/spriteSheet/actors/player/mami/dead.gif"));
		
		soulGemSprite=SpriteData.gem_mami;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.maStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
	}
	public void useUltimate(){
		game.getController().addEntity(new TiroFinale(this.xGridNearest,yGridNearest,game,this));
		pVoice.playUltimateSound();
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
	
}
