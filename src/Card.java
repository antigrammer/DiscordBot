import java.io.File;


public class Card {

	private File picture;
	private String name;
	
	public Card(File f, String n) {
		picture = f;
		name = n;
	}
	
	public File getPicture() {
		return picture;
	}
	public void setFile(File f) {
		picture = f;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	
}
