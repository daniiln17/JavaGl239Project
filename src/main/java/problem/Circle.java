package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.Random;

public class Circle {
    Vector2 pos;
    double r;

    public Circle(Vector2 pos, double r) {
        this.pos = pos;
        this.r = r;
    }

    public static Circle getRandomCircle() {
        Random random = new Random();
        return new Circle(
                new Vector2(
                        random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1
                ),
                random.nextDouble() * 0.3
        );
    }

    public void render(GL2 gl) {
        Figures.renderCircle(gl, pos, r, false);
    }

    public boolean contains(Vector2 pos) {
        return pos.minus(this.pos).len() <= r;
    }
}
