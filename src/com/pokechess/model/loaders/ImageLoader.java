package com.pokechess.model.loaders;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageLoader {

    public static String ICON = "/com/pokechess/view/images/icon.png";
    public static String BG = "/com/pokechess/view/images/background/";
    public static String BTN = "/com/pokechess/view/images/button/";
    public static String PKM_CARD = "/com/pokechess/view/images/pkm/card/card-";
    public static String PKM_FRAME = "/com/pokechess/view/images/pkm/frame/card-";
    public static String PKM_FULL = "/com/pokechess/view/images/pkm/full/full-";
    public static String PKM_MOVE = "/com/pokechess/view/images/pkm/skill/";

    public static Image loadImage(String path) {

        URL resource = ImageLoader.class.getResource(path);
        Image image = Toolkit.getDefaultToolkit().getImage(resource);

        return image;
    }

    public static ImageIcon loadImageIcon(String path) {

        Image image = loadImage(path);

        ImageIcon imageIcon = new ImageIcon(image);

        return imageIcon;
    }

    public static ImageIcon loadImageIcon(String path, int width) {

        Image image = loadImage(path, width);
        ImageIcon imageIcon = new ImageIcon(image);

        return imageIcon;
    }

    public static Image loadImage(String path, int width) {

        Image image = loadImage(path);

        ImageIcon imageIcon = new ImageIcon(image);

        float originalWidth = imageIcon.getIconWidth();
        float originalHeight = imageIcon.getIconHeight();

        float ratio = width / originalWidth;

        int height = (int) (originalHeight * ratio);

        image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return image;
    }
}
