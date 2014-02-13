package game;

public class Brick extends Object implements WallInterface{

	public Brick(int x, int y, Game game) {
		super(x, y, game);
		super.ss = super.game.ss3;
		super.image=ss.grabImage(1,1,32,32);
	}
	public void tick(){
		
		if(Physics.hitByAttack(this, game.fireList)){
			game.c.removeEntity(this);
		
		}
	}
}
