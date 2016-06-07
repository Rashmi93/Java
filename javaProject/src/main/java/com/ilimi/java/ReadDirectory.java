package com.ilimi.java;

import java.io.File;

public class ReadDirectory {

	public File[] readFromFolder() throws InterruptedException {

		File Folder = new File("src/main/resources");

		File[] files = Folder.listFiles();
		return files;
	}
}
