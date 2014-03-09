package game;

import java.awt.Graphics;

public class AdelbertMini extends Enemy{
	public int counter=0;
	public int time=1;
	public AdelbertMini(int x, int y, Game game) {
		super(x, y, game);
		ss=SpriteData.adelbertMini;
		ssWidth=48;
		ssHeight=48;
		this.ssX=1;
		ssY=1;
		frames=9;
		
		// TODO Auto-generated constructor stub
	}
	
	public void tick(){
		super.tick();
		if(counter<time){
			counter++;
		}
		else{
			counter=0;
			setTime();
			startCharge(10,12);
		}
		Animate.animate(this);
		
	}
	public void render(Graphics g){
		g.drawImage(image,(int)(x-8),(int)(y-8),null);
	}
	public void setTime(){
		time=rand.nextInt(100);
	}
}
