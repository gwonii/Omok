package kr.ac.ajou.main;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
import processing.core.PApplet;

public class Window extends PApplet {

  private String str = "";
  private char[] tempChars = new char[100];
  private Stack<Character> characterStack = new Stack<>();

  private StringTokenizer stringTokenizer;

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

    text(str, 10, 50);
  }

  public void keyPressed() {

    if (key == ENTER) {
      str = "";

    } else if (key == BACKSPACE) {
      char[] chars = str.toCharArray();
      chars[str.length() - 1] = ' ';
      str = Arrays.toString(chars);
      stringTokenizer = new StringTokenizer(str, ",|[] ");
      str = "";
      while (stringTokenizer.hasMoreTokens()) {
        str += stringTokenizer.nextToken();
      }
    } else {
      str += key;
    }

  }

  public static void main(String[] args) {
    PApplet.main(Window.class);
  }
}
