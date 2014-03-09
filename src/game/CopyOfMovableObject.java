package game;

import game.GameObject.ORIENTATION;

public abstract class CopyOfMovableObject extends GameObject{
	public double xTemp,yTemp;
	public double spd=2;
	public double hp=100;
	public int damage;
	private boolean moving=false;
	
	public boolean buttonPressed;
	
	public CopyOfMovableObject(int x, int y, Game game) {
		super(x, y, game);
		xTemp=this.x;
		yTemp=this.y;
		buttonPressed=false;
	}
	
	public void tick(){
		//updates position of char
		//maps the position to the closest "grid"
		
		checkIfBlocked();
		if(blocked){
			return;
		}
		else{
			
			if(finishingMove){
				if(atEdge){
					finishingMove=false;
				}
				else{
					finishMove();
				}
			}
			
				x+=velX;
				y+=velY;
				updatePosition();
			
		}
		//creates an artificial border around the edge of the screen to prevent moving outside.
		checkIfAtEdge();
	}
	
	public void checkIfBlocked() {
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
		try{
			if(game.c.wallArray[lastX][lastY]||game.c.wallArray[nextX][nextY]){
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
	
	public void moveDown(){
			moving=true;
			orientation=ORIENTATION.DOWN;
			this.velX=0;
			this.direction="down";
			this.velY=spd;
	}
	public void moveUp(){
			moving=true;
			orientation=ORIENTATION.UP;
			this.velX=0;
			this.direction="up";
			this.velY=-1*spd;	
	}
	public void moveLeft(){		
			moving=true;
			orientation=ORIENTATION.LEFT;
			this.velX=-1*spd;
			this.velY=0;
			this.direction="left";
	}
	public void moveRight(){
			moving=true;
			this.direction="right";
			orientation=ORIENTATION.RIGHT;
			this.velY=0;
			this.velX=spd;
	}
	public void moveStop(){
		this.direction="stand";
		orientation=ORIENTATION.STAND;
		this.velX=0;
		this.velY=0;
		moving=false;
		moveToNext(nextX,nextY);
		/*
		if(blocked){
			nextX=lastX;
			nextY=lastY;
			blocked=false;
		}
		*/

	}
	
	public boolean isMoving(){
		return this.moving;
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
	
	public void moveToNext(int x,int y){
		if(blocked||atEdge||buttonPressed){
			return;
		}
		targetX = x;
		targetY = y;
		finishingMove=true;
	}
	
	//the following code will move the sprite to the next grid
	public void finishMove(){
			System.out.println("in retarded loop");
			if(buttonPressed){
				finishingMove=false;
				return;
			}
			if(lastX<targetX){
				velX=spd;
				velY=0;
			}
			else if(lastX>targetX){
				velX=-1*spd;
				velY=0;
			}
			if(lastY<targetY){
				velY=spd;
				velX=0;
			}
			else if(lastY>targetY){
				velY=-1*spd;
				velX=0;
			}
			if(lastX==targetX&&lastY==targetY){
				moveStop();
				finishingMove=false;
			}
	}
}
