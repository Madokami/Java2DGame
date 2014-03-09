package game;

import game.GameObject.ORIENTATION;

public abstract class MovableObject extends GameObject{
	public double xTemp,yTemp;
	public int spd=4;
	public int damage;
	public boolean nextLocationSet;
	public boolean moving=false;
	public boolean buttonReleased;
	public String nextMove="null";
	public int toleranceX = ssWidth/2;
	public int toleranceY = ssHeight/2;
	
	
	public int nextXCrude,nextYCrude;
	
	public int buttonPressed;
	
	//starting from here is some special variables for active abilities
	public int chargeSpeed;
	public int chargeDuration;
	public int chargeDurationTimer;
	
	public MovableObject(int x, int y, Game game) {
		super(x, y, game);
		hp=100;
		xTemp=this.x;
		yTemp=this.y;
		buttonPressed=0;
		targetX=xGridNearest;
		targetY=yGridNearest;
		finishingMove=false;
		moving=false;
		nextLocationSet=false;
	}
	public void tick(){
		//first check if blocked
		super.tick();
		
		if(checkIfBlocked()){
			//checks the orientation
			if(orientation==ORIENTATION.UP||orientation==ORIENTATION.DOWN){
				//sets it so that the y position is at right place
				//update position so variables catch up to the explicit change
				y=yTemp;
				updatePosition();
				//if the right side is not blocked, and the player is close enough to right, shift him to right;
				if(!checkIfBlocked(lastX+1,lastY,nextX+1,nextY)){
					if(xTemp+this.ssWidth-x<=toleranceX){
						x=xTemp+ssWidth;
					}
					else{
						return;
					}
				}
				
				else if(!checkIfBlocked(lastX-1,lastY,nextX-1,nextY)){
					if(x-(xTemp-ssWidth)<=toleranceX){
						x=xTemp-ssWidth;
					}
					else{
						return;
					}
				}
				else{
					return;
				}
			}
			else if(orientation==ORIENTATION.LEFT||orientation==ORIENTATION.RIGHT){
				x=xTemp;
				updatePosition();
				if(!checkIfBlocked(lastX,lastY+1,nextX,nextY+1)){
					if(yTemp+this.ssHeight-y<=toleranceY){
						y=yTemp+ssHeight;
					}
					else{
						return;
					}
				}
				else if(!checkIfBlocked(lastX,lastY-1,nextX,nextY-1)){
					if(y-(yTemp-ssHeight)<=toleranceY){
						y=yTemp-ssHeight;
					}
					else{
						return;
					}
				}
				else{
					return;
				}
			}
			
		}
		else{
			if(checkWallCollision()){
				this.moveToLastAcceptableLocation();
			}
		}
		
	
		
		if(buttonReleased){
			if(targetPositionReached()){
				velX=0;
				velY=0;
				moving=false;
			}
		}
		
		if(chargeDurationTimer<chargeDuration) {
			chargeDurationTimer++;
			charge();
		}
		
		x+=velX;
		y+=velY;
		updatePosition();
		checkIfAtEdge();
	}
	//moveUp shall move 1 grid up only
	public void moveUp(){
		moving=true;
		orientation=ORIENTATION.UP;
		direction="up";
		setNextXY();
		setDestination(nextX,nextY);
		velY=-1*spd/2;
		velX=0;
	}
	public void moveDown(){
		moving=true;
		System.out.println("moveDown called\n");
		orientation=ORIENTATION.DOWN;
		direction="down";
		setNextXY();
		setDestination(nextX,nextY);
		velY=spd/2;
		velX=0;
	}
	public void moveRight(){
		moving=true;
		orientation=ORIENTATION.RIGHT;
		direction="right";
		setNextXY();
		setDestination(nextX,nextY);
		velX=spd/2;
		velY=0;
	}
	public void moveLeft(){
		
		moving=true;
		orientation=ORIENTATION.LEFT;
		direction="left";
		setNextXY();
		setDestination(nextX,nextY);
		velX=-1*spd/2;
		velY=0;
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
		if(y-curY>=size/2){
			curY=curY+size;
			yGridNearest++;
		}
		else if(curX-x>=size/2){
			curX=curX-size;
			xGridNearest--;
		}
		else if(x-curX>=size/2){
			curX=curX+size;
			xGridNearest++;
		}
		else if(curY-y>=size/2){
			curY=curY-size;
			yGridNearest--;
		}
		//sets the last completely arrived location grid
		if(y-yTemp>=size){
			yTemp=yTemp+size;
			y=yTemp;
			lastY++;
		}
		else if(xTemp-x>=size){
			xTemp=xTemp-size;
			x=xTemp;
			lastX--;
		}
		else if(x-xTemp>=size){
			xTemp=xTemp+size;
			x=xTemp;
			lastX++;
		}
		else if(yTemp-y>=size){
			yTemp=yTemp-size;
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
			if(game.c.wallArray[lastX][lastY]||game.c.wallArray[nextX][nextY]){
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
			if(game.c.wallArray[lastX][lastY]||game.c.wallArray[nextX][nextY]){
				return true;
			}	
		}catch(IndexOutOfBoundsException x){
			
		
		}
		return false;
		
	}
	
	public boolean checkWallCollision(){
		if(Physics.hitWall(this, game.brickList)!=-1){
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
		if(x>=GameSystem.GAME_WIDTH-32){
			x=GameSystem.GAME_WIDTH-32;
			atEdge=true;
			moving=false;
		}
		if(y>=GameSystem.GAME_HEIGHT-32){
			y=GameSystem.GAME_HEIGHT-32;
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
	public void applyNextMove(){
		if(nextMove.equals("right")){
			moveRight();
		}
		else if(nextMove.equals("left")){
			moveLeft();
		}
		else if(nextMove.equals("up")){
			moveUp();
		}
		else if(nextMove.equals("down")){
			moveDown();
		}
	}
	
	public void startCharge(int value, int duration){
		chargeSpeed=value;
		chargeDuration=duration;
		chargeDurationTimer=0;
	}
	private void charge(){
		if(orientation==ORIENTATION.LEFT){
			velX=-chargeSpeed;
			velY=0;
		}
		else if(orientation==ORIENTATION.RIGHT){
			velX=chargeSpeed;
			velY=0;
		}
		else if(orientation==ORIENTATION.UP){
			velX=0;
			velY=-chargeSpeed;
		}
		else if(orientation==ORIENTATION.DOWN){
			velX=0;
			velY=chargeSpeed;
		}
	}
	public void refreshMovementSpeed(){
		if(orientation==ORIENTATION.RIGHT) moveRight();
		else if(orientation==ORIENTATION.LEFT) moveLeft();
		else if(orientation==ORIENTATION.UP) moveUp();
		else if(orientation==ORIENTATION.DOWN) moveDown();
	}
}
