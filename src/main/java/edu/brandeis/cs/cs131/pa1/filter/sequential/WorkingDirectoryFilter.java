package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;

public class WorkingDirectoryFilter extends SequentialFilter {

	@Override
	protected String processLine(String line) {
		return CurrentWorkingDirectory.get();
	}

}
