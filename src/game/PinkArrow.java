package game;

public class PinkArrow extends Projectile{

	public PinkArrow(int x, int y, Game game,GameObject o) {
		super(x, y, game,o);
		BufferedImageLoader loader = new BufferedImageLoader();
		ss = SpriteData.magicalArraw;
		this.ssX=7;
		this.ssY=1;
		this.ssWidth=48;
		this.ssHeight=48;
		this.MS=1;
		if(o.orientation==ORIENTATION.RIGHT){
			velX=40;
			direction="right";
		}
		else if(o.orientation==ORIENTATION.LEFT){
			velX=-1*40;
			direction="left";
		}
		if(o.orientation==ORIENTATION.UP){
			velY=-1*40;
			direction="up";
		}
		if(o.orientation==ORIENTATION.DOWN){
			velY=40;
			direction="down";
		}
	}
	public void tick(){
		super.tick();
		Animate.animate(this);
	}

}
