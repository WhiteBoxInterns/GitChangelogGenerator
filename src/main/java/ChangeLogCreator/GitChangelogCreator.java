package ChangeLogCreator;

import FileCreatorStrategy.FileCreator;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

/**
 * class which contains function to write commit messages between last two tags to changelog file and then
 * add file to git repo
 */
public class GitChangelogCreator {
	
	private FileCreator fileCreator;
	
	public void setFileCreator(FileCreator fileCreator) {
		this.fileCreator = fileCreator;
	}
	
	public void generateChangelog(String source, String branch) throws IOException, GitAPIException {
		fileCreator.create(source, branch);
	}
	
	
}
