package lotto.controller;

import lotto.dto.request.CalculateLottoRequest;
import lotto.dto.request.WinningLottoRequest;
import lotto.dto.response.CalculateLottoResponse;
import lotto.dto.response.WinningLottoResponse;
import lotto.service.WinningLottoService;

public class WinningLottoController {
    private final WinningLottoService winningLottoService;

    public WinningLottoController(WinningLottoService winningLottoService) {
        this.winningLottoService = winningLottoService;
    }

    public WinningLottoResponse createWinningLotto(WinningLottoRequest winningLottoRequest) {
        return winningLottoService.createWinningLotto(winningLottoRequest);
    }

    public CalculateLottoResponse calculateLotto(CalculateLottoRequest calculateLottoRequest) {
        return winningLottoService.calculateWinningLotto(calculateLottoRequest);
    }
}
