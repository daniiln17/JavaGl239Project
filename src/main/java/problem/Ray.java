package problem;

import javax.media.opengl.GL2;
import java.util.Random;

public class Ray {
    public Vector2 A;
    public Vector2 B;
    public Vector2 C;
    public Vector2 D;


    public Vector2 o;


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

    public void render(GL2 gl) {
        Figures.renderQuad(gl, A, B, C, D, false);
    }


    public boolean contains(Vector2 pos) {
        double k = B.minus(A).len();
        double l = B.minus(C).len();
        double S1 = l * k;//площадь широкого луча

        double sa = ((pos.x - B.x) * (A.y - B.y) - (A.x - B.x) * (pos.y - B.y)) / 2;


        double sb = ((pos.x - B.x) * (C.y - B.y) - (C.x - B.x) * (pos.y - B.y)) / 2;
        double sc = ((pos.x - D.x) * (C.y - D.y) - (C.x - D.x) * (pos.y - D.y)) / 2;
        double sd = ((pos.x - D.x) * (A.y - D.y) - (A.x - D.x) * (pos.y - D.y)) / 2;

        double S2 = Math.abs(sa) + Math.abs(sb) + Math.abs(sc) + Math.abs(sd);// сумма площадей треугольников с вершинами в центре луча и в центре окружности
        //System.out.println(S1+" "+S2);
        return Math.abs(S1 - S2) < 0.001;
    }


}
