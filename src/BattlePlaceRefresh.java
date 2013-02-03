/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import java.awt.Point;
public class BattlePlaceRefresh extends Thread{
	private BattlePlace panel;
	public BattlePlaceRefresh(BattlePlace p, BattleShip s) {
		panel = p;
	}
	
	public void run() {
		while(panel.Stop()){
		panel.repaint();
		//ship.repaint();
		}
	}
	public void getMouse(Point p) {		
	}
}
		