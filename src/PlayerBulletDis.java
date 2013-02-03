/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.awt.Color;

import javax.swing.JPanel;


public class PlayerBulletDis extends Thread{
	private double shootRange,sqShootRange,realDis=0,angle;
	private int xLoc=0, yLoc=0,meLocX,meLocY,select;
	private BattlePlace panel;
	private JPanel bullet;
	private int speed = 10;
	private BattleShip target;
	private static int  bulletPower=10;
	public PlayerBulletDis (int sourceX,int sourceY, double angle, double range,BattlePlace p,int se,BattleShip tar) {
		
		meLocX = sourceX;
		meLocY = sourceY;
		shootRange = range;
		sqShootRange = shootRange*shootRange;
		xLoc = meLocX+50;
		yLoc = meLocY+50;
		
		bullet = new Bullet(meLocX+50, meLocY+50, 20, 20,Color.GREEN);
		p.add(bullet);
		panel = p;
		this.angle = angle;
		target = tar;
		select = se;
	}
	public int returnX() {
		return xLoc+40;
	}
	public int returnY() {
		return yLoc+50;
	}
	
	public float Distante(BattleShip p) {
		return (float) Math.sqrt((this.xLoc-p.returnX()-50)*(this.xLoc-p.returnX()-50)+(this.yLoc-p.returnY()-50)*(this.yLoc-p.returnY()-50));
	}
	public void run(){
		double degree = angle;
		int speedX = (int) (-speed*(Math.sin(degree)));
		int speedY = (int) (speed*Math.cos(degree));
		while(realDis<sqShootRange){
			xLoc +=speedX;
			yLoc +=speedY;
			bullet.setLocation(xLoc, yLoc);
			realDis = (meLocX-xLoc)*(meLocX-xLoc)+(meLocY-yLoc)*(meLocY-yLoc);
			if(Distante(target)<30)
			{
				target.calHP(bulletPower);
				panel.disHp[select].setText(target.returnHP());
				if(target.getHP()==0){
					panel.disHp[select].setText("Level Up\n");
				}
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		xLoc = -50;
		yLoc = -50;
		panel.remove(bullet);
	}
}
