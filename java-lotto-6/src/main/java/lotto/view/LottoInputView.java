package lotto.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.common.exception.ExceptionFactory.getCustomException;

import lotto.common.exception.CustomExceptionType;
import lotto.dto.request.PurchaseLottoRequest;

/**
 * 로또 입력 뷰
 */
public class LottoInputView {
    /**
     * 로또 구입 금액을 입력받는다.
     *
     * @return 로또 구입 요청 객체
     */
    public PurchaseLottoRequest inputPurchaseMoney() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            String input = readLine();
            int purchaseMoney = Integer.parseInt(input);
            return new PurchaseLottoRequest(purchaseMoney);
        } catch (IllegalArgumentException e) {
            throw getCustomException(CustomExceptionType.INVALID_NUMBER_INPUT);
        }
    }
}
