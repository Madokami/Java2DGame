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
		
		
		//this.moveRightGif=loader.loadGif("/image/spriteSheet/actors/player/homura/run.gif");
		run=new ImageSequence("/image/spriteSheet/actors/player/homura/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/player/homura/stand",8);
		damage=new ImageSequence("/image/spriteSheet/actors/player/homura/damage",4);		
		dead=new ImageSequence("/image/spriteSheet/actors/player/homura/dead",14);
		ulty=new ImageSequence("/image/spriteSheet/actors/player/homura/ulty",7);
		ulty.setAnimationSpeed(0.3);
		sequence.startSequence(stand);
		
		pVoice=new HoVoice();
		hp=100;
		bombStrength = 20;
		bombLength = 3;
		
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
			sequence.startSequence(ulty, stand);
			//game.event1.startEvent(2000, "timeStopCutIn");
			game.event2.startEvent(5000, "timeStop");
			//GameSystem.musicPlayer.playSwoosh();
		}
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
	

}
