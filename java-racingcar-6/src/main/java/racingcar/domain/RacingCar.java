package racingcar.domain;

import static racingcar.common.ErrorMessage.CAR_NAME_EMPTY_ERROR;
import static racingcar.common.ErrorMessage.CAR_NAME_TOO_LONG_ERROR;
import static racingcar.common.ErrorMessage.CAR_NAME_TOO_SHORT_ERROR;

/**
 * 레이싱 자동차 클래스
 */
public class RacingCar {

  public static final int NAME_MIN_LENGTH = 1; // 최소 이름 길이
  public static final int NAME_MAX_LENGTH = 5; // 최대 이름 길이
  private final String name; // 이름
  private int position; // 위치

  /**
   * 생성자, private 으로 선언하여 외부에서 생성하지 못하도록 제한한다.
   *
   * @param name 이름
   */
  private RacingCar(String name) {
    validate(name);

    this.name = name;
    this.position = 0;
  }

  /**
   * 이름을 입력받아 RacingCar 객체를 생성한다.
   *
   * @param name 이름
   * @return RacingCar 객체
   */
  public static RacingCar of(String name) {
    return new RacingCar(name);
  }

  /**
   * 생성자로 받은 매개변수들을 검증한다.
   *
   * @param name 이름
   */
  private void validate(String name) {
    validateName(name);
  }

  /**
   * 이름을 검증한다.
   *
   * @param name 이름
   */
  private void validateName(String name) {
    validateEmptyName(name);
    validateNameMinLength(name);
    validateNameMaxLength(name);
  }

  /**
   * 이름이 비어있는지 검증한다.
   *
   * @param name 이름
   * @throws IllegalArgumentException 이름이 비어있는 경우
   */
  private void validateEmptyName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(CAR_NAME_EMPTY_ERROR);
    }
  }

  /**
   * 이름의 최소 길이를 검증한다.
   *
   * @param name 이름
   * @throws IllegalArgumentException 이름이 너무 짧은 경우
   */
  private void validateNameMinLength(String name) {
    if (name.length() < NAME_MIN_LENGTH) {
      throw new IllegalArgumentException(CAR_NAME_TOO_SHORT_ERROR);
    }
  }

  /**
   * 이름의 최대 길이를 검증한다.
   *
   * @param name 이름
   * @throws IllegalArgumentException 이름이 너무 긴 경우
   */
  private void validateNameMaxLength(String name) {
    if (name.length() > NAME_MAX_LENGTH) {
      throw new IllegalArgumentException(CAR_NAME_TOO_LONG_ERROR);
    }
  }

  /**
   * 자동차를 전진시킨다.
   *
   * @param distance 전진할 거리
   */
  public void move(int distance) {
    this.position += distance;
  }

  public String getName() {
    return name;
  }

  public int getPosition() {
    return position;
  }

  @Override
  public String toString() {
    return name;
  }
}
