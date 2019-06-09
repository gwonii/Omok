package kr.ac.ajou.main;

import processing.core.PApplet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Window extends PApplet {

    @Override
    public void settings() {
        connect();
    }

    private void connect(){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ConstantWindow.HOST_NAME, ConstantWindow.PORT_NUM));
            System.out.println("[서버 연결 성공]");

        } catch(IOException e){
            System.out.println("[서버 연결 안됨");
            exit();
        }
    }


    @Override
    public void draw() {

    }

    @Override
    public void mousePressed() {

    }
}
