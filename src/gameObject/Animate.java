package gameObject;

import gameObject.MovableObject.ANIMATION;

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
	public static void animateWithGif(MovableObject o){
		if(o.animation==ANIMATION.MOVELEFT) o.image=o.moveLeftGif;
		else if(o.animation==ANIMATION.MOVERIGHT) o.image=o.moveRightGif;
		else if(o.animation==ANIMATION.MOVEUP) {
			if(o.getVelX()>0){
				o.image=o.moveRightGif;
			}
			else if(o.getVelX()<0){
				o.image=o.moveLeftGif;
			}
		}
		else if(o.animation==ANIMATION.MOVEDOWN) {
			if(o.getVelX()>0){
				o.image=o.moveRightGif;
			}
			else if(o.getVelX()<0){
				o.image=o.moveLeftGif;
			}
		}
		else if(o.animation==ANIMATION.JUMPRIGHT) o.image=o.jumpRightGif;
		else if(o.animation==ANIMATION.JUMPLEFT) o.image=o.jumpLeftGif;
		else if(o.animation==ANIMATION.JUMPUP) o.image=o.jumpUpGif;
		else if(o.animation==ANIMATION.JUMPDOWN) o.image=o.jumpDownGif;
		else if(o.animation==ANIMATION.UPATTACK) o.image=o.upAttackGif;
	}
	public static void animateWithGif(Player_Kyouko o){
		if(o.animation==ANIMATION.MOVELEFT) {
			o.image=o.moveLeftGif;
			o.imageWidth=120;
			o.imageHeight=60;
			o.renderXShift=-40;
			o.renderYShift=-20;
		}
		else if(o.animation==ANIMATION.MOVERIGHT) {
			o.image=o.moveRightGif;
			o.imageWidth=120;
			o.imageHeight=60;
			o.renderXShift=-40;
			o.renderYShift=-20;
		}
		//else if(o.animation==ANIMATION.STAND) o.image=o.standGif;
		else if(o.animation==ANIMATION.JUMPRIGHT) o.image=o.jumpRightGif;
		else if(o.animation==ANIMATION.JUMPLEFT) o.image=o.jumpLeftGif;
		else if(o.animation==ANIMATION.JUMPUP) o.image=o.jumpUpGif;
		else if(o.animation==ANIMATION.JUMPDOWN) o.image=o.jumpDownGif;
		else if(o.animation==ANIMATION.UPATTACK) o.image=o.upAttackGif;
	}
	public static void animateWithGif(Player_Homura o){
		if(o.animation==ANIMATION.MOVELEFT) {
			o.image=o.moveLeftGif;
			o.imageWidth=120;
			o.imageHeight=60;
			o.renderXShift=-40;
			o.renderYShift=-20;
		}
		else if(o.animation==ANIMATION.MOVERIGHT) {
			o.image=o.moveRightGif;
			o.imageWidth=120;
			o.imageHeight=60;
			o.renderXShift=-40;
			o.renderYShift=-20;
		}
		else if(o.animation==ANIMATION.STAND) {
			o.image=o.standGif;
			o.imageWidth=46;
			o.imageHeight=86;
			o.renderXShift=0;
			o.renderYShift=35;
		}
		else if(o.animation==ANIMATION.JUMPRIGHT) o.image=o.jumpRightGif;
		else if(o.animation==ANIMATION.JUMPLEFT) o.image=o.jumpLeftGif;
		else if(o.animation==ANIMATION.JUMPUP) o.image=o.jumpUpGif;
		else if(o.animation==ANIMATION.JUMPDOWN) o.image=o.jumpDownGif;
		else if(o.animation==ANIMATION.UPATTACK) o.image=o.upAttackGif;
	}
}
