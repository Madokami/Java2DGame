package game;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage img){
		this.image = img;
	}
	public BufferedImage grabImage(int row,int col, int width, int height){
		BufferedImage img = image.getSubimage((row*32-32), (col*32-32), width, height);
		return img;
	}
}
