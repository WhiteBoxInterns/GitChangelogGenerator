package FileCreatorStrategy;

import CommitMessageGetterStrategy.CommitMessageGetter;
import FileCreatorStrategy.FileCreator;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LocalRepositoryCreator implements FileCreator {
	public CommitMessageGetter commitMessageGetter;
	
	public void setCommitMessageGetter(CommitMessageGetter commitMessageGetter) {
		this.commitMessageGetter = commitMessageGetter;
	}
	
	public LocalRepositoryCreator(CommitMessageGetter commitMessageGetter) {
		this.commitMessageGetter = commitMessageGetter;
	}
	
	@Override
	/**
	 * finds corresponding objectIds for the last two tags in the tagList of the repo
	 * displays commit messages between those two tags
	 * writes output to file and adds file to repo
	 *
	 * @param source - given as String by user in the command line
	 * @throws IOException     - work with files
	 * @throws GitAPIException - JGit specific exception
	 */
	public void create(String source, String branch) throws IOException, GitAPIException {
		BasicConfigurator.configure();
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repo = builder.setGitDir(new File(source)).setMustExist(true).build();
		Git git = new Git(repo);
		
		writeToFile(String.valueOf(commitMessageGetter.getCommitMessages(git, branch)));
		
		
	}
	
	public void writeToFile(String content) throws IOException {
		FileWriter fileWriter = new FileWriter("./Changelog.md");
		fileWriter.write(content);
		
		fileWriter.close();
	}
}
