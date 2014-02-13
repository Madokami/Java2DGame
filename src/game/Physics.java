package game;

import java.util.LinkedList;

public class Physics {
	public static boolean collision(FriendlyInterface f,LinkedList<EnemyInterface> ei){
		for(int i=0;i<ei.size();i++){
			if(f.getBounds(32,32).intersects(ei.get(i).getBounds(32,32))){
				return true;
			}
		}
		return false;
	}
	public static boolean collision(FriendlyInterface f,EnemyInterface e){
		if(f.getBounds(32,32).intersects(e.getBounds(32,32))){
			return true;
		}
		return false;
	}
	public static int collision(WallInterface w,LinkedList<EnemyInterface> ei){
		for(int i=0;i<ei.size();i++){
			if(w.getBounds(32,32).intersects(ei.get(i).getBounds(32,32))){
				return i;
			}
		}
		return -1;
	}
	public static boolean hitWall(Object f,LinkedList<WallInterface> wi){
		for(int i=0;i<wi.size();i++){
			if(f.getBounds(32, 32).intersects(wi.get(i).getBounds(32, 32)))
				return true;
		}
		return false;
	}
	public static boolean hitByAttack(Object f, LinkedList<Fire> fi){
		for(int i=0;i<fi.size();i++){
			if(f.getBounds(Game.SIZE, Game.SIZE).intersects(fi.get(i).getBounds(Game.SIZE, Game.SIZE)))
				return true;
		}
		return false;
	}
}
