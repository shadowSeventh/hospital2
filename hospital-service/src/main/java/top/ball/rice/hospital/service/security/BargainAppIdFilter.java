//package top.ball.rice.hospital.service.security;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
//
//public class BargainAppIdFilter extends OncePerRequestFilter
//        implements InitializingBean {
//
//    final String BARGAINAPP_ID_REQUEST_ATTR = getClass().getName() + ".BARGAINAPP_ID_REQUEST_ATTR";
//
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//
//        String url = request.getPathInfo();
//
//        String pattern = "/bargainApp/{bargainAppId}/**";
//        Map<String, String> bargainAppMap = new LinkedHashMap<>();
//
//        try {
//            bargainAppMap = pathMatcher.extractUriTemplateVariables(pattern, url);
//        } catch (Exception e) {
////            e.printStackTrace()
//        }
//
//        if (!bargainAppMap.isEmpty()) {
//
//            RequestContextHolder.getRequestAttributes().setAttribute(
//                    BARGAINAPP_ID_REQUEST_ATTR,
//                    bargainAppMap.get("bargainAppId"),
//                    SCOPE_REQUEST
//            );
//
//        }
//        filterChain.doFilter(request, response);
//
//    }
//
//    @Override
//    public void afterPropertiesSet() {
//
//    }
//
//    /**
//     * 获取bargain。
//     *
//     * @return bargainID
//     */
//    public String getBargainAppId() {
//        return (String) RequestContextHolder
//                .getRequestAttributes()
//                .getAttribute(BARGAINAPP_ID_REQUEST_ATTR, SCOPE_REQUEST);
//    }
//
//}
