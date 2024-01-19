package main;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class ImagePanel extends JPanel {
    private Image image;
    


	public ImagePanel() {
    	
    }
    
    public ImagePanel(Image image) {
        this.image = image;
    }
    
    public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
    
    @Override
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g); 
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),null);
    }
}
