package kr.ac.ajou.protocol;

import kr.ac.ajou.main.data.Data;

public class ClientNum implements Data {

    private int clientNum;

    public ClientNum(int clientNum) {
        this.clientNum = clientNum;
    }

    public int getClientNum() {
        return clientNum;
    }
}
