import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IntegrationTests {
    @Test
    public void whenCloneRemoteTestRepoAndCallCreateChangelogFunctionChangelogFileIsCreated() throws GitAPIException, IOException {
        String workingDir = System.getProperty("user.dir");
        Path repoPath = Paths.get( workingDir + "\\ChangeloggerTestRepo");
        GitChangelogCreator tester = new GitChangelogCreator();

        if(Files.exists(repoPath)){
            FileUtils.deleteDirectory(new File(String.valueOf(repoPath)));
        }

        Git git = Git.cloneRepository()
                .setURI( "https://github.com/WhiteBoxInterns/ChangeloggerTestRepo.git" )
                .call();

        tester.createWithProvidedRepo(git);
        assertTrue(Files.exists(Paths.get(workingDir + "\\Changelog.md")));
    }
}
