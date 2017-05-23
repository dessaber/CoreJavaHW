package javase01.t01.sequence;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by m-levin on 23.04.2017.
 */

public class Sequence {

    public static void main(String[] args) {
        double epsilon;
        int sequenceSize;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            while (true) {
                System.out.println("Input size of sequence (>0):");
                sequenceSize = Integer.parseInt(reader.readLine());
                if (sequenceSize > 0)
                    break;
            }
            while (true) {
                System.out.println("Now input epsilon (> 0):");
                epsilon = Double.parseDouble(reader.readLine());
                if (epsilon > 0)
                    break;
            }

        } catch (Exception e) {
            System.out.println("Wrong parameters");
            e.printStackTrace();
            return;
        }

        /* Such elaborate logic was made because of the assumed requirement to output the single element first and the whole sequence after that.
           Also this way amount of iterations will be less than 2n unless the single element is the last one.
         */
        ArrayList<Double> doubles = new ArrayList<>(sequenceSize);
        int i;
        for (i = 0; i < sequenceSize; ++i) {
            doubles.add(i, 1 / Math.pow(1 + i, 2));
            if (doubles.get(i) < epsilon) {
                System.out.println("First element fitting the condition is " + doubles.get(i));
                i++;
                break;
            }
        }
        System.out.println("The whole sequence: ");
        for (double d: doubles)
            System.out.println(d);
        while (i < sequenceSize) {
            doubles.add(i, 1/Math.pow(1 + i, 2));
            System.out.println(doubles.get(i));
            i++;
        }
    }
}
