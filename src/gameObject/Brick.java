package gameObject;

import game.Game;

public class Brick extends GameObject{

	public Brick(int x, int y, Game game) {
		super(x, y, game);
		hp=30;
		ss=SpriteData.bricks;
		super.image=ss.grabImage(1,1,32,32);
	}
	public void tick(){
		super.tick();
		if(hp<=0){
			game.getController().removeEntity(this);
			game.getController().addEntity(new SpeedUp(this.xGridNearest,yGridNearest,game));
		}
	}
}
