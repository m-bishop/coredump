package org.coredumpproject.coredump;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;



/**
 * Created by Gregory on 5/18/2017.
 */
@ServerEndpoint("/chat")
public class Websocket {

    @OnOpen
    public void open(Session session) {
    }

    @OnClose
    public void close(Session session) {
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException {
        System.out.println("message: " + message);
        for (Session peer : client.getOpenSessions()) {
            peer.getBasicRemote().sendText(message);
        }
    }
}
