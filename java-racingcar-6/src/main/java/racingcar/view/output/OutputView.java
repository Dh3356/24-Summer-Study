package racingcar.view.output;

/**
 * 출력 뷰
 *
 * @param <T> 출력 타입
 */
public interface OutputView<T> {

  /**
   * 출력
   *
   * @param t 출력
   */
  void print(T t);
}
