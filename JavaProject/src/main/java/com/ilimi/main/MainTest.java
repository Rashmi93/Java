package com.ilimi.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import com.ilimi.consumer.Consumer;
import com.ilimi.jsonParser.JsonPojo;
import com.ilimi.jsonParser.TextFileToJsonObj;
import com.ilimi.producer.Producer;

public class MainTest {

	public static void main(String args[]) throws IOException, InterruptedException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of producers ");
		int Producers = sc.nextInt();
		System.out.println("Enter the number of consumers ");
		int Consumers = sc.nextInt();

		// Creating shared object
		BlockingQueue<JsonPojo> sharedQueue = new LinkedBlockingQueue<JsonPojo>();
		BlockingQueue<ArrayList<JsonPojo>> fileList = new LinkedBlockingQueue<ArrayList<JsonPojo>>();

		// Creating Producer and Consumer Thread
		ExecutorService pThreadpool = Executors.newFixedThreadPool(Producers);
		ExecutorService cThreadpool = Executors.newFixedThreadPool(Consumers);

		TextFileToJsonObj jp = new TextFileToJsonObj();
        fileList = jp.parseToJson();
        System.out.println("result"+fileList);

		for (int pindex = 0; pindex < Producers; pindex++) {
			ArrayList<JsonPojo> file = fileList.poll();
			if (file != null)
				pThreadpool.execute(new Producer(sharedQueue, pindex, file));
		}
		int cindex= 0;
		for (; cindex < Consumers; cindex++)
			cThreadpool.execute(new Consumer(sharedQueue,cindex));
		
		pThreadpool.shutdown();
		cThreadpool.shutdown();
	}
}
