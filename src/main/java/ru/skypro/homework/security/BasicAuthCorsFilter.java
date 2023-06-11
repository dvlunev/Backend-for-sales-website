package ru.skypro.homework.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class is a component and a filter for handling requests.
 *   * It extends from the OncePerRequestFilter class and implements one doFilterInternal() method.
 *   * It is used to provide access to resources from other sources (CORS) and authorization.
 *
 * @see OncePerRequestFilter
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse,
                                 FilterChain filterChain)
            throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
