package com.pokechess.view.components;

import com.pokechess.model.loaders.Colors;

import javax.swing.*;
import java.awt.*;

public class ScrollablePanel extends JScrollPane {

    private JPanel viewport;

    public ScrollablePanel(int width, int height) {

        super();
        this.initComponents(width, height);
    }

    private void initComponents(int width, int height) {

        // Make our viewport
        this.initViewport();
        this.setViewportView(this.viewport);

        // Initialize own properties
        this.setVisible(true);
        this.getViewport().setOpaque(true);
        this.setBounds(0, 0, width, height);
        this.setBackground(Color.GRAY);

        // Properties for your scrollbars
        this.setAutoscrolls(true);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        // Change the scrollbar style
        this.getVerticalScrollBar().setUI(new BlackScrollBar());
        this.getHorizontalScrollBar().setUI(new BlackScrollBar());

        this.getVerticalScrollBar().setOpaque(false);
        this.getHorizontalScrollBar().setOpaque(false);

        this.setBorder(null);
    }

    private void initViewport() {

        this.viewport = new JPanel();
        this.viewport.setBackground(Colors.OVERLAY_BLACK);

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(10);

        this.viewport.setLayout(flowLayout);
    }

    public void addItem(Component component) {

        this.viewport.add(component);
        this.repaint();
    }

    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        this.repaint();
    }
}
