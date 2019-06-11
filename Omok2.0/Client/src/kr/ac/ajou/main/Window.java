package kr.ac.ajou.main;

import com.google.gson.Gson;
import kr.ac.ajou.protocol.ConstantProtocol;
import kr.ac.ajou.protocol.IdData;
import kr.ac.ajou.protocol.MainState;
import kr.ac.ajou.protocol.Protocol;
import kr.ac.ajou.view.LoginBox;
import kr.ac.ajou.view.RoomBox;
import processing.core.PApplet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Window extends PApplet {

    private LoginBox loginBox;
    private RoomBox roomBox;
    private MainState mainState;
    private Socket socket;

    private int mouseValue;

    Queue<Protocol> protocolQueue = new ConcurrentLinkedQueue<>();


    @Override
    public void settings() {

        // network
        connect();

        //init
        setStateLogin();
        mouseValue = 0;

        size(ConstantWindow.WIDTH, ConstantWindow.HEIGHT);

        loginBox = new LoginBox(ConstantWindow.ORIGIN_X, ConstantWindow.ORIGIN_Y,
                ConstantWindow.WIDTH, ConstantWindow.HEIGHT);

        roomBox = new RoomBox(ConstantWindow.ORIGIN_X, ConstantWindow.ORIGIN_Y,
                ConstantWindow.WIDTH, ConstantWindow.HEIGHT);

    }

    @Override
    public void draw() {

        checkMouseCursor();
        //NOTE: receiveThread 를 따로 만들자.
        //  read 자체가 블록함수라서 따로 스레드를 설정해야 한다.

        if (!protocolQueue.isEmpty()) {
            Protocol protocol = protocolQueue.poll();

            String data = protocol.getData();
            String type = protocol.getType();

            Gson gson = new Gson();

            switch (type) {
                case ConstantProtocol.MAIN_STATE:
                    mainState = gson.fromJson(data, MainState.class);
                    System.out.println("mainState: " + mainState);
                    break;
            }
        }

        switch (mainState.getState()) {
            case MainState.LOGIN_VALUE:
                loginBox.display(this);
                break;
            case MainState.LOBBY_VALUE:
                roomBox.display(this);
                break;
            case MainState.ROOM_VALUE:
                break;
        }

    }

    private void checkMouseCursor() {

        switch (mainState.getState()) {
            case MainState.LOGIN_VALUE:
                if (loginBox.getLoginButton().overButton(mouseX, mouseY)) {
                    cursor(HAND);
                    mouseValue = HAND;
                } else {
                    cursor(ARROW);
                    mouseValue = ARROW;
                }
                break;
            case MainState.LOBBY_VALUE:
                break;
            case MainState.ROOM_VALUE:
                break;

        }

    }

    @Override
    public void mousePressed() {
        if (mouseButton == LEFT && mouseValue == HAND) {
            switch (mainState.getState()) {
                case MainState.LOGIN_VALUE:
                    if (loginBox.getLoginButton().overButton(mouseX, mouseY)) {
                        if (loginBox.getId().length() != 0) {
                            sendIdData(loginBox.getId().toString());
                        } else {
                            System.out.println("id를 입력해주세요");
                        }
                    }
                    break;
                case MainState.LOBBY_VALUE:
                    break;
                case MainState.ROOM_VALUE:
                    break;

            }
        }
    }

    @Override
    public void keyPressed() {
        if (mainState.getState() == MainState.LOGIN_VALUE) {
            if (key == ENTER) {
                if (loginBox.getId().length() != 0) {
                    sendIdData(loginBox.getId().toString());
                } else {
                    System.out.println("id를 입력해주세요");
                }
            } else if (key == BACKSPACE) {
                loginBox.backSpaceIdText();
            } else {
                loginBox.plusIdText(key);
            }
        }
    }


    private void setStateLogin() {
        mainState = MainState.LOGIN_STATE;
        System.out.println("state: " + mainState);
    }


    //note : About Client
    private void connect() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ConstantWindow.HOST_NAME, ConstantWindow.PORT_NUM));
            System.out.println("[서버 연결 성공]");

            RecieveThread recieveThread = new RecieveThread(socket, this);
            recieveThread.start();

        } catch (IOException e) {
            System.out.println("[서버 연결 안됨]");
            exit();
        }
    }

    private void sendIdData(String id) {
        IdData idData = new IdData(id);

        Gson gson = new Gson();
        String data = gson.toJson(idData);
        String type = ConstantProtocol.ID_DATA;

        sendToServer(data, type);

    }

    private void sendToServer(String data, String type) {

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
