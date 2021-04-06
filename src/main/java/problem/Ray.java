package problem;

import javax.media.opengl.GL2;
import java.util.Random;

public class Ray {
    public Vector2 A;
    public Vector2 B;
    public Vector2 C;
    public Vector2 D;


    public Vector2 o;

    //public double d;

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

    public double find(Circle circle) {
        double k = B.minus(A).len();
        double l = B.minus(C).len();
        double s1 = l * k;//площадь широкого луча
        double sa = ((circle.pos.x - A.x) * (B.y - A.y) - (B.x - A.x) * (circle.pos.y - A.y)) / 2;
        double sb = ((circle.pos.x - C.x) * (B.y - C.y) - (B.x - C.x) * (circle.pos.y - C.y)) / 2;
        double sc = ((circle.pos.x - C.x) * (D.y - C.y) - (D.x - C.x) * (circle.pos.y - C.y)) / 2;
        double sd = ((circle.pos.x - A.x) * (B.y - A.y) - (B.x - A.x) * (circle.pos.y - A.y)) / 2;
        double s2 = sa + sb + sc + sd;// сумма площадей треугольников с вершинами в центре луча и в центре окружности
        if (s1 > s2) {
            System.out.print("за");
        }

        Line l1 = new Line(A.x, A.y, D.x, D.y);
        double a1 = A.y - D.y;
        double b1 = D.x - A.x;
        double c1 = A.x * D.y - D.x * A.y;
        double I1 = a1 * a1 * circle.pos.y - a1 * b1 * circle.pos.x - b1 * c1;
        double d1 = Math.pow((b1 * c1 + a1 * b1 * circle.pos.x - a1 * a1 * circle.pos.y), 2) - (a1 * a1 + b1 * b1) * (c1 * c1 + 2 * a1 * c1 * circle.pos.x + a1 * a1 * (circle.pos.x * circle.pos.x + circle.pos.y * circle.pos.y - circle.r * circle.r));
        double y1 = (I1 + Math.sqrt(d1)) / a1 * a1 + b1 * b1;            //координаты
        double x1 = (-c1 - b1 * y1) / a1;                            //координаты
        double Y1 = (I1 - Math.sqrt(d1)) / a1 * a1 + b1 * b1;               //координаты
        double X1 = (-c1 - b1 * Y1) / a1;                             //координаты
        double cos = (x1 * X1 + y1 * Y1) / Math.pow(circle.r, 2);     //косинус угла
        double f1 = Math.acos(cos);                         // угол
        double S1 = Math.pow(circle.r, 2) * ((Math.PI * f1 / 360) - (Math.sin(f1) / 2));   //площадь сегмента
        if (d1 > 0) {
            System.out.println("две общие точки");
        }
        if (d1 == 0) {
            System.out.println("касание");
        }
        if (d1 < 0) {
            System.out.println("нет");
        }

        Line l2 = new Line(A.x, A.y, B.x, B.y);
        double a2 = A.y - B.y;// коэффиценты прямой-стороны луча
        double b2 = B.x - A.x;
        double c2 = A.x * B.y - B.x * A.y;
        double I2 = a2 * a2 * circle.pos.y - a2 * b2 * circle.pos.x - b2 * c2;
        double d2 = Math.pow((b2 * c2 + a2 * b2 * circle.pos.x - a2 * a2 * circle.pos.y), 2) - (a2 * a2 + b2 * b2) * (c2 * c2 + 2 * a2 * c2 * circle.pos.x + a2 * a2 * (circle.pos.x * circle.pos.x + circle.pos.y * circle.pos.y - circle.r * circle.r));
        double y2 = (I2 + Math.sqrt(d2)) / a2 * a2 + b2 * b2;            //координаты
        double x2 = (-c2 - b2 * y2) / a2;                       //координаты
        double Y2 = (I2 - Math.sqrt(d2)) / a2 * a2 + b2 * b2;               //координаты
        double X2 = (-c2 - b2 * Y2) / a2;                          //координаты
        double cos2 = (x2 * X2 + y2 * Y2) / Math.pow(circle.r, 2);   //косинус угла
        double f2 = Math.acos(cos2);                  // угол
        double S2 = Math.pow(circle.r, 2) * ((Math.PI * f2 / 360) - (Math.sin(f2) / 2));   //площадь сегмента
        if (d2 > 0) {
            System.out.println("две общие точки");
        }
        if (d2 == 0) {
            System.out.println("касание");
        }
        if (d2 < 0) {
            System.out.println("нет");
        }

        Line l3 = new Line(B.x, B.y, C.x, C.y);
        double a3 = B.y - C.y;
        double b3 = C.x - B.x;
        double c3 = B.x * C.y - C.x * B.y;
        double I3 = a3 * a3 * circle.pos.y - a3 * b3 * circle.pos.x - b3 * c3;
        double d3 = Math.pow((b3 * c3 + a3 * b3 * circle.pos.x - a3 * a3 * circle.pos.y), 2) - (a3 * a3 + b3 * b3) * (c3 * c3 + 2 * a3 * c3 * circle.pos.x + a3 * a3 * (circle.pos.x * circle.pos.x + circle.pos.y * circle.pos.y - circle.r * circle.r));
        double y3 = (I3 + Math.sqrt(d3)) / a3 * a3 + b3 * b3;            //координаты
        double x3 = (-c3 - b3 * y3) / a3;                       //координаты
        double Y3 = (I3 - Math.sqrt(d3)) / a3 * a3 + b3 * b3;               //координаты
        double X3 = (-c3 - b3 * Y3) / a3;                          //координаты
        double cos3 = (x3 * X3 + y3 * Y3) / Math.pow(circle.r, 2);   //косинус угла
        double f3 = Math.acos(cos3);                  // угол
        double S3 = Math.pow(circle.r, 2) * ((Math.PI * f3 / 360) - (Math.sin(f3) / 2));   //площадь сегмента

        if (d3 > 0) {
            System.out.println("две общие точки");
        }
        if (d3 == 0) {
            System.out.println("касание");
        }
        if (d3 < 0) {
            System.out.println("нет");
        }
        if (s1 < s2) {
            System.out.print("внутри");
            // 1 Круг внутри луча или касается
            if (d3 <= 0 && d2 <= 0 && d1 <= 0) {
                double S = Math.PI * circle.r * circle.r;
            }
            // 2 Пересечение сверху
            if (d1 > 0 && d2 <= 0 && d3 <= 0) {
                double S = Math.pow(circle.r, 2) * Math.PI - S1;
            }
            //  3 Пересечение справа

            if (d2 > 0 && d3 <= 0 && d1 <= 0) {
                double S = Math.pow(circle.r, 2) * Math.PI - S2;
            }
            //  4 Пересечение снизу
            if (d3 > 0 && d2 <= 0 && d1 <= 0) {
                double S = Math.pow(circle.r, 2) * Math.PI - S3;
            }
            // 5 Пересечение снизу и сверху
            if (d1 > 0 && d3 > 0 && d2 <= 0) {
                double S = Math.PI * Math.pow(circle.r, 2) - S1 - S3;
            }
            // 6 Касание окружнсти верхней вершиной, пересечение верхней и правой сторон  или пересечение правой и верхней сторон
            if (d1 > 0 && d2 > 0 && d3 <= 0) {
                double S = Math.PI * Math.pow(circle.r, 2) - S1 - S2;
            }
            // 7 Касание окружнсти нижней вершиной, пересечение нижней и правой сторон  или пересечение правой и нижней сторон
            if (d2 > 0 && d3 > 0 && d1 <= 0) {
                double S = Math.PI * Math.pow(circle.r, 2) - S3 - S2;
            }
            // 8 Пересечение трех сторон
            if (d2 > 0 && d3 > 0 && d1 > 0) {
                double S = Math.PI * Math.pow(circle.r, 2) - S3 - S2 - S1;
            }
            // 9 Только две общие точки пересечния правой и верхней сторон
            if (x1 > A.x || X1 > A.x && d3 <= 0 && d1 > 0 && d2 > 0) {
                if (X1 > A.x && Y2 > A.y) {
                    double SS = ((circle.pos.x - A.x) * (y1 - A.y) - (x1 - A.x) * (circle.pos.y - A.y)) / 2;
                    double SS1 = ((circle.pos.x - A.x) * (y2 - A.y) - (x2 - A.x) * (circle.pos.y - A.y)) / 2;
                    double alfa = Math.acos((x1 * x2 + y1 * y2) / Math.pow(circle.r, 2));
                    double SSS = Math.PI * Math.pow(circle.r, 2) * alfa / 360;
                    double S = SSS + SS + SS1;
                }
                if (X1 > A.x && y2 > A.y) {
                    double SS = ((circle.pos.x - A.x) * (y1 - A.y) - (X1 - A.x) * (circle.pos.y - A.y)) / 2;
                    double SS1 = ((circle.pos.x - A.x) * (Y2 - A.y) - (X2 - A.x) * (circle.pos.y - A.y)) / 2;
                    double alfa = Math.acos((x1 * X2 + y1 * Y2) / Math.pow(circle.r, 2));
                    double SSS = Math.PI * Math.pow(circle.r, 2) * alfa / 360;
                    double S = SSS + SS + SS1;
                }
                if (x1 > A.x && y2 > A.y) {
                    double SS = ((circle.pos.x - A.x) * (Y1 - A.y) - (Y1 - A.x) * (circle.pos.y - A.y)) / 2;
                    double SS1 = ((circle.pos.x - A.x) * (Y2 - A.y) - (X2 - A.x) * (circle.pos.y - A.y)) / 2;
                    double alfa = Math.acos((X1 * X2 + Y1 * Y2) / Math.pow(circle.r, 2));
                    double SSS = Math.PI * Math.pow(circle.r, 2) * alfa / 360;
                    double S = SSS + SS + SS1;
                }
                if (x1 > A.x && Y2 > A.y) {
                    double SS = ((circle.pos.x - A.x) * (Y1 - A.y) - (X1 - A.x) * (circle.pos.y - A.y)) / 2;
                    double SS1 = ((circle.pos.x - A.x) * (y2 - A.y) - (x2 - A.x) * (circle.pos.y - A.y)) / 2;
                    double alfa = Math.acos((X1 * x2 + Y1 * y2) / Math.pow(circle.r, 2));
                    double SSS = Math.PI * Math.pow(circle.r, 2) * alfa / 360;
                    double S = SSS + SS + SS1;
                }
            }

            // 10 Только две общие точки пересечния правой и нижней сторон
            if (x3 > A.x || X3 > A.x && d3 <= 0 && d1 > 0 && d2 > 0) {
                if (X3 > A.x && Y2 > A.y) {
                    double SS = ((circle.pos.x - A.x) * (y3 - A.y) - (x3 - A.x) * (circle.pos.y - A.y)) / 2;
                    double SS1 = ((circle.pos.x - A.x) * (y2 - A.y) - (x2 - A.x) * (circle.pos.y - A.y)) / 2;
                    double alfa = Math.acos((x3 * x2 + y3 * y2) / Math.pow(circle.r, 2));
                    double SSS = Math.PI * Math.pow(circle.r, 2) * alfa / 360;
                    double S = SSS + SS + SS1;
                }
                if (X3 > A.x && y2 > A.y) {
                    double SS = ((circle.pos.x - A.x) * (y3 - A.y) - (X3 - A.x) * (circle.pos.y - A.y)) / 2;
                    double SS1 = ((circle.pos.x - A.x) * (Y2 - A.y) - (X2 - A.x) * (circle.pos.y - A.y)) / 2;
                    double alfa = Math.acos((x3 * X2 + y3 * Y2) / Math.pow(circle.r, 2));
                    double SSS = Math.PI * Math.pow(circle.r, 2) * alfa / 360;
                    double S = SSS + SS + SS1;
                }
                if (x3 > A.x && y2 > A.y) {
                    double SS = ((circle.pos.x - A.x) * (Y3 - A.y) - (Y3 - A.x) * (circle.pos.y - A.y)) / 2;
                    double SS1 = ((circle.pos.x - A.x) * (Y2 - A.y) - (X2 - A.x) * (circle.pos.y - A.y)) / 2;
                    double alfa = Math.acos((X3 * X2 + Y3 * Y2) / Math.pow(circle.r, 2));
                    double SSS = Math.PI * Math.pow(circle.r, 2) * alfa / 360;
                    double S = SSS + SS + SS1;
                }
                if (x3 > A.x && Y2 > A.y) {
                    double SS = ((circle.pos.x - A.x) * (Y3 - A.y) - (X3 - A.x) * (circle.pos.y - A.y)) / 2;
                    double SS1 = ((circle.pos.x - A.x) * (y2 - A.y) - (x2 - A.x) * (circle.pos.y - A.y)) / 2;
                    double alfa = Math.acos((X3 * x2 + Y3 * y2) / Math.pow(circle.r, 2));
                    double SSS = Math.PI * Math.pow(circle.r, 2) * alfa / 360;
                    double S = SSS + SS + SS1;
                }
            }
        }
    }


    public void render(GL2 gl) {
        Figures.renderQuad(gl, A, B, C, D, false);
    }
}
