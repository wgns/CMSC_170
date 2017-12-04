package MP2;

import java.io.*;
import java.math.*;
import java.util.*;

public class KMeans {
    public static void main(String[] args) {
        ArrayList<Example> examples = new ArrayList<>(), centroids = new ArrayList<>();
        BufferedReader br = null;
        Double prevJ = 0.0;

        try {                                                                                                           // file read
            String current;
            br = new BufferedReader(new FileReader("src/MP2/etc/kmdata1.txt"));

            while ((current = br.readLine()) != null) {
                String[] temp = current.split(" ");
                examples.add(new Example(Double.parseDouble(temp[1]), Double.parseDouble(temp[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        centroids.add(new Example(3, 3));
        centroids.add(new Example(6, 2));
        centroids.add(new Example(8, 5));

        for (int iter = 1; iter < 11; iter++) {                                                                         // assign examples to nearest centroid
            ArrayList<Assignment> assignments = new ArrayList<>();

            for (int i = 0; i < 300; i++) {
                ArrayList<Assignment> temp = new ArrayList<>();

                for (int j = 0; j < 3; j++) {
                    temp.add(new Assignment(Math.sqrt(Math.pow(examples.get(i).getX() - centroids.get(j).getX(), 2) + Math.pow(examples.get(i).getY() - centroids.get(j).getY(), 2)), j));
                }

                temp.sort(Comparator.comparingDouble(Assignment::getMinimum));
                assignments.add(temp.get(0));
            }

            try {                                                                                                       // file write; centroid# the values are assigned
                File file = new File("src/MP2/txt/iter" + iter + "_ca.txt");

                if (!file.exists()) {
                    file.createNewFile();
                }

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                for (Assignment assignment: assignments) {
                    bufferedWriter.write("" + (assignment.getGroup() + 1));
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            centroids.clear();

            for (int i = 0; i < 3; i++) {                                                                               // assigning centroids its new values
                ArrayList<Integer> temp = new ArrayList<>();
                Double x = 0.0, y = 0.0;

                for (int j = 0; j < 300; j++) {
                    if (assignments.get(j).getGroup() == i) {
                        temp.add(j);
                    }
                }

                for (Integer integer: temp) {
                    x += examples.get(integer).getX();
                }

                for (Integer integer: temp) {
                    y += examples.get(integer).getY();
                }

                x /= temp.size();
                y /= temp.size();

                centroids.add(new Example(x, y));
            }

            Double j = 0.0;                                                                                             // compute for J

            for (Assignment assignment: assignments) {
                j += assignment.getMinimum();
            }

            j /= 300;

            try {                                                                                                       // file write; new centroid values and J
                File file = new File("src/MP2/txt/iter" + iter + "_cm.txt");

                if (!file.exists()) {
                    file.createNewFile();
                }

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

                for (Example centroid: centroids) {
                    BigDecimal x = new BigDecimal(centroid.getX());
                    BigDecimal y = new BigDecimal(centroid.getY());

                    bufferedWriter.write(x.setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString() + " " + y.setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
                    bufferedWriter.newLine();
                }

                bufferedWriter.write("J = " + j);
                bufferedWriter.newLine();

                if (iter == 1) {
                    bufferedWriter.write("dJ = -");
                } else {
                    bufferedWriter.write("dJ = " + (j - prevJ));
                }

                prevJ = j;

                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}