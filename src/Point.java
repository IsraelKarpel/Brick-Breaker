/**
the class get two coardinates and makes a point from them, it can also
calulate the distance between the two points, checks if they are
equals, and retuens the x any y values of the point.*/

public class Point {
private int x;
private int y;

    /**
    * Construct a point givne x and y coordinates.
    * @param x the x coordinate
    * @param y the y coordinate
    */
    public Point(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
}

    /**
    * calculates the distance between two points.
    * @param other point to find her disance from the other point.
    * @return the distance of this point to the other point.
    */
    public double distance(Point other) {
        if ((other == null) || (this == null)) {
            return -1;
        }
        double xvalues = Math.pow((this.x - other.getX()), 2);
        double yvalues = Math.pow((this.y - other.getY()), 2);
        return Math.sqrt(xvalues + yvalues);
    }

    /**
    * checks if the points are the same.
    * @param other point to find her disance from the other point.
    * @return wheather the points are equals or not.
    */
    public boolean equals(Point other) {
    return ((other.getX() == this.x) && (other.getY() == this.y));
    }

    /**
    * @return the x coordinate
    */
    public int getX() {
        return this.x;
    }
    /**
    * @return the y coordinate
    */
    public int getY() {
        return this.y;
        }
}