//package src.bakalarka;
//
//import com.google.gson.JsonObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import src.bakalarka.structures.PgStation;
//
//import java.net.InetSocketAddress;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class Client extends Thread {
//    private static Logger logger = LoggerFactory.getLogger(Client.class);
//    private List<PgStation> pgStations;
//    private static List<StationCounters> allStationCounters = new ArrayList<>();
//    private boolean isWaiting;
//
//    public Client(List<PgStation> pgStations) {
//        this.pgStations = pgStations;
//    }
//
//    @Override
//    public void interrupt() {
//        super.interrupt();
//        logger.info("Interrupting thread {}", getName());
//    }
//
//    public void run() {
//        int handlerCnt = 0;
//        while (MyApplication.canRun && !isInterrupted()) {
//            isWaiting = true;
//            //TODO not working properly because it's not thread-safe
////            if (!allStationCounters.isEmpty()) {
////                for (StationCounters counter : allStationCounters) {
////                    counter.updateQuery();
////                }
////                allStationCounters.clear();
//            //} //TODO till here
//
//
//            //todo in, out, bet, ... isn't those
//            handlerCnt += 1;
//            String host = "10.0.0.66";
//            String port = "6060";
//
//            NioSocketConnector connector = new NioSocketConnector();
//            String name = "h" + handlerCnt;
//            ClientHandler handler = new ClientHandler(name);
//
//            connector.setHandler(handler);
//            TextLineCodecFactory tlcf = new TextLineCodecFactory(Charset.forName("UTF-8"));
//            tlcf.setDecoderMaxLineLength(9262144);
//            tlcf.setEncoderMaxLineLength(9262144);
//            ProtocolCodecFilter pcf = new ProtocolCodecFilter(tlcf);
//
//            DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
//            filterChain.addLast("compress", new CompressionFilter());
//            filterChain.addLast("codec", pcf);
//            filterChain.addLast("json", new JsonFilter());
//
//            if (!pgStations.isEmpty()) {
//                for (PgStation station : pgStations) {
//                    ConnectFuture cf;
//                    try {
//                        cf = connect(host, port, connector);
//                        IoSession session = cf.getSession();
//                        try {
//                            session.getCloseFuture().await(30, TimeUnit.MILLISECONDS);
//                        } catch (InterruptedException e) {
//                            break;
//                        }
//                        JsonObject login = station.getLogin();
//                        SessionWrapper sessionWrapper = ((ClientHandler) connector.getHandler()).getSessionWrapper();
//                        sessionWrapper.sendMessage(session, login, station.getIdStation());
//
//                        JsonObject counters = station.getCounters();
//                        sessionWrapper.sendMessage(session, counters, station.getIdStation());
//
//                    } catch (InterruptedException e) {
//                        logger.info("Cannot connect to the server...");
//                    }
//                }
//            }
//            connector.dispose();
//            try {
//                //TODO not tread safe
//                allStationCounters.addAll(((ClientHandler) connector.getHandler()).getStationCounters());
//                //TODO till here
//                isWaiting = false;
//                TimeUnit.SECONDS.sleep(20);
//            } catch (InterruptedException e) {
//                break;
//            }
//        }
//    }
//
//    private ConnectFuture connect(String host, String port, NioSocketConnector connector) throws InterruptedException {
//        ConnectFuture cf = connector.connect(new InetSocketAddress(host, Integer.parseInt(port)));
//        try {
//            cf.await();
//            return cf;
//        } catch (InterruptedException e) {
//            logger.info("Cannot connect to the server...");
//            throw e;
//        }
//    }
//
//    public static List<StationCounters> getAllStationCounters() {
//        return allStationCounters;
//    }
//
//    public boolean isWaiting() {
//        return isWaiting;
//    }
//}
