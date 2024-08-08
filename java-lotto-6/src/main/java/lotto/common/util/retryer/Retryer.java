package lotto.common.util.retryer;

import static lotto.common.exception.CustomExceptionHandler.handleException;

import lotto.common.exception.classes.CustomException;

/**
 * 예외 처리를 위한 Retryer 클래스
 * <p>
 * 함수를 받아 예외 처리를 수행하고, 예외가 발생하면 재시도하는 기능을 제공한다.
 */
public class Retryer {

    /**
     * 예외 처리를 위한 메서드 SupplierWithException 를 통해 예외 처리를 수행한다. 반환 값이 있는 경우 사용한다.
     *
     * @param supplier 예외 처리를 수행할 함수형 인터페이스
     * @return 예외 처리 결과
     */
    public static <T> T repeatUntilSuccess(SupplierWithException<T> supplier) {
        try {
            return supplier.get();
        } catch (CustomException e) {
            handleException(e);
            return repeatUntilSuccess(supplier);
        }
    }

    /**
     * 예외 처리를 위한 메서드 RunnableWithException 를 통해 예외 처리를 수행한다. 반환 값이 없는 경우 사용한다.
     *
     * @param runnable 예외 처리를 수행할 함수형 인터페이스
     */
    public static void repeatUntilSuccess(RunnableWithException runnable) {
        try {
            runnable.run();
        } catch (CustomException e) {
            handleException(e);
            repeatUntilSuccess(runnable);
        }
    }

    /**
     * 예외 처리를 위한 함수형 인터페이스 반환 값이 있는 경우 사용한다.
     *
     * @param <T> 반환 값의 타입
     */
    @FunctionalInterface
    public interface SupplierWithException<T> {
        T get() throws CustomException;
    }

    /**
     * 예외 처리를 위한 함수형 인터페이스 반환 값이 없는 경우 사용한다.
     */
    @FunctionalInterface
    public interface RunnableWithException {
        void run() throws CustomException;
    }
}
