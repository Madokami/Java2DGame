package game;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class Controller implements Serializable {
	private LinkedList<Player> p = new LinkedList<Player>();
	private LinkedList<Bomb> b= new LinkedList<Bomb>();
	private LinkedList<Enemy> e= new LinkedList<Enemy>();
	private LinkedList<PowerUps> pow = new LinkedList<PowerUps>();
	Random r = new Random();
	public Controller(){
		
	}
	public void tick(){
		for(int i=0;i<p.size();i++){
			p.get(i).tick();
		}
		for(int i=0;i<b.size();i++){
			b.get(i).tick();
		}
		for(int i=0;i<e.size();i++){
			e.get(i).tick();
		}
		for(int i=0;i<pow.size();i++){
			pow.get(i).tick();
		}
	}
	public void render(Graphics g){
		for(int i=0;i<p.size();i++){
			p.get(i).render(g);
		}
		for(int i=0;i<pow.size();i++){
			pow.get(i).render(g);
		}
		for(int i=0;i<b.size();i++){
			b.get(i).render(g);
		}
		for(int i=0;i<e.size();i++){
			e.get(i).render(g);
		}
	}
	public void createPlayer(Player o){
		p.add(o);
	}
	public void addEntity(PowerUps o){
		pow.add(o);
	}
	public void removeEntity(PowerUps o){
		pow.remove(o);
	}
	public void addEntity(Bomb o){
		b.add(o);
	}
	
	public void removeEntity(Bomb o){
		b.remove(o);
	}
	public void addEntity(Enemy o){
		e.add(o);
	}
	public void removeEntity(Enemy o){
		e.remove(o);
	}
	public LinkedList<Bomb> getBList(){
		return b;
	}
	public LinkedList<Enemy> getEList(){
		return e;
	}
	public LinkedList<PowerUps> getPList(){
		return pow;
	}
	public Player getPlayer(){
		return p.get(0);
	}
	public void removePlayer(Player o){
		p.remove(o);
	}
}
