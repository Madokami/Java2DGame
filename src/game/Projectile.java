package game;

public abstract class Projectile extends MovableObject {
	
	public int damage;
	public GameObject owner=null;
	public Projectile(int x, int y, Game game,GameObject o) {
		super(x, y, game);
		owner=o;
		damage = 10;
		// TODO Auto-generated constructor stub
	}
	public void tick(){
		removeIfOutSideScreen();
		int tempWall=Physics.hitWall(this, game.brickList);
		if(tempWall!=-1){
			applyDamage(game.brickList.get(tempWall));
		}
		int tempEnemy=Physics.collision(this, game.eList);
		if(tempEnemy!=-1){
			applyDamage(game.eList.get(tempEnemy));
		}
		x+=velX;
		y+=velY;
	}
	
	public void removeIfOutSideScreen(){
		if(x<=-ssWidth){
			game.c.removeEntity(this);
		}
		else if(y<=-ssHeight){
			game.c.removeEntity(this);
		}
		else if(x>=GameSystem.GAME_WIDTH){
			game.c.removeEntity(this);
		}
		else if(y>=GameSystem.GAME_HEIGHT){
			game.c.removeEntity(this);
		}
	}
	public void applyDamage(GameObject o){
		o.hp-=damage;
		game.c.removeEntity(this);
	}
}
