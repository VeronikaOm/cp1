package cp1;

import java.util.concurrent.Semaphore;

public class Main {
	static final Semaphore barista = new Semaphore(2);

	public static int PeopleHere = 0;
	
	static boolean isAvailableHours = true;
	
	public static synchronized boolean isOpen () {
		return isAvailableHours;
	}
	
	public static synchronized void closeCafe () {
		isAvailableHours = false;
		 System.err.println("_=_=_=_=_=_=_=_=_=Кав'ярню зачинено=_=_=_=_=_=_=_=_=_=_=");
	}

	public static void main(String[] args) throws InterruptedException {
		Runnable cafe = () -> {
			int i = 0;
			while(isAvailableHours) {
				new Thread(new People(), String.valueOf(i)).start();
				i++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		};
		
		Thread cafeThread = new Thread(cafe, "Кав'ярня");
		cafeThread.start();
		Thread.sleep(12000);
			closeCafe();
	}

}
