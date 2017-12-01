package MP2;

import java.io.*;
import java.util.*;

public class KMeans {
    public static final String FILE_NAME = "src/MP2/etc/kmdata1.txt";

    public static void main(String[] args) {
        ArrayList<Example> examples = new ArrayList<>();
        ArrayList<Example> centroids = new ArrayList<>();
        ArrayList<Assignment> assignments = new ArrayList<>();
        BufferedReader br = null;

        try {
            String current;
            br = new BufferedReader(new FileReader(FILE_NAME));

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

        for (int i = 0; i < 300; i++) {
            ArrayList<Assignment> temp = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                temp.add(new Assignment(Math.sqrt(Math.pow(examples.get(i).getX() - centroids.get(j).getX(), 2) + Math.pow(examples.get(i).getY() - centroids.get(j).getY(), 2)), j + 1));
            }

            temp.sort(Comparator.comparingDouble(Assignment::getMinimum));
            assignments.add(temp.get(0));
        }
    }
}
