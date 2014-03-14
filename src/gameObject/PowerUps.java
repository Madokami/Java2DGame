package gameObject;

import game.Game;

public abstract class PowerUps extends GameObject{
	public String type;
	public PowerUps(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		return;
	}
	
	public abstract void applyEffect(Player player);
}
