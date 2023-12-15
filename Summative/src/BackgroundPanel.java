import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;


class BackgroundPanel extends JComponent {
    private Image image;
    public BackgroundPanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight()*7/10,null);
    }
}