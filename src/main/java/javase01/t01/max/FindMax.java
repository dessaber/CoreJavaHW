package javase01.t01.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** The class has static methods to create and traverse an array of real numbers.
 *
 * @author m-levin 23.04.17
 */

public class FindMax {
    public static void main(String[] args) {

        traverseArray(inputArray());

    }

    /** The method creates an array using integer number as its length, then fills the array with real numbers.
     *
     * 23.04.17
     * @return array with doubles
     */

    public static double[] inputArray() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double[] doubles;
        int arrayLength;
        try {
            System.out.println("Input n (gotta be > 0).");
            while (true) {
                arrayLength = Integer.parseInt(reader.readLine());
                if (arrayLength > 0)
                    break;
            }
            doubles = new double[arrayLength];
            for (int i = 0; i < doubles.length; ++i) {
                doubles[i] = Double.parseDouble(reader.readLine());
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Array input failure");
            doubles = new double[0];
        }

        return doubles;
    }

    /** On every iteration this method counts sum of two elements of the array and outputs the maximum sum.
     *  If the array contains only one number, it will be the output.
     *
     * @param doubles array of real numbers
     * 23.04.17
     */

    public static void traverseArray(double[] doubles) {
        double currentMax;
        if (doubles.length == 0) {
            System.out.println("Nothing to count");
        } else if (doubles.length == 1) {
            System.out.println("The answer is " + doubles[0]);
        } else if (doubles.length == 2) {
            System.out.println("The answer is " + (doubles[0] + doubles[1]));
        } else {
            currentMax = doubles[0] + doubles[1];
            double tempMax = currentMax;
            for (int i = 2; i < doubles.length; ++i) {
                if (i % 2 == 0) {
                    currentMax = currentMax < tempMax ? tempMax : currentMax;
                    tempMax = 0;
                }
                tempMax += doubles[i];
            }
            currentMax = currentMax < tempMax ? tempMax : currentMax;
            System.out.println("The answer is " + currentMax);
        }
    }
}
