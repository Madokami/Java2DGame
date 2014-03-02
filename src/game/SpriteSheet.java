package game;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage img){
		this.image = img;
	}
	public BufferedImage grabImage(int x,int y, int width, int height){
		BufferedImage img = image.getSubimage((x*width-width), (y*height-height), width, height);
		return img;
	}
}
