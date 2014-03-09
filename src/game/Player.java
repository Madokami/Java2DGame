package game;


import game.GameObject.ORIENTATION;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Player extends MovableObject{
	public int bombStrength;
	public int bombLength;
	public BufferedImage soulGemImage;
	public SpriteSheet soulGemSprite;
	public static final int soulGemWidth=64;
	public static final int soulGemHeight=96;
	public double soulGemCounter=0;
	public final double soulGemAnimationSpeed = 0.3;
	
	public BufferedImage expBar;
	
	public BufferedImageLoader loader;
	public double maxSoul;
	public double soul;
	public double mp,maxMp,maxHp;
	public double bombSpd;
	
	public SpriteSheet status;
	public BufferedImage okStatus,midDamageStatus,highDamageStatus,despairStatus,statusBg;
	public final int W_STATUS = 128;
	public final int H_STATUS = 128;
	
	
	public PlayerData pData;
	
	public int level;
	public BufferedImage[] levelImage;
	public BufferedImage[] soulGemValueImage;
	
	public double expCurrent;
	public int BP;
	public int score;
	
	public LevelUp levelUpdater;
	
	public int speedChangeDuration;
	public int speedChangeTimer;
	public int spdOriginal;
	
	public boolean damageMediumPlayed,damageHeavyPlayed,soulGemDarkSoundPlayed;

	public Player(int x, int y, Game game) {
		super(x, y, game);
		loader = new BufferedImageLoader();
		bombStrength = 50;
		bombLength=3;
		hp=100;
		mp=100;
		maxHp=hp;
		maxMp=mp;
		maxSoul=500;
		bombSpd=10;
		soul=maxSoul;
		spd=6;
		spdOriginal=spd;
		
		
		expBar=loader.loadImage("/image/expBar.png");
		
		statusBg=loader.loadImage("/image/statusBg.png");
		
		pData = game.pData;
		levelUpdater = new LevelUp();
		// TODO Auto-generated constructor stub
	}


	public void tick(){
		super.tick();
		this.curX=super.curX;
		this.curY=super.curY;
		
		if(hp<=0){
			playDeathSound();
			game.c.removePlayer(this);
			game.playerIsAlive=false;
		}
		if(soul>0){
			if(hp<maxHp){
				soul--;
				hp=hp+0.2;
				soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
			}
		}
		if(soul>0){
			if(mp<maxMp){
				soul--;
				mp=mp+0.2;
				soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
			}
		}
		
		
	
		//changes the player's "playerImage" depending on movement orientation
		levelUpdater.checkIfLevelUp(this);
		Animate.animate(this);
		Animate.animateGem(this);
	}
	
	public void render(Graphics g){
		super.render(g);
	}
	public void playDamagedSound() {
		
	}
	public void playDeathSound(){
		
	}
	public void playLevelUpSound(){
		
	}
	public void playItemFoundSound(){
		
	}
	public void playDamagedMediumSound(){
		
	}
	public void playDamagedHeavySound(){
		
	}
	public void playSoulGemDarkSound(){
		
	}

	

	public void useUltimate(){
		
	}
	public void setStatusImages() {
		okStatus = status.grabImage(1, 1, W_STATUS, H_STATUS);
		midDamageStatus = status.grabImage(2, 1, W_STATUS, H_STATUS);
		highDamageStatus = status.grabImage(3, 1, W_STATUS, H_STATUS);
		despairStatus = status.grabImage(4, 1, W_STATUS, H_STATUS);
		
	}

	public void renderPlayerStatus(Graphics g){
		//g.drawImage(statusBg,0, GameSystem.ABSHEIGHT-H_STATUS+28,null);
	if(soul==0){
		if(!soulGemDarkSoundPlayed) playSoulGemDarkSound();
		soulGemDarkSoundPlayed=true;
			g.drawImage(despairStatus,0, GameSystem.ABSHEIGHT-H_STATUS+14,null);
			return;
	}
	if(hp/maxHp>0.6){
		damageMediumPlayed=false;
		damageHeavyPlayed=false;
		g.drawImage(okStatus,0, GameSystem.ABSHEIGHT-H_STATUS+14,null);
	}
	else if(hp/maxHp<=0.6&&hp/maxHp>0.3){
		damageHeavyPlayed=false;
		if(!damageMediumPlayed) playDamagedMediumSound();
		damageMediumPlayed=true;
		g.drawImage(midDamageStatus,0, GameSystem.ABSHEIGHT-H_STATUS+14,null);
	}
	else if(hp/maxHp<=0.3){
		if(!damageHeavyPlayed) playDamagedHeavySound();
		damageHeavyPlayed=true;
		g.drawImage(highDamageStatus,0, GameSystem.ABSHEIGHT-H_STATUS+14,null);
	}
	}
	
	public void renderSoulGem(Graphics g){
		g.drawImage(soulGemImage,GameSystem.GAME_WIDTH/2-60,GameSystem.GAME_HEIGHT,null);
		for(int i=0;i<soulGemValueImage.length;i++){
			g.drawImage(soulGemValueImage[i],GameSystem.GAME_WIDTH/2-60+i*20,GameSystem.GAME_HEIGHT+70,null);
		}
	}
	public void renderExp(Graphics g){
		g.drawImage(expBar, 115, GameSystem.GAME_HEIGHT+83, null);
		double ratio = this.expCurrent/this.levelUpdater.expRequired;
		g.setColor(Color.YELLOW);
		g.fillRect(120, GameSystem.GAME_HEIGHT+86, (int)(ratio*67), 4);
	}
	public void renderPlayerLevel(Graphics g){
		for(int i=0;i<levelImage.length;i++){
			g.drawImage(levelImage[i],85+i*12,GameSystem.GAME_HEIGHT+82,null);
		}
	}
	public void kickBomb(){
		Bomb kicked = Physics.onTopOfBomb(this, game.bList);
		if(kicked!=null){
			if(direction.equals("up")){
				kicked.velY=-1*bombSpd;
			}
			else if(direction.equals("down")){
				kicked.velY=bombSpd;
			}
			else if(direction.equals("left")){
				kicked.velX=-1*bombSpd;
			}
			else if(direction.equals("right")){
				kicked.velX=bombSpd;
			}
		}
	}
	public void useAbility1(){
		
	}
	
	public void updatePlayerData() {
		// will be overwritten
		
	}
	public void changeSpeed(int value, int duration){
		speedChangeDuration=duration;
		speedChangeTimer=0;
		spdOriginal=spd;
		this.spd=value;
		refreshMovementSpeed();
	}
	public void restoreSpeed(){
		spd=spdOriginal;
	}
	
	
}
