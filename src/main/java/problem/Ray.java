package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

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
        D=o.mult(3).plus(A);
    }

    public void render(GL2 gl){
        Figures.renderQuad(gl,A,B,C,D,false);
    }
}
