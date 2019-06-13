package kr.ac.ajou.main;

import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
import processing.core.PApplet;

public class WindowV2 extends PApplet {

  private String str = "";
  private StringBuilder stringBuilder = new StringBuilder();
  private Stack<Character> characterStack = new Stack<>();

  private StringTokenizer stringTokenizer;
  private int num = gameState.GAME_OVER;

  public void settings() {
    size(500, 100);
  }

  public void setup() {
    background(255);

  }

  public void draw() {
    background(255);


    textSize(30);
    fill(0);


    text(stringBuilder.toString(), 10, 50);
    text(gameState.SET_ORDER.toString(), 50, 50);
  }

  public void keyPressed() {

    if (key == BACKSPACE) {

      if(stringBuilder.length() > 0) {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      }
    } else {
      stringBuilder.append(key);
    }

  }

  public static void main(String[] args) {
    PApplet.main(WindowV2.class);

  }
}
