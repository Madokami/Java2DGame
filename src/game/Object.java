package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
//this is the super class that is extended by most game objects
public class Object {
	//stores the absolute position of character
	//stores the absolute position of character
		public double x;
		public double y;
		public int xGridNearest;
		public int yGridNearest;
		public int lastX,lastY,nextX,nextY; //used to implement moving into grids only
		public double xTemp,yTemp;
		public double speed=1;
		public int hp=1;
		public int damage;
		public SpriteSheet ss;
		public BufferedImage image;
		//stores the height and width of the character sprite
		public final int size = 32;
		public boolean moving=false;
	
		//stores the last moved position of the character
		public double curX;
		public double curY;
		//detects hitting bricks
		boolean hitTop,hitBottom,hitLeft,hitRight,hitBrick = false;
		public int ssX=1;
		public int ssY=1;
		
		int targetX,targetY;
		
		public boolean finishingMove;
		public boolean atEdge;
		
		Random rand;
		
		// MS is how fast the sprite changes pose
		public final double MS = 0.1;
		public double velX = 0;
		public double velY = 0;
		public Game game;
		public String direction;
		public double i=0;
		public boolean blocked;
		
		public enum ORIENTATION{
			DOWN,
			UP,
			LEFT,
			RIGHT,
			STAND
		};
		public ORIENTATION orientation = ORIENTATION.DOWN;
		
		
		public Object(int x, int y,Game game){
			xGridNearest=x;
			yGridNearest=y;
			lastX=x;
			lastY=y;
			nextX=x;
			nextY=y;
			this.x=(x-1)*GameSystem.SIZE;
			this.y=(y-1)*GameSystem.SIZE;
			this.game = game;
			curX=this.x;
			curY=this.y;
			xTemp=this.x;
			yTemp=this.y;
			direction = "stand";
			rand = new Random();
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
		
		private void checkIfBlocked() {
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
			else if(y>=GameSystem.HEIGHT*GameSystem.SCALE-32){
				y=GameSystem.HEIGHT*GameSystem.SCALE-33;
				atEdge=true;
			}
			else{
				atEdge=false;
			}
			
		}
		public double getX(){
			return x;
		}
		public double getY(){
			return y;
		}
		public void setX(double x){
			this.x = x;
		}
		public void setY(double y){
			this.y=y;
		}
		public void setVelX(double velX){
			this.velX=velX;
		}
		public void setVelY(double velY){
			this.velY=velY;
		}
		public void moveDown(){
				moving=true;
				orientation=ORIENTATION.DOWN;
				this.velX=0;
				this.direction="down";
				this.velY=speed;
		}
		public void moveUp(){
				moving=true;
				orientation=ORIENTATION.UP;
				this.velX=0;
				this.direction="up";
				this.velY=-1*speed;	
		}
		public void moveLeft(){		
				moving=true;
				orientation=ORIENTATION.LEFT;
				this.velX=-1*speed;
				this.velY=0;
				this.direction="left";
		}
		public void moveRight(){
				moving=true;
				this.direction="right";
				orientation=ORIENTATION.RIGHT;
				this.velY=0;
				this.velX=speed;
		}
		public void moveStop(){
			this.direction="stand";
			orientation=ORIENTATION.STAND;
			this.velX=0;
			this.velY=0;
			moving=false;
			/*
			if(blocked){
				nextX=lastX;
				nextY=lastY;
				blocked=false;
			}
			*/

		}
		public void render(Graphics g){
			g.drawImage(image,(int)x,(int)y,null);
		}
		public boolean isMoving(){
			return this.moving;
		}
		public Game getGame(){
			return this.game;
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
		public Rectangle getBounds(int width, int height){
			return new Rectangle((int)x,(int)y,width,height);
		}
		public void playDeathSound(){
			
		}
		public void moveToNext(int x,int y){
			if(blocked||atEdge){
				return;
			}
			targetX = x;
			targetY = y;
			finishingMove=true;
		}
		
		//the following code will move the sprite to the next grid
		public void finishMove(){
				System.out.println("in retarded loop");
				if(lastX<targetX){
					velX=speed;
					velY=0;
				}
				else if(lastX>targetX){
					velX=-1*speed;
					velY=0;
				}
				if(lastY<targetY){
					velY=speed;
					velX=0;
				}
				else if(lastY>targetY){
					velY=-1*speed;
					velX=0;
				}
				if(lastX==targetX&&lastY==targetY){
					moveStop();
					finishingMove=false;
				}
		}
		
}
