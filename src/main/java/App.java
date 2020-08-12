import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException, GitAPIException {
        Path repoPath = Paths.get(System.getProperty("user.dir") + "\\ChangeloggerTestRepo");
        GitChangelogCreator gitChangelogCreator = new GitChangelogCreator();

        if (Files.exists(repoPath)) {
            FileUtils.deleteDirectory(new File(String.valueOf(repoPath)));
        }

        Git git = Git.cloneRepository()
                .setURI("https://github.com/WhiteBoxInterns/ChangeloggerTestRepo.git")
                .call();

        gitChangelogCreator.createWithProvidedRepo(git);
    }
}
