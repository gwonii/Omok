package kr.ac.ajou.protocol;

public class MainState {

  public static final int LOGIN = 2;
  public static final int LOBBY = 3;
  public static final int ROOM = 4;

  private int mainState;
  private String Id;

  public MainState(int mainState, String Id) {
    this.mainState = mainState;
    this.Id = Id;
  }
  // 로그인 이외에 것들은 mainState만을 이용한 생성자를 사용한다.
  public MainState(int mainState){
    this.mainState = mainState;
    this.Id = "";
  }

  public int getMainState() {
    return mainState;
  }

  public String getId() {
    return Id;
  }
}
