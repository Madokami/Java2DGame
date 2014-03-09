package game;

public class Mami extends Player{

	public Mami(int x, int y, Game game) {
		super(x, y, game);
		ssX=1;
		ssY=1;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, size, size);
		
		soulGemSprite=SpriteData.gem_mami;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.maStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
	}
	public void useUltimate(){
		game.c.addEntity(new TiroFinale(this.xGridNearest,yGridNearest,game,this));
		playUltimateSound();
	}
	public void playDeathSound(){
		int x = rand.nextInt(3);
		String url = "/sound/maDeath";
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
		GameSystem.musicPlayer.playVoice("/sound/maLevel.wav");
	}
	public void playItemFoundSound(){
		GameSystem.musicPlayer.playVoice("/sound/maItem.wav");
	}
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
	
}
