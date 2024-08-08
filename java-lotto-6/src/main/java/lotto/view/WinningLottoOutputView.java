package lotto.view;

import static lotto.common.util.formatter.LottoResultMessageFormatter.lottoResultMessage;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import lotto.dto.response.CalculateLottoResponse;
import lotto.dto.response.LottoRankResponse;

/**
 * 당첨 로또 출력 뷰
 */
public class WinningLottoOutputView {

    /**
     * 당첨 결과 출력
     *
     * @param calculateLottoResponse 로또 수익률 계산 응답
     */
    public void printWinningResult(CalculateLottoResponse calculateLottoResponse) {
        System.out.println("당첨 통계");
        System.out.println("---");

        printLottoRanks(calculateLottoResponse.getLottoRankResponses());

        System.out.println("총 수익률은 " + calculateLottoResponse.getProfitRate() + "%입니다.");
    }

    /**
     * 로또 순위별 당첨 횟수를 정렬하여 반환한다.
     *
     * @param lottoRankResponses 정렬 전 로또 순위별 당첨 횟수
     * @return 정렬된 로또 순위별 당첨 횟수
     */
    private List<LottoRankResponse> getSortedLottoRanks(HashMap<LottoRankResponse, Integer> lottoRankResponses) {
        return lottoRankResponses.keySet().stream()
                .sorted(Comparator.comparingInt(LottoRankResponse::getMatchCount))
                .toList();
    }

    /**
     * 로또 순위별 당첨 결과를 출력한다.
     *
     * @param lottoRankResponses 로또 순위별 당첨 횟수
     */
    private void printLottoRanks(HashMap<LottoRankResponse, Integer> lottoRankResponses) {

        List<LottoRankResponse> sortedLottoRanks = getSortedLottoRanks(lottoRankResponses);

        sortedLottoRanks.forEach(lottoRankResponse -> {
            int count = lottoRankResponses.get(lottoRankResponse);
            printLottoRank(lottoRankResponse, count);
        });
    }

    /**
     * 로또 순위의 당첨 결과를 출력한다.
     *
     * @param lottoRankResponse 로또 순위
     * @param count             당첨 횟수
     */
    private void printLottoRank(LottoRankResponse lottoRankResponse, int count) {
        System.out.println(lottoResultMessage(lottoRankResponse) + count + "개");
    }


}