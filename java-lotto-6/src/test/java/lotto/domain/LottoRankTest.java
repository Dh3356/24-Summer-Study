package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoRankTest {
    private static Stream<Object[]> provideCountAndBonus() {
        return Stream.of(
                new Object[]{0, false, LottoRank.NONE},
                new Object[]{1, false, LottoRank.NONE},
                new Object[]{2, false, LottoRank.NONE},
                new Object[]{3, false, LottoRank.MATCHED_THREE},
                new Object[]{3, true, LottoRank.MATCHED_THREE},
                new Object[]{4, false, LottoRank.MATCHED_FOUR},
                new Object[]{4, true, LottoRank.MATCHED_FOUR},
                new Object[]{5, false, LottoRank.MATCHED_FIVE},
                new Object[]{5, true, LottoRank.MATCHED_FIVE_WITH_BONUS},
                new Object[]{6, false, LottoRank.MATCHED_SIX}
        );
    }

    @DisplayName("일치하는 숫자의 개수와 보너스 볼 여부에 따라 LottoRank 를 반환한다.")
    @ParameterizedTest(name = "Count: {0}, Bonus: {1}, Expected: {2}")
    @MethodSource("provideCountAndBonus")
    void valueOf(int count, boolean bonus, LottoRank expected) {
        assertEquals(expected, LottoRank.valueOf(count, bonus));
    }
}
