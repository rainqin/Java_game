/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.util.Random;
public class AIMove extends Thread { 
	private float xLoc=600, yLoc=600;
	private BattleShip[] objects;
	private BattlePlace warPlace;
	private int status=5, len;
	private float speedX,speedY,dis, sin, cos;
	private static float speedOffset = (float) 0.001;
	private static double angleSpeed = Math.PI/512;
	private double angle=0,targetAngle,direct=0;
	private Random random;
	private static int range = 300;
	
	public AIMove(BattleShip[] p,BattlePlace war) {
		len = p.length;
		objects = new BattleShip[len];
		for(int i = 0;i<len;i++) {
			objects[i] = p[i];
		}
		warPlace = war;
		random = new Random();
	}
	
	public float Distante(BattleShip[] p) {
		return (float) Math.sqrt((p[0].returnX()-p[1].returnX())*(p[0].returnX()-p[1].returnX())+(p[0].returnY()-p[1].returnY())*(p[0].returnY()-p[1].returnY()));
	}
	
	public void AIstatus() {
		int x,y;
		
		if(this.Distante(objects)>range){
			x = objects[0].returnX()-objects[1].returnX();
			y = objects[0].returnY()-objects[1].returnY();
			if(x<0)
				status = 1;
			if(x>0)
				status = 3;
			if(y<0)
				status = 2;
			if(y>0)
				status = 0;
		}
		else{
			status = random.nextInt(4);
			if(status>3)
				status = 5;
		}
	}
	
	public float Acc(float speed) {
		speed = speed+speedOffset;
		if(speed>20)
			speed = 20;
		
		return speed;
	}
	
	public float Dec(float speed) {
		speed = speed-speedOffset;
		if(speed<0) {
			speed=0;
		}
		return speed;
	}
	
	public void SpeedUpdate() {
		float offsetX,offsetY;
		dis = this.Distante(objects);
		
		sin = (objects[0].returnX()-objects[1].returnX())/dis;
		cos = (objects[0].returnY()-objects[1].returnY())/dis;
		offsetX = speedOffset*sin;
		offsetY = speedOffset*cos;
		if(dis>400){
			speedX += offsetX;
			speedY += offsetY;
		}
		if(dis>50&&dis<400){
			if(Math.abs(speedX)>0.01)
				speedX -= 4*offsetX;
			if(Math.abs(speedY)>0.01)
				speedY -= 4*offsetY;
		}
		if(dis<50){
			if(Math.abs(speedX)>0)
				speedX -= 10*offsetX;
			if(Math.abs(speedY)>0)
				speedY -= 10*offsetY;
		}
			
	}
	
	public void AngleUpdate() {
		int x = objects[0].returnX()-objects[1].returnX();
		int y = objects[0].returnY()-objects[1].returnY();
		if(x<0&&y>0)
			targetAngle = -Math.asin(sin);
		if(x<0&&y<0)
			targetAngle = Math.asin(sin)+Math.PI;
		if(x>0&&y<0)
			targetAngle = Math.asin(sin)+Math.PI;
		if(x>0&&y>0)
			targetAngle = -Math.asin(sin)+2*Math.PI;
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
	
	public void move() {
			yLoc =  yLoc + speedY;
			xLoc =  xLoc + speedX;
	}
	
	public void SetBaseTime(long time) {
	}

	@Override
	public void run() {
		while(warPlace.Stop()) {
			AIstatus();
			for(int i=0;i<300;i++){
				
				SpeedUpdate();
				AngleUpdate();
				move();
				
				
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
					speedX = -speedX/4;
					speedY = -speedY/4;
					for(int e = 0; e<10;e++){
						SpeedUpdate();
						move();
						objects[1].getX((int)xLoc);
						objects[1].getY((int)yLoc);
						objects[1].Move();
						try {
							Thread.sleep(1);
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					break;
				}
				else {
					
					objects[1].getX((int)xLoc);
					objects[1].getY((int)yLoc);
				}
				objects[1].Move();
				objects[1].getDegree(angle);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}
}
