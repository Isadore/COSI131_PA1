package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.io.File;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;

public class ListFilter extends SequentialFilter {

	@Override
	protected String processLine(String line) {
		return String.join(
				System.lineSeparator(), 
				new File(CurrentWorkingDirectory.get()).list()
			);
	}
	
	

}
