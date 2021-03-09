package problem;

public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x1, double y1) {
        x = x1;
        y = y1;
    }

    double len() {
        double d = Math.sqrt(x * x + y * y);
        return d;
    }

    public Vector2 ort() {
        double a = -1 * Math.PI / 2;
        double l = len();
        double e = this.x * Math.sin(a);
        double d = this.x * Math.cos(a);
        double x2 = (d - this.y * Math.sin(a)) / l;
        double y2 = (e + this.y * Math.cos(a)) / l;
        return new Vector2(x2, y2);
    }
    Vector2 minus(Vector2 v){
        return new Vector2(this.x-v.x, this.y-v.y);
    }
    public Vector2 mult(double a) {
        return new Vector2(x * a, y * a);
    }
    public Vector2 plus(Vector2 v){
      return new Vector2(this.x+v.x,
        this.y+v.y);
    }

}
