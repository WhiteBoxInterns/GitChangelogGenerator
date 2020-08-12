package Utils;

public class UsageMessage {
	private static final String usageMessage = "Usage : filepath/url --options.\n Options: -remote: determines that " +
		"the path given is the url for a remote repo; -branch branch name specify branch to get commit messages from.";
	
	public static String getUsageMessage() {
		return usageMessage;
	}
}
