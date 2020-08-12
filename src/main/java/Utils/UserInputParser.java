package Utils;

import ChangeLogCreator.GitChangelogCreator;
import CommitMessageGetterStrategy.CommitMessageGetterAllBranches;
import CommitMessageGetterStrategy.CommitMessageGetterSpecificBranch;
import FileCreatorStrategy.LocalRepositoryCreator;
import FileCreatorStrategy.RemoteRepositoryCreator;

public class UserInputParser {
	public static boolean parseInput(String[] args, Options options) {
		if (args.length > 1)
			if (args[1].equals("--options"))
				if (args.length > 2)
					if (args[2].matches("(.*)remote(.*)"))
						options.setRemoteRepository(true);
					else if (args[2].matches("(.*)branch(.*)"))
						if (args.length > 3) {
							options.setBranchName(args[3]);
							options.setBranchNotDefault(true);
						} else {
							System.out.println(UsageMessage.getUsageMessage());
							return false;
						}
					else {
						System.out.println(UsageMessage.getUsageMessage());
						return false;
					}
				else {
					System.out.println(UsageMessage.getUsageMessage());
					return false;
				}
			else {
				System.out.println(UsageMessage.getUsageMessage());
				return false;
			}
		else {
			System.out.println(UsageMessage.getUsageMessage());
			return false;
		}
		
		return true;
	}
	
	public static GitChangelogCreator determineStrategies(String[] args, Options options) {
		GitChangelogCreator changelogCreator = new GitChangelogCreator();
		try {
			String path = args[0];
			if (parseInput(args, options)) {
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
				return changelogCreator;
			}
		} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			System.out.println("Please enter repository path/url.");
		}
		return null;
	}
}
