package problem;

import javax.media.opengl.GL2;
import java.util.Random;

public class Ray {
    public Vector2 A;
    public Vector2 B;
    public Vector2 C;
    public Vector2 D;


    public Vector2 o;

    public double d;

    public Ray(Vector2 a, Vector2 b) {
        A = a;
        B = b;
        o = B.minus(A).ort();

        C = o.mult(3).plus(B);
        D = o.mult(3).plus(A);
    }

    public static Ray getRandomRay() {
        Random random = new Random();
        return new Ray(
                new Vector2(
                        random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1
                ), new Vector2(
                random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1
        ));
    }

    public void find(Vector2 a, Vector2 b, Vector2 c, Vector2 d, Vector2 pos) {
        A = a;
        B = b;
        C = c;
        D = d;

        double k = B.minus(A).len();
        double l = B.minus(C).len();
        double S1 = l * k;//площадь широкого луча
        double sa = ((pos.x - a.x) * (b.y - a.y) - (b.x - a.x) * (pos.y - a.y)) / 2;
        double sb = ((pos.x - c.x) * (b.y - c.y) - (b.x - c.x) * (pos.y - c.y)) / 2;
        double sc = ((pos.x - c.x) * (d.y - c.y) - (d.x - c.x) * (pos.y - c.y)) / 2;
        double sd = ((pos.x - a.x) * (b.y - a.y) - (b.x - a.x) * (pos.y - a.y)) / 2;
        double S2 = sa + sb + sc + sd;// сумма площадей треугольников с вершинами в центре луча и в центре окружности
        if (S1 > S2) {
            System.out.print("за");
        }
        if (S1 < S2) {
            System.out.print("внутри");
        }
        Line l1 = new Line(a.x, a.y, d.x, d.y);
        Line l2 = new Line(a.x, a.y, b.x, b.y);
        Line l3 = new Line(b.x, b.y, c.x, c.y);
        Line l4 = new Line(c.x, c.y, d.x, d.y);

    }


    public void render(GL2 gl) {
        Figures.renderQuad(gl, A, B, C, D, false);
    }
}
