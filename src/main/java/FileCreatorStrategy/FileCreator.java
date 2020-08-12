package FileCreatorStrategy;

import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public interface FileCreator {
	void create(String source, String branch) throws IOException, GitAPIException;
}
