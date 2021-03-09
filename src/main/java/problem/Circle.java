package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Circle {
    public static void renderCircle(GL2 gl, Vector2 pos, double r, boolean filled) {
        if (filled) {
            gl.glBegin(GL.GL_TRIANGLE_FAN);
            gl.glVertex2d(pos.x, pos.y);
            int n = 360;
            for (int i = 0; i <= n; i++) {
                double a = (2 * Math.PI / n) * i;
                double x = r * Math.cos(a);
                double y = r * Math.sin(a);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        }
        else{
            gl.glBegin(GL.GL_LINE_STRIP);
            int n = 360;
            for (int i = 0; i <= n; i++) {
                double a = (2 * Math.PI / n) * i;
                double x = r * Math.cos(a);
                double y = r * Math.sin(a);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        }}
}
