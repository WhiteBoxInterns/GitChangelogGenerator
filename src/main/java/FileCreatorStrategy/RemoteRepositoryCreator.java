package FileCreatorStrategy;

import CommitMessageGetterStrategy.CommitMessageGetter;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RemoteRepositoryCreator implements FileCreator {
	public CommitMessageGetter commitMessageGetter;
	
	public void setCommitMessageGetter(CommitMessageGetter commitMessageGetter) {
		this.commitMessageGetter = commitMessageGetter;
	}
	
	public RemoteRepositoryCreator(CommitMessageGetter commitMessageGetter) {
		this.commitMessageGetter = commitMessageGetter;
	}
	
	@Override
	public void create(String source, String branch) throws IOException, GitAPIException {
		BasicConfigurator.configure();
		Git git = Git.cloneRepository()
			.setDirectory(new File("./Repo"))
			.setURI(source)
			.call();
		
		writeToFile(String.valueOf(commitMessageGetter.getCommitMessages(git, branch)));
	}
	public void writeToFile(String content) throws IOException {
		FileWriter fileWriter = new FileWriter("./Changelog");
		fileWriter.write(content);
		
		fileWriter.close();
	}
}
