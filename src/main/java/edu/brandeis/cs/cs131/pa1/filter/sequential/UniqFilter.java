package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;

public class UniqFilter extends SequentialFilter {

	@Override
	protected String processLine(String line) {
		String[] lines = line.split("\\n");
		if(lines.length < 2) {
			return line;
		}
		ArrayList<String> out = new ArrayList();
		out.add(lines[0]);
		String prev = lines[0];
		for(int i = 1; i < lines.length; i++) {
			if(!lines[i].equalsIgnoreCase(prev)) {
				out.add(lines[i]);
			}
			prev = lines[i];
		}
		return String.join("\n", out);
	}

}
