package lotto.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.common.constant.ErrorMessageConstants.INVALID_INPUT_NUMBER;

import java.util.List;
import java.util.stream.Stream;
import lotto.dto.request.WinningLottoRequest;

/**
 * 당첨 로또 입력 뷰
 */
public class WinningLottoInputView {

    /**
     * 당첨 로또 번호와 보너스 번호를 입력받는다.
     *
     * @return 당첨 로또 생성 요청 객체
     */
    public WinningLottoRequest inputWinningLotto() {
        try {
            System.out.println("당첨 번호를 입력해 주세요.");
            List<Integer> winningLotto = Stream.of(readLine().split(","))
                    .map(Integer::parseInt)
                    .toList();

            System.out.println("보너스 번호를 입력해 주세요.");
            int bonusNumber = Integer.parseInt(readLine());

            return new WinningLottoRequest(winningLotto, bonusNumber);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_INPUT_NUMBER);
        }
    }
}
