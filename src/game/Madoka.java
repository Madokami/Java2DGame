package game;

public class Madoka extends Player{

	public Madoka(int x, int y, Game game) {
		super(x, y, game);
		bombLength = 3;
		bombStrength= 20;
		hp=100;
		ssX=7;
		ssY=1;
		ss=SpriteData.char1;
		
		
		image=ss.grabImage(ssX, ssY, size, size);
		
		soulGemSprite=SpriteData.gem_madoka;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.mdStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
		// TODO Auto-generated constructor stub
	}
	public void useUltimate(){
		game.c.addEntity(new PinkArrow(xGridNearest,yGridNearest,game,this));
	}
	public void useAbility1(){
		
	}
	public void playDeathSound(){
		int x = rand.nextInt(2);
		String url = "/sound/mdDeath";
		url=url.concat(Integer.toString(x)).concat(".wav");
		GameSystem.musicPlayer.playVoice(url);
	}
	public void playUltimateSound(){
		int x = rand.nextInt(3);
		String url = "/sound/hoUlt";
		url=url.concat(Integer.toString(x)).concat(".wav");
		System.out.println(url);
		GameSystem.musicPlayer.playVoice(url);
	}
	public void playLevelUpSound(){
		GameSystem.musicPlayer.playVoice("/sound/mdLevel.wav");
	}
	public void playItemFoundSound(){
		GameSystem.musicPlayer.playVoice("/sound/mdItem.wav");
	}
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
