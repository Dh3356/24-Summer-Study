package lotto.dto.request;

import java.util.List;

/**
 * 당첨 번호 요청 데이터
 * <p>
 *
 * @see CalculateLottoRequest
 */
public class WinningLottoRequest {
    private final List<Integer> numbers;
    private final int bonusNumber;

    public WinningLottoRequest(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public String toString() {
        return "WinningLottoRequest{" +
                "numbers=" + numbers +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
