//package src.bakalarka;
//
//import com.google.gson.JsonObject;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//
//class SessionWrapper {
//    private static HashMap<Long, SentMessage> sentMessageIds = new HashMap<>();
//    private static long msgId = 0;
//
//    void sendMessage(IoSession session, JsonObject message, Integer idStation) {
//        Long messageId = getNextMsgId();
//        message.addProperty("msg-id", messageId);
//        session.write(message);
//        sentMessageIds.put(messageId, new SentMessage(idStation));
//    }
//
//    private static synchronized long getNextMsgId() {
//        return msgId += 1;
//    }
//
//    synchronized Integer dispatchResponse(JsonObject response) {
//        Long msgId;
//        if (response.get("msg-id") != null) {
//            msgId = response.get("msg-id").getAsLong();
//            return sentMessageIds.get(msgId).getIdStation();
//        } else {
//            System.out.println("msg-id = null, SessionWrapper dispatchResponse");
//            return 0;
//        }
//    }
//
//    private class SentMessage {
//        LocalDateTime created = LocalDateTime.now();
//        Integer idStation;
//
//        SentMessage(Integer idStation) {
//            this.idStation = idStation;
//        }
//
//        Integer getIdStation() {
//            return idStation;
//        }
//
//        public boolean isValid() {
//            return  created.plusSeconds(10).isBefore(LocalDateTime.now());
//        }
//    }
//}
