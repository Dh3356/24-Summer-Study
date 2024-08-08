package lotto.dto.response;

import java.util.LinkedHashMap;

/**
 * 로또 수익률 계산 응답 DTO
 *
 * @see LottoRankResponse
 */
public class CalculateLottoResponse {
    private final LinkedHashMap<LottoRankResponse, Integer> lottoRankResponses;// 로또 순위별 당첨 횟수
    private final double profitRate;// 수익률

    public CalculateLottoResponse(LinkedHashMap<LottoRankResponse, Integer> lottoRankResponses, double profitRate) {
        this.lottoRankResponses = lottoRankResponses;
        this.profitRate = profitRate;
    }

    public LinkedHashMap<LottoRankResponse, Integer> getLottoRankResponses() {
        return lottoRankResponses;
    }

    public double getProfitRate() {
        return profitRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CalculateLottoResponse that)) {
            return false;
        }

        if (Double.compare(that.profitRate, profitRate) != 0) {
            return false;
        }
        // that의 lottoRankResponses 중 value가 0인 경우 제외하고 비교
        LinkedHashMap<LottoRankResponse, Integer> thatLottoRankResponses = that.lottoRankResponses.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll);
        LinkedHashMap<LottoRankResponse, Integer> thisLottoRankResponses = lottoRankResponses.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll);

        return thatLottoRankResponses.equals(thisLottoRankResponses);
    }

    @Override
    public String toString() {
        return "CalculateLottoResponse{" +
                "profitRate=" + profitRate +
                '}';
    }
}
