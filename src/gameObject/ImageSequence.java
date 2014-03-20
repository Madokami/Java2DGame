package gameObject;

import java.awt.image.BufferedImage;

import system.GameSystem;

public class ImageSequence {
	private int size = GameSystem.GRID_SIZE;
	private final double scale = GameSystem.GRID_SIZE*1.5;
	private final int yShift = GameSystem.GRID_SIZE/10;
	
	private BufferedImage[] images;
	private int x,y,width,height,frames;
	private double animationSpeed;
	public ImageSequence(String path,int frames){
		this.frames=frames;
		images=new BufferedImage[frames];
		for(int i=0;i<frames;i++){
			images[i]=GameSystem.loader.loadImage(path.concat(Integer.toString(i)).concat(".png"));
		}
		setDefaultValues();
	}
	
	public BufferedImage getImage(int index){
		return images[index];
	}
	
	public void setDefaultValues(){
		height=(int)scale;
		width=images[0].getWidth()*height/images[0].getHeight();
		x= (size-width)/2;
		y= size-height+yShift;
		animationSpeed=0.2;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	

	public int getHeight() {
		return height;
	}

	
	public int getFrames(){
		return frames;
	}

	public double getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(double animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	public void scale(double ratio){
		width*=ratio;
		height*=ratio;
	}
}
