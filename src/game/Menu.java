package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	public static Rectangle playButton = new Rectangle(Game.WIDTH/2 + 120, 150,100,50);
	public static Rectangle helpButton = new Rectangle(Game.WIDTH/2 + 120, 250,100,50);
	public static Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 120, 350,100,50);
	public static void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		
		Font f1 = new Font("arial",Font.BOLD,50);
		g.setFont(f1);
		g.setColor(Color.WHITE);
		g.drawString("Homuhomu Booom", Game.WIDTH/3, 100);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
	}
}
