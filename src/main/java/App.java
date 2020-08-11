import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException, GitAPIException {
		GitChangelogCreator c = new GitChangelogCreator();
		c.Create(args[1]);
	}
}
