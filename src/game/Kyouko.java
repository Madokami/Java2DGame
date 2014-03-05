package game;

public class Kyouko extends Player{

	public Kyouko(int x, int y, Game game) {
		super(x, y, game);
		ssX=4;
		ssY=1;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, size, size);
	
		status = SpriteData.kyStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
	}

	public void useUltimate(){
		
	}
	public void playDeathSound(){
		int x = rand.nextInt(3);
		String url = "/sound/kyDeath";
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
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}

}
