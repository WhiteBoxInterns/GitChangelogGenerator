import ChangeLogCreator.GitChangelogCreator;
import Utils.Options;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException, GitAPIException {
		GitChangelogCreator changelogCreator = new GitChangelogCreator();
		Options options = new Options();
		String path = args[0];
		if(args[1].equals("--options"))
			if(args[2].matches("(.*)remote(.*)"))
				options.setRemoteRepository(true);
	}
	
}
