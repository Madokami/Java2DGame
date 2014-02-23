package game;

import java.awt.Font;
import java.awt.Graphics;

public class Homura extends Player{
	public Homura(int x, int y, Game game) {
		
		super(x, y, game);
		hp=100;
		bombStrength = 20;
		bombLength = 3;
		ssX=1;
		ssY=1;
		ss=SpriteData.char2;
		image=ss.grabImage(ssX, ssY, size, size);
		ultCount=200;
		soulGem=loader.loadImage("/image/soulGemRed.png");
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
			playUltimateSound();
			game.event1.startEvent(2000, "timeStopCutIn");
			game.event2.startEvent(5000, "timeStop");
			game.musicPlayer.playSwoosh();
		}
	}
	public void playDeathSound(){
		int x = rand.nextInt(3);
		String url = "/sound/hoDeath";
		url=url.concat(Integer.toString(x)).concat(".wav");
		game.musicPlayer.playVoice(url);
	}
	public void playUltimateSound(){
		int x = rand.nextInt(3);
		String url = "/sound/hoUlt";
		url=url.concat(Integer.toString(x)).concat(".wav");
		System.out.println(url);
		game.musicPlayer.playVoice(url);
	}
	public void playDamagedSound(){
		
	}
	
}
