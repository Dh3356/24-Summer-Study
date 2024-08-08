package lotto.controller;

import lotto.dto.request.PurchaseLottoRequest;
import lotto.dto.response.PurchaseLottoResponse;
import lotto.service.LottoService;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public PurchaseLottoResponse purchaseLotto(PurchaseLottoRequest purchaseLottoRequest) {
        return lottoService.purchaseLottos(purchaseLottoRequest);
    }
}
