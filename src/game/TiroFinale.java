package game;

import java.awt.Graphics;

public class TiroFinale extends Projectile{

	public TiroFinale(int x, int y, Game game, GameObject o) {
		super(x, y, game, o);
		ss=SpriteData.tiroFinale;
		ssWidth=192;
		ssHeight=192;
		this.ssX=1;
		ssY=1;
		image=ss.grabImage(1, 1, ssWidth, ssHeight);
	}
	public void tick(){
		return;
	}
	
	public void render(Graphics g){
		if(owner.orientation==ORIENTATION.RIGHT){
			g.drawImage(image, (int)x, (int)y, 620,image.getHeight(),null);
		}
	}

}
