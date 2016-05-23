package com.ilimi.consumer;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilimi.databaseConnection.DbConnection;
import com.ilimi.jsonParser.JsonPojo;

// Consumer Class in Java
public class Consumer implements Runnable {

	private BlockingQueue<JsonPojo> sharedQueue;
	int consumer;
	ArrayList<JsonPojo> jsonObj = new ArrayList<JsonPojo>();
	ArrayList<String> jsonInString = new ArrayList<String>();

	public Consumer(BlockingQueue<JsonPojo> sharedQueue2, int cindex) {
		this.sharedQueue = sharedQueue2;
		this.consumer = cindex;
	}

	public Consumer() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		while (true) {
			try {
				// System.out.println("Consumed by consumer: " + consumer + " :
				// " + sharedQueue.take());
				JsonPojo item = sharedQueue.take();
				ObjectMapper mapper = new ObjectMapper();
				String str = mapper.writeValueAsString(item);
				DbConnection con = new DbConnection();
				con.insertToDatabase(item);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}