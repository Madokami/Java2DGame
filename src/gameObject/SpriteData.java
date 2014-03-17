package gameObject;

import system.BufferedImageLoader;

public class SpriteData {
	public static BufferedImageLoader loader;
	public static SpriteSheet char2;
	public static SpriteSheet bomb;
	public static SpriteSheet bricks;
	public static SpriteSheet upgrades;
	public static SpriteSheet char1;
	public static SpriteSheet char3;
	
	public static SpriteSheet mdStatus;
	public static SpriteSheet hoStatus;
	public static SpriteSheet saStatus;
	public static SpriteSheet maStatus;
	public static SpriteSheet kyStatus;
	
	public static SpriteSheet num_18_16;
	public static SpriteSheet num_20_36;
	public static SpriteSheet numDamage;
	
	public static SpriteSheet magicalArraw;
	public static SpriteSheet tiroFinale;
	public static SpriteSheet saDash;
	
	
	public static SpriteSheet gem_madoka;
	public static SpriteSheet gem_homura;
	public static SpriteSheet gem_mami;
	public static SpriteSheet gem_sayaka;
	public static SpriteSheet gem_kyouko;
	
	//Enemies start here
	public static SpriteSheet mrPringles;
	public static SpriteSheet mrPringles2;
	public static SpriteSheet adelbertMini;
	public static SpriteSheet gertrud;
	
	
	public static SpriteSheet mdRunning;
	public static SpriteSheet hoStand;
	
	
	public SpriteData(){
		loader = new BufferedImageLoader();
		char2 = new SpriteSheet(loader.loadImage("/PuellaSet2.png"));
		bomb = new SpriteSheet(loader.loadImage("/bomb.png"));
		bricks = new SpriteSheet(loader.loadImage("/bricks.png"));
		upgrades = new SpriteSheet(loader.loadImage("/powerUps.png"));
		char1 = new SpriteSheet(loader.loadImage("/PuellaSet1.png"));
		char3 = new SpriteSheet(loader.loadImage("/image/PuellaSet3.png"));
		
		mdStatus = new SpriteSheet(loader.loadImage("/image/mdStatus.png"));
		hoStatus = new SpriteSheet(loader.loadImage("/image/hoStatus.png"));
		saStatus = new SpriteSheet(loader.loadImage("/image/saStatus.png"));
		maStatus = new SpriteSheet(loader.loadImage("/image/maStatus.png"));
		kyStatus = new SpriteSheet(loader.loadImage("/image/kyStatus.png"));
		
		num_18_16 =  new SpriteSheet(loader.loadImage("/image/num_18_16.png"));
		num_20_36 =  new SpriteSheet(loader.loadImage("/image/num_20_36.png"));
		numDamage = new SpriteSheet(loader.loadImage("/image/spriteSheet/damageNumbers.png"));
		
		magicalArraw =  new SpriteSheet(loader.loadImage("/image/magicalArrowFull.png"));
		tiroFinale = new SpriteSheet(loader.loadImage("/image/projectiles/tiroTest2.png"));
		saDash = new SpriteSheet(loader.loadImage("/image/projectiles/saDash.png"));
		
		
		gem_madoka =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_madoka.png"));
		gem_homura =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_homura.png"));
		gem_mami =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_mami.png"));
		gem_sayaka =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_sayaka.png"));
		gem_kyouko =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_kyouko.png"));
		
		//Enemies start here
		mrPringles = new SpriteSheet(loader.loadImage("/image/spriteSheet/mr_pringles.png"));
		mrPringles2 = new SpriteSheet(loader.loadImage("/image/spriteSheet/mr_pringlesRetarded.png"));
		adelbertMini = new SpriteSheet(loader.loadImage("/image/spriteSheet/adelbertMini.png"));
		gertrud = new SpriteSheet(loader.loadImage("/image/spriteSheet/gertrud.png"));
		
		
		mdRunning = new SpriteSheet(loader.loadImage("/image/spriteSheet/actors/player/madoka/mdRunning.png"));
		hoStand = new SpriteSheet(loader.loadImage("/image/spriteSheet/actors/player/homura/hoStand.png"));
		
		
	}
}
