package lotto.common.util.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoRank;
import lotto.dto.response.LottoRankResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoResultMessageFormatterTest {

    private static List<Object[]> provideLottoRankResponseAndMessage() {
        return Arrays.asList(
                new Object[]{LottoRankResponse.of(LottoRank.MATCHED_SIX), "6개 일치 (2,000,000,000원) - "},
                new Object[]{LottoRankResponse.of(LottoRank.MATCHED_FIVE_WITH_BONUS),
                        "5개 일치, 보너스 볼 일치 (30,000,000원) - "},
                new Object[]{LottoRankResponse.of(LottoRank.MATCHED_FIVE), "5개 일치 (1,500,000원) - "},
                new Object[]{LottoRankResponse.of(LottoRank.MATCHED_FOUR), "4개 일치 (50,000원) - "},
                new Object[]{LottoRankResponse.of(LottoRank.MATCHED_THREE), "3개 일치 (5,000원) - "},
                new Object[]{LottoRankResponse.of(LottoRank.NONE), "0개 일치 (0원) - "}
        );
    }

    @DisplayName("올바른 로또 결과 메시지를 반환한다.")
    @ParameterizedTest(name = "expected: {0}, LottoRank: {1}")
    @MethodSource("provideLottoRankResponseAndMessage")
    void lottoResultMessage(LottoRankResponse lottoRankResponse, String expectedMessage) {
        // when
        String actualMessage = LottoResultMessageFormatter.lottoResultMessage(lottoRankResponse);

        // then
        assertEquals(expectedMessage, actualMessage);
    }
}
