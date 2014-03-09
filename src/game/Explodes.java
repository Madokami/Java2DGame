package game;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;

public class Explodes {
	public LinkedList<Fire> fireList = new LinkedList<Fire>();
	public LinkedList<Brick> brickList = new LinkedList<Brick>();
	
	public Game game;
	public boolean[][] wallArray;
	
	public Explodes(Game game){
		wallArray = new boolean[GameSystem.GRIDW+3][GameSystem.GRIDH+3];
		this.game=game;
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
	public void tick(){
		for(int i=0;i<fireList.size();i++){
			fireList.get(i).tick();
		}
		for(int i=0;i<brickList.size();i++){
			brickList.get(i).tick();
		}
	}
	public void render(Graphics g){
		for(int i=0;i<fireList.size();i++){
			fireList.get(i).render(g);
		}
		for(int i=0;i<brickList.size();i++){
			brickList.get(i).render(g);
		}
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
}