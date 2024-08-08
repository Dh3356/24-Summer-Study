package lotto.common.config;

import lotto.common.util.calculator.LottoWinningCalculator;
import lotto.common.util.generator.LottoNumberGenerator;
import lotto.controller.LottoController;
import lotto.controller.WinningLottoController;
import lotto.service.LottoService;
import lotto.service.WinningLottoService;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;
import lotto.view.WinningLottoInputView;
import lotto.view.WinningLottoOutputView;

/**
 * 애플리케이션 설정
 */
public class ApplicationConfig {
    // Singleton 패턴을 적용하여 객체를 생성한다.
    // 생성자 주입 방식을 사용하여 의존성을 주입한다.
    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
    private final LottoWinningCalculator lottoWinningCalculator = new LottoWinningCalculator();
    private final LottoService lottoService = new LottoService(lottoNumberGenerator);
    private final LottoController lottoController = new LottoController(lottoService);
    private final WinningLottoService winningLottoService = new WinningLottoService(lottoWinningCalculator);
    private final WinningLottoController winningLottoController = new WinningLottoController(winningLottoService);
    private final LottoInputView lottoInputView = new LottoInputView();
    private final LottoOutputView lottoOutputView = new LottoOutputView();
    private final WinningLottoInputView winningLottoInputView = new WinningLottoInputView();
    private final WinningLottoOutputView winningLottoOutputView = new WinningLottoOutputView();

    public LottoInputView lottoInputView() {
        return lottoInputView;
    }

    public LottoOutputView lottoOutputView() {
        return lottoOutputView;
    }

    public WinningLottoInputView winningLottoInputView() {
        return winningLottoInputView;
    }

    public WinningLottoOutputView winningLottoOutputView() {
        return winningLottoOutputView;
    }

    public LottoService lottoService() {
        return lottoService;
    }

    public WinningLottoService winningLottoService() {
        return winningLottoService;
    }

    public LottoController lottoController() {
        return lottoController;
    }

    public WinningLottoController winningLottoController() {
        return winningLottoController;
    }
}

