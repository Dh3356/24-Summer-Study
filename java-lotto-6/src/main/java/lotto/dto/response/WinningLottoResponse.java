package lotto.dto.response;

import java.util.List;
import lotto.domain.WinningLotto;

/**
 * 당첨 로또 응답 DTO
 */
public class WinningLottoResponse {
    private final List<Integer> numbers;
    private final int bonusNumber;

    private WinningLottoResponse(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLottoResponse of(WinningLotto winningLotto) {
        return new WinningLottoResponse(winningLotto.getNumbers(), winningLotto.getBonusNumber());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
