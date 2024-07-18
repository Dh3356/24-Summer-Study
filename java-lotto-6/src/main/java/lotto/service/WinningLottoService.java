package lotto.service;

import java.util.LinkedHashMap;
import java.util.List;
import lotto.common.util.calculator.LottoWinningCalculator;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.dto.request.CalculateLottoRequest;
import lotto.dto.request.LottoRequest;
import lotto.dto.request.WinningLottoRequest;
import lotto.dto.response.CalculateLottoResponse;
import lotto.dto.response.LottoRankResponse;
import lotto.dto.response.WinningLottoResponse;

/**
 * 당첨 로또 서비스
 */
public class WinningLottoService {
    private final LottoWinningCalculator lottoWinningCalculator;

    public WinningLottoService(LottoWinningCalculator lottoWinningCalculator) {
        this.lottoWinningCalculator = lottoWinningCalculator;
    }

    /**
     * 당첨 로또를 생성한다.
     *
     * @param request 당첨 로또 생성 요청 DTO
     * @return 당첨 로또 응답 DTO
     */
    public WinningLottoResponse createWinningLotto(WinningLottoRequest request) {
        WinningLotto winningLotto = new WinningLotto(request.getNumbers(), request.getBonusNumber());
        return WinningLottoResponse.of(winningLotto);
    }

    /**
     * 당첨 로또를 통해 당첨 결과를 계산한다.
     *
     * @param request 당첨 결과 계산 요청 DTO
     * @return 당첨 결과 응답 DTO
     */
    public CalculateLottoResponse calculateWinningLotto(CalculateLottoRequest request) {
        // 로또 요청 List 에서 로또 List 를 추출한다.
        List<Lotto> lottos = extractLottos(request.getLottoRequests());

        // 당첨 로또 요청에서 당첨 로또를 추출한다.
        WinningLotto winningLotto = extractWinningLotto(request.getWinningLottoRequest());

        // 로또 List 와 당첨 로또를 통해 로또 순위를 계산한다.
        List<LottoRank> lottoRanks = calculateLottoRanks(lottos, winningLotto);

        // 로또 순위 List 를 통해 로또 순위 응답 Map 을 생성한다.
        LinkedHashMap<LottoRankResponse, Integer> lottoRankResponsesMap = createLottoRankResponsesMap(lottoRanks);

        // 총 상금과 총 구매 금액을 통해 수익률을 계산한다.
        double profitRate = lottoWinningCalculator.calculateProfitRate(lottoRanks, lottos.size());

        // 로또 순위 응답 Map 과 수익률을 통해 로또 계산 응답 DTO 를 생성한다.
        return new CalculateLottoResponse(lottoRankResponsesMap, profitRate);
    }

    /**
     * 로또 요청 List 에서 로또 List 를 추출한다.
     *
     * @param lottoRequests 로또 요청 List
     * @return 로또 List
     */
    private List<Lotto> extractLottos(List<LottoRequest> lottoRequests) {
        return lottoRequests.stream()
                .map(lottoRequest -> new Lotto(lottoRequest.getNumbers()))
                .toList();
    }

    /**
     * 당첨 로또 요청에서 당첨 로또를 추출한다.
     *
     * @param winningLottoRequest 당첨 로또 요청
     * @return 당첨 로또
     */
    private WinningLotto extractWinningLotto(WinningLottoRequest winningLottoRequest) {
        return new WinningLotto(winningLottoRequest.getNumbers(), winningLottoRequest.getBonusNumber());
    }

    /**
     * 로또 List 와 당첨 로또를 통해 로또 순위를 계산한다.
     *
     * @param lottos       로또 List
     * @param winningLotto 당첨 로또
     * @return 로또 순위 List
     */
    private List<LottoRank> calculateLottoRanks(List<Lotto> lottos, WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> lottoWinningCalculator.calculateMatchedCount(lotto, winningLotto))
                .toList();
    }

    /**
     * 로또 순위 List 를 통해 로또 순위 응답 Map 을 생성한다.
     *
     * @param lottoRanks 로또 순위 List
     * @return 로또 순위 응답 Map
     */
    private LinkedHashMap<LottoRankResponse, Integer> createLottoRankResponsesMap(List<LottoRank> lottoRanks) {

        // NONE 을 제외한 모든 LottoRankResponse 를 0으로 초기화
        LinkedHashMap<LottoRankResponse, Integer> lottoRankResponsesMap = new LinkedHashMap<>();
        LottoRank.valuesExceptNone()
                .forEach(lottoRank -> lottoRankResponsesMap.put(LottoRankResponse.of(lottoRank), 0));

        // LottoRank 를 LottoRankResponse 로 변환하고 개수를 세어 맵에 추가
        lottoRanks.stream()
                .filter(lottoRank -> lottoRank != LottoRank.NONE)// NONE 은 제외
                .map(LottoRankResponse::of)// LottoRank 를 LottoRankResponse 로 변환
                .forEach(lottoRankResponse ->// 개수를 세어 맵에 추가
                        lottoRankResponsesMap.merge(lottoRankResponse, 1, Integer::sum)
                );

        return lottoRankResponsesMap;
    }
}

