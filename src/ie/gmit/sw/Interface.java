package ie.gmit.sw;

public class Interface {
	private String fileNameA, fileNameB;
	private int shingleSize, option;

	public Interface() {
		super();
	}

	/*
	 * function to launch a menu to the user menu will allow user to enter 2
	 * filenames and shingle size
	 */
	public Interface show() {

		option = BackEnd.getInputInt(BackEnd.showMainMenu);
		if (option == 1) {
			this.fileNameA = BackEnd.getInputString("Enter first file name: ");
			this.fileNameB = BackEnd.getInputString("Enter second file name: ");
			this.shingleSize = BackEnd.getInputInt("Enter Shingle Size:");
		}

		return null;

	}// show()

	public String getFileNameA() {
		return fileNameA;
	}

	protected void setFileNameA(String fileNameA) {
		this.fileNameA = fileNameA;
	}

	public String getFileNameB() {
		return fileNameB;
	}

	protected void setFileNameB(String fileNameB) {
		this.fileNameB = fileNameB;
	}

	public int getShingleSize() {
		return shingleSize;
	}

	protected void setShingleSize(int shingleSize) {
		this.shingleSize = shingleSize;
	}

	public int getOption() {
		return option;
	}

}