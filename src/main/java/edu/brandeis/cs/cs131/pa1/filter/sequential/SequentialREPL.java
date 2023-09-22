package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.List;
import java.util.Scanner;

import edu.brandeis.cs.cs131.pa1.filter.Message;

/**
 * The main implementation of the REPL loop (read-eval-print loop). It reads
 * commands from the user, parses them, executes them and displays the result.
 */
public class SequentialREPL {

	/**
	 * The main method that will execute the REPL loop
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {

		Scanner consoleReader = new Scanner(System.in);
		System.out.print(Message.WELCOME);

		while (true) {
			System.out.print(Message.NEWCOMMAND);

			// read user command, if its just whitespace, skip to next command
			String cmd = consoleReader.nextLine();
			if (cmd.trim().isEmpty()) {
				continue;
			}

			// exit the REPL if user specifies it
			if (cmd.trim().equals("exit")) {
				break;
			}
			
			List<SequentialFilter> sf = SequentialCommandBuilder.createFiltersFromCommand(cmd);
			if(sf != null) {
				sf.get(0).output.write(sf.get(0).processLine(null));
				for(int i = 1; i < sf.size(); i++) {
					String s = sf.get(i).input.read();
					if(s != null) {
						sf.get(i).input.write(s);
						sf.get(i).process();
					} else {
						break;
					}
				}
			}
		}
		System.out.print(Message.GOODBYE);
		consoleReader.close();

	}
	
	public static void printErr(Message m, String p) {
		System.out.print(m.with_parameter(p));
	}
	
	public static void print(String s) {
		System.out.print(Message.NEWCOMMAND);
		System.out.print(s);
		System.out.println();
	}

}
