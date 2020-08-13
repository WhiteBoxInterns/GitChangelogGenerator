import ChangeLogCreator.GitChangelogCreator;
import CommitMessageGetterStrategy.CommitMessageGetterAllBranches;
import CommitMessageGetterStrategy.CommitMessageGetterSpecificBranch;
import FileCreatorStrategy.LocalRepositoryCreator;
import FileCreatorStrategy.RemoteRepositoryCreator;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTests {
    @Test
    public void givenLocalRepowhenNoExtraOptionsthenFileIsCreated() throws GitAPIException, IOException {
        BasicConfigurator.configure();
        String workingDir = System.getProperty("user.dir");
        Path repoPath = Paths.get(workingDir + "\\ChangeloggerTestRepo");
        
        GitChangelogCreator tester = new GitChangelogCreator();
        tester.setFileCreator(new LocalRepositoryCreator(new CommitMessageGetterAllBranches()));
        
        Git git = Git.cloneRepository()
            .setDirectory(new File("./ChangeloggerTestRepo"))
            .setURI("https://github.com/WhiteBoxInterns/ChangeloggerTestRepo.git")
            .call();
        
        tester.generateChangelog(git.getRepository().getIdentifier(), null);
        assertTrue(Files.exists(Paths.get("./Changelog")));
        FileUtils.forceDelete(new File("./Changelog"));
    }
    
    @Test
    public void givenRemoteRepowhenNoExtraOptionsthenFileIsCreated() throws GitAPIException, IOException {
        BasicConfigurator.configure();
        
        GitChangelogCreator tester = new GitChangelogCreator();
        tester.setFileCreator(new RemoteRepositoryCreator(new CommitMessageGetterAllBranches()));
        
        tester.generateChangelog("https://github.com/WhiteBoxInterns/ChangeloggerTestRepo.git", null);
        assertTrue(Files.exists(Paths.get("./Changelog")));
        FileUtils.forceDelete(new File("./Changelog"));
        FileUtils.forceDelete(new File("./Repo"));
    }
    
    @Test
    public void givenRemoteRepowhenCustomBranchthenFileIsCreated() throws GitAPIException, IOException {
        BasicConfigurator.configure();
        
        GitChangelogCreator tester = new GitChangelogCreator();
        tester.setFileCreator(new RemoteRepositoryCreator(new CommitMessageGetterSpecificBranch()));
        
        tester.generateChangelog("https://github.com/WhiteBoxInterns/ChangeloggerTestRepo.git", "branch");
        assertTrue(Files.exists(Paths.get("./Changelog")));
        FileUtils.forceDelete(new File("./Changelog"));
    }
}
