package game;

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
			game.c.removeEntity(this);
			game.c.addEntity(new SpeedUp(this.xGridNearest,yGridNearest,game));
		}
	}
}
