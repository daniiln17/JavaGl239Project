package problem;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "На плоскости задано множество широких лучей\" и множество\n" +
            "окружностей. Найти такую пару \"широкий луч\"-окружность, что фигура,\n" +
            "находящаяся внутри \"широкого луча\" и окружности, имеет максимальную \n" +
            "площадь. В качестве ответа: выделить найденные \"широкий луч\" и\n" +
            "окружность, выделить контур фигуры, которая ограничивает точки внутри\n" +
            "найденного \"широкого луча\" и окружности, желательно выделить внутреннее\n" +
            "пространство фигуры (\"залить цветом\").";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-7 Иванова Ивана";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "points.txt";

    /**
     * список лучей и окружностей
     */
    private ArrayList<Ray> rays;
    private ArrayList<Circle> circles;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        rays = new ArrayList<>();
        circles = new ArrayList<>();
    }


    /**
     * Добавить заданное число случайных окружностей и широких лучей
     *
     * @param n кол-во точек
     */
    public void addRandomCircles(int n) {
        for (int i = 0; i < n; i++) {
            Circle p = Circle.getRandomCircle();
            circles.add(p);
        }
    }

    public void addRandomRays(int n) {
        for (int i = 0; i < n; i++) {
            Ray p = Ray.getRandomRay();
            rays.add(p);
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        circles.clear();
        rays.clear();
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        for (Circle circle : circles) {
            circle.render(gl);
        }

        for (Ray ray : rays) {
            ray.render(gl);
        }


        //Figures.renderTriangle(gl, new Vector2(0, 0), new Vector2(0.1, 0.2), new Vector2(0.5, -0.9), false);
         //Figures.renderQuad(gl, new Vector2(0, 0), new Vector2(0.1, 0), new Vector2(0.1, -0.9), new Vector2(-0.1, -0.9), true);
        //Figures.renderCircle(gl, new Vector2(0,0), 0.2, false);
       // Figures.renderPoint(gl, new Vector2(0.5,0.7), 1000);
//
//        Ray ray = new Ray(new Vector2(-0.1,0.2), new Vector2(0.6,-0.1));
//        ray.render(gl);
//        public void addPoint(double x, double y, int setVal) {
//            Point point = new Point(x, y, setVal);
//            points.add(point);
//        }

    }


    /**
     * Решить задачу
     */
    public void solve() {
        // перебираем пары точек
//        for (Point p : points) {
//            for (Point p2 : points) {
//                // если точки являются разными
//                if (p != p2) {
//                    // если координаты у них совпадают
//                    if (Math.abs(p.x - p2.x) < 0.0001 && Math.abs(p.y - p2.y) < 0.0001) {
//                        p.isSolution = true;
//                        p2.isSolution = true;
//                    }
//                }
//            }
//        }
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {

        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            circles.clear();
            int n = sc.nextInt();
            // пока в файле есть непрочитанные строки
            for (int i = 0; i < n; i++) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                double r = sc.nextDouble();
                sc.nextLine();
                Circle circle = new Circle(new Vector2(x, y), r);
                circles.add(circle);
            }
            rays.clear();
            int k = sc.nextInt();
            for (int i = 0; i < k; i++) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                double x1 = sc.nextDouble();
                double y1 = sc.nextDouble();
                sc.nextLine();
                Ray ray = new Ray(new Vector2(x, y), new Vector2(x1, y1));
                rays.add(ray);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }

    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            out.println(circles.size());
            for (Circle circle : circles) {
                out.printf("%.2f %.2f %.2f\n", circle.pos.x, circle.pos.y, circle.r);
            }
            for (Ray ray : rays) {
                out.printf("%.2f %.2f %.2f %.2f\n", ray.A.x, ray.A.y, ray.B.x, ray.B.y);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить точку
     *
     * @param x координата X точки
     * @param y координата Y точки
     */
    public void addPoint(double x, double y) {
//        Point point = new Point(x, y, setVal);
//        points.add(point);
    }

}
