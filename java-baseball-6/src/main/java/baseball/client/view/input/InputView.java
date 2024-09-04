package baseball.client.view.input;

/**
 * 입력 뷰
 *
 * @param <T> 입력 타입
 */
public interface InputView<T> {

  /**
   * 입력.
   *
   * @return 입력
   */
  T input();

  /**
   * 입력 전 출력.
   */
  void printBeforeInput();
}
