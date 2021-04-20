package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Polygon {
    ArrayList<Vector2> polygon_coordinates;

    Polygon(List<Vector2> a) {
        polygon_coordinates = new ArrayList<>(a);
        Vector2 center = middlepoint(polygon_coordinates);
        // сортируем по углу
        polygon_coordinates.sort(Comparator.comparingDouble(aPoint -> aPoint.minus(center).angle()));
    }

    public Vector2 middlepoint(ArrayList<Vector2> polygon_coordinates) {
        Vector2 a = new Vector2(0, 0);
        for (Vector2 polygon_coordinate : polygon_coordinates) {
            a = a.plus(polygon_coordinate);
        }
        return a.multiply(1.0 / polygon_coordinates.size());
    }

    // Определим бесконечность (используем INT_MAX, чтобы не вызвать проблемы с переполнением ячеек)
    static int INF = 10000;

    // Исследуем три коллинеарных вектора p, q, r,
    // метод проверяет лежит ли точка q на отрезке "pr"
    static boolean onSegment(Vector2 p, Vector2 q, Vector2 r) {
        if (q.x <= Math.max(p.x, r.x) &&
                q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) &&
                q.y >= Math.min(p.y, r.y)) {
            return true;
        }
        return false;
    }

    // Цель - найти взаимное расположение упорядоченного триплета (p,q,r)
    // Функция возвращает полученное значение
    // 0 --> p, q и r коллинеарны
    // 1 --> По часовой стрелке
    // 2 --> Против часовой стрелки
    static int orientation(Vector2 p, Vector2 q, Vector2 r) {
        double val = (q.y - p.y) * (r.x - q.x)
                - (q.x - p.x) * (r.y - q.y);

        if (Math.abs(val) <= 0.001) {
            return 0; // коллинеарны
        }
        return (val > 0) ? 1 : 2; // по часовой или против часовой стрелки
    }

    // Функция, возвращающая true, если
    // отрезкии 'p1q1' и 'p2q2' пересекаются.
    static boolean doIntersect(Vector2 p1, Vector2 q1,
                               Vector2 p2, Vector2 q2) {
        // Нахождение четырёх ориентаций, необходимых для
        // главных и отдельных случаев
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // Главный случай
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Отдельные случаи
        // p1, q1 и p2 коллинеарны и
        // p2 лежит на отрезке p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true;
        }

        // p1, q1 и p2 коллинеарны
        // q2 лежит на отрезке p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) {
            return true;
        }

        // p2, q2 и p1 коллинеарны
        // p1 лежит на отрезке p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true;
        }

        // p2, q2 и q1 коллинеарны
        // q1 лежит на отрезке p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) {
            return true;
        }

        // Не подходит ни в один из случаев
        return false;
    }

    // Возвращает true если точка p лежит
    // внутри the polygon[] с n вершинами
    boolean isInside(Vector2 p) {
        // polygon[] должен иметь не менее 3 вершин
        if (polygon_coordinates.size() < 3) {
            System.out.println("list too small");
            return false;
        }

        // Создаём точку для отрезка линии от точки p до бесконечности
        Vector2 extreme = new Vector2(INF, p.y);

        // Считаем количество пересечений вышеуказанной линии с сторонами многоугольника
        int count = 0, i = 0;
        do {
            int next = (i + 1) % polygon_coordinates.size();

            // Проверим, пересекается ли отрезок от "p" до
            // 'extreme' с отрезком 'polygon[i]' до 'polygon[next]'
            if (doIntersect(polygon_coordinates.get(i), polygon_coordinates.get(next), p, extreme)) {
                // Если точка 'p' коллинеарна отрезку
                // 'i-next', проверить, лежит ли она на отрезке.
                // Если это ложь, вернуть true, иначе - false.
                if (orientation(polygon_coordinates.get(i), p, polygon_coordinates.get(next)) == 0) {
                    return onSegment(polygon_coordinates.get(i), p, polygon_coordinates.get(next));
                }

                count++;
            }
            i = next;
        } while (i != 0);

        // Вернуть true если счётчик нечётный, в противном случае - false
        return (count % 2 == 1); // То же самое (count%2 == 1)
    }
}

