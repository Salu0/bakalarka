package src.bakalarka;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends IoHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(Client.class);
    private SessionWrapper sessionWrapper = new SessionWrapper();
    private List<StationCounters> stationCounters = new ArrayList<>();
    private String name;

    ClientHandler(String name) {
        this.name = name;
    }

    public void messageSent(IoSession session, Object message) throws Exception {
//        logger.info("Object {} was sent from client", message);
    }

    public void messageReceived(IoSession session, Object message) {
        if (message.toString().charAt(16) == 'm') {
//            logger.info("Object {} was received by client", message);
            handleMessage((JsonObject) message);
        }
    }

    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    private List<StationCounters> handleMessage(JsonObject message) {
        try {
            JsonObject jsonData = (JsonObject) message.get("data");

            JsonElement serial = jsonData.get("serial");
            JsonElement in = jsonData.get("in");
            JsonElement out = jsonData.get("out");
            JsonElement bet = jsonData.get("bet");
            JsonElement win = jsonData.get("win");
            JsonElement game = jsonData.get("game");
            JsonElement updated = jsonData.get("updated");

            String serialAsString = serial.getAsString();
            BigDecimal inAsBigDecimal = in.getAsBigDecimal();
            BigDecimal outAsBigDecimal = out.getAsBigDecimal();
            BigDecimal betAsBigDecimal = bet.getAsBigDecimal();
            BigDecimal winAsBigDecimal = win.getAsBigDecimal();
            BigDecimal gameAsBigDecimal = game.getAsBigDecimal();
            LocalDateTime updatedAsLocalDate = LocalDateTime.parse(updated.getAsString());

            stationCounters.add(new StationCounters(sessionWrapper.dispatchResponse(message), serialAsString, inAsBigDecimal, outAsBigDecimal, betAsBigDecimal, winAsBigDecimal, gameAsBigDecimal, updatedAsLocalDate));

        } catch (NullPointerException npe1) {
            try {
                logger.error("Malformed message was received - {}, station id - {}", message.toString(), sessionWrapper.dispatchResponse(message));
            } catch (NullPointerException npe2) {
                logger.error("Malformed message  without station id was received - {}", message.toString());
            }
        }
        return stationCounters;
    }
    SessionWrapper getSessionWrapper() {
        return sessionWrapper;
    }

    public List<StationCounters> getStationCounters() {
        return stationCounters;
    }

    String getName() {
        return name;
    }
}
