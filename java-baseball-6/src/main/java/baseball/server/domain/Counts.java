package baseball.server.domain;

import java.util.Objects;

/**
 * 스트라이크와 볼의 개수를 나타내는 클래스
 *
 * @param strikeCount 스트라이크 개수
 * @param ballCount   볼 개수
 */
public record Counts(int strikeCount, int ballCount) {

  @Override
  public String toString() {
    return "Counts{" +
        "strikeCount=" + strikeCount +
        ", ballCount=" + ballCount +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Counts that)) {
      return false;
    }

    return strikeCount == that.strikeCount && ballCount == that.ballCount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(strikeCount, ballCount);
  }
}
