package baseball.server.framework;

import baseball.protocol.request.ClientRequest;
import baseball.protocol.response.ServerResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * 컨트롤러 메서드를 매핑하는 클래스
 */
public class ControllerMethodMapper {

  /**
   * 컨트롤러 메서드를 매핑한다.
   *
   * @param controllers 컨트롤러 목록
   * @return 컨트롤러 메서드 맵
   */
  public static Map<String, Function<ClientRequest<?>, ServerResponse<?>>> mapMethods(
      final Set<Controller> controllers) {

    // 메서드 맵 생성
    final Map<String, Function<ClientRequest<?>, ServerResponse<?>>> methodMap = new HashMap<>();

    // 컨트롤러 별로 메서드 맵을 생성하여 합친다
    controllers.forEach(controller -> {
      methodMap.putAll(getControllerMethodMap(controller));
    });

    return methodMap;
  }

  /**
   * 컨트롤러의 메서드 맵을 생성한다.
   * <p>
   * Controller 클래스의 메서드 중 ClientEndpoint 어노테이션이 붙은 메서드를 찾아 엔드포인트와 매핑한다.
   *
   * @param controller 컨트롤러
   * @return 컨트롤러의 메서드 맵
   */
  private static Map<String, Function<ClientRequest<?>, ServerResponse<?>>> getControllerMethodMap(
      final Controller controller) {

    // 메서드 맵 생성
    final Map<String, Function<ClientRequest<?>, ServerResponse<?>>> controllerMethodMap = new HashMap<>();

    // ClientEndpoint 어노테이션이 붙은 메서드를 찾아 엔드포인트와 매핑한다
    final Method[] methods = filterClientEndpointMethods(controller);

    // 메서드 맵에 추가
    for (final Method method : methods) {
      final String endpoint = getEndpoint(method);
      final Function<ClientRequest<?>, ServerResponse<?>> function = createFunction(controller,
          method);
      controllerMethodMap.put(endpoint, function);
    }

    return controllerMethodMap;
  }

  /**
   * 컨트롤러의 ClientEndpoint 어노테이션이 붙은 메서드를 필터링한다.
   *
   * @param controller 컨트롤러
   * @return ClientEndpoint 어노테이션이 붙은 메서드 배열
   */
  private static Method[] filterClientEndpointMethods(final Controller controller) {
    return Arrays.stream(controller.getClass().getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(ClientEndpoint.class))
        .toArray(Method[]::new);
  }

  /**
   * 메서드의 엔드포인트를 반환한다.
   * <p>
   * ClientEndpoint 어노테이션의 endPoint 필드를 반환한다.
   *
   * @param method 메서드
   * @return 엔드포인트
   */
  private static String getEndpoint(final Method method) {
    return method.getAnnotation(ClientEndpoint.class).endPoint();
  }

  /**
   * 컨트롤러의 메서드를 처리하는 함수를 생성한다.
   *
   * @param controller 컨트롤러
   * @param method     메서드
   * @return 컨트롤러의 메서드를 처리하는 함수
   */
  private static Function<ClientRequest<?>, ServerResponse<?>> createFunction(
      final Controller controller,
      final Method method) {

    return (request) -> {
      try {
        final Parameter[] parameters = method.getParameters(); // 메서드의 파라미터
        final Object[] args = resolveArguments(parameters, request); // 메서드의 파라미터에 맞게 매개변수를 생성
        return (ServerResponse<?>) invokeMethod(controller, method, args); // 메서드 호출
      } catch (final InvocationTargetException e) { // 메서드 호출 중 예외 발생
        throw handleInvocationException(e); // 예외 처리
      } catch (final IllegalAccessException | IllegalArgumentException e) {
        throw new RuntimeException(e);
      }
    };
  }

  /**
   * 메서드의 파라미터에 맞게 매개변수를 생성한다.
   *
   * @param parameters 메서드의 파라미터
   * @param request    클라이언트 요청
   * @return 메서드의 파라미터에 맞게 생성된 매개변수 배열
   */
  private static Object[] resolveArguments(
      final Parameter[] parameters,
      final ClientRequest<?> request) {

    // 메서드의 파라미터가 1개이고, ClientRequest 타입이거나 ClientRequest 의 데이터 타입과 같은 경우
    if (parameters.length == 1) {
      Class<?> paramType = parameters[0].getType(); // 메서드의 파라미터 타입

      // ClientRequest 타입인 경우
      if (paramType.equals(ClientRequest.class)) {
        return new Object[]{request}; // 클라이언트 요청을 매개변수로 사용
      }
      // ClientRequest 의 data 의 타입과 같은 경우
      else if (paramType.isAssignableFrom(request.getData().getClass())) {
        return new Object[]{request.getData()}; // 클라이언트 요청의 데이터를 매개변수로 사용
      }
    }
    return new Object[0]; // 매개변수가 없는 경우
  }

  /**
   * Controller 의 메서드를 호출한다.
   *
   * @param controller Controller
   * @param method     메서드
   * @param args       매개변수
   * @return 메서드의 반환값
   * @throws InvocationTargetException 메서드 호출 중 예외 발생
   * @throws IllegalAccessException    메서드 호출 권한이 없는 경우
   */
  private static Object invokeMethod(
      final Controller controller,
      final Method method,
      final Object... args) throws InvocationTargetException, IllegalAccessException {

    return method.invoke(controller, args);
  }

  /**
   * InvocationTargetException 을 처리한다.
   * <p>
   * InvocationTargetException 의 원인이 RuntimeException 인 경우 발생한 예외 클래스로 변환하여 반환한다.
   *
   * @param e InvocationTargetException
   * @return RuntimeException
   */
  private static RuntimeException handleInvocationException(final InvocationTargetException e) {

    final Throwable cause = e.getCause();
    if (cause instanceof RuntimeException) {
      return (RuntimeException) cause;
    } else {
      return new RuntimeException(cause);
    }
  }
}
