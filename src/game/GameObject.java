package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;
//this is the super class that is extended by most game objects
public class GameObject{
	//stores the absolute position of character
	//stores the absolute position of character
		public double x;
		public double y;
		public int xGridNearest;
		public int yGridNearest;
		public int lastX,lastY,nextX,nextY; //used to implement moving into grids only
		public SpriteSheet ss;
		public BufferedImage image;
		//stores the height and width of the character sprite
		public int size = 32;
	
		//stores the last moved position of the character
		public double curX;
		public double curY;
		//detects hitting bricks
		public int ssX=1;
		public int ssY=1;
		public int ssWidth=32;
		public int ssHeight=32;
		public int frames = 3;
		
		int targetX,targetY;
		
		public boolean finishingMove;
		public boolean atEdge;
		
		Random rand;
		
		// MS is how fast the sprite changes pose
		public double MS = 0.2;
		public double velX = 0;
		public double velY = 0;
		public Game game;
		public String direction;
		public double i=0;
		public boolean blocked;
		
		public boolean invincible = false;
		public int invincibleTimer;
		public int invincibleTime;
	
		
		public enum ORIENTATION{
			DOWN,
			UP,
			LEFT,
			RIGHT,
			STAND
		};
		public ORIENTATION orientation = ORIENTATION.STAND;
		public double hp;
		
		
		public GameObject(int x, int y,Game game){
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
			direction = "stand";
			rand = new Random();
		}
		public void tick(){
			//updates position of char
			//maps the position to the closest "grid"
			if(invincible){
				invincibleTimer++;
				if(invincibleTimer>invincibleTime){
					invincibleTimer=0;
					invincible=false;
				}
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
		
		public void render(Graphics g){
			g.drawImage(image,(int)x,(int)y,null);
		}
	
		public Game getGame(){
			return this.game;
		}
		
		
		
		public Rectangle getBounds(int width, int height){
			return new Rectangle((int)x,(int)y,width,height);
		}
		public void playDeathSound(){
			
		}
		public void setInvincible(int time){
			invincibleTime=time;
			invincibleTimer=0;
			invincible=true;
		}
		public void takeDamage(int damage){
			this.hp-=damage;
		}
		
		
}
