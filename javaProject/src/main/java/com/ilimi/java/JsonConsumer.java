package com.ilimi.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Consumer Class in Java
@SuppressWarnings("rawtypes")
public class JsonConsumer implements Runnable, Consumer {

	private BlockingQueue<ArrayList<String>> sharedQueue;
	int consumer;
	ArrayList<JsonPojo> jsonObject = new ArrayList<JsonPojo>();

	public JsonConsumer(BlockingQueue<ArrayList<String>> sharedQueue, int cindex) {
		this.sharedQueue = sharedQueue;
		this.consumer = cindex;
	}

	public void run() {
		while (true) {
			try {
				consume(consumer);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void consume(int consumer) {

		ArrayList<String> eventData = new ArrayList<String>();
		try {
			eventData = sharedQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Consumed by consumer: " + consumer + " :" + eventData);

		ObjectMapper mapper = new ObjectMapper();
		JsonPojo json = new JsonPojo();

		for (String event : eventData) {
			try {
				json = mapper.readValue(event, JsonPojo.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonObject.add(json);
		}
		System.out.println("jsonobject :" + jsonObject);
		DbConnection con = new DbConnection();
		try {
			con.insertToDatabase(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
