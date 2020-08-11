public class Options {
	private boolean remoteRepository;
	private boolean txtFormat;
	private boolean pdfFormat;
	private boolean markDownFormat;
	private boolean customChangelog;
	private String versionFrom;
	private String versionTo;
	
	public Options() {
		this.remoteRepository = false;
		this.txtFormat = false;
		this.pdfFormat = false;
		this.markDownFormat = false;
		this.customChangelog = false;
	}
	
	public boolean isRemoteRepository() {
		return remoteRepository;
	}
	
	public void setRemoteRepository(boolean remoteRepository) {
		this.remoteRepository = remoteRepository;
	}
	
	public boolean isTxtFormat() {
		return txtFormat;
	}
	
	public void setTxtFormat(boolean txtFormat) {
		this.txtFormat = txtFormat;
	}
	
	public boolean isPdfFormat() {
		return pdfFormat;
	}
	
	public void setPdfFormat(boolean pdfFormat) {
		this.pdfFormat = pdfFormat;
	}
	
	public boolean isMarkDownFormat() {
		return markDownFormat;
	}
	
	public void setMarkDownFormat(boolean markDownFormat) {
		this.markDownFormat = markDownFormat;
	}
	
	public boolean isCustomChangelog() {
		return customChangelog;
	}
	
	public void setCustomChangelog(boolean customChangelog) {
		this.customChangelog = customChangelog;
	}
	
	public String getVersionFrom() {
		return versionFrom;
	}
	
	public void setVersionFrom(String versionFrom) {
		this.versionFrom = versionFrom;
	}
	
	public String getVersionTo() {
		return versionTo;
	}
	
	public void setVersionTo(String versionTo) {
		this.versionTo = versionTo;
	}
}
