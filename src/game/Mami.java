package game;

public class Mami extends Player{

	public Mami(int x, int y, Game game) {
		super(x, y, game);
		ssX=1;
		ssY=1;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, size, size);
		
		status = SpriteData.maStatus;
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
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
