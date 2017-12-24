package org.coredumpproject.coredump;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;



/**
 * Created by Gregory on 5/18/2017.
 */
@ServerEndpoint("/chat")
public class Websocket {

    @Inject
    private World world;

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
        try {
            System.out.println("message: " + message);
            String[] command = message.split(" ");

            if (command[1].equalsIgnoreCase("claim")) {
                world.claim(command[0]);
                System.out.println(command[0] + " claimed");
                for (Session peer : client.getOpenSessions()) {
                    peer.getBasicRemote().sendText(world.getName());
                }
            }else if (command[1].equalsIgnoreCase("getname")) {
                System.out.println(command[0] + " asked who owns this world!");
                for (Session peer : client.getOpenSessions()) {
                    peer.getBasicRemote().sendText(world.getName());
                }
            }
            for (Session peer : client.getOpenSessions()) {
                peer.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
