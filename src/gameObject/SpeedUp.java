package gameObject;

import game.Game;

public class SpeedUp extends PowerUps{
	public SpeedUp(int x, int y, Game game) {
		super(x, y, game);
		ss = SpriteData.upgrades;
		image = ss.grabImage(11,19,ssWidth,ssHeight);
		type = "speed";
	}
	public void tick(){
		if(Physics.collide(this,game.getPlayer())){
			controller.removeEntity(this);
			game.getPlayer().pVoice.playItemFoundSound();
			//game.p.pData.saSpd++;
		}
	}
	@Override
	public void applyEffect(Player player) {
		player.increaseSpeed(1);
		
	}

}
