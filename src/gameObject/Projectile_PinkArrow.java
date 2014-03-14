package gameObject;

import system.BufferedImageLoader;
import game.Game;

public class Projectile_PinkArrow extends Projectile{

	public Projectile_PinkArrow(int x, int y, Game game,GameObject o) {
		super(x, y, game,o);
		BufferedImageLoader loader = new BufferedImageLoader();
		ss = SpriteData.magicalArraw;
		this.ssX=1;
		this.ssY=1;
		this.ssWidth=48;
		this.ssHeight=48;
		this.MS=0.6;
		if(o.orientation==ORIENTATION.RIGHT){
			velX=20;
			direction="right";
		}
		else if(o.orientation==ORIENTATION.LEFT){
			velX=-1*20;
			direction="left";
		}
		if(o.orientation==ORIENTATION.UP){
			velY=-1*20;
			direction="up";
		}
		if(o.orientation==ORIENTATION.DOWN){
			velY=20;
			direction="down";
		}
	}
	public void tick(){
		super.tick();
		Animate.animate(this);
	}

}
