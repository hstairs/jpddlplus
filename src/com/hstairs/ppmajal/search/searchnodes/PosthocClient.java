package com.hstairs.ppmajal.search.searchnodes;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class PosthocClient extends WebSocketClient {

    public PosthocClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connessione aperta");
        // Messaggio di benvenuto opzionale
        send("{\"event\":\"test\",\"message\":\"Connessione stabilita\"}");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Messaggio ricevuto: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connessione chiusa con codice: " + code + " motivo: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
