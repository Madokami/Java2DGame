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
	
}
