import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;

public class CustomButton extends JButton{
    private Image image;
    private int width, height;
    public CustomButton(Image image) {
    	super();
    	setContentAreaFilled(false);
    	setBorder(null);
        this.image = image;
        this.width = width;
        this.height = height;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),null);
    }
    
    public void setImage(Image image) {
    	this.image = image;
    	repaint();
    }
    
    public Image getImage() {
    	return image;
    }
}

