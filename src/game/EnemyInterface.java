package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EnemyInterface {
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds(int width,int height);
	public double getX();
	public double getY();
}
