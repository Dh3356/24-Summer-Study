package lotto.common.util.calculator;

import static lotto.common.constant.LottoConstants.LOTTO_PRICE;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;

public class LottoWinningCalculator {

    /**
     * 로또 와 당첨 로또 를 비교하여 LottoRank 를 계산한다.
     *
     * @param lotto        로또
     * @param winningLotto 당첨 로또
     * @return LottoRank
     */
    public LottoRank calculateMatchedCount(Lotto lotto, WinningLotto winningLotto) {
        int count = winningLotto.countMatch(lotto);
        boolean containsBonusNumber = winningLotto.containsBonusNumber(lotto);

        return LottoRank.valueOf(count, containsBonusNumber);
    }

    /**
     * 수익률을 계산한다.
     *
     * @param lottoRanks LottoRank 의 List
     * @param lottoCount 로또 구매 개수
     * @return
     */
    public double calculateProfitRate(List<LottoRank> lottoRanks, int lottoCount) {
        long totalPrize = calculateTotalPrize(lottoRanks);
        int totalPurchasePrice = lottoCount * LOTTO_PRICE;

        return Math.round((double) totalPrize / totalPurchasePrice * 10000) / 100f;
    }

    /**
     * LottoRank 의 List 를 받아 총 상금을 계산한다.
     *
     * @param lottoRanks LottoRank 의 List
     * @return 총 상금
     */
    private long calculateTotalPrize(List<LottoRank> lottoRanks) {
        return lottoRanks.stream()
                .mapToInt(LottoRank::getPrize)
                .sum();
    }
}
