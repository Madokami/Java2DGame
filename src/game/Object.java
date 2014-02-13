package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//this is the super class that is extended by most game objects
public class Object {
	//stores the absolute position of character
	//stores the absolute position of character
		public double x;
		public double y;
		public int xGrid;
		public int yGrid;
		public SpriteSheet ss;
		public BufferedImage image;
		//stores the height and width of the character sprite
		private int size = 32;
		private boolean moving=false;
		private boolean movingToNearestGrid=false;
		//stores the last moved position of the character
		public double curX;
		public double curY;
		public double prevX,prevY;
		//detects hitting bricks
		boolean hitTop,hitBottom,hitLeft,hitRight,hitBrick = false;
		public int ssCol=1;
		public int ssRow=1;
		
		// MS is how fast the sprite changes pose
		public final double MS = 0.1;
		
		private boolean isAtEdge=false;
		public double velX = 0;
		public double velY = 0;
		public Game game;
		
		public String direction;
		public double i=0;
		
		public Object(int x, int y,Game game){
			xGrid=x;
			yGrid=y;
			this.x=(x-1)*Game.SIZE;
			this.y=(y-1)*Game.SIZE;
			this.game = game;
			this.ss = game.ss1;
			curX=this.x;
			curY=this.y;
			prevX=curX;
			prevY=curY;
			this.direction = "stand";
			image = ss.grabImage(1, 1, size, size);
		}
		public void tick(){
			//detects if you hit a wall
			//if you hit a wall set the direction hit with the current movement direction
				if(Physics.hitWall(this, game.wi)){
					setHitDirection(direction);
				}
			//if collision with wall ended, remove hit direction
				else{
					hitBoxReset();
				}
			//shoves the object out of collision by moving in the opposite direction
				if(hitTop){
					y=y+0.1;
					if(velY<0)
						velY=0;
				}
				else if(hitBottom){
					y=y-0.1;
					if(velY>0)
						velY=0;
				}
				else if(hitLeft){
					x=x+0.1;
					if(velX<0)
						velX=0;
				}
				else if(hitRight){
					x=x-0.1;
					if(velX>0)
						velX=0;
				
			}
				
			//updates position of char
			x+=velX;
			y+=velY;
			//maps the position to the closest "grid"
			if(y-curY>size/2){
				prevY=curY;
				curY=curY+size;
				yGrid++;
			}
			else if(curX-x>size/2){
				prevX=curX;
				curX=curX-size;
				xGrid--;
			}
			else if(x-curX>size/2){
				prevX=curX;
				curX=curX+size;
				xGrid++;
			}
			else if(curY-y>size/2){
				prevY=curY;
				curY=curY-size;
				yGrid--;
			}
			
			
			//creates an artificial border around the edge of the screen to prevent moving outside.
			if(x<=0){
				x=0;
				isAtEdge=true;
			}
			else if(y<=0){
				y=0;
				isAtEdge=true;
			}
			else if(x>=Game.WIDTH*Game.SCALE-23){
				x=Game.WIDTH*Game.SCALE-23;
				isAtEdge=true;
			}
			else if(y>=Game.HEIGHT*Game.SCALE-23){
				y=Game.HEIGHT*Game.SCALE-23;
				isAtEdge=true;
			}
			else{
				isAtEdge=false;
			}
		}
		
		//this method is not used because it's really buggy
		public void moveToNearestGrid(int i){
			movingToNearestGrid=true;
			final double xCord = curX;
			final double yCord = curY;
		
			//keep moving until character reaches next grid
			
			while(xCord==curX&&yCord==curY){
				//don't remove this print statement or else it doesn't work O.o
				//System.out.println("moving to nearest grid");
				if(x<=0){
					if(i==2)
						break;
				}
				else if(y<=0){
					if(i==3)
						break;
				}
				else if(x>=Game.WIDTH*Game.SCALE){
					if(i==1)
						break;
				}
				else if(y>=Game.HEIGHT*Game.SCALE){
					if(i==4)
						break;
				}
			}
			
			//this.moveStop();
			movingToNearestGrid=false;
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
			
				this.velX=0;
				this.direction="down";
				this.velY=1;
			
			//if(!this.movingToNearestGrid)
			//	this.moveToNearestGrid(4);
		}
		public void moveUp(){
			moving=true;
	
					this.velX=0;
					this.direction="up";
					this.velY=-1;	
			
			//this.moveToNearestGrid(3);
		}
		public void moveLeft(){
			
				moving=true;
				hitBoxReset();
				this.velX=-1;
				this.velY=0;
				this.direction="left";
			
			//this.moveToNearestGrid(2);
		}
		public void moveRight(){
				moving=true;
				this.direction="right";
				this.velY=0;
				this.velX=1;
			
			//this.moveToNearestGrid(1);
		}
		public void moveStop(){
			this.direction="stand";
			this.velX=0;
			this.velY=0;
			moving=false;

		}
		public void hitBoxReset(){
			hitLeft=false;
			hitRight=false;
			hitTop=false;
			hitBottom=false;
			hitBrick=false;
		}
		public void render(Graphics g){
			g.drawImage(image,(int)x,(int)y,null);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(Color.PINK);
			g2d.draw(this.getBounds(32,32));
		}
		public boolean isMovingToNearestGrid(){
			return this.movingToNearestGrid;
		}
		public boolean isMoving(){
			return this.moving;
		}
		public Game getGame(){
			return this.game;
		}
		public void setHitDirection(String d){
			if(d.equals("up")){
				hitTop=true;
				hitBottom=false;
				hitLeft=false;
				hitRight=false;
			}
			else if(d.equals("down")){
				hitBottom=true;
				hitTop=false;
				hitLeft=false;
				hitRight=false;
			}
			else if(d.equals("left")){
				hitLeft=true;
				hitTop=false;
				hitRight=false;
				hitBottom=false;
			}
			else if(d.equals("right")){
				hitRight=true;
				hitLeft=false;
				hitTop=false;
				hitBottom=false;
			}
		}
		public Rectangle getBounds(int width, int height){
			return new Rectangle((int)x,(int)y,32,32);
		}
}
