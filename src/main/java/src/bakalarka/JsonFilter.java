//package src.bakalarka;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.stream.JsonReader;
//
//import java.io.IOException;
//import java.io.StringReader;
//
//public class JsonFilter extends WriteRequestFilter {
//    private final Gson gson = new Gson();
//    private final JsonParser parser = new JsonParser();
//
//    @Override
//    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession session, Object message) throws Exception {
//        try {
//            JsonReader reader = new JsonReader(new StringReader((String) message));
//            reader.setLenient(true);
//            JsonElement el = parser.parse(reader);
//
//            if (el != null && el.isJsonObject()) {
//                nextFilter.messageReceived(session, el);
//            } else {
//                System.out.println(message + " must be JsonElement");
//            }
//        } catch (Exception e) {
//            System.out.println("IGNORING " + message);
//        }
//    }
//
////    @Override
//    protected Object doFilterWrite(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws IOException {
//        return gson.toJson((JsonObject) writeRequest.getMessage());
//    }
//}
