import java.util.ArrayList;
import java.util.List;

/**
the class get two points and makes a line from them, it can also
retuen its length,its midlle, if two lines are inetrsecting and their
intexest point, if they are equals and the points that creats thw line .*/
public class Line {
    private Point start, end;

    /**
    * Construct a line givne two points.
    * @param start point coordinate
    * @param end point coordinate
    */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
    * Construct a line givne four coardinates.
    * @param x1 x values of the first point
    * @param y1 y values of the first point
    * @param x2 x values of the second point
    * @param y2 y values of the second point
    */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
    * @return the length of the line
    */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
    * @return the middle point of the line
    */
    public Point middle() {
        int middlex = ((start.getX() + end.getX()) / 2);
        int middley = ((start.getY() + end.getY()) / 2);
        return new Point(middlex, middley);
    }

    /**
    * @return the start point of the line
    */
    public Point start() {
        return this.start;
    }

    /**
    * @return the end point of the line
    */
    public Point end() {
        return this.end;
    }
    /**
    * checks if the lines are intersecting.
    * @param other line
    * @return true if the lines are intersect, and false if they dont*/
    public boolean isIntersecting(Line other) {
        //we want to find the equation of the lines as ax + by = c
        double a1 = this.end.getY() - this.start.getY();
        double b1 = (this.start.getX() - this.end.getX());
        double c1 = (a1 * this.start.getX()) + (b1 * this.start.getY());
        double a2 = (other.end.getY() - other.start.getY());
        double b2 = (other.start.getX() - other.end.getX());
        double c2 = (a2 *  other.start.getX()) + (b2 * other.start.getY());
        //calculate the deteminant of the lines
        double determinant = a1 * b2 - b1 * a2;

        if (determinant == 0) {
        // the lines are paralleel therefor they dont intersrct
            return false;
            }
        // we caluculate the intersection point and then checks
        // if she is in both of our lines
        double tempx = (((b2 * c1) - (b1 * c2)) / determinant);
        double tempy = (((a1 * c2) - (a2 * c1)) / determinant);
        return ((tempx <= Math.max(this.start.getX(), this.end.getX()))
                && (tempx >= Math.min(this.start.getX(), this.end.getX()))
                && (tempy <= Math.max(this.start.getY(), this.end.getY()))
                && (tempy >= Math.min(this.start.getY(), this.end.getY()))
                && (tempx <= Math.max(other.start.getX(), other.end.getX()))
                && (tempx >= Math.min(other.start.getX(), other.end.getX()))
                && (tempy <= Math.max(other.start.getY(), other.end.getY()))
                && (tempy >= Math.min(other.start.getY(), other.end.getY())));
}
    /**
    * checks if the lines are intersecting.
    * @param other line
    * @return the intersect point if the lines intersect, and null otherwise*/
    public Point intersectionWith(Line other) {
        //we want to find the equation of the lines as ax + by = c
       int a1 = this.end.getY() - this.start.getY();
       int b1 = (this.start.getX() - this.end.getX());
       int c1 = (a1 * this.start.getX()) + (b1 * this.start.getY());
       int a2 = (other.end.getY() - other.start.getY());
       int b2 = (other.start.getX() - other.end.getX());
       int c2 = (a2 *  other.start.getX()) + (b2 * other.start.getY());
       //calculate the deteminant of the lines
       int determinant = a1 * b2 - b1 * a2;

        if (determinant == 0) {
            // the lines are paralleel therefor they dont intersrct
            return null;
            }
        // we caluculate the intersection point and then checks if she is in
        // both of our lines if so, we bulid point and retuen it, else null
        int tempx = (((b2 * c1) - (b1 * c2)) / determinant);
        int tempy = (((a1 * c2) - (a2 * c1)) / determinant);
        if ((tempx <= Math.max(this.start.getX(), this.end.getX()))
                && (tempx >= Math.min(this.start.getX(), this.end.getX()))
                && (tempy <= Math.max(this.start.getY(), this.end.getY()))
                && (tempy >= Math.min(this.start.getY(), this.end.getY()))
                && (tempx <= Math.max(other.start.getX(), other.end.getX()))
                && (tempx >= Math.min(other.start.getX(), other.end.getX()))
                && (tempy <= Math.max(other.start.getY(), other.end.getY()))
                && (tempy >= Math.min(other.start.getY(), other.end.getY()))) {
           return new Point(tempx, tempy);
        } else {
            return null;
        }
    }
    /**
    * checks if the lines are equal.
    * @param other line
    * @return return true is the lines are equal, false otherwise*/
    public boolean equals(Line other) {
        return (((this.start.getX() == other.start.getX())
            && (this.start.getY() == other.start.getY()))
        && ((this.end.getX() == other.end.getX())
            && (this.end.getY() == other.end.getY())));
    }

    /**
    * find the closet point to the first of the line of the rectangle.
    * @param rect the rectangle we want to check distance to
    * @return null if this line does not intersect with the rectangle
    * Otherwise, return the closest intersection point to the start of the lin
    */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectPoints = new ArrayList<Point>();
        intersectPoints = rect.intersectionPoints(this);
        //if there isn't any point on the line that intersect with
        //the rectangle, return null
        if (intersectPoints.get(0) == null) {
            return null;
        }
        //else, find the closet point to the start of the line
        double minLength = this.start.distance(intersectPoints.get(0));
        Point minPoint = (intersectPoints.get(0));
        for (int i = 1; i < intersectPoints.size(); i++) {
            if (minLength  > this.start.distance(intersectPoints.get(i))) {
                minLength = this.start.distance(intersectPoints.get(i));
                minPoint = intersectPoints.get(i);
            }
        }
        return minPoint;
    }
}



