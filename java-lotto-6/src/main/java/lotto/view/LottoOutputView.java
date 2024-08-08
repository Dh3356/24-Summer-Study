package lotto.view;

import java.util.List;
import lotto.dto.response.PurchaseLottoResponse;

/**
 * 로또 출력 뷰
 */
public class LottoOutputView {
    /**
     * 로또 구매 응답 출력
     *
     * @param purchaseLottoResponse 로또 구매 응답
     */
    public void printLottoPurchaseResult(PurchaseLottoResponse purchaseLottoResponse) {
        int lottoCount = purchaseLottoResponse.getLottoResponses().size();
        System.out.println(lottoCount + "개를 구매했습니다.");
        purchaseLottoResponse.getLottoResponses().forEach(lottoResponse -> {
            List<Integer> numbers = lottoResponse.getNumbers().stream().sorted().toList();
            System.out.println(numbers);
        });
    }
}
