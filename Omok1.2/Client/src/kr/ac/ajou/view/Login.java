package kr.ac.ajou.view;

import static kr.ac.ajou.view.ConstantView.*;

import processing.core.PApplet;
import processing.core.PConstants;

public class Login implements Displayable {

  private int length;
  private int loginButtonX;
  private int loginButtonY;
  private int loginButtonWidth;
  private int loginButtonHeight;
  private String loginButtonLabel;

  private StringBuilder stringBuilder = new StringBuilder();


  private Button loginButton;

  @Override
  public void display(PApplet p) {

    // 제목 부분
    p.fill(Color.BLACK.getValue());
    p.textSize(60);
    p.textAlign(p.CENTER);
    p.text("NETWORK OMOK", TITLE_TEXT_X, TITLE_TEXT_Y);

    // ID 부분
    p.fill(Color.BLACK.getValue());
    p.textSize(40);
    p.textAlign(p.CENTER);
    p.text("ID", ID_TEXT_X, ID_TEXT_Y);

    // ID_TEXT_BOX
    p.fill(Color.WHITE.getValue());
    p.stroke(Color.GREY.getValue());
    p.rect(ID_TEXT_BOX_X, ID_TEXT_BOX_Y,
        ID_TEXT_BOX_WIDTH, ID_TEXT_BOX_HEIGHT);
    loginButton.display(p);
    writeText(p);
  }

  public void makeLoginButton() {
    loginButton = new Button(LOGIN_BUTTON_X,
        LOGIN_BUTTON_Y,
        LOGIN_BUTTON_WIDTH,
        LOGIN_BUTTON_HEIGHT);
    loginButton.setLabel(LOGIN_BUTTON_LABEL);
    loginButton.setColor(Color.GREY.getValue());
  }

  private void writeText(PApplet p) {
    p.textSize(40);
    p.fill(Color.BLACK.getValue());
    p.textAlign(p.LEFT);
    p.text(stringBuilder.toString(), ID_TEXT_BOX_X + 20, ID_TEXT_BOX_Y + 50);
  }

  public StringBuilder getStringBuilder() {
    return stringBuilder;
  }

  public void setStringBuilder(StringBuilder stringBuilder) {
    this.stringBuilder = stringBuilder;
  }

  public Button getLoginButton() {
    return loginButton;
  }

  public boolean overButton(int mouseX, int mouseY) {
    return mouseX >= LOGIN_BUTTON_X && mouseX <= LOGIN_BUTTON_X + LOGIN_BUTTON_WIDTH &&
        mouseY >= LOGIN_BUTTON_Y && mouseY <= LOGIN_BUTTON_Y + LOGIN_BUTTON_HEIGHT;
  }
}
