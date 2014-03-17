package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import system.BufferedImageLoader;
import system.GameSystem;

public class StageRenderer {
	private int size = GameSystem.GRID_SIZE;
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage example = loader.loadImage("/image/stage/stage1/bara1.png");
	
	
	public void render1(Graphics g){
		
	}
	public void render2(Graphics g){
		g.drawImage(example, 1*size, 3*size,size,size, null);
		g.drawImage(example, 2*size, 3*size,size,size, null);	
		g.drawImage(example, 3*size, 3*size,size,size, null);	
		g.drawImage(example, 4*size, 3*size,size,size, null);	
	}
	public void render3(Graphics g){
		
	}
	public void render4(Graphics g){
		
	}
	public void render5(Graphics g){
		
	}
	public void render6(Graphics g){
		
	}
	public void render7(Graphics g){
			
		}
	public void render8(Graphics g){
		
	}
}
