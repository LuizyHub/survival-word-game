import java.util.Vector;

public class MoveThread extends Thread {
	private boolean waitFlag = false; public void setWait() {waitFlag = true;}
	private boolean deadFlag = false; public void setDead() {deadFlag = true;}
	private int refresh = 60;
	Vector<Moveable> moveVector;
	Vector<Moveable> removeVector = new Vector<Moveable>();
	public MoveThread(Vector<Moveable> mv) {
		this.moveVector = mv;
	}
	public void SyncRemove(Moveable m) {
		removeVector.add(m);
	}
//	private synchronized void removing() {
//		for(Moveable mv : removeVector) {
//			moveVector.remove(mv);
//		}
//		removeVector.clear();
//		notifyAll();
//		
//	}
	@Override
	public void run() {
		
		while(!deadFlag) {
			try {Thread.sleep(1000/refresh);} catch (InterruptedException e) {}
			for (int i = moveVector.size() - 1; i >= 0; i--) {
	            moveVector.get(i).move();
	        }
//			for(Moveable mv : moveVector) {
//				mv.move();
//			}
			if(!removeVector.isEmpty()) {
				for(Moveable mv : removeVector) {
					moveVector.remove(mv);
				}
				removeVector.clear();
			}
			
			
		}
	}
}
