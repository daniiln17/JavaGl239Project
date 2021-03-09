package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Project {

    public static void renderQuad(GL2 gl, Vector2 pos, Vector2 pos1, Vector2 pos2, Vector2 pos3, boolean filled) {
        if (filled == true) {
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2d(pos.x, pos.y);
            gl.glVertex2d(pos1.x, pos1.y);
            gl.glVertex2d(pos2.x, pos2.y);
            gl.glVertex2d(pos3.x, pos3.y);
            gl.glEnd();
        } else {
            gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2d(pos.x, pos.y);
            gl.glVertex2d(pos1.x, pos1.y);
            gl.glVertex2d(pos2.x, pos2.y);
            gl.glVertex2d(pos3.x, pos3.y);
            gl.glVertex2d(pos.x, pos.y);
            gl.glEnd();
        }
    }

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
        } else {
            gl.glBegin(GL.GL_LINE_STRIP);
            int n = 360;
            for (int i = 0; i <= n; i++) {
                double a = (2 * Math.PI / n) * i;
                double x = r * Math.cos(a);
                double y = r * Math.sin(a);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        }
    }
}
