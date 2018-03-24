package company.LinkedIn;

import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * LC 149
 * Given n points on a 2D plane,
 * find the maximum number of points that lie on the same straight line.
 */
class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }
    Point(int a, int b) { x = a; y = b; }
}
public class MaxPointOnLine {
    public int maxPoints(Point[] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            int duplicate = 1;
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int dx = points[j].x - points[i].x;
                int dy = points[j].y - points[i].y;
                // 两点相同， duplicate++ continue
                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }
                // 两点不同， 计算最大公约数
                int d = gcd(dx, dy);
                String slope = dx / d + "@" + dy / d;
                map.put(slope, map.getOrDefault(slope, 0) + 1);
            }
            // 更新结果
            res = Math.max(res, duplicate);
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                res = Math.max(res, e.getValue() + duplicate);
            }
        }
        return res;
    }
    // 计算最大公约数
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
