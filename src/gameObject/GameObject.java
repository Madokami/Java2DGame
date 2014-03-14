package gameObject;

import game.Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import system.GameSystem;
//this is the super class that is extended by most game objects
public class GameObject{
	//stores the absolute position of character
	//stores the absolute position of character
		protected double x;
		protected double y;
		protected int xGridNearest;
		protected int yGridNearest;
		protected int lastX,lastY,nextX,nextY; //used to implement moving into grids only
		protected SpriteSheet ss;
		protected SpriteSheet ssStand;
		protected Image image;
		//stores the last moved position of the character
		public double curX;
		public double curY;
		//detects hitting bricks
		protected int ssX=1;
		protected int ssY=1;
		protected int ssWidth=32;
		protected int ssHeight=32;
		protected int frames = 3;
		
		protected int targetX,targetY;
		
		public boolean finishingMove;
		public boolean atEdge;
		protected Image standGif;
		
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
		
		public int collisionWidth;
		public int collisionHeight;
		public int renderXShift=0;
		public int renderYShift=0;
		
		private DamageRenderer damageRenderer;
		protected Controller controller;
	
		
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
			collisionWidth=GameSystem.GRID_SIZE;
			collisionHeight=GameSystem.GRID_SIZE;
			
			damageRenderer=new DamageRenderer(this);
			controller=game.getController();
		}
		public void tick(){
			//updates position of char
			//maps the position to the closest "grid"
			damageRenderer.tick();
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
			g.drawImage(image,(int)x+renderXShift,(int)y+renderYShift,ssWidth,ssHeight,null);
			
		}
		public void renderDamage(Graphics g){
			damageRenderer.render(g);
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
			this.damageRenderer.renderDamage(damage);
		}
		public void applyDamage(int value, int invincibleDuration, GameObject target){
			if(target.invincible) return;
			else{
				target.setInvincible(invincibleDuration);
				target.takeDamage(value);
			}
		}
		
		
		
		public double getXAbsolute(){
			return this.x;
		}
		public double getYAbsolute(){
			return this.y;
		}
		public void placeBomb(int bombStrength,int bombLength,int duration){
			controller.addEntity(new Bomb(this.xGridNearest,this.yGridNearest,game,bombStrength,bombLength,duration));
		}
		public void kickBomb(){
			int kickedNum = Physics.onTopOfBomb(this, game.getBombList());
			if(kickedNum!=-1){
				Bomb kickedBomb=game.getBombList().get(kickedNum);
				if(orientation==ORIENTATION.UP){
					kickedBomb.velX=0;
					kickedBomb.velY=-1*10;
				}
				else if(orientation==ORIENTATION.DOWN){
					kickedBomb.velX=0;
					kickedBomb.velY=10;
				}
				else if(orientation==ORIENTATION.LEFT){
					kickedBomb.velX=-1*10;
					kickedBomb.velY=0;
				}
				else if(orientation==ORIENTATION.RIGHT){
					kickedBomb.velX=10;
					kickedBomb.velY=0;
				}
			}
		}
		
}
