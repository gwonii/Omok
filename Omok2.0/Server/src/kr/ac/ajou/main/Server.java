package kr.ac.ajou.main;

import kr.ac.ajou.main.data.ClientNumAscendingComparator;
import kr.ac.ajou.protocol.ClientInfo;
import kr.ac.ajou.protocol.ClientNum;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT_NUM = 5550;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(HOST_NAME, PORT_NUM));

            ClientCount clientCount = new ClientCount();
            TreeSet<ClientNum> deletedClientNumTreeSet = new TreeSet<>(new ClientNumAscendingComparator());

            List<Socket> socketList = new ArrayList<>();

            Map<ClientNum, ClientInfo> clientInfoMap = new HashMap<>();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println();
                System.out.println("[클라이언트 접속]");
                SessionThread sessionThread = new SessionThread(clientCount, deletedClientNumTreeSet,
                        socket, socketList,
                        clientInfoMap);
                sessionThread.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
