package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;

/**
 * Filter to remove all but the first 10 lines of piped input.
 * @author Isadore H.
 *
 */
public class HeadFilter extends SequentialFilter {

	/**
	 * Removes all lines from piped input but the first 10
	 */
	@Override
	protected String processLine(String line) {
		String[] lines = line.strip().split("\\n");
		ArrayList<String> head = new ArrayList();
		for(int i = 0; i < 10 && i < lines.length; i++) {
			head.add(lines[i]);
		}
		return String.join("\n", head);
	}

}
