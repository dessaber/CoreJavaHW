package javase01.t01.tangens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by m-levin on 23.04.2017.
 */

public class Tangens {

    public static void main (String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double a;
        double b;
        double h;
        try {
            a = Double.parseDouble(reader.readLine());
            while (true) {
                System.out.println("Input b (gotta be > a).");
                b = Double.parseDouble(reader.readLine());
                if (b > a)
                    break;
            }
            while (true) {
                System.out.println("Input step (gotta be > 0).");
                h = Double.parseDouble(reader.readLine());
                if (h > 0)
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        while (a < b) {
            System.out.print(a + " ");
            System.out.println(Math.tan(2*a) - 3);
            a += h;
        }
        System.out.print(b + " ");
        System.out.println(Math.tan(2*b) - 3);

    }
}
