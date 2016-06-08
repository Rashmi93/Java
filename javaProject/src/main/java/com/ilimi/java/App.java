package com.ilimi.java;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class App {

	public static void main(String args[]) {
		try{
		Properties prop = new Properties();
	    String fileName = "resources/config.properties";
	    InputStream is  = new FileInputStream(fileName);
		prop.load(is);
		String producer = prop.getProperty("producer");
	    String consumer = prop.getProperty("consumer");
		int producers   = Integer.parseInt(producer);
		int consumers   = Integer.parseInt(consumer);
		System.out.println("no of producers : " + producers + " no of consumers : " + consumers);
		
		BlockingQueue<ArrayList<String>> sharedQueue = new LinkedBlockingQueue<ArrayList<String>>();
		
		// Creating Producer and Consumer Thread
		ExecutorService pThreadpool = Executors.newFixedThreadPool(producers);
		ExecutorService cThreadpool = Executors.newFixedThreadPool(consumers);
        
		for (int pindex = 0; pindex < producers; pindex++) {		
		pThreadpool.execute(new Producer(sharedQueue, pindex));
		}
        
		
		for (int cindex= 0; cindex < consumers; cindex++){
			cThreadpool.execute(new JsonConsumer(sharedQueue,cindex));
		}
//		pThreadpool.shutdown();
//		cThreadpool.shutdown();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 catch (IOException e) {
				e.printStackTrace();
			}
		}
}
