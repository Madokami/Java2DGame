package game;

public class SpriteData {
	public static BufferedImageLoader loader = new BufferedImageLoader();
	public static SpriteSheet char2 = new SpriteSheet(loader.loadImage("/PuellaSet2.png"));
	public static SpriteSheet bomb = new SpriteSheet(loader.loadImage("/bomb.png"));
	public static SpriteSheet bricks = new SpriteSheet(loader.loadImage("/bricks.png"));
	public static SpriteSheet upgrades = new SpriteSheet(loader.loadImage("/powerUps.png"));
	public static SpriteSheet char1 = new SpriteSheet(loader.loadImage("/PuellaSet1.png"));
	public static SpriteSheet char3 = new SpriteSheet(loader.loadImage("/image/PuellaSet3.png"));
	
	public static SpriteSheet mdStatus = new SpriteSheet(loader.loadImage("/image/mdStatus.png"));
	public static SpriteSheet hoStatus = new SpriteSheet(loader.loadImage("/image/hoStatus.png"));
	public static SpriteSheet saStatus = new SpriteSheet(loader.loadImage("/image/saStatus.png"));
	public static SpriteSheet maStatus = new SpriteSheet(loader.loadImage("/image/maStatus.png"));
	public static SpriteSheet kyStatus = new SpriteSheet(loader.loadImage("/image/kyStatus.png"));
	
	public static SpriteSheet num_18_16 =  new SpriteSheet(loader.loadImage("/image/num_18_16.png"));
	public static SpriteSheet num_20_36 =  new SpriteSheet(loader.loadImage("/image/num_20_36.png"));
	
	public static SpriteSheet magicalArraw =  new SpriteSheet(loader.loadImage("/image/magicalArrowFull.png"));
	public static SpriteSheet tiroFinale = new SpriteSheet(loader.loadImage("/image/projectiles/tiroTest2.png"));
	public static SpriteSheet saDash = new SpriteSheet(loader.loadImage("/image/projectiles/saDash.png"));
	
	
	public static SpriteSheet gem_madoka =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_madoka.png"));
	public static SpriteSheet gem_homura =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_homura.png"));
	public static SpriteSheet gem_mami =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_mami.png"));
	public static SpriteSheet gem_sayaka =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_sayaka.png"));
	public static SpriteSheet gem_kyouko =  new SpriteSheet(loader.loadImage("/image/soulGem/gem_kyouko.png"));
	
	//Enemies start here
	public static SpriteSheet mrPringles = new SpriteSheet(loader.loadImage("/image/spriteSheet/mr_pringles.png"));
	public static SpriteSheet mrPringles2 = new SpriteSheet(loader.loadImage("/image/spriteSheet/mr_pringlesRetarded.png"));
	public static SpriteSheet adelbertMini = new SpriteSheet(loader.loadImage("/image/spriteSheet/adelbertMini.png"));
	public static SpriteSheet gertrud = new SpriteSheet(loader.loadImage("/image/spriteSheet/gertrud.png"));
	
	
	public static SpriteSheet mdRunning = new SpriteSheet(loader.loadImage("/image/spriteSheet/mdRunning.png"));
}
