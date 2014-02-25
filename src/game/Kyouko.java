package game;

public class Kyouko extends Player{

	public Kyouko(int x, int y, Game game) {
		super(x, y, game);
		ssX=4;
		ssY=1;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, size, size);
	}
	public void useUltimate(){
		
	}
	public void playDeathSound(){
		int x = rand.nextInt(2);
		String url = "/sound/kyDeath";
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
