import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        //odd numbers from 17 to 5 in decreasing order
        short from = 17, to = 5;
        short[] a = generateArray(from, to);

        float[] x = new float[12];
        for (int i = 0; i < x.length; i++) x[i] = randomInRange(-2.0f, 7.0f);

        double[][] b = new double[7][12];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                double result, firstFactor, secondFactor;
                if (a[i] == 9) {
                    firstFactor = asin(pow(Math.E, -abs(x[j])));
                    secondFactor = ((x[j] / 3 + 4) / log(abs(x[j]))) - 1;
                    result = pow(firstFactor * secondFactor, 2);
                }
                else if (a[i] == 5 || a[i] == 7 || a[i] == 17) {
                    firstFactor = cbrt(x[j]);
                    secondFactor = pow(Math.E, firstFactor);
                    result = cbrt(secondFactor);
                }
                else {
                    firstFactor = pow(Math.E, abs(x[j]));
                    secondFactor = pow(1 / firstFactor, 2);
                    result = log(acos(secondFactor));
                }
                b[i][j] = result;
            }
        }

        for (double[] doubles : b) {
            for (double aDouble : doubles) {
                //print floating number with 5 decimal places
                System.out.printf("%.5f ", aDouble);
            }
            System.out.println();
        }
    }

    //generate array consisting of odd numbers in decreasing order in range [from; to]
    public static short[] generateArray(short from, short to) {
        int arrayLength = 0;
        for (int i = from; i >= to; i -= 2) arrayLength++;

        short[] resultArray = new short[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            resultArray[i] = from;
            from -= 2;
        }
        return resultArray;
    }

    public static float randomInRange(float leftBorder, float rightBorder) {
        return (float) ((random() * (rightBorder - leftBorder)) + leftBorder);
    }
}