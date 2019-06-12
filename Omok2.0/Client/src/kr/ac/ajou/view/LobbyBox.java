package kr.ac.ajou.view;

import processing.core.PApplet;

public class LobbyBox implements Displayable {


    private final float rectX;
    private final float rectY;
    private final float width;
    private final float height;

    public LobbyBox(float rectX, float rectY, float width, float height) {
        this.rectX = rectX;
        this.rectY = rectY;
        this.width = width;
        this.height = height;
    }


    @Override
    public void display(PApplet p) {
        drawFrame(p);
        drawTitle(p);
    }

    private void drawFrame(PApplet p) {
        setStrokeGrey(p);
        fillGrey(p);
        p.rect(rectX, rectY, width, height);
    }


    private void drawTitle(PApplet p) {
        fillBlack(p);
        p.textAlign(PApplet.CENTER);
        p.textSize(100);
        p.text("LOBBY", width / 2, height / 4);
    }

}
