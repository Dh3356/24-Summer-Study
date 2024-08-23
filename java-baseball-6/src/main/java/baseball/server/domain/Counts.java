package baseball.server.domain;

/**
 * 스트라이크와 볼의 개수를 나타내는 클래스
 *
 * @param strikeCount 스트라이크 개수
 * @param ballCount   볼 개수
 */
public record Counts(int strikeCount, int ballCount) {

}
