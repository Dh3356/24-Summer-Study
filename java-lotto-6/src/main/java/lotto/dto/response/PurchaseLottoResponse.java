package lotto.dto.response;

import java.util.List;

/**
 * 로또 구매 응답 DTO
 *
 * @see LottoResponse
 */
public class PurchaseLottoResponse {
    List<LottoResponse> lottoResponses;

    public PurchaseLottoResponse(List<LottoResponse> lottoResponses) {
        this.lottoResponses = lottoResponses;
    }

    public List<LottoResponse> getLottoResponses() {
        return lottoResponses;
    }
}
