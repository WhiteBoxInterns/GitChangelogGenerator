package Utils;

public class Options {
	public boolean remoteRepository;
	public boolean branchNotDefault;
	public String branchName;
	
	public boolean isBranchNotDefault() {
		return branchNotDefault;
	}
	
	public void setBranchNotDefault(boolean branchNotDefault) {
		this.branchNotDefault = branchNotDefault;
	}
	
	public boolean isRemoteRepository() {
		return remoteRepository;
	}
	
	public void setRemoteRepository(boolean remoteRepository) {
		this.remoteRepository = remoteRepository;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
