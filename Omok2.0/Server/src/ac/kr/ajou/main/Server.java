package ac.kr.ajou.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT_NUM = 5550;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(HOST_NAME, PORT_NUM));

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("[클라이언트 접속]");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
