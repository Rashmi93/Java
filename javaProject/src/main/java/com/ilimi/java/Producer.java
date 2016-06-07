package com.ilimi.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Producer Class in java
public class Producer implements Runnable {

	private final BlockingQueue<ArrayList<String>> sharedQueue;
	private final int producer;
	@SuppressWarnings("unused")
	private String List = new String();
	ArrayList<String> strLine = new ArrayList<String>();
	static BlockingQueue<String> file = new LinkedBlockingQueue<String>();
    static BlockingQueue<String> readFile = new LinkedBlockingQueue<String>();
	public Producer(BlockingQueue<ArrayList<String>> sharedQueue, int pindex) {
		this.sharedQueue = sharedQueue;
		this.producer = pindex;
	}

	public void run() {
		try { 
			 ReadDirectory rd = new ReadDirectory();
			 File[] listOfFiles = rd.readFromFolder();
			 for (int i = 0; i < listOfFiles.length; i++) {
					file.put(listOfFiles[i].getPath());
			}
			String getFile = file.poll();
			produce(producer,getFile);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
	

	protected void produce(int producer,String getFile) throws FileNotFoundException, InterruptedException{
        if(!readFile.contains(getFile)){
        	readFile.add(getFile);
		FileReader fileReader = new FileReader(getFile);	
		
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		try {
			for (; (line = br.readLine()) != null;) {
				strLine.add(line + "\n");
			}
			sharedQueue.put(strLine);
			System.out.println("Produced by producer : "+producer+" : "+strLine);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        }
	}
}
