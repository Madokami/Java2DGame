package game;

public class Kyouko extends Player{

	public Kyouko(int x, int y, Game game) {
		super(x, y, game);
		ssX=4;
		ssY=1;
		ss=SpriteData.char3;
		image=ss.grabImage(ssX, ssY, size, size);
	
		soulGemSprite=SpriteData.gem_kyouko;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.kyStatus;
		setStatusImages();
		
		pData.loadPlayerStatus(this);
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
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
	public void playLevelUpSound(){
		GameSystem.musicPlayer.playVoice("/sound/kyLevel.wav");
	}
	public void playItemFoundSound(){
		GameSystem.musicPlayer.playVoice("/sound/kyItem.wav");
	}
	public void playDamagedMediumSound(){
		GameSystem.musicPlayer.playVoice("/sound/kyDamage0.wav");
	}
	public void playDamagedHeavySound(){
		GameSystem.musicPlayer.playVoice("/sound/kyDamage1.wav");
	}
	public void playSoulGemDarkSound(){
		GameSystem.musicPlayer.playVoice("/sound/kySoul1.wav");
	}

}
