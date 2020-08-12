package Utils;

public class UserInputParser {
	public static boolean parseInput(String[] args, Options options) {
		if (args.length > 1)
			if (args[1].equals("--options"))
				if (args.length > 2)
					if (args[2].matches("(.*)remote(.*)"))
						options.setRemoteRepository(true);
					else if (args[2].matches("(.*)branch(.*)"))
						if (args.length > 3) {
							options.setBranchName(args[3]);
							options.setBranchNotDefault(true);
						} else {
							System.out.println(UsageMessage.getUsageMessage());
							return false;
						}
					else {
						System.out.println(UsageMessage.getUsageMessage());
						return false;
					}
				else {
					System.out.println(UsageMessage.getUsageMessage());
					return false;
				}
			else {
				System.out.println(UsageMessage.getUsageMessage());
				return false;
			}
		else {
			System.out.println(UsageMessage.getUsageMessage());
			return false;
		}
		
		return true;
	}
}
