package com.xdx.util;


public class TicketTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*TicketThread thread = new TicketThread();
        
        for(int i=0;i<3;i++)
        {
        	new Thread(thread,"client-"+(i+1)).start();
        }*/
		System.out.println("main 线程启动ʼ");
		SellTicket sellTicket=new SellTicket();
		Thread t1=new Thread(sellTicket, "线程:A");
		Thread t2=new Thread(sellTicket, "线程:B");
		Thread t3=new Thread(sellTicket, "线程:C");
		t2.start();
		t1.start();
		t3.start();
	}

}
