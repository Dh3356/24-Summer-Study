package lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.common.config.ApplicationConfig;
import lotto.domain.LottoRank;
import lotto.dto.request.CalculateLottoRequest;
import lotto.dto.request.LottoRequest;
import lotto.dto.request.WinningLottoRequest;
import lotto.dto.response.CalculateLottoResponse;
import lotto.dto.response.LottoRankResponse;
import lotto.dto.response.WinningLottoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoServiceTest {
    private final ApplicationConfig applicationConfig = new ApplicationConfig();
    private final WinningLottoService winningLottoService = applicationConfig.winningLottoService();

    // 올바른 당첨 로또 생성 요청 생성
    private static Stream<WinningLottoRequest> provideValidWinningLottoRequests() {
        return Stream.of(
                new WinningLottoRequest(List.of(1, 2, 3, 4, 5, 6), 7),
                new WinningLottoRequest(List.of(1, 2, 3, 4, 5, 10), 6),
                new WinningLottoRequest(List.of(1, 2, 3, 4, 5, 16), 10),
                new WinningLottoRequest(List.of(1, 2, 3, 4, 7, 8), 9),
                new WinningLottoRequest(List.of(1, 2, 3, 11, 7, 8), 9),
                new WinningLottoRequest(List.of(1, 2, 12, 11, 7, 8), 9)
        );
    }

    // 올바른 당첨 로또 계산 요청 생성
    private static Stream<Arguments> provideValidCalculateLottoRequestsWithExpected() {
        List<LottoRequest> lottoRequests = List.of(
                new LottoRequest(List.of(1, 2, 3, 4, 5, 6))
        );
        List<LottoRankResponse> lottoRankResponses = List.of(
                LottoRankResponse.of(LottoRank.MATCHED_SIX),
                LottoRankResponse.of(LottoRank.MATCHED_FIVE_WITH_BONUS),
                LottoRankResponse.of(LottoRank.MATCHED_FIVE),
                LottoRankResponse.of(LottoRank.MATCHED_FOUR),
                LottoRankResponse.of(LottoRank.MATCHED_THREE),
                LottoRankResponse.of(LottoRank.NONE)
        );

        return Stream.of(
                Arguments.of(
                        new CalculateLottoRequest(new WinningLottoRequest(List.of(1, 2, 3, 4, 5, 6), 7), lottoRequests),
                        new CalculateLottoResponse(new LinkedHashMap<>(Map.of(lottoRankResponses.get(0), 1)), 2.0E8)
                ),
                Arguments.of(
                        new CalculateLottoRequest(new WinningLottoRequest(List.of(1, 2, 3, 4, 5, 10), 6),
                                lottoRequests),
                        new CalculateLottoResponse(new LinkedHashMap<>(Map.of(lottoRankResponses.get(1), 1)), 3000000.0)
                ),
                Arguments.of(
                        new CalculateLottoRequest(new WinningLottoRequest(List.of(1, 2, 3, 4, 5, 16), 10),
                                lottoRequests),
                        new CalculateLottoResponse(new LinkedHashMap<>(Map.of(lottoRankResponses.get(2), 1)), 150000.0)
                ),
                Arguments.of(
                        new CalculateLottoRequest(new WinningLottoRequest(List.of(1, 2, 3, 4, 7, 8), 9), lottoRequests),
                        new CalculateLottoResponse(new LinkedHashMap<>(Map.of(lottoRankResponses.get(3), 1)), 5000.0)
                ),
                Arguments.of(
                        new CalculateLottoRequest(new WinningLottoRequest(List.of(1, 2, 3, 11, 7, 8), 9),
                                lottoRequests),
                        new CalculateLottoResponse(new LinkedHashMap<>(Map.of(lottoRankResponses.get(4), 1)), 500.0)
                ),
                Arguments.of(
                        new CalculateLottoRequest(new WinningLottoRequest(List.of(1, 2, 12, 11, 7, 8), 9),
                                lottoRequests),
                        new CalculateLottoResponse(new LinkedHashMap<>(Map.of(lottoRankResponses.get(5), 0)), 0.0)
                )
        );
    }

    @DisplayName("정상적으로 당첨 로또를 생성한다.")
    @ParameterizedTest(name = "winningLottoRequest: {0}")
    @MethodSource("provideValidWinningLottoRequests")
    void createWinningLotto(WinningLottoRequest request) {
        WinningLottoResponse winningLottoResponse = winningLottoService.createWinningLotto(request);
        assertNotNull(winningLottoResponse);
    }

    @DisplayName("당첨 로또를 통해 정상적으로 당첨 결과를 확인한다.")
    @ParameterizedTest(name = "calculateLottoRequest: {0}, expected: {1}")
    @MethodSource("provideValidCalculateLottoRequestsWithExpected")
    void calculateLotto(CalculateLottoRequest request, CalculateLottoResponse expected) {
        CalculateLottoResponse calculateLottoResponse = winningLottoService.calculateWinningLotto(request);
        assertEquals(expected, calculateLottoResponse);
    }
}

