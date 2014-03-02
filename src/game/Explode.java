package game;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;

public class Explode {
	public LinkedList<Fire> f;
	public LinkedList<WallInterface> w = new LinkedList<WallInterface>();
	
	public Game game;
	public boolean[][] wallArray;
	WallInterface wInt;
	
	public Explode(Game game){
		f = new LinkedList<Fire>();
		wallArray = new boolean[GameSystem.GRIDW+3][GameSystem.GRIDH+3];
		this.game=game;
	}
	public void createExplosion(int x, int y, int size,int strength){
		GameSystem.musicPlayer.playExplosion();
		Game.explosionPlayed=true;
		f.add(new Fire(x,y,game,strength));
		for(int i=1;i<=size;i++){
			if(x-i<1)
				break;
			if(wallArray[x-i][y]==true){
				f.add(new Fire(x-i,y,game,strength));
				break;
			}
			f.add(new Fire(x-i,y,game,strength));
		}
		for(int i=1;i<=size;i++){
			if(x+i>GameSystem.GRIDW)
				break;
			if(wallArray[x+i][y]==true){
				f.add(new Fire(x+i,y,game,strength));
				break;
			}
			f.add(new Fire(x+i,y,game,strength));
		}
		for(int j=1;j<=size;j++){
			if(y+j>GameSystem.GRIDH)
				break;
			if(wallArray[x][y+j]==true){
				f.add(new Fire(x,y+j,game,strength));
				break;
			}
			f.add(new Fire(x,y+j,game,strength));
		}
		for(int j=1;j<=size;j++){
			if(y-j<1)
				break;
			if(wallArray[x][y-j]==true){
				f.add(new Fire(x,y-j,game,strength));
				break;
			}
			f.add(new Fire(x,y-j,game,strength));
		}
	}
	public void tick(){
		for(int i=0;i<f.size();i++){
			f.get(i).tick();
		}
		for(int i=0;i<w.size();i++){
			w.get(i).tick();
		}
	}
	public void render(Graphics g){
		for(int i=0;i<f.size();i++){
			f.get(i).render(g);
		}
		for(int i=0;i<w.size();i++){
			wInt=w.get(i);
			wInt.render(g);
		}
	}
	public void removeFire(Fire fire){
		f.remove(fire);
	}
	public void addEntity(Brick o){
		w.add(o);
		wallArray[o.xGridNearest][o.yGridNearest]=true;
	}
	public void removeEntity(Brick o){
		wallArray[o.xGridNearest][o.yGridNearest]=false;
		w.remove(o);
	}
	public LinkedList<WallInterface> getWList(){
		return w;
	}
}
