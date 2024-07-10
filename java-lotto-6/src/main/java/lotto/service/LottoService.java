package lotto.service;

import static lotto.common.constant.LottoConstants.LOTTO_PRICE;
import static lotto.common.constant.NumberConstants.ZERO;
import static lotto.common.exception.ExceptionFactory.getCustomException;

import java.util.LinkedList;
import java.util.List;
import lotto.common.exception.CustomExceptionType;
import lotto.common.util.generator.LottoNumberGenerator;
import lotto.domain.Lotto;
import lotto.dto.request.PurchaseLottoRequest;
import lotto.dto.response.LottoResponse;
import lotto.dto.response.PurchaseLottoResponse;

/**
 * 로또 구매 서비스
 */
public class LottoService {
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoService(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    /**
     * 로또를 구매한다.
     *
     * @param request 로또 구매 요청 DTO
     * @return 로또 구매 응답 DTO
     */
    public PurchaseLottoResponse purchaseLottos(PurchaseLottoRequest request) {
        int money = request.getMoney();
        validateMoney(money);

        List<Lotto> lottos = generateLottos(money);

        List<LottoResponse> lottoResponses = lottos.stream()
                .map(LottoResponse::of)
                .toList();

        return new PurchaseLottoResponse(lottoResponses);
    }

    /**
     * 로또 List 를 생성한다.
     *
     * @param money 로또 구매 금액
     * @return 로또 List
     */
    private List<Lotto> generateLottos(int money) {
        int count = money / LOTTO_PRICE;

        List<Lotto> lottos = new LinkedList<>();

        for (int i = ZERO; i < count; i++) {
            lottos.add(createLotto());
        }

        return lottos;
    }

    /**
     * 로또 구매 금액이 로또 가격의 배수인지 확인한다.
     *
     * @param money 로또 구매 금액
     */
    private void validateMoney(int money) {
        validateMoneyRange(money);
        validateMoneyMultiple(money);
    }

    /**
     * 로또 구매 금액의 범위를 확인한다.
     *
     * @param money 로또 구매 금액
     */
    private void validateMoneyRange(int money) {
        if (money < LOTTO_PRICE) {
            throw getCustomException(CustomExceptionType.LOTTO_MONEY_TOO_SMALL);
        }
    }

    private void validateMoneyMultiple(int money) {
        if (money % LOTTO_PRICE != ZERO) {
            throw getCustomException(CustomExceptionType.LOTTO_PURCHASE_AMOUNT_NOT_MULTIPLE);
        }
    }

    /**
     * 로또를 하나 생성한다.
     *
     * @return 로또
     */
    private Lotto createLotto() {
        List<Integer> numbers = lottoNumberGenerator.generateLottoNumbers();

        return new Lotto(numbers);
    }
}
