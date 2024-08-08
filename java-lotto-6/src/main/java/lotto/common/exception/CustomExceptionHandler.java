package lotto.common.exception;

import lotto.common.exception.classes.CustomException;

/**
 * 커스텀 예외를 처리하는 클래스
 * <p>
 * 예외를 전달받아 콘솔에 출력하는 기능을 담당한다.
 */
public class CustomExceptionHandler {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public static void handleException(CustomException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }
}
