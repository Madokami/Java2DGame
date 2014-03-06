package game;

import java.awt.image.BufferedImage;

public class Animate {
	public static void animate(GameObject o){
		if(o.direction.equals("up")){
			double count = o.i%o.frames;
			o.image=o.ss.grabImage((int)count + o.ssX,o.ssY+3,o.ssWidth,o.ssHeight);
			o.i+=o.MS;
		}
		else if(o.direction.equals("down")){
			double count = o.i%o.frames;
			o.image=o.ss.grabImage((int)count + o.ssX,o.ssY,o.ssWidth,o.ssHeight);
			o.i+=o.MS;
		}
		else if(o.direction.equals("left")){
			double count = o.i%o.frames;
			o.image=o.ss.grabImage((int)count + o.ssX,o.ssY+1,o.ssWidth,o.ssHeight);
			o.i+=o.MS;
		}
		else if(o.direction.equals("right")){
			double count = o.i%o.frames;
			o.image=o.ss.grabImage((int)count + o.ssX,o.ssY+2,o.ssWidth,o.ssHeight);
			o.i+=o.MS;
		}
		else if(o.direction.equals("stand")){
			return;
		}
		
	}
}
