package gameObject;

import game.Game;

public class Enemy_Boss_1 extends Enemy{

	public Enemy_Boss_1(int x, int y, Game game) {
		super(x, y, game);
		ss=SpriteData.gertrud;
		ssWidth=96;
		ssHeight=96;
		imageWidth=96;
		imageHeight=96;
		this.collisionWidth=96;
		collisionHeight=96;
		this.collisionDamage=40;
		hp=200;
		image=ss.grabImage(1, 1, 96, 96);
		spd=20;
		// TODO Auto-generated constructor stub
	}
	

}
