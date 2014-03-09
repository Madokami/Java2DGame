package game;

import java.awt.Graphics;

public class MrPringles extends Enemy{

	public MrPringles(int x, int y, Game game) {
		super(x, y, game);
		ss=SpriteData.mrPringles;
		ssWidth=48;
		ssHeight=48;
		ssX=1;
		ssY=1;
		frames=8;
		
		// TODO Auto-generated constructor stub
	}
	public void render(Graphics g){
		g.drawImage(image,(int)(x-8),(int)(y-8),null);
	}
	
}
