package lotto.dto.response;

import lotto.domain.LottoRank;

/**
 * 로또 결과 데이터
 * <p>
 *
 * @see CalculateLottoResponse
 */
public class LottoRankResponse {
    private final int matchCount;
    private final boolean isBonusMatch;
    private final int prize;

    private LottoRankResponse(int matchCount, boolean isBonusMatch, int price) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prize = price;
    }

    /**
     * LottoRankResponse 생성
     *
     * @param lottoRank 로또 순위
     * @return LottoRankResponse
     */
    public static LottoRankResponse of(LottoRank lottoRank) {
        return new LottoRankResponse(lottoRank.getMatchCount(), lottoRank.isBonusMatch(), lottoRank.getPrize());
    }


    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }

    public int getPrize() {
        return prize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoRankResponse that)) {
            return false;
        }

        if (matchCount != that.matchCount) {
            return false;
        }
        if (isBonusMatch != that.isBonusMatch) {
            return false;
        }
        return prize == that.prize;
    }

    @Override
    public int hashCode() {
        int result = matchCount;
        result = 31 * result + (isBonusMatch ? 1 : 0);
        result = 31 * result + prize;
        return result;
    }
}
