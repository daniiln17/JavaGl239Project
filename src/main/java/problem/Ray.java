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

    public Find(Vector2 a, Vector2 b, Vector2 c, Vector2 d, Vector2 pos) {
        A = a;
        B = b;
        C = c;
        D = d;
        double k = B.minus(A).len();
        double l = B.minus(C).len();
        double S1 = l*k;
    }


    public void render(GL2 gl) {
        Figures.renderQuad(gl, A, B, C, D, false);
    }
}
