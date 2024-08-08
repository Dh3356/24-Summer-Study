package lotto.dto.response;

import java.util.List;
import lotto.domain.Lotto;

/**
 * 로또 응답 데이터
 * <p>
 *
 * @see CalculateLottoResponse
 */
public class LottoResponse {
    List<Integer> numbers;

    private LottoResponse(List<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * LottoResponse 생성
     *
     * @param lotto 로또
     * @return LottoResponse
     */
    public static LottoResponse of(final Lotto lotto) {
        return new LottoResponse(lotto.getNumbers());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
