package kr.ac.ajou.view;

import static kr.ac.ajou.view.ConstantView.*;

import java.util.concurrent.ConcurrentHashMap;
import processing.core.PApplet;

public class Lobby implements Displayable {

  private Button autoMatchingButton;
  private Button exitButton;

  private ConcurrentHashMap<String,Integer> userListMap;

  @Override
  public void display(PApplet p) {

    drawWattingBox(p);
    drawInfoBox(p);
    drawUserListBox(p);
    drawAutoMatchingButton(p);
    drawExitButton(p);
  }



  private void drawWattingBox(PApplet p) {
    p.fill(Color.LIGHT_GREY.getValue());
    p.rect(WATTING_BOX_X, WATTING_BOX_Y,
        WATTING_BOX_WIDTH,WATTING_BOX_HEIGHT);

  }

  private void drawInfoBox(PApplet p){
    p.fill(Color.LIGHT_GREY.getValue());
    p.rect(INFO_BOX_X, INFO_BOX_Y,
        INFO_BOX_WIDTH, INFO_BOX_HEIGHT);
  }

  private void drawUserListBox(PApplet p){

    p.fill(Color.LIGHT_GREY.getValue());
    p.rect(USER_LIST_BOX_X, USER_LIST_BOX_Y,
        USER_LIST_BOX_WIDTH, USER_LIST_BOX_HEIGHT);
  }

  private void drawAutoMatchingButton(PApplet p){
    autoMatchingButton.display(p);
  }

  private void drawExitButton(PApplet p){
    exitButton.display(p);
  }

  public void makeAutoMatchingButton(){
    autoMatchingButton =
        new Button(AUTO_MATCHING_BUTTON_X, AUTO_MATCHING_BUTTON_Y,
            AUTO_MATCHING_BUTTON_WIDTH,AUTO_MATCHING_BUTTON_HEIGHT );
    autoMatchingButton.setColor(Color.BLACK.getValue());
    autoMatchingButton.setLabel("AUTO MATCHING");
    autoMatchingButton.setButtonTextSize(5);
  }

  public void makeExitButton(){
    exitButton = new Button(EXIT_BUTTON_X,EXIT_BUTTON_Y,
        EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
    exitButton.setColor(Color.BLACK.getValue());
    exitButton.setLabel("EXIT");
    exitButton.setButtonTextSize(20);
  }

  public void drawClientList(PApplet p){
    int gap = 0;

    for(String key : userListMap.keySet()){
      p.fill(Color.BLACK.getValue());
      p.textSize(20);
      p.textAlign(PApplet.CENTER);
      p.text(userListMap.get(key) + ")  " + key,USER_LIST_BOX_X + 50, USER_LIST_BOX_Y + 30 + gap);
      gap += 30;
    }
  }

  public void setUserListMap(
      ConcurrentHashMap<String, Integer> userListMap) {
    this.userListMap = userListMap;
  }
}
