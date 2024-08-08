package lotto.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import lotto.common.config.ApplicationConfig;
import lotto.dto.request.PurchaseLottoRequest;
import lotto.dto.response.PurchaseLottoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoServiceTest {
    private final ApplicationConfig applicationConfig = new ApplicationConfig();
    private final LottoService lottoService = applicationConfig.lottoService();

    // 올바른 로또 구매 요청 생성
    private static Stream<PurchaseLottoRequest> provideValidPurchaseLottoRequest() {
        return Stream.of(
                new PurchaseLottoRequest(1000),
                new PurchaseLottoRequest(14000),
                new PurchaseLottoRequest(5000),
                new PurchaseLottoRequest(10000),
                new PurchaseLottoRequest(20000000)
        );
    }

    // 올바르지 않은 로또 구매 요청 생성
    private static Stream<PurchaseLottoRequest> provideInvalidPurchaseLottoRequest() {
        return Stream.of(
                new PurchaseLottoRequest(999),
                new PurchaseLottoRequest(10001),
                new PurchaseLottoRequest(0),
                new PurchaseLottoRequest(-1),
                new PurchaseLottoRequest(-1000)
        );
    }

    @DisplayName("정상적으로 로또를 구매한다.")
    @ParameterizedTest(name = "purchaseLotto: {0}")
    @MethodSource("provideValidPurchaseLottoRequest")
    void purchaseLottos(PurchaseLottoRequest request) {
        PurchaseLottoResponse purchaseLottoResponse = lottoService.purchaseLottos(request);
        assertNotNull(purchaseLottoResponse);
    }

    @DisplayName("로또 구매 시 금액이 정상 범위가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest(name = "purchaseLotto: {0}")
    @MethodSource("provideInvalidPurchaseLottoRequest")
    void purchaseInvalidLottos(PurchaseLottoRequest request) {
        assertThrows(IllegalArgumentException.class, () -> lottoService.purchaseLottos(request));
    }
}
