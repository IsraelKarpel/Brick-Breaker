
import java.util.ArrayList;
import java.util.List;

/**
 * the class is a rectangle that appears on the screen.
 */
   class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;

    /**
    * Create a new rectangle with location and width/height.
    * @param upperLeft the upperleft point of the rectangle.
    * @param width the length g the rectangle width.
    * @param height the height of the rectangle .
    */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        }

        /**
        * creates a list of points that intersect with the line.
        * @param line the line we want to check intersect with
        * @return a (possibly empty) List of intersection points
        * with the specified line.
        */
        public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectPoints = new ArrayList<Point>();
        for (int i = 0; i < this.height; i++) {
            Line tempLine = new Line(upperLeft.getX(), upperLeft.getY() + i, upperRight.getX(), upperRight.getY() + i);
            if (tempLine.isIntersecting(line)) {
                Point tempPoint = (tempLine.intersectionWith(line));
                intersectPoints.add(tempPoint);
                }
            }
            if (intersectPoints.size() == 0) {
            intersectPoints.add(null);
            }
                return intersectPoints;
        }

        /**
        @return the width of the rectangle
        */
        public double getWidth() {
        return this.width;
        }

        /**
        @return the width of the rectangle
        */
        public double getHeight() {
        return this.height;
        }

        /**
        @return the upper-left point of the rectangle.
        */
        public Point getUpperLeft() {
        return this.upperLeft;
        }

        /**
        @return the upper-right point of the rectangle.
        */
         public Point getUpperRight() {
            return this.upperRight;
            }

        /**
        * checks if a certain point is inside the rectangle.
        * @param p a point
        * @return boolean answer if the point is inside the rect.
        */
        public boolean isPointInRectangle(Point p) {
        Point temp;
        int x = this.upperLeft.getX();
        int y = this.upperLeft.getY();
        int maxHeight = y + (int) this.height;
        int maxWidth = x + (int) this.width;
        for (; y < maxHeight; y++) {
            for (; x < maxWidth; x++) {
                temp = new Point(x, y);
                if (temp.equals(p)) {
                    return true;
                    }
                }
                x = this.upperLeft.getX();
            }
            return false;
        }
    }