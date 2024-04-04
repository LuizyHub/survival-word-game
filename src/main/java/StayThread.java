import java.util.Vector;

public class StayThread extends Thread {
	private Vector<Stayable> stayVector;
	
	public StayThread(Vector<Stayable> stayVector) {
		this.stayVector = stayVector;
	}
	
	@Override
	public void run() {
		while(true) {
			for(Stayable s : stayVector) {
				s.stay();
			}
			GameEngine.getInstance().getFrame().repaint();
		}
	}
	
}
