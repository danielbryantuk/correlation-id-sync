package uk.co.taidev.experiments.correlationidsync.web.filters;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.taidev.experiments.correlationidsync.reporting.RequestCorrelation;

/**
 * CorrelationHeaderFilter
 */
public class CorrelationHeaderFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorrelationHeaderFilter.class);


    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String currentCorrId = httpServletRequest.getHeader(RequestCorrelation.CORRELATION_ID_HEADER);

        if (currentCorrId == null) {
            currentCorrId = UUID.randomUUID().toString();
            LOGGER.info("No correlationId found in Header. Generated : " + currentCorrId);
        } else {
            LOGGER.info("Found correlationId in Header : " + currentCorrId);
        }

        RequestCorrelation.setId(currentCorrId);

        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader(RequestCorrelation.CORRELATION_ID_HEADER, RequestCorrelation.getId());

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    @Override
    public void destroy() {
    }

}
