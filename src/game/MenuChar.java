package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MenuChar implements GeneralMenu{
		private BufferedImage mdSelectOn,mdSelectOff,hoSelectOn,hoSelectOff,saSelectOn,saSelectOff,kySelectOn,kySelectOff,maSelectOn,maSelectOff;
		private BufferedImage mdBg,kyBg,saBg,maBg,hoBg,charSelectBg;
		private BufferedImage mdName,kyName,saName,maName,hoName;
		
		private BufferedImage mdStats,hoStats,saStats,maStats,kyStats;
		private BufferedImage statsSelectionCombinedOff, statsSelectionHpOn,statsSelectionMpOn,statsSelectionSpdOn,statsSelectionSoulOn,statsSelectionDamageOn,statsSelectionRangeOn;
		
		private BufferedImage mdPortrait,saPortrait,maPortrait,hoPortrait,kyPortrait;
		private BufferedImage cursorLeft,cursorRight;
		
		private int cSelectIndex;
		private int cSelectHeight,cSelectWidth;
		double yShift;
		private boolean shiftingDown;
		private BufferedImageLoader loader;
		
		private int height = 350;
		private int width = 115;
		
		private static int statsX=280;
		private static int statsY=140;
		public static int statsShift=28;
		
		private SpecialEffects effect;
		
		private int selectStyle = 2;
		
		public Game game;
		public AttributeHandler handler;
		private Random rand = new Random();
		
		//helper variables that animate the character choosing screen
		public double centerShiftX,centerShiftY,centerScale,leftShiftX,leftShiftY,leftScale,rightShiftX,rightShiftY,rightScale,backLeftShiftX,backLeftShiftY,backLeftScale,backRightShiftX,backRightShiftY,backRightScale;
		public boolean isRotating;
		public String rotateDirection=null;
		private double rotationSpeed=3;
		private BufferedImage bg2;
		
		public static enum CHARACTER{
			MADOKA,
			HOMURA,
			SAYAKA,
			MAMI,
			KYOUKO,
		};
		
		public static enum CHAR_MENU_STATE{
			IS_CHOOSING,
			DISPLAYING_STATUS,
		}
		
		public static enum SELECTED_ATTRIBUTE{
			HP,
			MP,
			SOUL,
			SPEED,
			DAMAGE,
			RANGE,
		}
		
		public static CHARACTER cSelected = CHARACTER.MADOKA;
		public static CHAR_MENU_STATE cState = CHAR_MENU_STATE.IS_CHOOSING;
		public static SELECTED_ATTRIBUTE cAttribute = SELECTED_ATTRIBUTE.HP;
		
		public MenuChar(Game game){
			this.game = game;
			loader = new BufferedImageLoader();
			mdSelectOn = loader.loadImage("/image/mdSelectOn.png");
			mdSelectOff = loader.loadImage("/image/mdSelectOff.png");
			hoSelectOn = loader.loadImage("/image/hoSelectOn.png");
			hoSelectOff = loader.loadImage("/image/hoSelectOff.png");
			maSelectOn = loader.loadImage("/image/maSelectOn.png");
			maSelectOff = loader.loadImage("/image/maSelectOff.png");
			saSelectOn = loader.loadImage("/image/saSelectOn.png");
			saSelectOff = loader.loadImage("/image/saSelectOff.png");
			kySelectOn = loader.loadImage("/image/kySelectOn.png");
			kySelectOff = loader.loadImage("/image/kySelectOff.png");
			
			mdBg=loader.loadImage("/image/mdBgTrans.png");
			maBg=loader.loadImage("/image/maBg.png");
			saBg=loader.loadImage("/image/saBgTrans.png");
			kyBg=loader.loadImage("/image/kyBg.png");
			hoBg=loader.loadImage("/image/hoBgTrans.png");
			
			hoName=loader.loadImage("/image/hoName.png");
			saName=loader.loadImage("/image/saName.png");
			kyName=loader.loadImage("/image/kyName.png");
			maName=loader.loadImage("/image/maName.png");
			mdName=loader.loadImage("/image/mdName.png");
			
			mdStats=loader.loadImage("/image/madoka_Stats.png");
			hoStats=loader.loadImage("/image/homura_Stats.png");
			saStats=loader.loadImage("/image/sayaka_Stats.png");
			maStats=loader.loadImage("/image/mami_Stats.png");
			kyStats=loader.loadImage("/image/kyouko_Stats.png");
			
			mdPortrait=loader.loadImage("/image/portraits/md.png");
			maPortrait=loader.loadImage("/image/portraits/ma.png");
			saPortrait=loader.loadImage("/image/portraits/sa.png");
			hoPortrait=loader.loadImage("/image/portraits/ho.png");
			kyPortrait=loader.loadImage("/image/portraits/ky.png");
			cursorLeft=loader.loadImage("/image/portraits/cursorLeft.png");
			cursorRight=loader.loadImage("/image/portraits/cursorRight.png");
			bg2=loader.loadImage("/image/portraits/bg.png");
			
			
			statsSelectionCombinedOff=loader.loadImage("/image/statusSelectionCombinedOff.png");
			statsSelectionHpOn=loader.loadImage("/image/statsSelectionHpOn.png");
			statsSelectionMpOn=loader.loadImage("/image/statsSelectionMpOn.png");
			statsSelectionSoulOn=loader.loadImage("/image/statsSelectionSoulOn.png");
			statsSelectionSpdOn=loader.loadImage("/image/statsSelectionSpeedOn.png");
			statsSelectionDamageOn=loader.loadImage("/image/statsSelectionDamageOn.png");
			statsSelectionRangeOn=loader.loadImage("/image/statsSelectionRangeOn.png");
			
			
			charSelectBg = loader.loadImage("/image/bg.jpg");
			
			
			cSelectIndex=(GameSystem.ABSWIDTH-5*mdSelectOn.getWidth())/6;
			cSelectWidth=mdSelectOn.getWidth();
			cSelectHeight = mdSelectOn.getHeight();
			yShift=1;
			shiftingDown=true;
			
			handler = new AttributeHandler(game);
			effect = new SpecialEffects();
			
			resetRotationVariables();
		}
		
		public void tick(){
			shiftDown();
			effect.tick();
			if(this.isRotating ==true){
				if(rotateDirection.equals("right")){
					rotateRight();
				}
				else if(rotateDirection.equals("left")){
					rotateLeft();
				}
			}
		}
		
		public void render(Graphics g){
			if(isChooseChar()){
				if(selectStyle==1){
					g.drawImage(charSelectBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
					
					g.drawImage(mdSelectOff,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
					g.drawImage(hoSelectOff,2*cSelectIndex+cSelectWidth+8,(int) yShift+32,null);
					g.drawImage(saSelectOff,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
					g.drawImage(maSelectOff,4*cSelectIndex+3*cSelectWidth+8,(int)yShift+32,null);
					g.drawImage(kySelectOff,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
				}
				else if(selectStyle==2){
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, GameSystem.ABSWIDTH+10, GameSystem.ABSHEIGHT+10);
					g.drawImage(bg2,0,0,null);
				}
				
			}
			else if(isDisplayStatus()){
				if(cSelected == CHARACTER.MADOKA){
					g.drawImage(mdStats, 0,0,null);
				}
				else if(cSelected == CHARACTER.HOMURA){
					g.drawImage(hoStats, 0,0,null);
				}
				else if(cSelected == CHARACTER.SAYAKA){
					g.drawImage(saStats, 0,0,null);
				}
				else if(cSelected == CHARACTER.MAMI){
					g.drawImage(maStats, 0,0,null);
				}
				else if(cSelected == CHARACTER.KYOUKO){
					g.drawImage(kyStats, 0,0,null);
				}
				g.drawImage(statsSelectionCombinedOff,statsX,statsY,null);
			}
			renderSelected(g);
		}
		
		public void shiftDown(){
			if(yShift>=(GameSystem.ABSHEIGHT-cSelectHeight)/2){
				yShift=(GameSystem.ABSHEIGHT-cSelectHeight)/2;
				return;
			}
			yShift=yShift*1.23;
		
		}
		public void rotateLeft(){
			if(centerShiftX>-60) centerShiftX-=4*rotationSpeed;
			else centerShiftX=-60;
			
			if(centerShiftY<70) centerShiftY+=4.66*rotationSpeed;
			else centerShiftY=70;
			
			if(centerScale>0.5) centerScale-=0.035*rotationSpeed;
			else centerScale=0.5;
			
			if(rightShiftX>-340) rightShiftX-=22.66666666*rotationSpeed;
			else rightShiftX=-340;
			
			if(rightShiftY>-70) rightShiftY-=4.66*rotationSpeed;
			else rightShiftY=-70;
			
			if(rightScale<2) rightScale+=0.06666666*rotationSpeed;
			else rightScale=2;
			
			if(backRightShiftX<70) backRightShiftX+=4.66666666*rotationSpeed;
			else backRightShiftX=70;
			
			if(backRightShiftY<50) backRightShiftY+=3.33333333*rotationSpeed;
			else backRightShiftY=50;
			
			if(backRightScale<3.0/2) backRightScale+=0.0333333333*rotationSpeed;
			else backRightScale=3.0/2;
			
			if(backLeftShiftX<200) backLeftShiftX+=13.3333333*rotationSpeed;
			else backLeftShiftX=200;
			
			
			if(leftShiftX<130) leftShiftX+=8.6666666*rotationSpeed;
			else leftShiftX=130;
			
			if(leftShiftY>-50) leftShiftY-=3.33333333*rotationSpeed;
			else leftShiftY=-50;
			
			if(leftScale>2.0/3) leftScale-=0.03333333*rotationSpeed;
			else leftScale=2.0/3;
			
			if(centerShiftX==-60&&centerShiftY==70&&centerScale==0.5&&
			   rightShiftX==-340&&rightShiftY==-70&&rightScale==2&&
			   backRightShiftX==70&&backRightShiftY==50&&backRightScale==3.0/2&&
			   backLeftShiftX==200&&leftShiftX==130&&leftShiftY==-50&&leftScale==2.0/3){
				effect.startFadeWhiteReversed();
				isRotating=false;
				setNextCycle("left");
				resetRotationVariables();
			}
		}
		public synchronized void renderSelected(Graphics g){
			if(isChooseChar()){
				if(cSelected == CHARACTER.MADOKA){
					if(selectStyle==1){
						g.drawImage(mdSelectOn,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
						g.drawImage(mdName,cSelectIndex+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-16,null);
					}
					else if(selectStyle==2){
						g.drawImage(mdBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.GAME_HEIGHT-30,null);
						
						g.drawImage(maPortrait, (int)(110+30+backLeftShiftX), (int)(20+backLeftShiftY),(int)(maPortrait.getWidth()/3*backLeftScale),(int)(maPortrait.getHeight()/3*backLeftScale), null);
						g.drawImage(saPortrait, (int)(110+230+backRightShiftX), (int)(20+backRightShiftY),(int)(saPortrait.getWidth()/3*backRightScale),(int)(saPortrait.getHeight()/3*backRightScale), null);
						g.drawImage(kyPortrait, (int)(110-100+leftShiftX), (int)(70+leftShiftY),(int)(kyPortrait.getWidth()/2*leftScale),(int)(kyPortrait.getHeight()/2*leftScale), null);
						g.drawImage(hoPortrait, (int)(110+300+rightShiftX), (int)(70+rightShiftY),(int)(hoPortrait.getWidth()/2*rightScale),(int)(hoPortrait.getHeight()/2*rightScale), null);
						g.drawImage(mdPortrait, (int)(70+centerShiftX), (int)(centerShiftY),(int)(mdPortrait.getWidth()*centerScale),(int)(mdPortrait.getHeight()*centerScale), null);
						
						
						g.drawImage(mdName,270,15,null);
						g.drawImage(cursorLeft, 220, 16, null);
						g.drawImage(cursorRight, 420, 16, null);
					}
				}
				else if(cSelected == CHARACTER.HOMURA){
					if(selectStyle==1){
						g.drawImage(hoSelectOn,2*cSelectIndex+cSelectWidth+8,(int)yShift+32,null);
						g.drawImage(hoName,2*cSelectIndex+cSelectWidth+8,(int)yShift-16,null);
					}
					else if(selectStyle==2){
						g.drawImage(hoBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.GAME_HEIGHT-30,null);
						
						g.drawImage(kyPortrait, (int)(110+30+backLeftShiftX), (int)(20+backLeftShiftY),(int)(kyPortrait.getWidth()/3*backLeftScale),(int)(kyPortrait.getHeight()/3*backLeftScale), null);
						g.drawImage(maPortrait, (int)(110+230+backRightShiftX), (int)(20+backRightShiftY),(int)(maPortrait.getWidth()/3*backRightScale),(int)(maPortrait.getHeight()/3*backRightScale), null);
						g.drawImage(mdPortrait, (int)(110-100+leftShiftX), (int)(70+leftShiftY),(int)(mdPortrait.getWidth()/2*leftScale),(int)(mdPortrait.getHeight()/2*leftScale), null);
						g.drawImage(saPortrait, (int)(110+300+rightShiftX), (int)(70+rightShiftY),(int)(saPortrait.getWidth()/2*rightScale),(int)(saPortrait.getHeight()/2*rightScale), null);
						g.drawImage(hoPortrait, (int)(70+centerShiftX), (int)(centerShiftY),(int)(hoPortrait.getWidth()*centerScale),(int)(hoPortrait.getHeight()*centerScale), null);
						
						g.drawImage(hoName,270,15,null);
						g.drawImage(cursorLeft, 220, 16, null);
						g.drawImage(cursorRight, 420, 16, null);
					}
				}
				else if(cSelected == CHARACTER.SAYAKA){
					if(selectStyle==1){
						g.drawImage(saSelectOn,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
						g.drawImage(saName,3*cSelectIndex+2*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-16,null);
					}
					else if(selectStyle==2){
						g.drawImage(saBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.GAME_HEIGHT-30,null);
						
						g.drawImage(mdPortrait, (int)(110+30+backLeftShiftX), (int)(20+backLeftShiftY),(int)(mdPortrait.getWidth()/3*backLeftScale),(int)(mdPortrait.getHeight()/3*backLeftScale), null);
						g.drawImage(kyPortrait, (int)(110+230+backRightShiftX), (int)(20+backRightShiftY),(int)(kyPortrait.getWidth()/3*backRightScale),(int)(kyPortrait.getHeight()/3*backRightScale), null);
						g.drawImage(hoPortrait, (int)(110-100+leftShiftX), (int)(70+leftShiftY),(int)(hoPortrait.getWidth()/2*leftScale),(int)(hoPortrait.getHeight()/2*leftScale), null);
						g.drawImage(maPortrait, (int)(110+300+rightShiftX), (int)(70+rightShiftY),(int)(maPortrait.getWidth()/2*rightScale),(int)(maPortrait.getHeight()/2*rightScale), null);
						g.drawImage(saPortrait, (int)(70+centerShiftX), (int)(centerShiftY),(int)(saPortrait.getWidth()*centerScale),(int)(saPortrait.getHeight()*centerScale), null);
						
						
						g.drawImage(saName,270,15,null);
						g.drawImage(cursorLeft, 220, 16, null);
						g.drawImage(cursorRight, 420, 16, null);
					}
				}
				else if(cSelected == CHARACTER.MAMI){
					if(selectStyle==1){
						g.drawImage(maSelectOn,4*cSelectIndex+3*cSelectWidth+8,(int)yShift+32,null);
						g.drawImage(maName,4*cSelectIndex+3*cSelectWidth+8,(int)yShift-16,null);
					}
					else if(selectStyle==2){
						g.drawImage(maBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.GAME_HEIGHT-30,null);
						
						g.drawImage(hoPortrait, (int)(110+30+backLeftShiftX), (int)(20+backLeftShiftY),(int)(hoPortrait.getWidth()/3*backLeftScale),(int)(hoPortrait.getHeight()/3*backLeftScale), null);
						g.drawImage(mdPortrait, (int)(110+230+backRightShiftX), (int)(20+backRightShiftY),(int)(mdPortrait.getWidth()/3*backRightScale),(int)(mdPortrait.getHeight()/3*backRightScale), null);
						g.drawImage(saPortrait, (int)(110-100+leftShiftX), (int)(70+leftShiftY),(int)(saPortrait.getWidth()/2*leftScale),(int)(saPortrait.getHeight()/2*leftScale), null);
						g.drawImage(kyPortrait, (int)(110+300+rightShiftX), (int)(70+rightShiftY),(int)(kyPortrait.getWidth()/2*rightScale),(int)(kyPortrait.getHeight()/2*rightScale), null);
						g.drawImage(maPortrait, (int)(70+centerShiftX), (int)(centerShiftY),(int)(maPortrait.getWidth()*centerScale),(int)(maPortrait.getHeight()*centerScale), null);
						
						g.drawImage(maName,270,15,null);
						g.drawImage(cursorLeft, 220, 16, null);
						g.drawImage(cursorRight, 420, 16, null);
					}
				}
				else if(cSelected == CHARACTER.KYOUKO){
					if(selectStyle==1){
						g.drawImage(kySelectOn,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift+32,null);
						g.drawImage(kyName,5*cSelectIndex+4*cSelectWidth+8,(GameSystem.ABSHEIGHT-cSelectHeight)-(int)yShift-16,null);
					}
					else if(selectStyle==2){
						g.drawImage(kyBg,0,0,GameSystem.ABSWIDTH+10,GameSystem.GAME_HEIGHT-30,null);
						
						g.drawImage(saPortrait, (int)(110+30+backLeftShiftX), (int)(20+backLeftShiftY),(int)(saPortrait.getWidth()/3*backLeftScale),(int)(saPortrait.getHeight()/3*backLeftScale), null);
						g.drawImage(hoPortrait, (int)(110+230+backRightShiftX), (int)(20+backRightShiftY),(int)(hoPortrait.getWidth()/3*backRightScale),(int)(hoPortrait.getHeight()/3*backRightScale), null);
						g.drawImage(maPortrait, (int)(110-100+leftShiftX), (int)(70+leftShiftY),(int)(maPortrait.getWidth()/2*leftScale),(int)(maPortrait.getHeight()/2*leftScale), null);
						g.drawImage(mdPortrait, (int)(110+300+rightShiftX), (int)(70+rightShiftY),(int)(mdPortrait.getWidth()/2*rightScale),(int)(mdPortrait.getHeight()/2*rightScale), null);
						g.drawImage(kyPortrait, (int)(70+centerShiftX), (int)(centerShiftY),(int)(kyPortrait.getWidth()*centerScale),(int)(kyPortrait.getHeight()*centerScale), null);

						g.drawImage(kyName,270,15,null);
						g.drawImage(cursorLeft, 220, 16, null);
						g.drawImage(cursorRight, 420, 16, null);
					}
				}
			}
			else if(isDisplayStatus()){
				if(cAttribute == SELECTED_ATTRIBUTE.HP){
					g.drawImage(statsSelectionHpOn, statsX-12, statsY+27+statsShift*0, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.MP){
					g.drawImage(statsSelectionMpOn, statsX-12, statsY+27+statsShift*1, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
					g.drawImage(statsSelectionSoulOn, statsX-12, statsY+27+statsShift*2, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
					g.drawImage(statsSelectionSpdOn, statsX-12, statsY+27+statsShift*3, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
					g.drawImage(statsSelectionDamageOn, statsX-12, statsY+27+statsShift*4, null);
				}
				else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
					g.drawImage(statsSelectionRangeOn, statsX-12, statsY+27+statsShift*5, null);
				}
				//handler will render the attribute values in form of images
				handler.render(g);
			}
			effect.render(g);
		}

		public void keyPressed(int key) {
			if(this.isChooseChar()){
				if(key==KeyEvent.VK_X){
					yShift=1;
					GameSystem.playCancel();
					Menu.mState=Menu.MENUSTATE.MAIN;
					effect.startFadeWhiteReversed();
				}
				else if(key==KeyEvent.VK_LEFT){				
					startRotateLeft();
					GameSystem.playSwitch();
				}
				else if(key==KeyEvent.VK_RIGHT){
					startRotateRight();
					GameSystem.playSwitch();
				}
				else if(key==KeyEvent.VK_Z){
					handler.refreshAll();
					this.setDisplayStatus();
					GameSystem.playConfirm();
					effect.startFadeWhiteReversed();
				}
				
			}
			
			
			
			else if(this.isDisplayStatus()){
				 if(key==KeyEvent.VK_Z){
					if(cSelected == CHARACTER.MADOKA){
						Game.cChosen=Game.CHARACTER.MADOKA;
					}
					else if(cSelected == CHARACTER.HOMURA){
						Game.cChosen=Game.CHARACTER.HOMURA;
					}
					else if(cSelected == CHARACTER.SAYAKA){
						Game.cChosen=Game.CHARACTER.SAYAKA;
					}
					else if(cSelected == CHARACTER.MAMI){
						Game.cChosen=Game.CHARACTER.MAMI;
					}
					else if(cSelected == CHARACTER.KYOUKO){
						Game.cChosen=Game.CHARACTER.KYOUKO;
					}
					handler.setNewValues();
					GameSystem.playConfirm();
					playSelectionSound();
					Menu.toGameMode();
				}
				else if(key==KeyEvent.VK_X){
					yShift=1;
					handler.restoreOriginalValue();
					GameSystem.playCancel();
					setChooseChar();
				}
				else if(key==KeyEvent.VK_DOWN){
					if(cAttribute == SELECTED_ATTRIBUTE.HP){
						cAttribute = SELECTED_ATTRIBUTE.MP;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.MP){
						cAttribute = SELECTED_ATTRIBUTE.SOUL;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
						cAttribute = SELECTED_ATTRIBUTE.SPEED;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
						cAttribute = SELECTED_ATTRIBUTE.DAMAGE;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
						cAttribute = SELECTED_ATTRIBUTE.RANGE;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
						cAttribute = SELECTED_ATTRIBUTE.HP;
					}
					GameSystem.playSwitch();
				}
				else if(key==KeyEvent.VK_UP){
					if(cAttribute == SELECTED_ATTRIBUTE.HP){
						cAttribute = SELECTED_ATTRIBUTE.RANGE;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.MP){
						cAttribute = SELECTED_ATTRIBUTE.HP;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
						cAttribute = SELECTED_ATTRIBUTE.MP;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
						cAttribute = SELECTED_ATTRIBUTE.SOUL;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
						cAttribute = SELECTED_ATTRIBUTE.SPEED;
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
						cAttribute = SELECTED_ATTRIBUTE.DAMAGE;
					}
					GameSystem.playSwitch();
				}
				
				//increase stats if you have enough pt
				else if(key==KeyEvent.VK_RIGHT){
					if(cAttribute == SELECTED_ATTRIBUTE.HP){
						if(handler.BPCur>=handler.hpCost){
							GameSystem.playConfirm();
							handler.BPCur=handler.BPCur-handler.hpCost;
							handler.hpCur=handler.hpCur+handler.hpValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.MP){
						if(handler.BPCur>=handler.mpCost){
							GameSystem.playConfirm();
							handler.BPCur=handler.BPCur-handler.mpCost;
							handler.mpCur+=handler.mpValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
						if(handler.BPCur>=handler.soulCost){
							GameSystem.playConfirm();
							handler.BPCur-=handler.soulCost;
							handler.soulCur+=handler.soulValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
						if(handler.BPCur>=handler.spdCost){
							GameSystem.playConfirm();
							handler.BPCur-=handler.spdCost;
							handler.spdCur+=handler.spdValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
						if(handler.BPCur>=handler.bombStrengthCost){
							GameSystem.playConfirm();
							handler.BPCur-=handler.bombStrengthCost;
							handler.bombStrengthCur+=handler.bombStrengthValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
						if(handler.BPCur>=handler.bombLengthCost){
							GameSystem.playConfirm();
							handler.BPCur-=handler.bombLengthCost;
							handler.bombLengthCur+=handler.bombLengthValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
				}
				//reduces stats if conditions meet
				else if(key==KeyEvent.VK_LEFT){
					if(cAttribute == SELECTED_ATTRIBUTE.HP){
						if(handler.BPCur+handler.hpCost<=handler.BPOriginal&&handler.hpCur-handler.hpValue>=handler.hpOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.hpCost;
							handler.hpCur-=handler.hpValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.MP){
						if(handler.BPCur+handler.mpCost<=handler.BPOriginal&&handler.mpCur-handler.mpValue>=handler.mpOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.mpCost;
							handler.mpCur-=handler.mpValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SOUL){
						if(handler.BPCur+handler.soulCost<=handler.BPOriginal&&handler.soulCur-handler.soulValue>=handler.soulOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.soulCost;
							handler.soulCur-=handler.soulValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.SPEED){
						if(handler.BPCur+handler.spdCost<=handler.BPOriginal&&handler.spdCur-handler.spdValue>=handler.spdOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.spdCost;
							handler.spdCur-=handler.spdValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.DAMAGE){
						if(handler.BPCur+handler.bombStrengthCost<=handler.BPOriginal&&handler.bombStrengthCur-handler.bombStrengthValue>=handler.bombStrengthOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.bombStrengthCost;
							handler.bombStrengthCur-=handler.bombStrengthValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
					else if(cAttribute == SELECTED_ATTRIBUTE.RANGE){
						if(handler.BPCur+handler.bombLengthCost<=handler.BPOriginal&&handler.bombLengthCur-handler.bombLengthValue>=handler.bombLengthOriginal){
							GameSystem.playConfirm();
							handler.BPCur+=handler.bombLengthCost;
							handler.bombLengthCur-=handler.bombLengthValue;
							handler.refreshImage();
						}
						else{
							GameSystem.playError();
						}
					}
				}
			}
		}
		public void playSelectionSound(){
			if(cSelected == CHARACTER.MADOKA){
				GameSystem.musicPlayer.playVoice("/sound/mdSelect.wav");
			}
			else if(cSelected == CHARACTER.HOMURA){
				GameSystem.musicPlayer.playVoice("/sound/hoSelect.wav");
			}
			else if(cSelected == CHARACTER.SAYAKA){
				GameSystem.musicPlayer.playVoice("/sound/saSelect.wav");
			}
			else if(cSelected == CHARACTER.MAMI){
				GameSystem.musicPlayer.playVoice("/sound/maSelect.wav");
			}
			else if(cSelected == CHARACTER.KYOUKO){
				GameSystem.musicPlayer.playVoice("/sound/kySelect.wav");
			}
		}
		
		
		public boolean isChooseChar(){
			if(cState == CHAR_MENU_STATE.IS_CHOOSING){
				return true;
			}
			return false;
		}
		
		public void setChooseChar(){
			cState = CHAR_MENU_STATE.IS_CHOOSING;
		}
		
		public boolean isDisplayStatus(){
			if(cState == CHAR_MENU_STATE.DISPLAYING_STATUS){
				return true;
			}
			return false;
		}
		
		public void setDisplayStatus(){
			cState = CHAR_MENU_STATE.DISPLAYING_STATUS;
		}
		
		public void startRotateRight(){
			rotateDirection="right";
			isRotating=true;
		}
		public void startRotateLeft(){
			rotateDirection="left";
			isRotating=true;
		}
		
		public void setNextCycle(String s){
			if(s.equals("right")){
				if(cSelected == CHARACTER.HOMURA){
					cSelected=CHARACTER.MADOKA;
				}
				else if(cSelected == CHARACTER.SAYAKA){
					cSelected=CHARACTER.HOMURA;
				}
				else if(cSelected == CHARACTER.MAMI){
					cSelected=CHARACTER.SAYAKA;
				}
				else if(cSelected == CHARACTER.KYOUKO){
					cSelected=CHARACTER.MAMI;
				}
				else if(cSelected == CHARACTER.MADOKA){
					cSelected=CHARACTER.KYOUKO;
				}
			}
			else if(s.equals("left")){
				if(cSelected == CHARACTER.MADOKA){
					cSelected=CHARACTER.HOMURA;
				}
				else if(cSelected == CHARACTER.HOMURA){
					cSelected=CHARACTER.SAYAKA;
				}
				else if(cSelected == CHARACTER.SAYAKA){
					cSelected=CHARACTER.MAMI;
				}
				else if(cSelected == CHARACTER.MAMI){
					cSelected=CHARACTER.KYOUKO;
				}
				else if(cSelected == CHARACTER.KYOUKO){
					cSelected=CHARACTER.MADOKA;
				}
			}
		}
		public void resetRotationVariables(){
			centerShiftX=centerShiftY=leftShiftX=leftShiftY=rightShiftX=rightShiftY=backLeftShiftX=backRightShiftX=backRightShiftY=backLeftShiftY=0;
			centerScale=leftScale=rightScale=backRightScale=backLeftScale=1;
		}
		public void rotateRight(){
			if(centerShiftX<290) centerShiftX+=28.6666666*rotationSpeed;
			else if(centerShiftX>290&&centerShiftX<340) centerShiftX+=8*rotationSpeed;
			else centerShiftX=340;
			
			if(centerShiftY<70) centerShiftY+=4.66*rotationSpeed;
			else centerShiftY=70;
			
			if(centerScale>0.5) centerScale-=0.035*rotationSpeed;
			else centerScale=0.5;
			
			if(rightShiftX>-70) rightShiftX-=4.66*rotationSpeed;
			else rightShiftX=-70;
			
			if(rightShiftY>-50) rightShiftY-=3.3333*rotationSpeed;
			else rightShiftY=-50;
			
			if(rightScale>2.0/3) rightScale-=0.02*rotationSpeed;
			else rightScale=2.0/3;
			
			if(backRightShiftX>-200) backRightShiftX-=13.3333333*rotationSpeed;
			else backRightShiftX=-200;
			
			if(backLeftShiftX>-130) backLeftShiftX-=8.6666666*rotationSpeed;
			else backLeftShiftX=-130;
			
			if(backLeftShiftY<50) backLeftShiftY+=3.33333333*rotationSpeed;
			else backLeftShiftY=50;
			
			if(backLeftScale<3.0/2) backLeftScale+=0.03333333*rotationSpeed;
			else backLeftScale=3.0/2;
			
			if(leftShiftX<60) leftShiftX+=4*rotationSpeed;
			else leftShiftX=60;
			
			if(leftShiftY>-70) leftShiftY-=4.66666666*rotationSpeed;
			else leftShiftY=-70;
			
			if(leftScale<2) leftScale+=0.0666666*rotationSpeed;
			else leftScale=2;
			
			if(centerShiftX==340&&centerShiftY==70&&centerScale==0.5&&
			   rightShiftX==-70&&rightShiftY==-50&&rightScale==2.0/3&&
			   backRightShiftX==-200&&backLeftShiftX==-130&&backLeftShiftY==50&&
			   backLeftScale==3.0/2&&leftShiftX==60&&leftShiftY==-70&&leftScale==2){
				effect.startFadeWhiteReversed();
				isRotating=false;
				setNextCycle("right");
				resetRotationVariables();
			}
		}
		
}
