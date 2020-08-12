import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class which contains function to write commit messages between last two tags to changelog file and then
 * add file to git repo
 */
public class GitChangelogCreator {

	public List<ObjectId> getLogTags(Git git) throws GitAPIException {
		List<ObjectId> logTags = new ArrayList<>();
		Iterable<Ref> tags = git.tagList().call();
		for (Ref rev : tags) {
			logTags.add(rev.getObjectId());
		}
		return logTags;
	}

	public StringBuilder getCommitMessages(Git git) throws GitAPIException, IncorrectObjectTypeException, MissingObjectException {
		List<ObjectId> logTags = getLogTags(git);
		Iterable<RevCommit> logs = git.log().addRange(logTags.get(logTags.size() - 2), logTags.get(logTags.size() - 1)).call();
		StringBuilder sb = new StringBuilder();
		for (RevCommit rev : logs) {
			sb.append(rev.getFullMessage());
			sb.append("\n");
		}
		return sb;
	}

	public void createWithProvidedRepo(Git git) throws GitAPIException, IOException {
		writeToFile(String.valueOf(getCommitMessages(git)));
		git.add().addFilepattern(".").call();
		git.commit();
	}
	/**
	 * finds corresponding objectIds for the last two tags in the tagList of the repo
	 * displays commit messages between those two tags
	 * writes output to file and adds file to repo
	 *
	 * @param repoPath - given as String by user in the command line
	 * @throws IOException     - work with files
	 * @throws GitAPIException - JGit specific exception
	 */
	public void createFromLocalRepo(String repoPath) throws IOException, GitAPIException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repo = builder.setGitDir(new File(repoPath)).setMustExist(true).build();
		Git git = new Git(repo);

		writeToFile(String.valueOf(getCommitMessages(git)));

		git.add().addFilepattern(".").call();
		git.commit();
	}

	public void writeToFile(String content) throws IOException {
		FileWriter fileWriter = new FileWriter("./Changelog.md");
		fileWriter.write(content);

		fileWriter.close();
	}
}
