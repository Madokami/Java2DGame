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
		
		pData.loadPlayerStatus(this);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
	}
	public void useUltimate(){
		game.event1.startEvent(1000, "sayakaCutIn");
		//game.event2.startEvent(5000, "timeStop");
		playUltimateSound();
		GameSystem.musicPlayer.playSwoosh();
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
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
