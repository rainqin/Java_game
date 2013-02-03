/*Name: Yuzhu QIN
 *Andrew ID: yuzhuq
 *Course: 08-600
 */
import javax.swing.JTextArea;


public class Timer extends Thread{
	private int count = 151;
	private JTextArea time;
	private BattlePlace warPlace;
	public Timer(JTextArea j, BattlePlace war){
		time = j;
		warPlace = war;
	}
	public int returnCount(){
		return count;
	}
	public void run(){
		while(warPlace.Stop()){
			count--;
			if(count<=0)
				count = 0;
			time.setText("REMAINING BONUS  "+count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
