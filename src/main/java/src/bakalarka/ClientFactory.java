//package src.bakalarka;
//
//import com.google.common.collect.Lists;
//import src.bakalarka.structures.PgStation;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//
//public class ClientFactory {
//    public static void splitStations(List<PgStation> stationsToSplit, int partitionsToCreate, ExecutorService threadPool) {
//        float partitionSize2 = (float) stationsToSplit.size() / (float) partitionsToCreate;
//        int partitionMaxSize = (int) Math.ceil(partitionSize2);
//
//        List<List<PgStation>> partitions = new ArrayList<>(Lists.partition(stationsToSplit, partitionMaxSize));
//        createThreads(partitions, threadPool);
//    }
//
//    private static void createThreads(List<List<PgStation>> splittedStations, ExecutorService threadPool) {
//        if (!threadPool.isTerminated()) {
//            for (List<PgStation> stationList : splittedStations) {
//                threadPool.submit(new Client(stationList));
//            }
//        }
//    }
//}