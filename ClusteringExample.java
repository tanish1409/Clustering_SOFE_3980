import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;

public class ClusteringExample {
    public static void main(String[] args) throws Exception {
        // Load the Iris dataset
        Dataset data = FileHandler.loadDataset(new File("iris.data"), 4, ",");

        // Initialize clustering algorithm (e.g., KMeans)
        Clusterer kmeans = new KMeans(3); // KMeans with 3 clusters

        // Perform clustering
        Dataset[] clusters = kmeans.cluster(data);

        // Print the output
        for (int i = 0; i < clusters.length; i++) {
            System.out.println("Cluster " + (i + 1) + ":");
            for (Instance instance : clusters[i]) {
                System.out.println(instance);
            }
            System.out.println();
        }
    }
}
