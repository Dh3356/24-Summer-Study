package baseball.client.view.output;

/**
 * 출력에 필요한 매개변수가 있는 출력 뷰
 *
 * @param <T> 출력 타입
 */
public interface OutputResultView<T> {

  /**
   * 결과를 출력.
   *
   * @param t 출력
   */
  void print(T t);
}
