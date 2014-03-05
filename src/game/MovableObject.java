package game;

import game.GameObject.ORIENTATION;

public abstract class MovableObject extends GameObject{
	public double xTemp,yTemp;
	public int spd=4;
	public double hp=100;
	public int damage;
	public boolean nextLocationSet;
	public boolean moving=false;
	public boolean buttonReleased;
	
	public int buttonPressed;
	
	public MovableObject(int x, int y, Game game) {
		super(x, y, game);
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
		if(invulnerable){
			timer++;
			if(timer>60){
				timer=0;
				invulnerable=false;
			}
		}
		
		checkIfBlocked();
		if(blocked){
			return;
		}
		if(buttonReleased){
			if(targetPositionReached()){
				velX=0;
				velY=0;
				moving=false;
			}
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
		if(y-curY>size/2){
			curY=curY+size;
			yGridNearest++;
		}
		else if(curX-x>size/2){
			curX=curX-size;
			xGridNearest--;
		}
		else if(x-curX>size/2){
			curX=curX+size;
			xGridNearest++;
		}
		else if(curY-y>size/2){
			curY=curY-size;
			yGridNearest--;
		}
		//sets the last completely arrived location grid
		if(y-yTemp>size){
			yTemp=yTemp+size;
			y=yTemp;
			lastY++;
		}
		else if(xTemp-x>size){
			xTemp=xTemp-size;
			x=xTemp;
			lastX--;
		}
		else if(x-xTemp>size){
			xTemp=xTemp+size;
			x=xTemp;
			lastX++;
		}
		else if(yTemp-y>size){
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
	public void checkIfBlocked(){
		setNextXY();
		try{
			if(game.e.wallArray[lastX][lastY]||game.e.wallArray[nextX][nextY]){
				blocked=true;
			}
			else{
				blocked=false;
			}
			
			
		
		}catch(IndexOutOfBoundsException x){
			
		
		}
		
		
	}
	
	private void checkIfAtEdge() {
		if(x<=0){
			x=1;
			atEdge=true;
		}
		else if(y<=0){
			y=1;
			atEdge=true;
		}
		else if(x>=GameSystem.WIDTH*GameSystem.SCALE-32){
			x=GameSystem.WIDTH*GameSystem.SCALE-33;
			atEdge=true;
		}
		else if(y>=GameSystem.HEIGHT*GameSystem.SCALE-32-96){
			y=GameSystem.HEIGHT*GameSystem.SCALE-33-96;
			atEdge=true;
		}
		else{
			atEdge=false;
		}
		
	}
}
