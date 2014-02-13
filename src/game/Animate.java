package game;

import java.awt.image.BufferedImage;

public class Animate {
	public static void animate(Player o){
		if(o.direction.equals("up")){
			double count = o.i%3;
			o.image=o.ss.grabImage((int)count + o.ssRow,o.ssCol+3,32,32);
			o.i+=o.MS;
		}
		else if(o.direction.equals("down")){
			double count = o.i%3;
			o.image=o.ss.grabImage((int)count + o.ssRow,o.ssCol,32,32);
			o.i+=o.MS;
		}
		else if(o.direction.equals("left")){
			double count = o.i%3;
			o.image=o.ss.grabImage((int)count + o.ssRow,o.ssCol+1,32,32);
			o.i+=o.MS;
		}
		else if(o.direction.equals("right")){
			double count = o.i%3;
			o.image=o.ss.grabImage((int)count + o.ssRow,o.ssCol+2,32,32);
			o.i+=o.MS;
		}
		else if(o.direction.equals("stand")){
			return;
		}
		
	}
	
	public static void animate(Enemy o){
		if(o.direction.equals("up")){
			double count = o.i%3;
			o.image=o.ss.grabImage((int)count + o.ssRow,o.ssCol+3,32,32);
			o.i+=o.MS;
		}
		else if(o.direction.equals("down")){
			double count = o.i%3;
			o.image=o.ss.grabImage((int)count + o.ssRow,o.ssCol,32,32);
			o.i+=o.MS;
		}
		else if(o.direction.equals("left")){
			double count = o.i%3;
			o.image=o.ss.grabImage((int)count + o.ssRow,o.ssCol+1,32,32);
			o.i+=o.MS;
		}
		else if(o.direction.equals("right")){
			double count = o.i%3;
			o.image=o.ss.grabImage((int)count + o.ssRow,o.ssCol+2,32,32);
			o.i+=o.MS;
		}
		else if(o.direction.equals("stand")){
			return;
		}
		
	}
}
