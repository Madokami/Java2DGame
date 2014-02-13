package game;

public class Fire extends Object{
int strength;
long start;
	public Fire(int x, int y, Game game,int Strength) {
		super(x, y, game);
		super.ss=super.game.ss3;
		super.image=super.ss.grabImage(5, 15, 32, 32);
		this.strength = strength;
		start = System.currentTimeMillis();
	}
	public void tick(){
		super.tick();
		if(System.currentTimeMillis()-start>1000){
			super.game.e.removeFire(this);
		}
	}
	
	
	

}
