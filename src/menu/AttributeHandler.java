package menu;

import game.Game;
import game.PlayerData;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import menu.MenuChar.CHARACTER;
import system.IntToImage;

public class AttributeHandler {
	public PlayerData pData;
	
	public double hpOriginal,mpOriginal,soulOriginal;
	public int bombStrengthOriginal,bombLengthOriginal,BPOriginal,spdOriginal;	
	public double hpCur,mpCur,soulCur;
	public int bombStrengthCur,bombLengthCur,BPCur,spdCur;
	
	public BufferedImage[] hp,mp,soul,spd,bombStrength,bombLength,BPCurImg,BPOriImg;
	
	public int hpCost = 1, hpValue = 5;
	public int mpCost = 1, mpValue = 5;
	public int soulCost = 1, soulValue = 10;
	public int spdCost = 5, spdValue = 1;
	public int bombStrengthCost=1, bombStrengthValue=1;
	public int bombLengthCost=20, bombLengthValue=1;
	
	private int attributeX = MenuChar.statsX+130;
	private int attributeY = MenuChar.statsY+35;
	
	
	public AttributeHandler(Game game){
		this.pData=game.getPlayerData();
		refreshAll();
		
	}

	//sets the original values of the parameters as well as the current value;
	//this is done so that you can not lower a stat past its original level while letting you reset stats if you screw up.
	public void updateOriginalValue() {
		if(MenuChar.cSelected==CHARACTER.SAYAKA){
			hpOriginal=pData.saHp;
			mpOriginal=pData.saMp;
			soulOriginal=pData.saSoul;
			spdOriginal=pData.saSpd;
			bombStrengthOriginal=pData.saBombStrength;
			bombLengthOriginal=pData.saBombLength;
			BPOriginal=pData.saBP;
		}
		else if(MenuChar.cSelected==CHARACTER.HOMURA){
			hpOriginal=pData.hoHp;
			mpOriginal=pData.hoMp;
			soulOriginal=pData.hoSoul;
			spdOriginal=pData.hoSpd;
			bombStrengthOriginal=pData.hoBombStrength;
			bombLengthOriginal=pData.hoBombLength;
			BPOriginal=pData.hoBP;
		}
		else if(MenuChar.cSelected==CHARACTER.MAMI){
			hpOriginal=pData.maHp;
			mpOriginal=pData.maMp;
			soulOriginal=pData.maSoul;
			spdOriginal=pData.maSpd;
			bombStrengthOriginal=pData.maBombStrength;
			bombLengthOriginal=pData.maBombLength;
			BPOriginal=pData.maBP;
		}
		else if(MenuChar.cSelected==CHARACTER.MADOKA){
			hpOriginal=pData.mdHp;
			mpOriginal=pData.mdMp;
			soulOriginal=pData.mdSoul;
			spdOriginal=pData.mdSpd;
			bombStrengthOriginal=pData.mdBombStrength;
			bombLengthOriginal=pData.mdBombLength;
			BPOriginal=pData.mdBP;
		}
		else if(MenuChar.cSelected==CHARACTER.KYOUKO){
			hpOriginal=pData.kyHp;
			mpOriginal=pData.kyMp;
			soulOriginal=pData.kySoul;
			spdOriginal=pData.kySpd;
			bombStrengthOriginal=pData.kyBombStrength;
			bombLengthOriginal=pData.kyBombLength;
			BPOriginal=pData.kyBP;
		}	
	}
	public void setCurrentValue(){
		hpCur=hpOriginal;
		mpCur=mpOriginal;
		soulCur=soulOriginal;
		spdCur=spdOriginal;
		bombStrengthCur=bombStrengthOriginal;
		bombLengthCur=bombLengthOriginal;
		BPCur=BPOriginal;
	}
	public void setNewValues(){
		//sets game's pData to new data;
		if(MenuChar.cSelected==CHARACTER.SAYAKA){
			pData.saHp=hpCur;
			pData.saMp=mpCur;
			pData.saSoul=soulCur;
			pData.saSpd=spdCur;
			pData.saBombStrength=bombStrengthCur;
			pData.saBombLength=bombLengthCur;
			pData.saBP=BPCur;
		}
		else if(MenuChar.cSelected==CHARACTER.HOMURA){
			pData.hoHp=hpCur;
			pData.hoMp=mpCur;
			pData.hoSoul=soulCur;
			pData.hoSpd=spdCur;
			pData.hoBombStrength=bombStrengthCur;
			pData.hoBombLength=bombLengthCur;
			pData.hoBP=BPCur;
		}
		else if(MenuChar.cSelected==CHARACTER.MAMI){
			pData.maHp=hpCur;
			pData.maMp=mpCur;
			pData.maSoul=soulCur;
			pData.maSpd=spdCur;
			pData.maBombStrength=bombStrengthCur;
			pData.maBombLength=bombLengthCur;
			pData.maBP=BPCur;
		}
		else if(MenuChar.cSelected==CHARACTER.MADOKA){
			pData.mdHp=hpCur;
			pData.mdMp=mpCur;
			pData.mdSoul=soulCur;
			pData.mdSpd=spdCur;
			pData.mdBombStrength=bombStrengthCur;
			pData.mdBombLength=bombLengthCur;
			pData.mdBP=BPCur;
		}
		else if(MenuChar.cSelected==CHARACTER.KYOUKO){
			pData.kyHp=hpCur;
			pData.kyMp=mpCur;
			pData.kySoul=soulCur;
			pData.kySpd=spdCur;
			pData.kyBombStrength=bombStrengthCur;
			pData.kyBombLength=bombLengthCur;
			pData.kyBP=BPCur;
		}	
	}
	//let's you restore the original values if you press cancel or smth.
	public void restoreOriginalValue(){
		if(MenuChar.cSelected==CHARACTER.SAYAKA){
			pData.saHp=hpOriginal;
			pData.saMp=mpOriginal;
			pData.saSoul=soulOriginal;
			pData.saSpd=spdOriginal;
			pData.saBombStrength=bombStrengthOriginal;
			pData.saBombLength=bombLengthOriginal;
			pData.saBP=BPOriginal;
		}
		else if(MenuChar.cSelected==CHARACTER.HOMURA){
			pData.hoHp=hpOriginal;
			pData.hoMp=mpOriginal;
			pData.hoSoul=soulOriginal;
			pData.hoSpd=spdOriginal;
			pData.hoBombStrength=bombStrengthOriginal;
			pData.hoBombLength=bombLengthOriginal;
			pData.hoBP=BPOriginal;
		}
		else if(MenuChar.cSelected==CHARACTER.MAMI){
			pData.maHp=hpOriginal;
			pData.maMp=mpOriginal;
			pData.maSoul=soulOriginal;
			pData.maSpd=spdOriginal;
			pData.maBombStrength=bombStrengthOriginal;
			pData.maBombLength=bombLengthOriginal;
			pData.maBP=BPOriginal;
		}
		else if(MenuChar.cSelected==CHARACTER.MADOKA){
			pData.mdHp=hpOriginal;
			pData.mdMp=mpOriginal;
			pData.mdSoul=soulOriginal;
			pData.mdSpd=spdOriginal;
			pData.mdBombStrength=bombStrengthOriginal;
			pData.mdBombLength=bombLengthOriginal;
			pData.mdBP=BPOriginal;
		}
		else if(MenuChar.cSelected==CHARACTER.KYOUKO){
			pData.kyHp=hpOriginal;
			pData.kyMp=mpOriginal;
			pData.kySoul=soulOriginal;
			pData.kySpd=spdOriginal;
			pData.kyBombStrength=bombStrengthOriginal;
			pData.kyBombLength=bombLengthOriginal;
			pData.kyBP=BPOriginal;
		}	
	}
	
	//sets the image arrays that are rendered onto screen;
	public void setImageArrays(){
		hp=IntToImage.toImageSmall((int) hpCur);
		mp=IntToImage.toImageSmall((int)mpCur);
		soul=IntToImage.toImageSmall((int)soulCur);
		spd=IntToImage.toImageSmall(spdCur);
		bombStrength=IntToImage.toImageSmall(bombStrengthCur);
		bombLength=IntToImage.toImageSmall(bombLengthCur);
		BPCurImg=IntToImage.toImageSmall(BPCur);
		BPOriImg=IntToImage.toImageSmall(BPOriginal);
		/*
		if(MenuChar.cSelected==CHARACTER.SAYAKA){
			hp=IntToImage.toImageSmall((int)pData.saHp);
			mp=IntToImage.toImageSmall((int)pData.saMp);
			soul=IntToImage.toImageSmall((int)pData.saSoul);
			spd=IntToImage.toImageSmall(pData.saSpd);
			bombStrength=IntToImage.toImageSmall(pData.saBombStrength);
			bombLength=IntToImage.toImageSmall(pData.saBombLength);
			BPCurImg=IntToImage.toImageSmall(pData.saBP);
			BPOriImg=IntToImage.toImageSmall(BPOriginal);
		}
		else if(MenuChar.cSelected==CHARACTER.HOMURA){
			hp=IntToImage.toImageSmall((int)pData.hoHp);
			mp=IntToImage.toImageSmall((int)pData.hoMp);
			soul=IntToImage.toImageSmall((int)pData.hoSoul);
			spd=IntToImage.toImageSmall(pData.hoSpd);
			bombStrength=IntToImage.toImageSmall(pData.hoBombStrength);
			bombLength=IntToImage.toImageSmall(pData.hoBombLength);
			BPCurImg=IntToImage.toImageSmall(pData.hoBP);
			BPOriImg=IntToImage.toImageSmall(BPOriginal);
		}
		else if(MenuChar.cSelected==CHARACTER.MAMI){
			hp=IntToImage.toImageSmall((int)pData.maHp);
			mp=IntToImage.toImageSmall((int)pData.maMp);
			soul=IntToImage.toImageSmall((int)pData.maSoul);
			spd=IntToImage.toImageSmall(pData.maSpd);
			bombStrength=IntToImage.toImageSmall(pData.maBombStrength);
			bombLength=IntToImage.toImageSmall(pData.maBombLength);
			BPCurImg=IntToImage.toImageSmall(pData.maBP);
			BPOriImg=IntToImage.toImageSmall(BPOriginal);
		}
		else if(MenuChar.cSelected==CHARACTER.MADOKA){
			hp=IntToImage.toImageSmall((int)pData.mdHp);
			mp=IntToImage.toImageSmall((int)pData.mdMp);
			soul=IntToImage.toImageSmall((int)pData.mdSoul);
			spd=IntToImage.toImageSmall(pData.mdSpd);
			bombStrength=IntToImage.toImageSmall(pData.mdBombStrength);
			bombLength=IntToImage.toImageSmall(pData.mdBombLength);
			BPCurImg=IntToImage.toImageSmall(pData.mdBP);
			BPOriImg=IntToImage.toImageSmall(BPOriginal);
		}
		else if(MenuChar.cSelected==CHARACTER.KYOUKO){
			hp=IntToImage.toImageSmall((int)pData.kyHp);
			mp=IntToImage.toImageSmall((int)pData.kyMp);
			soul=IntToImage.toImageSmall((int)pData.kySoul);
			spd=IntToImage.toImageSmall(pData.kySpd);
			bombStrength=IntToImage.toImageSmall(pData.kyBombStrength);
			bombLength=IntToImage.toImageSmall(pData.kyBombLength);
			BPCurImg=IntToImage.toImageSmall(pData.kyBP);
			BPOriImg=IntToImage.toImageSmall(BPOriginal);
		}	
		*/
	}
	
	public void render(Graphics g){
		for(int i=0;i<BPCurImg.length;i++){
			g.drawImage(BPCurImg[i], attributeX+12*i+65, attributeY-25, null);
		}
		for(int i=0;i<BPOriImg.length;i++){
			g.drawImage(BPOriImg[i], attributeX+12*i+102, attributeY-25, null);
		}
		for(int i=0;i<hp.length;i++){
			g.drawImage(hp[i], attributeX+12*i, attributeY, null);
		}
		for(int i=0;i<mp.length;i++){
			g.drawImage(mp[i], attributeX+12*i, attributeY+MenuChar.statsShift, null);
		}
		for(int i=0;i<soul.length;i++){
			g.drawImage(soul[i], attributeX+12*i, attributeY+MenuChar.statsShift*2, null);
		}
		for(int i=0;i<spd.length;i++){
			g.drawImage(spd[i], attributeX+12*i, attributeY+MenuChar.statsShift*3, null);
		}
		for(int i=0;i<bombStrength.length;i++){
			g.drawImage(bombStrength[i], attributeX+12*i, attributeY+MenuChar.statsShift*4, null);
		}
		for(int i=0;i<bombLength.length;i++){
			g.drawImage(bombLength[i], attributeX+12*i, attributeY+MenuChar.statsShift*5, null);
		}
	}
	public void refreshAll(){
		this.updateOriginalValue();
		setCurrentValue();
		this.setImageArrays();
	}
	public void refreshImage(){
		this.setImageArrays();
	}
	
}
