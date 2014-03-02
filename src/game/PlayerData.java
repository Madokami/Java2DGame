package game;

import java.io.Serializable;

public class PlayerData implements Serializable {
	public double saHp,saMaxHp,saMp,saMaxMp,saSpd,saSoul,saMaxSoul;
	public int saBombStrength,saBombLength;
	
	public void loadDefaultValues(){
		saHp=100;
		saMaxHp=100;
		saMp=200;
		saMaxMp=200;
		saSpd=6;
		saSoul=5000;
		saMaxSoul=5000;
		saBombStrength = 40;
		saBombLength = 5;
	}
	
	public void loadPlayerStatus(Sayaka sa){
		sa.hp=saHp;
		sa.maxHp=saMaxHp;
		sa.mp=saMp;
		sa.maxMp=saMaxMp;
		sa.spd=saSpd;
		sa.bombStrength=saBombStrength;
		sa.bombLength=saBombLength;
		sa.soul=saSoul;
		sa.maxSoul=saMaxSoul;
	}
	
	public void upDatePlayerData(Sayaka sa){
		saHp=sa.hp;
		saMaxHp=sa.maxHp;
		saMp=sa.mp;
		saMaxMp=sa.maxMp;
		saSpd=sa.spd;
		saBombStrength=sa.bombStrength;
		saBombLength=sa.bombLength;
		saSoul=sa.soul;
		saMaxSoul=sa.maxSoul;
	}
}
