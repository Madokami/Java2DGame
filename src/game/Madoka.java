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
		
		status = SpriteData.mdStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
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
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
