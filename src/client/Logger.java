package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Classe che scrive il file logger csv, contenente tutti gli errori, MOLTO ULTILE IN FASE DI DEBUG!
 *
 */
public class Logger {
	public static void WriteError(Exception e, String Class, String Function) {
		try {
			FileWriter pw = new FileWriter("Logger.csv", true);
			Date now = new Date();
			StringBuilder builder = new StringBuilder();
			builder.append(now.toString()+",");
			builder.append(Class+",");
			builder.append(Function+",");
			builder.append(e.getMessage());
			pw.write(builder.toString()+";\n");
			pw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
