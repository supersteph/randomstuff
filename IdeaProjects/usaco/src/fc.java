/*
ID: xiaoyun4
LANG: JAVA
TASK: fc
*/
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.StringTokenizer;

/** Class point **/
class Point
{
    double x, y;
}

/** Class Jarvis **/
public class fc
{
    static private boolean clockwise(Point p, Point q, Point r)
    {
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);


        if (val >= 0)
            return false;
        return true;
    }
    static public double convexHull(Point[] points)
    {
        int n = points.length;

        if (n < 3) {
            double total = 0.0;
            for (int i = 0; i<n;i++) {
                total+= points[i].x*points[i].x+points[i].y*points[i].y;
            }
            return total;
        }



        int leftMost = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[leftMost].x)
                leftMost = i;
        int p = leftMost;
        int q;
        double total = 0.0;
        do{
            q = (p + 1) % n;
            for (int i = 0; i < n; i++)
                if (clockwise(points[p], points[i], points[q]))
                    q = i;
            total+=Math.hypot(points[p].x-points[q].x, points[p].y-points[q].y);
            p = q;

        }while (p!=leftMost);

         return total;
    }

    public static void main (String[] args) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader("fc.in"));

        int n = Integer.parseInt(f.readLine());
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(f.readLine());
            points[i] = new Point();
            points[i].x = Double.parseDouble(st.nextToken());
            points[i].y = Double.parseDouble(st.nextToken());
        }
        double haha = convexHull(points);
        PrintWriter out = new PrintWriter(new FileWriter("fc.out"));
        out.printf("%.2f", haha);
        out.println();
        System.out.println(haha);
        out.close();
        System.exit(0);
    }
}