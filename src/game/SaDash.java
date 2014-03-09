package game;

import java.awt.Graphics;

public class SaDash extends Projectile{

	public SaDash(int x, int y, Game game, GameObject o) {
		super(x, y, game, o);
		ss=SpriteData.saDash;
		ssWidth=48;
		ssHeight=48;
		ssX=1;
		ssY=1;
		MS=1;
		frames=12;
		if(o.orientation==ORIENTATION.RIGHT){
			direction="right";
		}
		else if(o.orientation==ORIENTATION.LEFT){
			direction="left";
		}
		if(o.orientation==ORIENTATION.UP){
			direction="up";
		}
		if(o.orientation==ORIENTATION.DOWN){
			direction="down";
		}
		this.x=o.x;
		this.y=o.y;
	}
	public void tick(){
	
		if(owner.orientation==ORIENTATION.RIGHT){
			direction="right";
		}
		else if(owner.orientation==ORIENTATION.LEFT){
			direction="left";
		}
		if(owner.orientation==ORIENTATION.UP){
			direction="up";
		}
		if(owner.orientation==ORIENTATION.DOWN){
			direction="down";
		}
		this.x=owner.x;
		this.y=owner.y;
		Animate.animate(this);
	}
	public void render(Graphics g){
		g.drawImage(image, (int)(x-8), (int)(y-8), null);
	}
	

}
