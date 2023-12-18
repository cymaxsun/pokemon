import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CustomButton extends JButton{
    private Image image, imagePressed;
    private boolean pressed = false;

    public CustomButton(Image image, Image pressedImage) {
    	super();
    	setContentAreaFilled(false);
    	setBorder(null);
        this.image = image;
        this.imagePressed = pressedImage;
        
        addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pressed = true;
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e ) {
				pressed = false;
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e ) {
				pressed = false;
				repaint();
			}
		});

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pressed) {
        	g.drawImage(imagePressed, 0, 0, this.getWidth(), this.getHeight(),null);
        } else {
        	g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),null);
        }
        
    }
    
    
}

