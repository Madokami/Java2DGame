package gameObject;

import game.Game;

import java.awt.Font;
import java.awt.Graphics;

import system.GameSystem;
import system.IntToImage;

public class Player_Homura extends Player{
	public Player_Homura(int x, int y, Game game) {
		
		super(x, y, game);
		pVoice=new HoVoice();
		hp=100;
		bombStrength = 20;
		bombLength = 3;
		ssX=1;
		ssY=1;
		ss=SpriteData.char2;
		ssStand=SpriteData.hoStand;
		this.renderYShift=-20;
		image=ss.grabImage(ssX, ssY, ssWidth, ssHeight);
		
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
			game.event1.startEvent(2000, "timeStopCutIn");
			game.event2.startEvent(5000, "timeStop");
			GameSystem.musicPlayer.playSwoosh();
		}
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}

}
