package CommitMessageGetterStrategy;

import Utils.LogTagsGetter;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.IOException;
import java.util.List;

public class CommitMessageGetterSpecificBranch implements CommitMessageGetter {
	
	@Override
	public StringBuilder getCommitMessages(Git git, String branch) throws IOException, GitAPIException {
		BasicConfigurator.configure();
		List<ObjectId> logTags = LogTagsGetter.getLogTags(git);
		if (logTags.size() == 0)
			return new StringBuilder("No commit messages to show.");
		Iterable<RevCommit> logs = git.log().add(git.getRepository().resolve(branch)).addRange(logTags.get(logTags.size() - 2), logTags.get(logTags.size() - 1)).call();
		StringBuilder sb = new StringBuilder();
		for (RevCommit log : logs) {
			sb.append(log.getFullMessage());
			sb.append("\n");
		}
		return sb;
	}
}
