package gameObject;

import gameObject.MovableObject.ANIMATION;
import gameObject.MovableObject.FACING;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import system.GameSystem;

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
		if(o.animation==ANIMATION.MOVELEFT) {
			o.image=o.animationParameters.getWalkGif().getImage();
			o.imageWidth=-o.animationParameters.getWalkGif().getWidth();
			o.imageHeight=o.animationParameters.getWalkGif().getHeight();
			o.renderXShift=o.animationParameters.getWalkGif().getX()+o.animationParameters.getWalkGif().getWidth();
			o.renderYShift=o.animationParameters.getWalkGif().getY();
		}
		else if(o.animation==ANIMATION.MOVERIGHT) {
			o.image=o.animationParameters.getWalkGif().getImage();
			o.imageWidth=o.animationParameters.getWalkGif().getWidth();
			o.imageHeight=o.animationParameters.getWalkGif().getHeight();
			o.renderXShift=o.animationParameters.getWalkGif().getX();
			o.renderYShift=o.animationParameters.getWalkGif().getY();
		}
		else if(o.animation==ANIMATION.MOVEUP||o.animation==ANIMATION.MOVEDOWN){
			if(o.facing==FACING.RIGHT){
				o.image=o.animationParameters.getWalkGif().getImage();
				o.imageWidth=o.animationParameters.getWalkGif().getWidth();
				o.imageHeight=o.animationParameters.getWalkGif().getHeight();
				o.renderXShift=o.animationParameters.getWalkGif().getX();
				o.renderYShift=o.animationParameters.getWalkGif().getY();
			}
			else{
				o.image=o.animationParameters.getWalkGif().getImage();
				o.imageWidth=-o.animationParameters.getWalkGif().getWidth();
				o.imageHeight=o.animationParameters.getWalkGif().getHeight();
				o.renderXShift=o.animationParameters.getWalkGif().getX()+o.animationParameters.getWalkGif().getWidth();
				o.renderYShift=o.animationParameters.getWalkGif().getY();
			}
		}
		else if(o.animation==ANIMATION.STAND) {
			if(o.facing==FACING.RIGHT){
				o.image=o.animationParameters.getStandGif().getImage();
				o.imageWidth=o.animationParameters.getStandGif().getWidth();
				o.imageHeight=o.animationParameters.getStandGif().getHeight();
				o.renderXShift=o.animationParameters.getStandGif().getX();
				o.renderYShift=o.animationParameters.getStandGif().getY();
			}
			else{
				o.image=o.animationParameters.getStandGif().getImage();
				o.imageWidth=-o.animationParameters.getStandGif().getWidth();
				o.imageHeight=o.animationParameters.getStandGif().getHeight();
				o.renderXShift=o.animationParameters.getStandGif().getX()+o.animationParameters.getStandGif().getWidth();
				o.renderYShift=o.animationParameters.getStandGif().getY();
			}
		}
		else if(o.animation==ANIMATION.DAMAGED){
			if(o.facing==FACING.RIGHT){
				o.image=o.animationParameters.getDamagedGif().getImage();
				o.imageWidth=o.animationParameters.getDamagedGif().getWidth();
				o.imageHeight=o.animationParameters.getDamagedGif().getHeight();
				o.renderXShift=o.animationParameters.getDamagedGif().getX();
				o.renderYShift=o.animationParameters.getDamagedGif().getY();
			}
			else{
				o.image=o.animationParameters.getDamagedGif().getImage();
				o.imageWidth=-o.animationParameters.getDamagedGif().getWidth();
				o.imageHeight=o.animationParameters.getDamagedGif().getHeight();
				o.renderXShift=o.animationParameters.getDamagedGif().getX()+o.animationParameters.getDamagedGif().getWidth();
				o.renderYShift=o.animationParameters.getDamagedGif().getY();
			}
		}
		else if(o.animation==ANIMATION.DYING){
			if(o.facing==FACING.RIGHT){
				o.image=o.animationParameters.getDeathGif().getImage();
				o.imageWidth=o.animationParameters.getDeathGif().getWidth();
				o.imageHeight=o.animationParameters.getDeathGif().getHeight();
				o.renderXShift=o.animationParameters.getDeathGif().getX();
				o.renderYShift=o.animationParameters.getDeathGif().getY();
			}
			else{
				o.image=o.animationParameters.getDeathGif().getImage();
				o.imageWidth=-o.animationParameters.getDeathGif().getWidth();
				o.imageHeight=o.animationParameters.getDeathGif().getHeight();
				o.renderXShift=o.animationParameters.getDeathGif().getX()+o.animationParameters.getDeathGif().getWidth();
				o.renderYShift=o.animationParameters.getDeathGif().getY();
			}
		}

	}

	/*
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
	
	*/

	
}
