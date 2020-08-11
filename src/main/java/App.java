import org.eclipse.jgit.api.errors.GitAPIException;
import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException, GitAPIException {
		GitChangelogCreator c = new GitChangelogCreator();
		Options options = new Options();
		for(String arg : args) {
			if(arg.matches("(.*)remote(.*)"))
				options.setRemoteRepository(true);
			else if(arg.matches("(.*)txt(.*)"))
				options.setTxtFormat(true);
			
		}
		c.Create(args[0], options);
	}
}
