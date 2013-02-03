/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
public class AIFire extends Thread{
	private BattleShip[] s;
	private BattlePlace p;
	public AIFire(BattleShip[] s, BattlePlace p){
		this.s = s;
		this.p = p;
	}
	public void run(){
		while(true){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new BulletDis(s[1].returnX(),s[1].returnY(),s[1].returnDegree(),300,p,0,s[0]).start();
			
		}
	}
	
}
