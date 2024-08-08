package lotto.domain;

import java.util.Arrays;
import java.util.List;

/**
 * 로또 순위를 나타내는 Enum
 */
public enum LottoRank {
    NONE(0, 0, false),//3 개 이하 일치 (0 원)
    MATCHED_THREE(3, 5000, false),// 3 개 일치 (5,000 원)
    MATCHED_FOUR(4, 50000, false),// 4 개 일치 (50,000 원)
    MATCHED_FIVE(5, 1500000, false),// 5 개 일치 (1,500,000 원)
    MATCHED_FIVE_WITH_BONUS(5, 30000000, true),// 5 개 일치, 보너스 볼 일치 (30,000,000 원)
    MATCHED_SIX(6, 2000000000, false);// 6 개 일치 (2,000,000,000 원)

    private final int matchCount;// 일치하는 숫자의 개수
    private final boolean isBonusMatch;// 보너스 볼 일치 여부
    private final int prize;// 당첨 금액

    LottoRank(int matchCount, int prize, boolean isBonusMatch) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.isBonusMatch = isBonusMatch;
    }

    /**
     * 일치하는 숫자의 개수와 보너스 볼 여부에 따라 LottoRank 를 반환한다.
     *
     * @param count 일치하는 숫자의 개수
     * @param bonus 보너스 볼 여부
     * @return LottoRank
     */
    public static LottoRank valueOf(int count, boolean bonus) {
        if (bonus && count == MATCHED_FIVE.matchCount) {
            return MATCHED_FIVE_WITH_BONUS;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == count)
                .findFirst()
                .orElse(NONE);
    }

    public static List<LottoRank> valuesExceptNone() {
        return Arrays.stream(values())
                .filter(rank -> rank != NONE)
                .toList();
    }

    /**
     * 일치하는 숫자의 개수를 반환한다.
     *
     * @return 일치하는 숫자의 개수
     */
    public int getMatchCount() {
        return matchCount;
    }

    /**
     * 당첨 금액을 반환한다.
     *
     * @return 당첨 금액
     */
    public int getPrize() {
        return prize;
    }

    /**
     * 보너스 볼 일치 여부를 반환한다.
     *
     * @return 보너스 볼 일치 여부
     */
    public boolean isBonusMatch() {
        return isBonusMatch;
    }
}
