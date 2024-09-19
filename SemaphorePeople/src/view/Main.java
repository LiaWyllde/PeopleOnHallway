package view;

import java.util.concurrent.Semaphore;

import controller.People;

public class Main {
	
	public static void main(String[] args) {
		
		Semaphore semaph = new Semaphore(1);
		//only one person can passthrough the door
		
		for (int i = 0; i < 4; i++) {
			
			People person = new People(semaph, (i+1));
			person.start();
			
		}
		
	}
	
}
