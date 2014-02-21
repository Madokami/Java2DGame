package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Fire{
int strength;
long start;
SpriteSheet ss;
double x;
double y;

Game game;
BufferedImage image;
	public Fire(int x, int y, Game game,int Strength) {
		this.game = game;
		this.x=(x-1)*GameSystem.SIZE;
		this.y=(y-1)*GameSystem.SIZE;
		ss=SpriteData.bricks;
		image=ss.grabImage(5, 15, 32, 32);
		this.strength = Strength;
		start = System.currentTimeMillis();
	}
	public void tick(){
		if(System.currentTimeMillis()-start>500){
			game.e.removeFire(this);
		}
	}
	public void render(Graphics g){
		g.drawImage(image, (int)x,(int)y,null);
	}
	public Rectangle getBounds(int width, int height){
		return new Rectangle((int)x,(int)y,width,height);
	}
	public int getStrength(){
		return strength;
	}
}
