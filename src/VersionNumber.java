
public class VersionNumber {
	int familyNumber;
	int updateNumber;
	public VersionNumber(int familyNumber, int updateNumber) {
		super();
		this.familyNumber = familyNumber;
		this.updateNumber = updateNumber;
	}
	public boolean lt(VersionNumber parse) {
		long mine = (this.familyNumber << 32) + this.updateNumber;
		long target = (parse.familyNumber << 32) + parse.updateNumber;
		return mine < target;
	}
	public boolean gt(VersionNumber parse) {
		long mine = (this.familyNumber << 32) + this.updateNumber;
		long target = (parse.familyNumber << 32) + parse.updateNumber;
		return mine > target;
	}
	public VersionNumber nextLimitedUpdate() {
		Integer updateNumber = ((this.updateNumber / 20) + 1) * 20;
		return new VersionNumber(this.familyNumber, updateNumber);
	}
	public VersionNumber nextCliticalPatchUpdate() {
		Integer updateNumber = ((this.updateNumber / 5) + 1) * 5;
		if(updateNumber % 2 == 0) updateNumber++;
		return new VersionNumber(this.familyNumber, updateNumber);
	}
	public VersionNumber nextSecurityAlert() {
		Integer updateNumber = this.updateNumber;
		if(++updateNumber % 5 == 0) updateNumber++;
		return new VersionNumber(this.familyNumber,updateNumber);
	}

}
