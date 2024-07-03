package lotto.dto.request;

import java.util.List;

/**
 * 로또 계산 요청 DTO
 *
 * @see WinningLottoRequest
 * @see LottoRequest
 */
public class CalculateLottoRequest {
    private final WinningLottoRequest winningLottoRequest;

    private final List<LottoRequest> userLottoRequests;

    public CalculateLottoRequest(WinningLottoRequest winningLottoRequest, List<LottoRequest> userLottoRequests) {
        this.winningLottoRequest = winningLottoRequest;
        this.userLottoRequests = userLottoRequests;
    }

    public WinningLottoRequest getWinningLottoRequest() {
        return winningLottoRequest;
    }

    public List<LottoRequest> getLottoRequests() {
        return userLottoRequests;
    }
}
