package kr.ac.ajou.view;

public enum Color {

    BLACK(0),LIGHT_GREY(220), GREY(128), WHITE(255);

    private int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
