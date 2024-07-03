package lotto.common.util.generator;

import static lotto.common.constant.LottoConstants.LOTTO_MAX;
import static lotto.common.constant.LottoConstants.LOTTO_MIN;
import static lotto.common.constant.LottoConstants.LOTTO_SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

/**
 * 로또 번호 생성기
 */
public class LottoNumberGenerator {
    /**
     * 로또 번호 리스트를 생성한다.
     *
     * @return 생성된 로또 번호 리스트
     */
    public List<Integer> generateLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_MIN, LOTTO_MAX, LOTTO_SIZE);
    }
}
