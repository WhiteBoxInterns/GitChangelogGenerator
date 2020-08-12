package CommitMessageGetterStrategy;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;

import java.io.IOException;

public interface CommitMessageGetter {
	StringBuilder getCommitMessages(Git git, String branch) throws IOException, GitAPIException;
}
