package controller;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class People extends Thread{
	
	Semaphore semaph;
	final int hallway = 200;
	int[] ppo = new int[2];
	Random rand = new Random();
	
	//ppo[0] ran - ppo[1] id
	
	public People(Semaphore s, int i) {
		this.semaph = s;
		ppo[1] = i;
	}
	
	private void walk() {
		
		
		
		while (ppo[0] < hallway) {
			
			if (ppo[0] > 194) {
				int complement;
				complement = 200 - ppo[0];
				ppo[0] += complement;
			} else { ppo[0] += rand.nextInt(4,6); }
			
			try {
				sleep(100);
				System.out.println("Person " + ppo[1] + " ran " + ppo[0]);
			} catch (InterruptedException e) {
				e.printStackTrace();}
		}
		System.out.println("Person " + ppo[1] + " finished the hallway! ");
	}
	
	private void door() {
		
		try {
			sleep(rand.nextInt(1000,2000));
			System.out.println("Person " + ppo[1] + " opened the door!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		walk();
		
		try {
			semaph.acquire();
			door();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally { semaph.release(); }
		
	}
}
