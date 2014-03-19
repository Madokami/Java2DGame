package gameObject;

import java.awt.Image;

import system.GameSystem;



public class AnimationParameters {
	private int size = GameSystem.GRID_SIZE;
	private final double scale = GameSystem.GRID_SIZE*1.5;
	private final int yShift = GameSystem.GRID_SIZE/10;
	
	private GifAttribute walkGif,standGif,damagedGif,deathGif;
	/*
	public Image walkGif,standGif,damagedGif,deathGif;
	
	public  int walkWidth,walkHeight,walkX,walkY;
	public int standWidth,standHeight,standX,standY;
	public int damagedWidth,damagedHeight,damagedX,damagedY;
	public int deathWidth,deathHeight,deathX,deathY;
	public int ability1Width,ability1Height,ability1X,ability1Y;
	private int ability2Width,ability2Height,ability2X,ability2Y;
	*/
	public AnimationParameters(){
		
	}
	/*
	public void setWalk(int x,int y, int width, int height){
		walkX=x;
		walkY=y;
		walkWidth=width;
		walkHeight=height;
	}
	public void setStand(int x,int y,int width,int height){
		standX=x;
		standY=y;
		standWidth=width;
		standHeight=height;
	}
	public void setWalkGif(Image gif){
		walkGif=gif;
	}
	public Image getWalkGif(){
		return walkGif;
	}
	public void setStandGif(Image gif){
		standGif=gif;
	}
	public Image getStandGif(){
		return standGif;
	}
	public void setDamagedGif(Image gif){
		damagedGif=gif;
	}
	public Image getDamagedGif(){
		return damagedGif;
	}
	public void setDeathGif(Image gif){
		deathGif=gif;
	}
	public Image getDeathGif(){
		return deathGif;
	}
	
	*/
	public GifAttribute getWalkGif() {
		return walkGif;
	}
	public void setWalkGif(Image walkGif) {
		this.walkGif = new GifAttribute(walkGif);
	}
	public GifAttribute getStandGif() {
		return standGif;
	}
	public void setStandGif(Image standGif) {
		this.standGif = new GifAttribute(standGif);
	}
	public GifAttribute getDamagedGif() {
		return damagedGif;
	}
	public void setDamagedGif(Image damagedGif) {
		this.damagedGif = new GifAttribute(damagedGif);
	}
	public GifAttribute getDeathGif() {
		return deathGif;
	}
	public void setDeathGif(Image deathGif) {
		this.deathGif = new GifAttribute(deathGif);
	}
	
	
}
