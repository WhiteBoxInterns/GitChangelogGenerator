import CommitMessageGetterStrategy.CommitMessageGetterAllBranches;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests {
    private Git git;
    private CommitMessageGetterAllBranches commitMessageGetter;

    // Tests for CommitMessageGetters
    @BeforeEach
    public void setGit() throws GitAPIException, IOException {
        BasicConfigurator.configure();
        Path repoPath = Paths.get(System.getProperty("user.dir") + "\\ChangeloggerTestRepo");
        if (Files.exists(repoPath)) {
            FileUtils.deleteDirectory(new File(String.valueOf(repoPath)));
        }

        git = Git.cloneRepository()
                .setURI("https://github.com/WhiteBoxInterns/ChangeloggerTestRepo.git")
                .call();
        commitMessageGetter = new CommitMessageGetterAllBranches();

    }

    @Test
    public void givenUrlWhenAllBranchesThenReturnTwoTags() throws IOException, GitAPIException {
        assertEquals("second realease" + "\n\n" + "added more features" + "\n\n", commitMessageGetter.getCommitMessages(git, "").toString());
    }

    @Test
    public void givenUrlWhenMasterBranchThenReturnTwoTags() throws IOException, GitAPIException {
        assertEquals("second realease" + "\n" + "\n" + "added more features" + "\n" + "\n", commitMessageGetter.getCommitMessages(git, "master").toString());
    }

    @AfterEach
    public void closeRepository() {
        git.getRepository().close();
    }
}
