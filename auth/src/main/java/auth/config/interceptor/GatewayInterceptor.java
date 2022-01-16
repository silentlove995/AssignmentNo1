package auth.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author datdv
 */
@Service
@Slf4j
public class GatewayInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        request.setAttribute(GatewayConstant.START_PROCESSING_TIME, System.currentTimeMillis());
        String sessionId = createSessionId();
        ThreadContext.put(GatewayConstant.CORRELATION_ID_HEADER, sessionId);
        log.info("========== Start process request [{}]:[{}] ==========", request.getMethod(), request.getServletPath());
        return true;
    }

    /**
     * Logging all successfully request here, if request throw exception then it's logged in exceptionTranslator
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) {
        Long startProcessingTime = (Long) request.getAttribute(GatewayConstant.START_PROCESSING_TIME);
        Long endProcessingTime = System.currentTimeMillis();
        log.info("========== End process request [{}]:[{}] with [{}]. Processing time: [{}] milliseconds ==========", request.getMethod(), request.getServletPath(), response.getStatus(), endProcessingTime - startProcessingTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private String createSessionId() {
        String sessionToken = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        return sessionToken;
    }
}
