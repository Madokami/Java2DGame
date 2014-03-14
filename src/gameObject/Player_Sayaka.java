package gameObject;

import system.GameSystem;
import system.IntToImage;
import game.Game;

public class Player_Sayaka extends Player{

	public Player_Sayaka(int x, int y, Game game) {
		super(x, y, game);
		pVoice=new SaVoice();
		status = SpriteData.saStatus;
		setStatusImages();
		
		ssX=1;
		ssY=5;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, ssWidth, ssHeight);	
		
		soulGemSprite=SpriteData.gem_sayaka;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
	}
	public void useUltimate(){
		/*
		game.event1.startEvent(1000, "sayakaCutIn");
		//game.event2.startEvent(5000, "timeStop");
		playUltimateSound();
		GameSystem.musicPlayer.playSwoosh();
		*/
		GameSystem.musicPlayer.playSwoosh();
		controller.addEntity(new SaDash(xGridNearest,yGridNearest,game,this));
		startCharge(40,5);
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
