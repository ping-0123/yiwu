package com.test;

public class VolatileTest {
	public  static int count= 0;
	
	public synchronized static void inc(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
	}
	
	public synchronized static void show(){
		System.out.println("VolatileTest.count= " + VolatileTest.count);
	}
	
	public static void main(String[] args) {
		for(int i=0; i<1000; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					VolatileTest.inc();
				}
			}).start();
		}
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
