package game;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class Controller implements Serializable {
	private LinkedList<FriendlyInterface> f= new LinkedList<FriendlyInterface>();
	private LinkedList<EnemyInterface> e= new LinkedList<EnemyInterface>();
	private LinkedList<PowerUps> p = new LinkedList<PowerUps>();
	FriendlyInterface fInt;
	EnemyInterface eInt;
	Random r = new Random();
	public Controller(){
		
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
		for(int i=0;i<p.size();i++){
			p.get(i).tick();
		}
	}
	public void render(Graphics g){
		for(int i=0;i<p.size();i++){
			p.get(i).render(g);
		}
		for(int i=0;i<f.size();i++){
			fInt=f.get(i);
			fInt.render(g);
		}
		for(int i=0;i<e.size();i++){
			eInt=e.get(i);
			eInt.render(g);
		}
	}
	public void addEntity(PowerUps o){
		p.add(o);
	}
	public void removeEntity(PowerUps o){
		p.remove(o);
	}
	public void addEntity(FriendlyInterface o){
		f.add(o);
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
	public LinkedList<FriendlyInterface> getFList(){
		return f;
	}
	public LinkedList<EnemyInterface> getEList(){
		return e;
	}
	public LinkedList<PowerUps> getPList(){
		return p;
	}
}
