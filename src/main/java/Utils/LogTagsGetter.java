package Utils;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;

import java.util.ArrayList;
import java.util.List;

public class LogTagsGetter {
	public static List<ObjectId> getLogTags(Git git) throws GitAPIException {
		List<ObjectId> logTags = new ArrayList<>();
		Iterable<Ref> tags = git.tagList().call();
		for (Ref tag : tags) {
			logTags.add(tag.getObjectId());
		}
		return logTags;
	}
}
