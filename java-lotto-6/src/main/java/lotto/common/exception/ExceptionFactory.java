package lotto.common.exception;

import lotto.common.exception.classes.CustomException;
import lotto.common.exception.classes.CustomExceptionCreationFailedException;

/**
 * 커스텀 예외를 생성하는 클래스
 * <p>
 * 커스텀 예외 타입에 따라 커스텀 예외 객체를 생성한다.
 */
public class ExceptionFactory {

    /**
     * 커스텀 예외 타입에 따라 커스텀 예외 객체를 생성한다.
     *
     * @param type 커스텀 예외 타입
     * @return 커스텀 예외 객체
     */
    public static CustomException getCustomException(CustomExceptionType type) {
        // CustomExceptionType에 정의된 예외 클래스 추출
        Class<? extends CustomException> exceptionClass = type.getExceptionClass();

        try {
            // 예외 클래스의 기본 생성자로 객체 생성
            return exceptionClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // 예외 객체 생성 실패 시 CustomExceptionCreationFailedException 예외 발생
            throw new CustomExceptionCreationFailedException(e);
        }
    }
}
