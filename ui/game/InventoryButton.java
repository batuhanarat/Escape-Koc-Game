package ui.game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class InventoryButton extends ImageIcon {
	private ImageIcon icon;
	private Image image;
	private String name;

	public InventoryButton(String name, Image image) {
		// super(name, new ImageIcon(image));
		this.name = name;
		this.image = image;

		// setVerticalTextPosition(SwingConstants.TOP);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
