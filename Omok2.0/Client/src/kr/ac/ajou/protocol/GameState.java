package kr.ac.ajou.protocol;

public class GameState {
    public static final int WAITING = 5;
    public static final int SET_ORDER = 6;
    public static final int RUNNING = 7;
    public static final int GAME_OVER = 8;

    private int gameState;

    public GameState(int gameState) {
        this.gameState = gameState;
    }

    public int getGameState() {
        return gameState;
    }
}
