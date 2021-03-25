package problem;

public class Line {
    public double A;
    public double B;
    public double C;
    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        A = y1 - y2;
        B = x2 - x1;
        C = x1 * y2 - x2 * y1;
    }
}
