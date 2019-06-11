package kr.ac.ajou.view;

import processing.core.PApplet;

public class LoginButton implements Displayable {

    private static final int TEXT_SIZE = 30;

    private final float rectX;
    private final float rectY;
    private final float width;
    private final float height;

    private String label;

    public LoginButton(float rectX, float rectY, float width, float height) {
        this.rectX = rectX;
        this.rectY = rectY;
        this.width = width;
        this.height = height;
        label = "";

    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void display(PApplet p) {
        drawFrame(p);
    }
    private void drawFrame(PApplet p) {

        p.rect(rectX, rectY, width, height);
    }

    private void drawText(PApplet p) {
        p.textAlign(p.CENTER, p.CENTER);
        fillBlack(p);
        p.textSize(TEXT_SIZE);
        p.text(label, rectX + (width / 2), rectY + (height / 2));
    }

    public boolean overRect(int mouseX, int mouseY) {
        return mouseX >= rectX && mouseX <= rectX + width &&
                mouseY >= rectY && mouseY <= rectY + height;
    }
}
