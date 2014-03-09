package game;

import java.util.LinkedList;

public class Physics {
	public static int collision(Player p,LinkedList<Enemy> ei){
		for(int i=0;i<ei.size();i++){
			if(p.getBounds(p.collisionWidth,p.collisionHeight).intersects(ei.get(i).getBounds(ei.get(i).collisionWidth,ei.get(i).collisionHeight))){
				return i;
			}
		}
		return -1;
	}
	public static Bomb onTopOfBomb(Player p,LinkedList<Bomb> bList){
		Bomb ret =null;
		for(int i=0;i<bList.size();i++){
			if(p.xGridNearest==bList.get(i).xGridNearest&&p.yGridNearest==bList.get(i).yGridNearest){
				ret = bList.get(i);
				return ret;
			}
		}
		return ret;
	}
	public static int collision(GameObject w,LinkedList<Enemy> ei){
		for(int i=0;i<ei.size();i++){
			if(w.getBounds(w.collisionWidth,w.collisionHeight).intersects(ei.get(i).getBounds(ei.get(i).collisionWidth,ei.get(i).collisionHeight))){
				return i;
			}
		}
		return -1;
	}
	
	public static int hitBomb(GameObject w,LinkedList<Bomb> list){
		for(int i=0;i<list.size();i++){
			if(w.getBounds(w.collisionWidth,w.collisionHeight).intersects(list.get(i).getBounds(list.get(i).collisionWidth,list.get(i).collisionHeight))){
				return i;
			}
		}
		return -1;
	}
	
	public static int hitWall(GameObject f,LinkedList<Brick> wi){
		for(int i=0;i<wi.size();i++){
			if(f.getBounds(f.collisionWidth-1, f.collisionHeight-1).intersects(wi.get(i).getBounds(31, 31)))
				return i;
		}
		return -1;
	}
	
	public static boolean collide(GameObject x,GameObject y){
		if(x.getBounds(x.collisionWidth, x.collisionHeight).intersects(y.getBounds(y.collisionWidth,y.collisionHeight))){
			return true;
		}
		return false;
	}
}
