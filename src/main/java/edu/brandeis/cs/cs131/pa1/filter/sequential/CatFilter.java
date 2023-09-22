package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.io.File;
import java.nio.file.Files;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;
import edu.brandeis.cs.cs131.pa1.filter.Message;

/**
 * Reads text file from location parameter and outputs to pipe
 * @author Isadore H
 * 
 */
public class CatFilter extends SequentialFilter {
	
	private String location;
	
	/**
	 * Stores location to be used when processLine is called
	 * @param fileLocation Must be a child of current directory. Ex: test.txt, dir/test.txt
	 */
	public CatFilter(String fileLocation) {
		this.location = fileLocation;
	}

	/**
	 * Validates supplied location and reads file from disk.
	 * @return text data contained in file.
	 */
	@Override
	protected String processLine(String line) {
		File file = new File(CurrentWorkingDirectory.get(), location);
		if(!file.isFile()) {
			SequentialREPL.printErr(Message.FILE_NOT_FOUND, "cat " + location);
			return null;
		}
		try {
			return new String(Files.readAllBytes(file.toPath()));
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
