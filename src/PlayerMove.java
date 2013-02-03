/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */


public class PlayerMove extends Thread { 
	private float xLoc=200, yLoc=200;
	private BattleShip[] objects;
	private BattlePlace warPlace;
	private int status=5, len;
	private float speedUp,speedDown,speedLeft,speedRight;
	private static float speedOffset = (float) 0.00005;
	private static double angleSpeed = Math.PI/1024/1024;
	private long baseTime, currentTime;
	private double angle=0,targetAngle,direct=0;
	
	public PlayerMove(BattleShip[] p,BattlePlace war) {
		len = p.length;
		objects = new BattleShip[len];
		for(int i = 0;i<len;i++) {
			objects[i] = p[i];
		}
		warPlace = war;
		speedUp=0;
		speedDown=0;
		speedLeft=0;
		speedRight=0;
	}
	
	public float Acc(float speed) {
		float timeSlot = (float) (currentTime - baseTime);//0.1s
		speed = speed+speedOffset*timeSlot;
		if(speed>10)
			speed = 10;
		
		return speed;
	}
	
	public float Dec(float speed) {
		
		float timeSlot = (float) (currentTime - baseTime);//0.1s
		speed = speed-speedOffset*(float)1*timeSlot;
		if(speed<0) {
			speed=0;
		}
		return speed;
	}
	
	public void SpeedUpdate() {
		if(status==5) {
			speedUp = Dec(speedUp);
			speedDown = Dec(speedDown);
			speedLeft = Dec(speedLeft);
			speedRight = Dec(speedRight);
		
		}
		else {
			if(status==2) {
				speedUp = Acc(speedUp);
				speedDown = Dec(speedDown);
				speedLeft = Dec(speedLeft);
				speedRight = Dec(speedRight);
				
			}
			if(status==0) {
				speedUp = Dec(speedUp);
				speedDown = Acc(speedDown);
				speedLeft = Dec(speedLeft);
				speedRight = Dec(speedRight);
				
			}
			if(status==1) {
				speedDown = Dec(speedDown);
				speedUp = Dec(speedUp);
				speedLeft = Acc(speedLeft);
				speedRight = Dec(speedRight);
				
			}
			if(status==3) {
				speedDown = Dec(speedDown);
				speedLeft = Dec(speedLeft);
				speedUp = Dec(speedUp);
				speedRight = Acc(speedRight);
				
			}
		}
	}
	
	public void AngleUpdate() {
		if(status!=5){
			switch(status){
			case 2:targetAngle = Math.PI;break;
			case 0:targetAngle = 0;break;
			case 1:targetAngle = Math.PI/2;break;
			case 3:targetAngle = 3*Math.PI/2;break;
			default:break;
			}
			direct = targetAngle-angle;
			if(direct>Math.PI/128||direct<-Math.PI/128){
				if(direct<-Math.PI||(direct<Math.PI&&direct>0))
					angle+=angleSpeed;
				else
					angle-=angleSpeed;
				if(angle>=Math.PI*2)
					angle -= Math.PI*2;
				if(angle<0)
					angle += Math.PI*2;
			}
		}
	}
	
	public void move(int slot) {
			yLoc =  yLoc - (speedUp - speedDown)*slot;
			xLoc =  xLoc - (speedLeft - speedRight)*slot;
	}
	
	public void SetBaseTime(long time) {
		this.baseTime = time;
	}

	public void modifyUp() {
		
		status = 2;
	}
	public void modifyDown() {
		//baseTime = System.currentTimeMillis();
		status = 0;
	}
	public void modifyLeft() {
		//baseTime = System.currentTimeMillis();
		status = 1;		
	}
	public void modifyRight() {
		//baseTime = System.currentTimeMillis();
		status = 3;		
	}
	public void modifyBlank() {
		status = 5;
		//baseTime = System.currentTimeMillis();
	}
	

	@Override
	public void run() {
		float tempSpeed;
		while(warPlace.Stop()) {
			currentTime = System.currentTimeMillis();
			SpeedUpdate();
			AngleUpdate();
			move((int) (currentTime-baseTime));
			baseTime = System.currentTimeMillis();
			
			
			if(xLoc>warPlace.getWidth()-150||xLoc<-50||yLoc<-50||yLoc>warPlace.getHeight()-150)
			{
				if(xLoc>warPlace.getWidth()-150)
					xLoc = warPlace.getWidth()-151;
				if(xLoc<-50)
					xLoc = -49;
				if(yLoc<-50)
					yLoc = -49;
				if(yLoc>warPlace.getHeight()-150)
					yLoc = warPlace.getHeight()-151;
				tempSpeed = speedUp;
				speedUp = speedDown;
				speedDown = tempSpeed;
				
				tempSpeed = speedLeft;
				speedLeft = speedRight;
				speedRight = tempSpeed;
				status = 5;
				for(int e = 0; e<300;e++){
				currentTime = System.currentTimeMillis();
				SpeedUpdate();
				move((int) (currentTime-baseTime));
				baseTime = System.currentTimeMillis();
				objects[0].getX((int)xLoc);
				objects[0].getY((int)yLoc);
				objects[0].Move();
				
				}
			}
			else {
				
				objects[0].getX((int)xLoc);
				objects[0].getY((int)yLoc);
			}
			objects[0].Move();
			objects[0].getDegree(angle);
			objects[0].repaint();
		}
	}
}
