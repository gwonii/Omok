package ac.kr.ajou.main;

import ac.kr.ajou.protocol.ConstantProtocol;
import ac.kr.ajou.protocol.IdData;
import ac.kr.ajou.protocol.MainState;
import ac.kr.ajou.protocol.Protocol;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class SessionThread extends Thread {

    private static final int MAX_SIZE = 1024;

    private String id;
    private Socket socket;
    private List<Socket> socketList;

    private MainState mainState;


    SessionThread(Socket socket, List<Socket> socketList) {
        //init
        mainState = MainState.LOGIN_STATE;

        this.socket = socket;
        this.socketList = socketList;
        socketList.add(socket);
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream();
             DataInputStream dis = new DataInputStream(is)) {

            byte[] buf = new byte[MAX_SIZE];
            while (true) {
                int len;
                try {
                    len = dis.readInt();
                } catch (EOFException e) {
                    System.out.println("[클라이언트가 비정상 종료되었습니다.]");
                    socketList.remove(socket);
                    break;
                }
                int ret = is.read(buf, 0, len);
                String json = new String(buf, 0, ret);

                // 객체로 변환하기 (by Gson)
                Gson gson = new Gson();
                Protocol protocol = gson.fromJson(json, Protocol.class);

                String data = protocol.getData();
                String type = protocol.getType();

                switch (type) {
                    case ConstantProtocol.ID_DATA:
                        IdData idData = gson.fromJson(data, IdData.class);
                        System.out.println("id: " + idData.getId());

                        setStateLobby();
                        sendMainState();
                        break;
                }

            }

        } catch (SocketException e) {
            System.out.println("[클라이언트가 비정상 종료되었습니다.]");
            socketList.remove(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendMainState(){

        Gson gson = new Gson();

        String data = gson.toJson(mainState);
        String type = ConstantProtocol.MAIN_STATE;

        sendToClient(data,type);
    }

    private void sendToClient(String data, String type) {
        try {
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            Gson gson = new Gson();

            Protocol protocol = new Protocol(data, type);
            String json = gson.toJson(protocol);
            int len = json.length();

            dos.writeInt(len);
            os.write(json.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToOther(String data, String type) {
        for (Socket socket : socketList) {
            if (socket == this.socket) {
                continue;
            }

            try {
                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                Gson gson = new Gson();

                Protocol protocol = new Protocol(data, type);
                String json = gson.toJson(protocol);
                int len = json.length();

                dos.writeInt(len);
                os.write(json.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcast(String data, String type) {
        try {
            for (Socket socket : socketList) {
                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                Gson gson = new Gson();

                Protocol protocol = new Protocol(data, type);
                String json = gson.toJson(protocol);

                int len = json.length();

                dos.writeInt(len);
                os.write(json.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setStateLogin() {
        mainState = MainState.LOGIN_STATE;
        System.out.println("state: " + mainState);
    }

    private void setStateLobby() {
        mainState = MainState.LOBBY_STATE;
        System.out.println("state: " + mainState);
    }

    private void setStateRoom() {
        mainState = MainState.ROOM_STATE;
        System.out.println("state: " + mainState);
    }
}
