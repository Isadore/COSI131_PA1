package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.io.File;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;
import edu.brandeis.cs.cs131.pa1.filter.Message;

/**
 * Updates CurrentWorkingDirectory class based on user input
 * @author Isadore H
 *
 */
public class ChangeDirectoryFilter extends SequentialFilter {
	
	private String directory;
	
	/**
	 * Stores user input for use in processLine
	 * @param dir new directory or dot shorthand user input
	 */
	public ChangeDirectoryFilter(String dir) {
		this.directory = dir.trim();
	}

	/**
	 * Parses and validates directory input and updates CurrentWorkingDirectory
	 * @param line unused
	 */
	@Override
	protected String processLine(String line) {
		if(directory.equals("")) {
			CurrentWorkingDirectory.reset();
			return null;
		}
		File cwd = new File(CurrentWorkingDirectory.get());
		String[] dirs = directory.split(CurrentWorkingDirectory.getPathSeparator(true));
		for(String d : dirs) {
			if(d.equals("..")) {
				cwd = cwd.getAbsoluteFile().getParentFile();
			} else if(!d.equals(".") && !d.equals("")) {
				cwd = new File(cwd, d);
			}
		}
		if(cwd.exists() && cwd.isDirectory()) {
			CurrentWorkingDirectory.setTo(cwd.getAbsolutePath());
		} else {
			SequentialREPL.printErr(Message.DIRECTORY_NOT_FOUND, "cd " + directory);
		}
		return null;
	}
	
	

}
