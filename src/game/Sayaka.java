package game;

public class Sayaka extends Player{

	public Sayaka(int x, int y, Game game) {
		super(x, y, game);
		
		status = SpriteData.saStatus;
		setStatusImages();
		
		ssX=1;
		ssY=5;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, size, size);	
		
		soulGemSprite=SpriteData.gem_sayaka;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
	}
	public void useUltimate(){
		/*
		game.event1.startEvent(1000, "sayakaCutIn");
		//game.event2.startEvent(5000, "timeStop");
		playUltimateSound();
		GameSystem.musicPlayer.playSwoosh();
		*/
		GameSystem.musicPlayer.playSwoosh();
		game.c.addEntity(new SaDash(xGridNearest,yGridNearest,game,this));
		startCharge(40,5);
	}
	public void playDeathSound(){
		int x = rand.nextInt(2);
		String url = "/sound/saDeath";
		url=url.concat(Integer.toString(x)).concat(".wav");
		GameSystem.musicPlayer.playVoice(url);
	}
	public void playUltimateSound(){
		
		String url = "/sound/saUlt.wav";
		System.out.println(url);
		GameSystem.musicPlayer.playVoice(url);
	}
	public void playLevelUpSound(){
		GameSystem.musicPlayer.playVoice("/sound/saLevel.wav");
	}
	public void playItemFoundSound(){
		GameSystem.musicPlayer.playVoice("/sound/saItem.wav");
	}
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
