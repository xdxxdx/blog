package com.xdx.util;

import java.util.Random;

public class SellTicket implements Runnable {
	private int total = 100;

	public void run() {
		while (true) {
			synchronized (this) {
				if(total > 0) {
					try {
						System.out.println(Thread.currentThread().getName()
								+ " 正在售票...");
						// Thread.sleep(new Random().nextInt(1000));
						System.out.println("当前线程: "
								+ Thread.currentThread().getName()
								+ "--正在执行...");
						System.out.println("剩余票数: total=" + total);
						total--;
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}
	}
}
