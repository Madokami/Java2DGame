package gameObject;

import gameObject.MovableObject.ANIMATION;
import gameObject.MovableObject.FACING;

public class Animation {
	private double counter;
	private int frames;
	private ImageSequence currentSequence,nextSequence;
	private MovableObject owner;
	private boolean goToNext,oneTimeSequence;
	
	public Animation(MovableObject o){
		this.owner=o;
	}
	public void animate(){
		if(owner.facing==FACING.RIGHT){
			owner.image=currentSequence.getImage((int)counter);
			owner.imageWidth=currentSequence.getWidth();
			owner.imageHeight=currentSequence.getHeight();
			owner.renderXShift=currentSequence.getX();
			owner.renderYShift=currentSequence.getY();
		}
		else{
			owner.image=currentSequence.getImage((int)counter);
			owner.imageWidth=-currentSequence.getWidth();
			owner.imageHeight=currentSequence.getHeight();
			owner.renderXShift=currentSequence.getX()+currentSequence.getWidth();
			owner.renderYShift=currentSequence.getY();
		}
	}
	public void tick(){
		
		if(currentSequence!=null){
			counter=counter%frames;
			animate();
			counter+=currentSequence.getAnimationSpeed();
			if(counter>=frames){
				if(oneTimeSequence) counter=frames-1;
				if(goToNext) {
					if(nextSequence!=null) startSequence(nextSequence);
				}
				
			}
		}
	}
	public void startSequence(ImageSequence sequence){
		goToNext=false;
		oneTimeSequence=false;
		currentSequence=sequence;
		counter=0;
		if(currentSequence!=null){
			frames=currentSequence.getFrames();
		}
	}
	public void startSequence(ImageSequence sequence, ImageSequence nextSequence){
		goToNext=true;
		oneTimeSequence=false;
		currentSequence=sequence;
		this.nextSequence=nextSequence;
		counter=0;
		if(currentSequence!=null){
			frames=currentSequence.getFrames();
		}
	}
	public void startOneTimeSequence(ImageSequence sequence){
		oneTimeSequence=true;
		goToNext=false;
		currentSequence=sequence;
		counter=0;
		if(currentSequence!=null){
			frames=currentSequence.getFrames();
		}
	}
	
}
