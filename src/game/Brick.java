package game;

public class Brick extends Object implements WallInterface{

	public Brick(int x, int y, Game game) {
		super(x, y, game);
		ss=SpriteData.bricks;
		super.image=ss.grabImage(1,1,32,32);
	}
	public void tick(){
		if(Physics.hitByAttack(this, game.fireList)!=-1){
			game.e.removeEntity(this);
			game.c.addEntity(new SpeedUp(xGridNearest,yGridNearest,game));
		}
	}
}
