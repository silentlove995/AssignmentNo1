package auth.config.mvc;

import auth.config.interceptor.GatewayInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author datdv
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private GatewayInterceptor gatewayInterceptor;

    /**
     * final InterceptorRegistry registry
     *
     * @param registry
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(gatewayInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/v2/api-docs", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/**", "/swagger-ui.html"
                        , "/webjars/**", "/csrf", "/error", "/");
    }
}
