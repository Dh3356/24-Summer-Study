package baseball;

import baseball.baseball_game_client.BaseballGameClient;

public class Application {

  private final static BaseballGameClient BASEBALL_GAME_CLIENT = new BaseballGameClient();

  public static void main(String[] args) {
    BASEBALL_GAME_CLIENT.run();
  }
}

