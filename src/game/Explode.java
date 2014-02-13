package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Explode {
	public LinkedList<Fire> f;
	public Game game;
	public Explode(Game game){
		f = new LinkedList<Fire>();
		this.game = game;
	}
	public void createExplosion(int x, int y, int size,int strength){
		for(int i=x-size;i<=x+size;i++){
			f.add(new Fire(i,y,game,strength));
		}
		for(int i=y-size;i<=y+size;i++){
			f.add(new Fire(x,i,game,strength));
		}
	}
	public void tick(){
		for(int i=0;i<f.size();i++){
			f.get(i).tick();
		}
	}
	public void render(Graphics g){
		for(int i=0;i<f.size();i++){
			f.get(i).render(g);
		}
	}
	public void removeFire(Fire fire){
		f.remove(fire);
	}
}
