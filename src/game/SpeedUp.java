package game;

public class SpeedUp extends PowerUps{
	public SpeedUp(int x, int y, Game game) {
		super(x, y, game);
		ss = SpriteData.upgrades;
		image = ss.grabImage(11,19,size,size);
		type = "speed";
	}
	public void tick(){
		if(Physics.collide(this,game.p)){
			game.c.removeEntity(this);
			game.p.spd=game.p.spd+1;
			game.p.pData.saSpd++;
		}
	}

}
