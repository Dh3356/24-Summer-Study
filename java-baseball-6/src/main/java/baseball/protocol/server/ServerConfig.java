package baseball.protocol.server;

/**
 * 서버 설정을 나타내는 인터페이스
 */
public interface ServerConfig {

  /**
   * 서버 이름을 반환한다.
   *
   * @return 서버 이름
   */
  String getServerName();

  /**
   * 서버 객체를 반환한다.
   *
   * @return 서버 객체
   */
  Server getServer();
}
