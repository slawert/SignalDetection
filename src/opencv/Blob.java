package opencv;

import java.awt.Point;
import java.awt.Rectangle;
import processing.core.PImage;

public class Blob
{

    public float area;
    public float length;
    public Point centroid;
    public Rectangle rectangle;
    public Point points[];
    public boolean isHole;
    public int pixels[];

    protected Blob()
    {
        area = 0.0F;
        length = 0.0F;
        centroid = new Point();
        rectangle = new Rectangle();
        points = new Point[0];
        isHole = false;
        pixels = new int[0];
    }

    protected Blob(float area, float length, Point centroid, Rectangle rect, Point points[], boolean isHole)
    {
        this.area = 0.0F;
        this.length = 0.0F;
        this.centroid = new Point();
        rectangle = new Rectangle();
        this.points = new Point[0];
        this.isHole = false;
        pixels = new int[0];
        this.area = area;
        this.length = length;
        this.centroid = centroid;
        rectangle = rect;
        this.points = points;
        this.isHole = isHole;
    }

    public void loadPixels()
    {
    }

    public PImage image()
    {
        return new PImage(rectangle.width, rectangle.height);
    }
}
