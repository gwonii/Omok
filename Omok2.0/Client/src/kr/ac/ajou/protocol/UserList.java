package kr.ac.ajou.protocol;

import java.util.concurrent.ConcurrentHashMap;

public class UserList {
  private ConcurrentHashMap<String,Integer> userListMap;

  UserList(ConcurrentHashMap<String,Integer> userListMap){
    this.userListMap = userListMap;
  }

  public ConcurrentHashMap<String, Integer> getUserListMap() {
    return userListMap;
  }
}
