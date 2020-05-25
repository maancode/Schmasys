package schoolManegementSystem;

import javax.swing.Icon;

public class ImgNtext {
	private String name;
	private Icon image;

	public ImgNtext(String name , Icon icon){
        this.image=icon;
        this.name=name;
        
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Icon getImage() {
		return image;
	}

	public void setImage(Icon image) {
		this.image = image;
	}
	
}
