package com.ilimi.jsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilimi.ReadFiles.ReadDirectory;

public class TextFileToJsonObj {

	public BlockingQueue<ArrayList<JsonPojo>> parseToJson() throws InterruptedException, IOException {

		ReadDirectory rd = new ReadDirectory();
		File[] files = rd.readFromFolder();
		BlockingQueue<ArrayList<JsonPojo>> queue = new LinkedBlockingQueue<ArrayList<JsonPojo>>();
		ArrayList<String> strLine = new ArrayList<String>();

		for (int i = 0; i < files.length; i++) {
			// System.out.println("filePath : " + i + " : "
			// +files[i].getPath());
			File f = new File(files[i].getPath());
			FileReader in = new FileReader(f);
			BufferedReader br = new BufferedReader(in);
			String line;
			for (; (line = br.readLine()) != null;) {
				strLine.add(line);
			}

			ObjectMapper mapper = new ObjectMapper();
			try {
				JsonPojo json = new JsonPojo();
				ArrayList<JsonPojo> filedata = new ArrayList<JsonPojo>();
				for (String s : strLine) {
					json = mapper.readValue(s, JsonPojo.class);
					filedata.add(json);
					// System.out.println(js);
				}

				// System.out.println("list : "+queue);
				queue.put(filedata);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return queue;
	}
}