package lotto;

import static lotto.common.util.retryer.Retryer.repeatUntilSuccess;

import java.util.List;
import lotto.common.config.ApplicationConfig;
import lotto.controller.LottoController;
import lotto.controller.WinningLottoController;
import lotto.dto.request.CalculateLottoRequest;
import lotto.dto.request.LottoRequest;
import lotto.dto.request.PurchaseLottoRequest;
import lotto.dto.request.WinningLottoRequest;
import lotto.dto.response.CalculateLottoResponse;
import lotto.dto.response.PurchaseLottoResponse;
import lotto.dto.response.WinningLottoResponse;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;
import lotto.view.WinningLottoInputView;
import lotto.view.WinningLottoOutputView;

public class Application {
    // ApplicationConfig 를 통해 필요한 객체들을 얻는다.
    private static final ApplicationConfig applicationConfig = new ApplicationConfig();

    // Controllers
    private static final LottoController lottoController = applicationConfig.lottoController();
    private static final WinningLottoController winningLottoController = applicationConfig.winningLottoController();

    // Input Views
    private static final LottoInputView lottoInputView = applicationConfig.lottoInputView();
    private static final LottoOutputView lottoOutputView = applicationConfig.lottoOutputView();

    // Output Views
    private static final WinningLottoInputView winningLottoInputView = applicationConfig.winningLottoInputView();
    private static final WinningLottoOutputView winningLottoOutputView = applicationConfig.winningLottoOutputView();

    /**
     * 애플리케이션 실행
     */
    public static void main(String[] args) {
        repeatUntilSuccess(Application::run);
    }


    /**
     * 로또 구매
     *
     * @return 로또 구매 응답
     */
    private static PurchaseLottoResponse purchaseLotto() {
        // 로또 구매 요청을 입력 받는다.
        PurchaseLottoRequest purchaseLottoRequest = lottoInputView.inputPurchaseMoney();

        // 로또 구매 요청을 통해 로또 구매 응답을 얻는다.
        PurchaseLottoResponse purchaseLottoResponse = lottoController.purchaseLotto(purchaseLottoRequest);

        // 로또 구매 응답을 출력한다.
        lottoOutputView.printLottoPurchaseResult(purchaseLottoResponse);

        // 로또 구매 응답을 반환한다.
        return purchaseLottoResponse;
    }

    /**
     * 당첨 로또 생성
     *
     * @return 당첨 로또 응답
     */
    private static WinningLottoResponse createWinningLotto() {

        // 당첨 로또를 입력 받는다.
        WinningLottoRequest winningLottoRequest = winningLottoInputView.inputWinningLotto();

        // 당첨 로또를 생성한다.
        WinningLottoResponse winningLottoResponse = winningLottoController.createWinningLotto(winningLottoRequest);

        // 당첨 로또 응답을 반환한다.
        return winningLottoResponse;
    }

    /**
     * 로또 결과 계산을 위한 요청을 준비한다.
     *
     * @param purchaseLottoResponse 로또 구매 응답
     * @param winningLottoResponse  당첨 로또 응답
     * @return 로또 계산 요청
     */
    private static CalculateLottoRequest prepareCalculateLottoRequest(PurchaseLottoResponse purchaseLottoResponse,
                                                                      WinningLottoResponse winningLottoResponse) {
        // 로또 구매 응답에서 사용자 로또 요청 List 를 추출한다.
        List<LottoRequest> lottoRequests = purchaseLottoResponse.getLottoResponses().stream()
                .map(lottoResponse -> new LottoRequest(lottoResponse.getNumbers()))
                .toList();

        // 당첨 로또 응답을 통해 당첨 로또 요청 객체를 생성한다.
        WinningLottoRequest winningLottoRequest = new WinningLottoRequest(winningLottoResponse.getNumbers(),
                winningLottoResponse.getBonusNumber());

        // 사용자 로또 요청 List 와 당첨 로또 요청 객체를 통해 로또 계산 요청 객체를 생성한다.
        return new CalculateLottoRequest(winningLottoRequest, lottoRequests);
    }

    /**
     * 로또 결과 출력
     *
     * @param calculateLottoRequest 로또 계산 요청
     */
    private static void printResult(CalculateLottoRequest calculateLottoRequest) {
        CalculateLottoResponse calculateLottoResponse = winningLottoController.calculateLotto(calculateLottoRequest);
        winningLottoOutputView.printWinningResult(calculateLottoResponse);
    }

    private static void run() {
        // 로또 구매
        PurchaseLottoResponse purchaseLottoResponse = repeatUntilSuccess(Application::purchaseLotto);

        // 당첨 로또 생성
        WinningLottoResponse winningLottoResponse = repeatUntilSuccess(Application::createWinningLotto);

        // 로또 결과 계산
        CalculateLottoRequest calculateLottoRequest = repeatUntilSuccess(() ->
                prepareCalculateLottoRequest(purchaseLottoResponse, winningLottoResponse)
        );

        // 결과 출력
        repeatUntilSuccess(() ->
                printResult(calculateLottoRequest)
        );
    }

}

