package gameObject;

import system.IntToImage;
import game.Game;

public class Player_Mami extends Player{

	public Player_Mami(int x, int y, Game game) {
		super(x, y, game);
		pVoice=new MaVoice();
		
		run=new ImageSequence("/image/spriteSheet/actors/player/mami/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/player/mami/stand",7);
		damage=new ImageSequence("/image/spriteSheet/actors/player/mami/damage",5);		
		dead=new ImageSequence("/image/spriteSheet/actors/player/mami/dead",13);
		sequence.startSequence(stand);
		
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
