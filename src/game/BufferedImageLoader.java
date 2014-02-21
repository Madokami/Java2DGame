package game;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage image;
	private Image gif;
	
	public BufferedImage loadImage(String path){
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	public Image loadGif(String path){
		path = getClass().getResource(path).getFile();
		path = URLDecoder.decode(path);
		gif = Toolkit.getDefaultToolkit().createImage(path);
		return gif;
	}
}
