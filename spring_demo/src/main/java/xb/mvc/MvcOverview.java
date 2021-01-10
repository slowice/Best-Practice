package xb.mvc;

/**
 * DispatcherServlet通过已注册的HandlerMapping来获取request对应的handler。这个Handler是一个chain对象，包括了拦截器和handler本身。
 * 然后通过遍历已注册的adapter，来获取支持该handler的adapter，然后调用adapter的handle方法来处理request并范围的ModelAndView对象。
 * 这个handle方法时获取请求的信息后，通过反射交由实际的业务方法处理。

 * HandlerMapping:负责将url匹配到对应的controller
 *  常用的有2个，一个BeanNameUrlHandlerMapping（通过名称匹配，默认使用这个），一个RequestMappingHandlerMapping（通过）。
 * HandlerAdapter:
 */
public class MvcOverview {
}
