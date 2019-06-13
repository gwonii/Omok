package kr.ac.ajou.view;

import processing.core.PApplet;

public class Box {
  private static final int TEXT_SIZE = 30;

  private final float rectX;
  private final float rectY;
  private final float width;
  private final float height;
  private int color;

  public Box(float rectX, float rectY, float width, float height) {
    this.rectX = rectX;
    this.rectY = rectY;
    this.width = width;
    this.height = height;
    color = Color.LIGHT_GREY.getValue();
  }



  public void display(PApplet p) {
    drawFrame(p);

  }

  public void setColor(int color) {
    this.color = color;
  }

  private void drawFrame(PApplet p) {
    p.fill(color);
    p.rect(rectX, rectY, width, height);
  }

  public boolean overRect(int mouseX, int mouseY) {
    return mouseX >= rectX && mouseX <= rectX + width &&
        mouseY >= rectY && mouseY <= rectY + height;
  }

}
