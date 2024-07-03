package lotto.dto.response;

import java.util.LinkedHashMap;

/**
 * 로또 수익률 계산 응답 DTO
 *
 * @see LottoRankResponse
 */
public class CalculateLottoResponse {
    private final LinkedHashMap<LottoRankResponse, Integer> lottoRankResponses;// 로또 순위별 당첨 횟수
    private final float profitRate;// 수익률

    public CalculateLottoResponse(LinkedHashMap<LottoRankResponse, Integer> lottoRankResponses, float profitRate) {
        this.lottoRankResponses = lottoRankResponses;
        this.profitRate = profitRate;
    }

    public LinkedHashMap<LottoRankResponse, Integer> getLottoRankResponses() {
        return lottoRankResponses;
    }

    public float getProfitRate() {
        return profitRate;
    }
}
