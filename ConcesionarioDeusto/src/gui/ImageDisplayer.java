package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImageDisplayer extends JPanel {
    private Image image;

    public ImageDisplayer () {
        this (new BufferedImage (1, 1, BufferedImage.TYPE_INT_RGB));
    }

    public ImageDisplayer (Image image) {
        this (image, image.getWidth (null), image.getHeight (null));
    }

    public ImageDisplayer (Image image, int width) {
        this (image, width, image.getHeight (null) * width / image.getWidth (null));
    }

    public ImageDisplayer (Image image, int width, int height) {
        this (image, width, height, Image.SCALE_DEFAULT);
    }

    public ImageDisplayer (Image image, int width, int height, int hints) throws NullPointerException {
        super ();

        if (image == null)
            throw new NullPointerException ("Null images are not allowed in ImageDisplayers.");

        this.setImage (image, width, height, hints);
    }

    public Image getImage () {
        return this.image;
    }

    public void setImage (Image image) {
        this.setImage (image, image.getWidth (this), image.getHeight (this));
    }

    public void setImage (Image image, int width) {
        this.setImage (image, width, image.getHeight (this) * width / image.getWidth (this));
    }

    public void setImage (Image image, int width, int height) {
        this.setImage (image, width, height, Image.SCALE_DEFAULT);
    }

    public void setImage (Image image, int width, int height, int hints) throws NullPointerException {
        if (image == null)
        	throw new NullPointerException ("Null images are not allowed in ImageDisplayers.");

        this.image = image.getScaledInstance (width, height, hints);
        this.image.setAccelerationPriority (1);
        this.setSize (this.getSize ());
    }

    @Override
    protected void paintComponent (Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create ();
        g2d.setRenderingHint (RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint (RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint (RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint (RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint (RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent (g2d);
        g2d.drawImage (this.image, 0, 0, this.getWidth (), this.getHeight (), this);
    }

    @Override
    public Dimension getSize () {
        return this.getPreferredSize ();
    }

    @Override
    public Dimension getMinimumSize () {
        return this.getPreferredSize ();
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension (this.image.getWidth (this), this.image.getHeight (this));
    }

    @Override
    public Dimension getMaximumSize () {
        return this.getPreferredSize ();
    }
}
