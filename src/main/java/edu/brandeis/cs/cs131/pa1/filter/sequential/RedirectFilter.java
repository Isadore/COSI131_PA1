package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.io.File;
import java.nio.file.Files;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;

public class RedirectFilter extends SequentialFilter {
	
	File file;
	public RedirectFilter(String f) {
		this.file = new File(CurrentWorkingDirectory.get(), f);
	}

	@Override
	protected String processLine(String line) {
		try {
			Files.write(file.toPath(), line.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
