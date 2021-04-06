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
        double S1 = l * k;//площадь широкого луча
        double sa = ((circle.pos.x - A.x) * (B.y - A.y) - (B.x - A.x) * (circle.pos.y - A.y)) / 2;
        double sb = ((circle.pos.x - C.x) * (B.y - C.y) - (B.x - C.x) * (circle.pos.y - C.y)) / 2;
        double sc = ((circle.pos.x - C.x) * (D.y - C.y) - (D.x -C.x) * (circle.pos.y - C.y)) / 2;
        double sd = ((circle.pos.x - A.x) * (B.y - A.y) - (B.x - A.x) * (circle.pos.y - A.y)) / 2;
        double S2 = sa + sb + sc + sd;// сумма площадей треугольников с вершинами в центре луча и в центре окружности
        if (S1 > S2) {
            System.out.print("за");
        }
        if (S1 < S2) {
            System.out.print("внутри");
        }
        Line l1 = new Line(A.x, A.y, D.x, D.y);
        double a1 = A.y - D.y;
        double b1 = D.x - A.x;
        double c1 = A.x * D.y - D.x * A.y;
        double I1 = a1*a1*circle.pos.y-a1*b1*circle.pos.x-b1*c1;
        double d1 = Math.pow((b1 * c1 + a1 * b1 * circle.pos.x - a1 * a1 * circle.pos.y), 2) - (a1 * a1 + b1 * b1) * (c1 * c1 + 2 * a1 * c1 - circle.pos.x + a1 * a1 * (circle.pos.x * circle.pos.x + circle.pos.y * circle.pos.y - circle.r * circle.r));
        if(d1>0){
            System.out.println("две общие точки");
        }
        if(d1==0){
            System.out.println("касание");
        }
        if(d1<0){
            System.out.println("нет");
        }

        Line l2 = new Line(A.x, A.y, B.x, B.y);
        double a2 = A.y - B.y;
        double b2 = B.x -A.x;
        double c2 = A.x * B.y - B.x * A.y;
        double I2 = a2*a2*circle.pos.y-a2*b2*circle.pos.x-b2*c2;
        double d2 = Math.pow((b2 * c2 + a2 * b2 * circle.pos.x - a2 * a2 * circle.pos.y), 2) - (a2 * a2 + b2 * b2) * (c2 * c2 + 2 * a2 * c2 - circle.pos.x + a2 * a2 * (circle.pos.x * circle.pos.x + circle.pos.y * circle.pos.y - circle.r * circle.r));
        if(d2>0){
            System.out.println("две общие точки");
        }
        if(d2==0){
            System.out.println("касание");
        }
        if(d2<0){
            System.out.println("нет");
        }

        Line l3 = new Line(B.x, B.y, C.x, C.y);
        double a3 = B.y - C.y;
        double b3 = C.x - B.x;
        double c3 =B.x * C.y - C.x * B.y;
        double I3 = a3*a3*circle.pos.y-a3*b3*circle.pos.x-b3*c3;
        double d3 = Math.pow((b3 * c3 + a3 * b3 * circle.pos.x - a3 * a3 * circle.pos.y), 2) - (a3 * a3 + b3 * b3) * (c3 * c3 + 2 * a3 * c3 - circle.pos.x + a3 * a3 * (circle.pos.x * circle.pos.x + circle.pos.y * circle.pos.y - circle.r * circle.r));
        if(d3>0){
            System.out.println("две общие точки");
        }
        if(d3==0){
            System.out.println("касание");
        }
        if(d3<0){
            System.out.println("нет");
        }
        //Круг внутри луча или касается
        if(d3<=0&&d2<=0&&d1<=0){
           double S=Math.PI*circle.r*circle.r;
            System.out.println("нет");
        }
        // Пересечение сверху
        if(d1>0&&d2<=0&&d3<=0){
            double y1=(I1+Math.sqrt(d1))/a1*a1+b1*b1;            //координаты
            double x1=(-c1-b1*y1)/a1;                       //координаты
            double y12=(I1-Math.sqrt(d1))/a1*a1+b1*b1;               //координаты
            double x12=(-c1-b1*y12)/a1;                          //координаты
            double a=(x1*x12+y1*y12)/Math.pow(circle.r,2);   //косинус угла
            double f= Math.acos(a);                  // угол
            double S=Math.pow(circle.r,2)*((Math.PI-(Math.PI*f/360) -(Math.sin(f)/2)));
        }
        //  Пересечение справа

        if(d2>0&&d3<=0&&d1<=0){
            double y1=(I2+Math.sqrt(d2))/a2*a2+b2*b2;            //координаты
            double x1=(-c2-b2*y1)/a2;                       //координаты
            double y12=(I2-Math.sqrt(d2))/a2*a2+b2*b2;               //координаты
            double x12=(-c2-b2*y12)/a2;                          //координаты
            double a=(x1*x12+y1*y12)/Math.pow(circle.r,2);   //косинус угла
            double f= Math.acos(a);                  // угол
            double S=Math.pow(circle.r,2)*((Math.PI-(Math.PI*f/360) -(Math.sin(f)/2))); //Площадь сегмента круга, право
        }
        //  Пересечение снизу
        if(d3>0&&d2<=0&&d1<=0){
            double y1=(I2+Math.sqrt(d2))/a2*a2+b2*b2;            //координаты
            double x1=(-c2-b2*y1)/a2;                       //координаты
            double y12=(I2-Math.sqrt(d2))/a2*a2+b2*b2;               //координаты
            double x12=(-c2-b2*y12)/a2;                          //координаты
            double a=(x1*x12+y1*y12)/Math.pow(circle.r,2);   //косинус угла
            double f= Math.acos(a);                  // угол
            double S=Math.pow(circle.r,2)*(Math.PI-((Math.PI*f/360) -(Math.sin(f)/2))); //Площадь сегмента круга, низ
        }
        // Пересечение снизу и сверху
        if(d1>0&&d3>0&&d2<=0){
            double y1=(I1+Math.sqrt(d1))/a1*a1+b1*b1;            //координаты
            double x1=(-c1-b1*y1)/a1;                       //координаты
            double y12=(I1-Math.sqrt(d1))/a1*a1+b1*b1;               //координаты
            double x12=(-c1-b1*y12)/a1;                          //координаты
            double a=(x1*x12+y1*y12)/Math.pow(circle.r,2);   //косинус угла
            double f= Math.acos(a);                  // угол
            double Sa=Math.pow(circle.r,2)*((Math.PI-(Math.PI*f/360) -(Math.sin(f)/2)));
            double y2=(I2+Math.sqrt(d2))/a2*a2+b2*b2;            //координаты
            double x2=(-c2-b2*y1)/a2;                       //координаты
            double y22=(I2-Math.sqrt(d2))/a2*a2+b2*b2;               //координаты
            double x22=(-c2-b2*y12)/a2;                          //координаты
            double aa=(x1*x12+y1*y12)/Math.pow(circle.r,2);   //косинус угла
            double f2= Math.acos(a);                  // угол
            double Sb=Math.pow(circle.r,2)*(Math.PI-((Math.PI*f/360) -(Math.sin(f)/2))); //Площадь сегмента круга, низ
            double S=Sb+Sa;
        }








    }


    public void render(GL2 gl) {
        Figures.renderQuad(gl, A, B, C, D, false);
    }
}
