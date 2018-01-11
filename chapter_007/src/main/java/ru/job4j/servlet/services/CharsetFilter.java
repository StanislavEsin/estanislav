package ru.job4j.servlet.services;

import javax.servlet.*;
import java.io.IOException;

/**
 * CharsetFilter.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.01.2018
 */
public class CharsetFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        encoding = encoding == null ? "UTF-8" : encoding;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding(encoding);

        next.doFilter(request, response);
    }
}