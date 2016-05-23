package com.ilimi.producer;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import com.ilimi.jsonParser.JsonPojo;

// Producer Class in java
public class Producer implements Runnable {

	private final BlockingQueue<JsonPojo> sharedQueue;
	private final int producer;
	private ArrayList<JsonPojo> fileList = new ArrayList<JsonPojo>();

	public Producer(BlockingQueue<JsonPojo> sharedQueue2, int pindex, ArrayList<JsonPojo> file) {
		this.sharedQueue = sharedQueue2;
		this.fileList = file;
		this.producer = pindex;
	}

	public void run() {
		// System.out.println("fileList"+fileList);
		for (JsonPojo s : fileList) {
			try {
				produce(s, producer);
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void produce(JsonPojo s, int producer) throws InterruptedException {
		sharedQueue.put(s);
		System.out.println("Produced by producer : " + producer + " : " + s);
	}

}
