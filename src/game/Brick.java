package game;

public class Brick extends GameObject{

	public Brick(int x, int y, Game game) {
		super(x, y, game);
		hp=30;
		ss=SpriteData.bricks;
		super.image=ss.grabImage(1,1,32,32);
	}
	public void tick(){
		if(Physics.hitByAttack(this, game.fireList)!=-1){
			game.c.removeEntity(this);
			game.c.addEntity(new SpeedUp(xGridNearest,yGridNearest,game));
		}
		else if(hp<=0){
			game.c.removeEntity(this);
		}
	}
}
