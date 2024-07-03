package lotto.dto.request;

import java.util.List;

/**
 * 로또 데이터
 * <p>
 *
 * @see CalculateLottoRequest
 */
public class LottoRequest {
    List<Integer> numbers;

    public LottoRequest(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
