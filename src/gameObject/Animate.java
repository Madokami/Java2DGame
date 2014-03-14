package gameObject;

import java.awt.Graphics;
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
			//double count = o.i%8;
			//o.image=o.ssStand.grabImage((int)count+1,1,48,66);
			//o.i+=o.MS;
		}
		
	}

	public static void animateGem(Player p) {
		// TODO Auto-generated method stub
		p.soulGemImage=p.soulGemSprite.grabImage(1, 1, Player.soulGemWidth, Player.soulGemHeight);
		if(1-p.soul/p.maxSoul>=0.25&&1-p.soul/p.maxSoul<0.5){
			double count = p.soulGemCounter%8;
			p.soulGemImage=p.soulGemSprite.grabImage(1,2+(int)count,Player.soulGemWidth, Player.soulGemHeight);
			p.soulGemCounter+=p.soulGemAnimationSpeed;
		}
		else if(1-p.soul/p.maxSoul>=0.5&&1-p.soul/p.maxSoul<0.75){
			double count = p.soulGemCounter%8;
			p.soulGemImage=p.soulGemSprite.grabImage(2,2+(int)count,Player.soulGemWidth, Player.soulGemHeight);
			p.soulGemCounter+=p.soulGemAnimationSpeed;
		}
		else if(1-p.soul/p.maxSoul>=0.75&&1-p.soul/p.maxSoul<1){
			double count = p.soulGemCounter%8;
			p.soulGemImage=p.soulGemSprite.grabImage(3,2+(int)count,Player.soulGemWidth, Player.soulGemHeight);
			p.soulGemCounter+=p.soulGemAnimationSpeed;
		}
		else if(1-p.soul/p.maxSoul==1){
			p.soulGemImage=p.soulGemSprite.grabImage(2, 1, Player.soulGemWidth, Player.soulGemHeight);
		}
		
	}
}
