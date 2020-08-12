import ChangeLogCreator.GitChangelogCreator;
import CommitMessageGetterStrategy.CommitMessageGetterAllBranches;
import CommitMessageGetterStrategy.CommitMessageGetterSpecificBranch;
import FileCreatorStrategy.LocalRepositoryCreator;
import FileCreatorStrategy.RemoteRepositoryCreator;
import Utils.Options;
import Utils.UserInputParser;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException, GitAPIException {
		GitChangelogCreator changelogCreator = new GitChangelogCreator();
		Options options = new Options();
		try {
			String path = args[0];
			if (UserInputParser.parseInput(args, options)) {
				if (options.branchNotDefault)
					if (options.remoteRepository)
						changelogCreator.setFileCreator(new RemoteRepositoryCreator(new CommitMessageGetterSpecificBranch()));
					else
						changelogCreator.setFileCreator(new LocalRepositoryCreator(new CommitMessageGetterSpecificBranch()));
				else {
					if (options.remoteRepository)
						changelogCreator.setFileCreator(new RemoteRepositoryCreator(new CommitMessageGetterAllBranches()));
					else
						changelogCreator.setFileCreator(new LocalRepositoryCreator(new CommitMessageGetterAllBranches()));
				}
				changelogCreator.generateChangelog(path, options.branchName);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter repository path/url.");
		}
		
		
	}
}
