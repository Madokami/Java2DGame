package game;

public abstract class Projectile extends MovableObject {

	public Projectile(int x, int y, Game game,GameObject o) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		super.tick();
		if(atEdge){
			game.c.removeEntity(this);
		}
	}

}
