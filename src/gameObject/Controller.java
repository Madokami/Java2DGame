package gameObject;

import game.Game;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

import system.GameSystem;

public class Controller implements Serializable {
	public Game game;
	
	private LinkedList<Player> player = new LinkedList<Player>();
	private LinkedList<Bomb> bombList= new LinkedList<Bomb>();
	private LinkedList<Enemy> enemyList= new LinkedList<Enemy>();
	private LinkedList<PowerUps> powerupList = new LinkedList<PowerUps>();
	private LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
	public LinkedList<Fire> fireList = new LinkedList<Fire>();
	public LinkedList<Brick> brickList = new LinkedList<Brick>();
	public boolean[][] wallArray;
	public boolean[][] bombArray;
	
	private DamageRenderer damageRenderer;
	
	Random r = new Random();
	public Controller(Game game){
		this.game=game;
		wallArray = new boolean[GameSystem.GRIDW+3][GameSystem.GRIDH+3];
		bombArray = new boolean[GameSystem.GRIDW+3][GameSystem.GRIDH+3];
	}
	public void tick(){
		for(int i=0;i<player.size();i++){
			player.get(i).tick();
		}
		for(int i=0;i<bombList.size();i++){
			bombList.get(i).tick();
		}
		for(int i=0;i<enemyList.size();i++){
			enemyList.get(i).tick();
		}
		for(int i=0;i<powerupList.size();i++){
			powerupList.get(i).tick();
		}
		for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).tick();
		}
		
		for(int i=0;i<fireList.size();i++){
			fireList.get(i).tick();
		}
		for(int i=0;i<brickList.size();i++){
			brickList.get(i).tick();
		}
	}
	public void render(Graphics g){
		
		for(int i=0;i<powerupList.size();i++){
			powerupList.get(i).render(g);
		}
		for(int i=0;i<bombList.size();i++){
			bombList.get(i).render(g);
		}
		for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).render(g);
		}
		
		for(int i=0;i<fireList.size();i++){
			fireList.get(i).render(g);
		}
		for(int i=0;i<brickList.size();i++){
			brickList.get(i).render(g);
		}
		for(int i=0;i<enemyList.size();i++){
			enemyList.get(i).render(g);
		}
		for(int i=0;i<player.size();i++){
			player.get(i).render(g);
		}
		//renders damage as image dealt to object. damage must be above the images at all time
		for(int i=0;i<player.size();i++){
			player.get(i).renderDamage(g);
		}
		for(int i=0;i<powerupList.size();i++){
			powerupList.get(i).renderDamage(g);
		}
		for(int i=0;i<bombList.size();i++){
			bombList.get(i).renderDamage(g);
		}
		for(int i=0;i<enemyList.size();i++){
			enemyList.get(i).renderDamage(g);
		}
		for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).renderDamage(g);
		}
		
		for(int i=0;i<fireList.size();i++){
			fireList.get(i).renderDamage(g);
		}
		for(int i=0;i<brickList.size();i++){
			brickList.get(i).renderDamage(g);
		}
	}
	public void createPlayer(Player o){
		player.add(o);
	}
	public void addEntity(PowerUps o){
		powerupList.add(o);
	}
	public void removeEntity(PowerUps o){
		powerupList.remove(o);
	}
	public void addEntity(Projectile o){
		projectiles.add(o);
	}
	public void removeEntity(Projectile o){
		projectiles.remove(o);
	}
	public void addEntity(Bomb o){
		bombList.add(o);
		bombArray[o.xGridNearest][o.yGridNearest]=true;
	}
	
	public void removeEntity(Bomb o){
		bombList.remove(o);
		bombArray[o.xGridNearest][o.yGridNearest]=false;
	}
	public boolean[][] getBombArray(){
		return this.bombArray;
	}
	public boolean[][] getWallArray(){
		return this.wallArray;
	}
	public void addEntity(Enemy o){
		enemyList.add(o);
	}
	public void removeEntity(Enemy o){
		enemyList.remove(o);
	}
	public LinkedList<Bomb> getBList(){
		return bombList;
	}
	public LinkedList<Enemy> getEList(){
		return enemyList;
	}
	public LinkedList<PowerUps> getPList(){
		return powerupList;
	}
	public LinkedList<Projectile> getProjectileList(){
		return projectiles;
	}
	public Player getPlayer(){
		return player.get(0);
	}
	public void removePlayer(Player o){
		player.remove(o);
	}
	
	public void removeFire(Fire fire){
		fireList.remove(fire);
	}
	public void addEntity(Brick o){
		brickList.add(o);
		wallArray[o.xGridNearest][o.yGridNearest]=true;
	}
	public void removeEntity(Brick o){
		wallArray[o.xGridNearest][o.yGridNearest]=false;
		brickList.remove(o);
	}
	public LinkedList<Brick> getBrickList(){
		return brickList;
	}
	
	public void createExplosion(int x, int y, int size,int strength){
		GameSystem.musicPlayer.playExplosion();
		Game.explosionPlayed=true;
		fireList.add(new Fire(x,y,game,strength));
		for(int i=1;i<=size;i++){
			if(x-i<1)
				break;
			if(wallArray[x-i][y]==true){
				fireList.add(new Fire(x-i,y,game,strength));
				break;
			}
			fireList.add(new Fire(x-i,y,game,strength));
		}
		for(int i=1;i<=size;i++){
			if(x+i>GameSystem.GRIDW)
				break;
			if(wallArray[x+i][y]==true){
				fireList.add(new Fire(x+i,y,game,strength));
				break;
			}
			fireList.add(new Fire(x+i,y,game,strength));
		}
		for(int j=1;j<=size;j++){
			if(y+j>GameSystem.GRIDH)
				break;
			if(wallArray[x][y+j]==true){
				fireList.add(new Fire(x,y+j,game,strength));
				break;
			}
			fireList.add(new Fire(x,y+j,game,strength));
		}
		for(int j=1;j<=size;j++){
			if(y-j<1)
				break;
			if(wallArray[x][y-j]==true){
				fireList.add(new Fire(x,y-j,game,strength));
				break;
			}
			fireList.add(new Fire(x,y-j,game,strength));
		}
	}
}
