package gameObject;

import game.Game;

import java.awt.Image;

import system.BufferedImageLoader;
import system.GameSystem;

public abstract class MovableObject extends GameObject{
	public double xTemp,yTemp;
	public int spd=4;
	//public int damage;
	public boolean nextLocationSet;
	public boolean moving=false;
	public boolean buttonReleased;
	public String nextMove="null";
	public int toleranceX = GameSystem.GRID_SIZE/2;
	public int toleranceY = GameSystem.GRID_SIZE/2;
	private double velX = 0;
	private double velY = 0;
	
	protected Image moveLeftGif,moveRightGif,moveUpGif,moveDownGif,jumpUpGif,jumpDownGif,jumpLeftGif,jumpRightGif,upAttackGif = null;
	
	public int nextXCrude,nextYCrude;
	protected BufferedImageLoader loader=new BufferedImageLoader();
	protected AnimationParameters animationParameters = new AnimationParameters();
	
	//starting from here is some special variables for active abilities
	private boolean charging=false;
	public int chargeSpeed;
	public int chargeDuration;
	public int chargeDurationTimer;
	
	public boolean damaged;
	public int damagedDuration;
	public int damagedDurationTimer;
	
	
	
	protected Event damage= new Event();
	
	public enum ANIMATION{
		MOVELEFT,
		MOVERIGHT,
		MOVEUP,
		MOVEDOWN,
		STAND,
		JUMPUP,
		JUMPDOWN,
		JUMPLEFT,
		JUMPRIGHT,
		DYING,
		DAMAGED,
		UPATTACK,
	};
	public enum FACING{
		LEFT,
		RIGHT
	};
	
	protected ANIMATION animation = ANIMATION.STAND;
	protected FACING facing = FACING.RIGHT;
	
	public MovableObject(int x, int y, Game game) {
		super(x, y, game);
		hp=100;
		xTemp=this.x;
		yTemp=this.y;
		targetX=xGridNearest;
		targetY=yGridNearest;
		finishingMove=false;
		moving=false;
		nextLocationSet=false;
	}
	public void tick(){
		//first check if blocked
		super.tick();
		tickTimers();
		applySpecialAbilitiesWithinDuration();
		if(adjustToBlockageAndReturnTrueIfBlocked()) {
			animation=ANIMATION.STAND;
			return;
		}
		if(buttonReleased){
			if(targetPositionReached()){
				velX=0;
				velY=0;
				moving=false;
				animation=ANIMATION.STAND;
				direction="stand";
			}
		}
		//updatePosition();
		if(damaged){
			animation=ANIMATION.DAMAGED;
			setVelX(0);
			setVelY(0);
		}
		
		x+=velX;
		y+=velY;
		updatePosition();
		checkIfAtEdge();
	}
	private void tickTimers() {
		chargeDurationTimer++;
		damagedDurationTimer++;
		//damage.tick();
		
	}
	private void applySpecialAbilitiesWithinDuration(){
		if(damagedDurationTimer>damagedDuration){
			stopDamaged();
		}
		
		if(chargeDurationTimer<chargeDuration) {
			charge();
			charging=true;
		}
		else {
			stopCharge();
		}
	}
	//moveUp shall move 1 grid up only
	public void moveUp(){
		if(damaged||game.getPlayer().dying) return;
		moving=true;
		orientation=ORIENTATION.UP;
		animation=ANIMATION.MOVEUP;
		direction="up";
		setNextXY();
		setDestination(nextX,nextY);
		velY=-1*spd/2;
		velX=0;
	}
	public void moveDown(){
		if(damaged||game.getPlayer().dying) return;
		moving=true;
		orientation=ORIENTATION.DOWN;
		animation=ANIMATION.MOVEDOWN;
		direction="down";
		setNextXY();
		setDestination(nextX,nextY);
		velY=spd/2;
		velX=0;
	}
	public void moveRight(){
		if(damaged||game.getPlayer().dying) return;
		moving=true;
		orientation=ORIENTATION.RIGHT;
		animation=ANIMATION.MOVERIGHT;
		facing=FACING.RIGHT;
		direction="right";
		setNextXY();
		setDestination(nextX,nextY);
		velX=spd/2;
		velY=0;
	}
	public void moveLeft(){
		if(damaged||game.getPlayer().dying) return;
		moving=true;
		orientation=ORIENTATION.LEFT;
		animation=ANIMATION.MOVELEFT;
		facing=FACING.LEFT;
		direction="left";
		setNextXY();
		setDestination(nextX,nextY);
		velX=-1*spd/2;
		velY=0;
	}
	public void moveStop(){
		buttonReleased=true;
	}
	
	
	public void setDestination(int nextX, int nextY) {
		targetX=nextX;
		targetY=nextY;
	}
	
	public boolean targetPositionReached(){
		
		if(orientation==ORIENTATION.UP&&targetY>=lastY){
			return true;
		}
		else if(orientation==ORIENTATION.DOWN&&targetY<=lastY){
			return true;
		}
		else if(orientation==ORIENTATION.LEFT&&targetX>=lastX){
			return true;
		}
		else if(orientation==ORIENTATION.RIGHT&&targetX<=lastX){
			return true;
		}
		
		else if(targetX==lastX&&targetY==lastY){
			return true;
		}
		return false;
	}
	public void updatePosition(){
		//maps the position to the closest "grid"
		if(y-curY>=GameSystem.GRID_SIZE/2){
			curY=curY+GameSystem.GRID_SIZE;
			yGridNearest++;
		}
		else if(curX-x>=GameSystem.GRID_SIZE/2){
			curX=curX-GameSystem.GRID_SIZE;
			xGridNearest--;
		}
		else if(x-curX>=GameSystem.GRID_SIZE/2){
			curX=curX+GameSystem.GRID_SIZE;
			xGridNearest++;
		}
		else if(curY-y>=GameSystem.GRID_SIZE/2){
			curY=curY-GameSystem.GRID_SIZE;
			yGridNearest--;
		}
		//sets the last completely arrived location grid
		if(y-yTemp>=GameSystem.GRID_SIZE){
			yTemp=yTemp+GameSystem.GRID_SIZE;
			y=yTemp;
			lastY++;
		}
		else if(xTemp-x>=GameSystem.GRID_SIZE){
			xTemp=xTemp-GameSystem.GRID_SIZE;
			x=xTemp;
			lastX--;
		}
		else if(x-xTemp>=GameSystem.GRID_SIZE){
			xTemp=xTemp+GameSystem.GRID_SIZE;
			x=xTemp;
			lastX++;
		}
		else if(yTemp-y>=GameSystem.GRID_SIZE){
			yTemp=yTemp-GameSystem.GRID_SIZE;
			y=yTemp;
			lastY--;
		}
		//only updates nextX and nextY when the move buttons are being pressed down
		/*
		if(movable){
			if(direction.equals("right")){
					nextX=lastX+1;
					nextY=lastY;
			}
			else if(direction.equals("left")){
				nextX=lastX-1;
				nextY=lastY;
			}
			else if(direction.equals("up")){
				nextY=lastY-1;
				nextX=lastX;
			}
			else if(direction.equals("down")){
				nextY=lastY+1;
				nextX=lastX;
			}
		}
		*/
		
	}
	public void setNextXYCrude(){
		if(orientation==ORIENTATION.DOWN){
			nextXCrude=xGridNearest;
			nextYCrude=yGridNearest+1;
		}
		else if(orientation==ORIENTATION.UP){
			nextXCrude=xGridNearest;
			nextYCrude=yGridNearest-1;
		}
		else if(orientation==ORIENTATION.LEFT){
			nextXCrude=xGridNearest-1;
			nextYCrude=yGridNearest;
		}
		else if(orientation==ORIENTATION.RIGHT){
			nextXCrude=xGridNearest+1;
			nextYCrude=yGridNearest;
		}
		else if(orientation==ORIENTATION.STAND){
			nextXCrude=xGridNearest;
			nextYCrude=yGridNearest;
		}
	}
	public void setNextXY(){
		if(orientation==ORIENTATION.DOWN){
			nextX=lastX;
			nextY=lastY+1;
		}
		else if(orientation==ORIENTATION.UP){
			nextX=lastX;
			nextY=lastY-1;
		}
		else if(orientation==ORIENTATION.LEFT){
			nextX=lastX-1;
			nextY=lastY;
		}
		else if(orientation==ORIENTATION.RIGHT){
			nextX=lastX+1;
			nextY=lastY;
		}
		else if(orientation==ORIENTATION.STAND){
			nextX=lastX;
			nextY=lastY;
		}
	}
	public boolean checkIfBlocked(int lastX,int lastY,int nextX, int nextY){
		try{
			if(game.getController().wallArray[lastX][lastY]||game.getController().wallArray[nextX][nextY]){
				return true;
			}	
		}catch(IndexOutOfBoundsException x){
			
		
		}
		return false;
	}
	public boolean checkIfBlocked(){
		setNextXY();
		/*if(Physics.hitWall(this, game.wi)){
			blocked=true;
		}
		else{
			blocked=false;
		}
		*/
		
		try{
			if(game.getController().wallArray[lastX][lastY]||game.getController().wallArray[nextX][nextY]){
				return true;
			}	
		}catch(IndexOutOfBoundsException x){
			
		
		}
		return false;
		
	}
	
	public boolean checkWallCollision(){
		if(Physics.hitWall(this, game.getBrickList())!=-1){
			return true;
		}
		if(Physics.hitPlaceHolder(this, game.getPlaceHolderList())!=-1){
			return true;
		}
		return false;
	}
	
	private void checkIfAtEdge() {
		if(x<=0){
			x=1;
			atEdge=true;
			moving=false;
		}
		if(y<=0){
			y=1;
			atEdge=true;
			moving=false;
		}
		if(x>=GameSystem.GAME_WIDTH-collisionWidth){
			x=GameSystem.GAME_WIDTH-collisionHeight;
			atEdge=true;
			moving=false;
		}
		if(y>=GameSystem.GAME_HEIGHT-collisionWidth){
			y=GameSystem.GAME_HEIGHT-collisionHeight;
			atEdge=true;
			moving=false;
		}
		else{
			atEdge=false;
		}
		
	}
	public void moveToLastAcceptableLocation(){
		this.x=this.xTemp;
		this.y=this.yTemp;
	}
	
	
	public void startCharge(int value, int duration){
		chargeSpeed=value;
		chargeDuration=duration;
		chargeDurationTimer=0;
	}
	public void stopCharge(){
		if(charging){
			charging=false;
			refreshMovementSpeed();
		}
	}
	private void charge(){
		if(orientation==ORIENTATION.LEFT){
			setVelX(-chargeSpeed);
			animation=ANIMATION.JUMPLEFT;
		}
		else if(orientation==ORIENTATION.RIGHT){
			setVelX(chargeSpeed);
			animation=ANIMATION.JUMPRIGHT;
		}
		else if(orientation==ORIENTATION.UP){
			setVelY(-chargeSpeed);
			animation=ANIMATION.JUMPUP;
		}
		else if(orientation==ORIENTATION.DOWN){
			setVelY(chargeSpeed);
			animation=ANIMATION.JUMPRIGHT;
		}
	}
	private void startDamaged(int duration){
		damaged=true;
		damagedDuration=duration;
		damagedDurationTimer=0;
	}
	
	private void stopDamaged(){
		if(!damaged) return;
		damaged=false;
		animation=ANIMATION.STAND;
	}
	
	
	public void setVelX(double value){
		velX=value/2;
		velY=0;
	}
	public void setVelY(double value){
		velY=value/2;
		velX=0;
	}
	public double getVelX(){
		return this.velX;
	}
	public double getVelY(){
		return this.velY;
	}
	public void refreshMovementSpeed(){
		if(orientation==ORIENTATION.RIGHT) moveRight();
		else if(orientation==ORIENTATION.LEFT) moveLeft();
		else if(orientation==ORIENTATION.UP) moveUp();
		else if(orientation==ORIENTATION.DOWN) moveDown();
	}
	private boolean adjustToBlockageAndReturnTrueIfBlocked(){
		if(checkIfBlocked()){
			//checks the orientation
			if(orientation==ORIENTATION.UP||orientation==ORIENTATION.DOWN){
				//sets it so that the y position is at right place
				//update position so variables catch up to the explicit change
				y=yTemp;
				updatePosition();
				//if the right side is not blocked, and the player is close enough to right, shift him to right;
				if(!checkIfBlocked(lastX+1,lastY,nextX+1,nextY)){
					if(xTemp+this.collisionWidth-x<=toleranceX){
						x=xTemp+collisionWidth;
						return false;
					}
			
				}
				
				if(!checkIfBlocked(lastX-1,lastY,nextX-1,nextY)){
					if((x+collisionWidth-xTemp)<=toleranceX){
						x=xTemp-collisionWidth;
						return false;
					}
					
				}
			}
			else if(orientation==ORIENTATION.LEFT||orientation==ORIENTATION.RIGHT){
				x=xTemp;
				updatePosition();
				if(!checkIfBlocked(lastX,lastY+1,nextX,nextY+1)){
					if(yTemp+this.collisionHeight-y<=toleranceY){
						y=yTemp+collisionHeight;
						return false;
					}
				
				}
				if(!checkIfBlocked(lastX,lastY-1,nextX,nextY-1)){
					if(y+collisionHeight-yTemp<=toleranceY){
						y=yTemp-collisionHeight;
						return false;
					}
				
				}
			}
			return true;
			
		}
		else{
			if(checkWallCollision()){
				this.moveToLastAcceptableLocation();
			}
		}
		return false;
	}
	public void takeDamage(int damage){
		super.takeDamage(damage);
		this.startDamaged(8);
	}
	
}
