package model;

import java.awt.*;

public class Obstacle extends Shape {

    private static final Color color = Color.BLACK;
    int initalX = 0;
    int initalY = 0;

    public Obstacle(int x, int y, int w, int h) {
        super(color, x, y, w, h);
    }

    public Obstacle() {
        super(color, 0, 0, 0, 0);
    }

    public void setParam(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        x2 = w;
        y2 = h;
    }

    public void draw(Graphics g) {
        Color save = g.getColor();
        g.setColor(color);
        g.fillRect(x, y, x2, y2);
        g.setColor(save);
    }

    @Override
    public boolean contains(Point p) {
        return ((p.getX() > x && p.getX() < (x + x2)) && (p.getY() > y && p.getY() < (y + y2)));
    }

    public void setStartBound(Point p) {
        x = (int) p.getX();
        y = (int) p.getY();
        initalX = x;
        initalY = y;

    }

    public void setEndBound(Point p) {
        int xEnd = (int) p.getX();
        int yEnd = (int) p.getY();
//        System.out.println("xpos  " + xEnd + "\nypos  " + yEnd + "\nx  " + x + "\ny  " + y + "\n initialX " + initalX);
        if (initalX < xEnd && initalY < yEnd) {
//            System.out.println("\n bottom right");
            x2 = xEnd - x; // width
            y2 = yEnd - y; // height
        }

        else if (initalX > xEnd && initalY < yEnd) {
//            System.out.println("\n bottom left");
//            x2 += x - xEnd;
            x2 = initalX - xEnd;
            x = xEnd;
            y2 = yEnd - y;
        }
        else if(initalX < xEnd && initalY > yEnd) {
//            System.out.println("\n top right");
            x2 = xEnd - x; // width
            y = yEnd;
            y2 = initalY - yEnd;  //height
        }
        else if (initalX > xEnd && initalY > yEnd) {
//            System.out.println("\n top left");
            x = xEnd;
            y = yEnd;
            x2 = initalX - xEnd;
            y2 = initalY - yEnd;  //height
        }
    }
}