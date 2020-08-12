import ChangeLogCreator.GitChangelogCreator;
import Utils.Options;
import Utils.UserInputParser;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException, GitAPIException {
		Options options = new Options();
		GitChangelogCreator changelogCreator = UserInputParser.determineStrategies(args, options);
		if (changelogCreator != null) {
			changelogCreator.generateChangelog(args[0], options.branchName);
			System.out.println("File successfully created as Changelog");
		}
		
		
	}
}
