package game;

import java.io.Serializable;

public class PlayerData implements Serializable {
	public double saHp,saMaxHp,saMp,saMaxMp,saSoul,saMaxSoul,saBombSpd;
	public int saBombStrength,saBombLength,saSpd,saExpCurrent,saLevel,saBP;
	public static int saHpLimit,saMpLimit,saSpdLimit,saSoulLimit,saBombSpdLimit,saBombStrengthLimit,saBombLengthLimit;
	
	public double hoHp,hoMaxHp,hoMp,hoMaxMp,hoSoul,hoMaxSoul,hoBombSpd;
	public int hoBombStrength,hoBombLength,hoSpd,hoExpCurrent,hoLevel,hoBP;
	public static int hoHpLimit,hoMpLimit,hoSpdLimit,hoSoulLimit,hoBombSpdLimit,hoBombStrengthLimit,hoBombLengthLimit;
	
	public double mdHp,mdMaxHp,mdMp,mdMaxMp,mdSoul,mdMaxSoul,mdBombSpd;
	public int mdBombStrength,mdBombLength,mdSpd,mdExpCurrent,mdLevel,mdBP;
	public static int mdHpLimit,mdMpLimit,mdSpdLimit,mdSoulLimit,mdBombSpdLimit,mdBombStrengthLimit,mdBombLengthLimit;
	
	public double maHp,maMaxHp,maMp,maMaxMp,maSoul,maMaxSoul,maBombSpd;
	public int maBombStrength,maBombLength,maSpd,maExpCurrent,maLevel,maBP;
	public static int maHpLimit,maMpLimit,maSpdLimit,maSoulLimit,maBombSpdLimit,maBombStrengthLimit,maBombLengthLimit;
	
	public double kyHp,kyMaxHp,kyMp,kyMaxMp,kySoul,kyMaxSoul,kyBombSpd;
	public int kyBombStrength,kyBombLength,kySpd,kyExpCurrent,kyLevel,kyBP;
	public static int kyHpLimit,kyMpLimit,kySpdLimit,kySoulLimit,kyBombSpdLimit,kyBombStrengthLimit,kyBombLengthLimit;
	
	public void loadDefaultValues(){
		saHp=100;
		//saMaxHp=100;
		saMp=200;
		//saMaxMp=200;
		saSpd=6;
		saSoul=500;
		//saMaxSoul=500;
		saBombStrength = 40;
		saBombLength = 5;
		saExpCurrent = 0;
		saLevel = 1;
		saBP=0;
		saBombSpd=10;
		
		hoHp=100;
		//hoMaxHp=100;
		hoMp=200;
		//hoMaxMp=200;
		hoSpd=6;
		hoSoul=500;
		//hoMaxSoul=500;
		hoBombStrength = 40;
		hoBombLength = 5;
		hoExpCurrent = 0;
		hoLevel=1;
		hoBP=0;
		hoBombSpd=10;
		
		mdHp=100;
		//mdMaxHp=100;
		mdMp=200;
		//mdMaxMp=200;
		mdSpd=6;
		mdSoul=500;
		//mdMaxSoul=500;
		mdBombStrength = 40;
		mdBombLength = 5;
		mdExpCurrent=0;
		mdLevel = 1;
		mdBP=0;
		mdBombSpd=10;
		
		maHp=100;
		//maMaxHp=100;
		maMp=200;
		//maMaxMp=200;
		maSpd=6;
		maSoul=500;
		//maMaxSoul=500;
		maBombStrength = 40;
		maBombLength = 5;
		maExpCurrent=0;
		maLevel=1;
		maBP=0;
		maBombSpd=10;
		
		kyHp=100;
		//kyMaxHp=100;
		kyMp=200;
		//kyMaxMp=200;
		kySpd=6;
		kySoul=500;
		//kyMaxSoul=500;
		kyBombStrength = 40;
		kyBombLength = 5;
		kyExpCurrent=0;
		kyLevel = 1;
		kyBP=0;
		kyBombSpd=10;
	}
	
	public void loadPlayerStatus(Sayaka sa){
		sa.hp=saHp;
		//sa.maxHp=saMaxHp;
		sa.mp=saMp;
		//sa.maxMp=saMaxMp;
		sa.spd=saSpd;
		sa.bombStrength=saBombStrength;
		sa.bombLength=saBombLength;
		sa.soul=saSoul;
		//sa.maxSoul=saMaxSoul;
		sa.expCurrent =saExpCurrent;
		sa.level=saLevel;
		sa.BP=saBP;
		sa.bombSpd=saBombSpd;
	}
	
	public void loadPlayerStatus(Homura ho){
		ho.hp=hoHp;
		//ho.maxHp=hoMaxHp;
		ho.mp=hoMp;
		//ho.maxMp=hoMaxMp;
		ho.spd=hoSpd;
		ho.bombStrength=hoBombStrength;
		ho.bombLength=hoBombLength;
		ho.soul=hoSoul;
		//ho.maxSoul=hoMaxSoul;
		ho.expCurrent = hoExpCurrent;
		ho.level=hoLevel;
		ho.BP=hoBP;
		ho.bombSpd=hoBombSpd;
	}
	
	public void loadPlayerStatus(Madoka md){
		md.hp=mdHp;
		//md.maxHp=mdMaxHp;
		md.mp=mdMp;
		//md.maxMp=mdMaxMp;
		md.spd=mdSpd;
		md.bombStrength=mdBombStrength;
		md.bombLength=mdBombLength;
		md.soul=mdSoul;
		//md.maxSoul=mdMaxSoul;
		md.expCurrent =mdExpCurrent;
		md.level=mdLevel;
		md.BP=mdBP;
		md.bombSpd=mdBombSpd;
	}
	
	public void loadPlayerStatus(Mami ma){
		ma.hp=maHp;
		//ma.maxHp=maMaxHp;
		ma.mp=maMp;
		//ma.maxMp=maMaxMp;
		ma.spd=maSpd;
		ma.bombStrength=maBombStrength;
		ma.bombLength=maBombLength;
		ma.soul=maSoul;
		//ma.maxSoul=maMaxSoul;
		ma.expCurrent =maExpCurrent;
		ma.level=maLevel;
		ma.BP=maBP;
		ma.bombSpd=maBombSpd;
	}
	
	public void loadPlayerStatus(Kyouko	ky){
		ky.hp=kyHp;
		//ky.maxHp=kyMaxHp;
		ky.mp=kyMp;
		//ky.maxMp=kyMaxMp;
		ky.spd=kySpd;
		ky.bombStrength=kyBombStrength;
		ky.bombLength=kyBombLength;
		ky.soul=kySoul;
		//ky.maxSoul=kyMaxSoul;
		ky.expCurrent =kyExpCurrent;
		ky.level=kyLevel;
		ky.BP=kyBP;
		ky.bombSpd=kyBombSpd;
	}
	
	//these methods will update the PlayerData Class with current values in the Player Class. 
	//This could doing so could result in the player getting permanently buffed with temp bonus.
	
	public void upDatePlayerData(Sayaka sa){
		/*
		saHp=sa.hp;
		saMaxHp=sa.maxHp;
		saMp=sa.mp;
		saMaxMp=sa.maxMp;
		saSpd=sa.spd;
		saBombStrength=sa.bombStrength;
		saBombLength=sa.bombLength;
		saSoul=sa.soul;
		saMaxSoul=sa.maxSoul;
		*/
		saExpCurrent=sa.expCurrent;
		saLevel=sa.level;
		saBP=sa.BP;
	}
	
	public void upDatePlayerData(Homura ho){
		/*
		hoHp=ho.hp;
		hoMaxHp=ho.maxHp;
		hoMp=ho.mp;
		hoMaxMp=ho.maxMp;
		hoSpd=ho.spd;
		hoBombStrength=ho.bombStrength;
		hoBombLength=ho.bombLength;
		hoSoul=ho.soul;
		hoMaxSoul=ho.maxSoul;
		*/
		hoExpCurrent=ho.expCurrent;
		hoLevel=ho.level;
		hoBP=ho.BP;
	}
	
	public void upDatePlayerData(Madoka md){
		/*
		mdHp=md.hp;
		mdMaxHp=md.maxHp;
		mdMp=md.mp;
		mdMaxMp=md.maxMp;
		mdSpd=md.spd;
		mdBombStrength=md.bombStrength;
		mdBombLength=md.bombLength;
		mdSoul=md.soul;
		mdMaxSoul=md.maxSoul;
		*/
		mdExpCurrent=md.expCurrent;
		mdLevel=md.level;
		mdBP=md.BP;
	}
	
	public void upDatePlayerData(Mami ma){
		/*
		maHp=ma.hp;
		maMaxHp=ma.maxHp;
		maMp=ma.mp;
		maMaxMp=ma.maxMp;
		maSpd=ma.spd;
		maBombStrength=ma.bombStrength;
		maBombLength=ma.bombLength;
		maSoul=ma.soul;
		maMaxSoul=ma.maxSoul;
		*/
		maExpCurrent=ma.expCurrent;
		maLevel=ma.level;
		maBP=ma.BP;
	}
	
	public void upDatePlayerData(Kyouko ky){
		/*
		kyHp=ky.hp;
		kyMaxHp=ky.maxHp;
		kyMp=ky.mp;
		kyMaxMp=ky.maxMp;
		kySpd=ky.spd;
		kyBombStrength=ky.bombStrength;
		kyBombLength=ky.bombLength;
		kySoul=ky.soul;
		kyMaxSoul=ky.maxSoul;
		*/
		kyExpCurrent=ky.expCurrent;
		kyLevel=ky.level;
		kyBP=ky.BP;
	}
}
