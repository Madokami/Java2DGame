package game;

public class Madoka extends Player{

	public Madoka(int x, int y, Game game) {
		super(x, y, game);
		bombLength = 3;
		bombStrength= 20;
		hp=100;
		ssX=7;
		ssY=1;
		ultCount=200;
		ss=SpriteData.char1;
		image=ss.grabImage(ssX, ssY, size, size);
		// TODO Auto-generated constructor stub
	}
	public void useUltimate(){
		
	}
	public void playDeathSound(){
		int x = rand.nextInt(2);
		String url = "/sound/mdDeath";
		url=url.concat(Integer.toString(x)).concat(".wav");
		game.musicPlayer.playVoice(url);
	}
	public void playUltimateSound(){
		int x = rand.nextInt(3);
		String url = "/sound/hoUlt";
		url=url.concat(Integer.toString(x)).concat(".wav");
		System.out.println(url);
		game.musicPlayer.playVoice(url);
	}

}
