package game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	private LinkedList<FriendlyInterface> f= new LinkedList<FriendlyInterface>();
	private LinkedList<EnemyInterface> e= new LinkedList<EnemyInterface>();
	private LinkedList<WallInterface> w = new LinkedList<WallInterface>();
	
	WallInterface wInt;
	FriendlyInterface fInt;
	EnemyInterface eInt;
	Random r = new Random();
	Game game;
	public Controller(Game game){
		this.game = game;
	}
	public void tick(){
		for(int i=0;i<f.size();i++){
			fInt=f.get(i);
			fInt.tick();
		}
		for(int i=0;i<e.size();i++){
			eInt=e.get(i);
			eInt.tick();
		}
		for(int i=0;i<w.size();i++){
			wInt=w.get(i);
			wInt.tick();
		}
	}
	public void render(Graphics g){
		for(int i=0;i<f.size();i++){
			fInt=f.get(i);
			fInt.render(g);
		}
		for(int i=0;i<e.size();i++){
			eInt=e.get(i);
			eInt.render(g);
		}
		for(int i=0;i<w.size();i++){
			wInt=w.get(i);
			wInt.render(g);
		}
	}
	public void addEntity(FriendlyInterface o){
		f.add(o);
	}
	public void addEntity(WallInterface o){
		w.add(o);
	}
	public void removeEntity(FriendlyInterface o){
		f.remove(o);
	}
	public void addEntity(EnemyInterface o){
		e.add(o);
	}
	public void removeEntity(EnemyInterface o){
		e.remove(o);
	}
	public void removeEntity(WallInterface o){
		w.remove(o);
	}
	public LinkedList<FriendlyInterface> getFList(){
		return f;
	}
	public LinkedList<WallInterface> getWList(){
		return w;
	}
	public LinkedList<EnemyInterface> getEList(){
		return e;
	}
}
