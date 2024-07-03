package lotto.common.constant;

import static lotto.common.constant.LottoConstants.LOTTO_BONUS_MAX;
import static lotto.common.constant.LottoConstants.LOTTO_BONUS_MIN;
import static lotto.common.constant.LottoConstants.LOTTO_MAX;
import static lotto.common.constant.LottoConstants.LOTTO_MIN;
import static lotto.common.constant.LottoConstants.LOTTO_PRICE;
import static lotto.common.constant.LottoConstants.LOTTO_SIZE;

public class ErrorMessageConstants {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public static final String INVALID_LOTTO_NUMBER_RANGE =
            ERROR_PREFIX + "로또 번호는 " + LOTTO_MIN + "부터 " + LOTTO_MAX + " 사이의 숫자여야 합니다.";

    public static final String INVALID_LOTTO_NUMBER_SIZE =
            ERROR_PREFIX + "로또 번호는 " + LOTTO_SIZE + "개여야 합니다.";

    public static final String INVALID_LOTTO_NUMBER_DUPLICATE =
            ERROR_PREFIX + "로또 번호는 중복되지 않아야 합니다.";

    public static final String INVALID_LOTTO_MONEY_TOO_SMALL =
            ERROR_PREFIX + "로또 구매 금액은 최소 " + LOTTO_PRICE + "원 이상이어야 합니다.";

    public static final String INVALID_LOTTO_MONEY_MULTIPLE =
            ERROR_PREFIX + "로또 구매 금액은 " + LOTTO_PRICE + "원 단위로만 가능합니다.";

    public static final String INVALID_BONUS_NUMBER_RANGE =
            ERROR_PREFIX + "보너스 번호는 " + LOTTO_BONUS_MIN + "부터 " + LOTTO_BONUS_MAX + " 사이의 숫자여야 합니다.";

    public static final String INVALID_BONUS_NUMBER_CONFLICT =
            ERROR_PREFIX + "보너스 번호는 당첨 번호와 중복되면 안됩니다.";

    public static final String INVALID_INPUT_NUMBER =
            ERROR_PREFIX + "입력한 숫자가 올바르지 않습니다.";
}
