import java.io.File;
import java.util.Scanner;

import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.distance.DistanceMeasure;
import net.sf.javaml.distance.EuclideanDistance;
import net.sf.javaml.clustering.evaluation.AICScore;
import net.sf.javaml.clustering.evaluation.BICScore;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;

//Kmeans
import net.sf.javaml.clustering.KMeans;
//Farthest First
import net.sf.javaml.clustering.FarthestFirst;
//KMedoids
import net.sf.javaml.clustering.KMedoids;

public class ClusteringExample {
    public static void main(String[] args) throws Exception {

        System.out.println("CLUSTERING MENU");
        System.out.println();
        System.out.println("1. Kmeans");
        System.out.println("2. Farthest First");
        System.out.println("3. KMedoids");
        System.out.println("4. Evaluate Menu");

        System. out.println("Enter choice:");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        // Load the Iris dataset
        Dataset data = FileHandler.loadDataset(new File("iris.data"), 4, ",");

        switch(choice){
            case 1:
                kmeans(data);
                break;
            case 2:
                farthestFirst(data);
                break;
            case 3:
                KMedoids(data);
                break;
            case 4: {
                System.out.println();
                System.out.println("1. Evaluate on AIC Score");
                System.out.println("2. Evaluate on BIC Score");
                System.out.println("3. Evaluate on Sum of Squared");
                System.out.println("Enter you choice");
                int echoice = sc.nextInt();
                switch(echoice){
                    case 1:
                        // Evaluate KMeans
                        System.out.println("Kmeans Clustering");
                        evaluateClustering(1,data, new KMeans(3));

                        // Evaluate Farthest First
                        System.out.println("Farthest First Clustering");
                        evaluateClustering(1, data, new FarthestFirst(3, new EuclideanDistance()));

                        // Evaluate KMedoids
                        System.out.println("KMedoids Clustering");
                        evaluateClustering(1, data, new KMedoids(3, 100, new EuclideanDistance()));
                        break;
                    case 2:
                        // Evaluate KMeans
                        System.out.println("Kmeans Clustering");
                        evaluateClustering(2, data, new KMeans(3));

                        // Evaluate Farthest First
                        System.out.println("Farthest First Clustering");
                        evaluateClustering(2, data, new FarthestFirst(3, new EuclideanDistance()));

                        // Evaluate KMedoids
                        System.out.println("KMedoids Clustering");
                        evaluateClustering(2, data, new KMedoids(3, 100, new EuclideanDistance()));
                        break;
                    case 3:
                        // Evaluate KMeans
                        System.out.println("Kmeans Clustering");
                        evaluateClustering(3, data, new KMeans(3));

                        // Evaluate Farthest First
                        System.out.println("Farthest First Clustering");
                        evaluateClustering(3, data, new FarthestFirst(3, new EuclideanDistance()));

                        // Evaluate KMedoids
                        System.out.println("KMedoids Clustering");
                        evaluateClustering(3, data, new KMedoids(3, 100, new EuclideanDistance()));
                        break;
                    default :
                        System.out.println("Wrong choice. Please choose a valid option.");
                        break;
                }
            }
            break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                break;
        }


    }
    public static void kmeans(Dataset data){
        System.out.println("Enter the number of clusters to generate:");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        // Initialize KMeans clustering algorithm
        Clusterer kmeans = new KMeans(k);

        // Perform clusters
        Dataset[] clusters = kmeans.cluster(data);

        // Print the Kmeans outputs
        for (int i = 0; i < clusters.length; i++) {
            System.out.println("Kmeans Cluster " + (i + 1) + ":");
            for (Instance instance : clusters[i]) {
                System.out.println(instance);
            }
            System.out.println();
        }
    }
    public static void farthestFirst(Dataset data){
        System.out.println("Enter the number of clusters to generate:");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        //Initialize Farthest First algorithm
        DistanceMeasure dm = new EuclideanDistance();
        Clusterer farthestFirst = new FarthestFirst(k, dm);

        // Perform clusters
        Dataset[] clusters = farthestFirst.cluster(data);

        // Print the farthest first output
        for (int i = 0; i < clusters.length; i++) {
            System.out.println("Farthest First Cluster " + (i + 1) + ":");
            for (Instance instance : clusters[i]) {
                System.out.println(instance);
            }
            System.out.println();
        }
    }

    public static void KMedoids(Dataset data){
        System.out.println("Enter the number of clusters to generate:");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        // Initialize Kmedoids clustering algorithm
        DistanceMeasure dm = new EuclideanDistance();
        Clusterer kmedoids = new KMedoids(k, 100, dm);

        // Perform clusters
        Dataset[] clusters = kmedoids.cluster(data);

        // Print the KMedoids output
        for (int i = 0; i < clusters.length; i++) {
            System.out.println("KMedoids Cluster " + (i + 1) + ":");
            for (Instance instance : clusters[i]) {
                System.out.println(instance);
            }
            System.out.println();
        }
    }
    public static void evaluateClustering(int choice, Dataset data, Clusterer clusterer) {
        // Cluster the data
        Dataset[] clusters = clusterer.cluster(data);
         if(choice ==1){
             ClusterEvaluation aic = new AICScore();
             double aicScore = aic.score(clusters);
             System.out.println("AIC Score: " + aicScore);
             System.out.println();
         }
         if(choice ==2){
             ClusterEvaluation bic = new BICScore();
             double bicScore = bic.score(clusters);
             System.out.println("BIC Score: " + bicScore);
             System.out.println();
         }
         if(choice==3){
             ClusterEvaluation sse = new SumOfSquaredErrors();
             double sseScore = sse.score(clusters);
             System.out.println("Sum of Squared Errors: " + sseScore);
             System.out.println();
         }
    }
}


