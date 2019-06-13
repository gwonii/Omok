package kr.ac.ajou.view;

import processing.core.PApplet;

public class Button implements Displayable {

    private static final int TEXT_SIZE = 30;

    private final float rectX;
    private final float rectY;
    private final float width;
    private final float height;

    private String label;
    private int color;
    private int buttonTextSize;

    public Button(float rectX, float rectY, float width, float height) {
        this.rectX = rectX;
        this.rectY = rectY;
        this.width = width;
        this.height = height;
        label = "";
        color = Color.WHITE.getValue();
        buttonTextSize = TEXT_SIZE;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setButtonTextSize(int buttonTextSize) {
        this.buttonTextSize = buttonTextSize;
    }

    public void display(PApplet p) {
        drawFrame(p);
        drawText(p);
    }

    private void drawFrame(PApplet p) {
        p.fill(color);
        p.rect(rectX, rectY, width, height);
    }

    private void drawText(PApplet p) {
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(Color.WHITE.getValue());
        p.textSize(buttonTextSize);
        p.text(label, rectX + (width / 2), rectY + (height / 2));
    }

    public boolean overRect(int mouseX, int mouseY) {
        return mouseX >= rectX && mouseX <= rectX + width &&
                mouseY >= rectY && mouseY <= rectY + height;
    }

    public int getColor() {
        return color;
    }
}