package com.pokechess.model.loaders;

import java.awt.*;

public class Guide {

    public static void centerHorizontally(Component parent, Component child) {
        int parentWidth = parent.getWidth();
        int childWidth = child.getWidth();

        int parentCenter = parentWidth / 2;
        int childCenter = childWidth / 2;

        int positionX = parentCenter - childCenter;
        child.setLocation(positionX, child.getY());
    }
}
