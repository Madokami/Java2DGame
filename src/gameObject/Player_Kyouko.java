package gameObject;

import system.IntToImage;
import game.Game;

public class Player_Kyouko extends Player{

	public Player_Kyouko(int x, int y, Game game) {
		super(x, y, game);
		pVoice=new KyVoice();
		ssX=4;
		ssY=1;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, ssWidth, ssHeight);
	
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
		
	}
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}

}
