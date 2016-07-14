/**
 * 
 */
package uk.co.taidev.experiments.correlationidsync.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import uk.co.taidev.experiments.correlationidsync.reporting.RequestCorrelation;

/**
 * @author muhammad.tasleem
 *
 */
public class MDCFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MDCFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest httpServletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		LOGGER.info("Adding " + RequestCorrelation.getId() + " as " + RequestCorrelation.CORRELATION_ID_HEADER + " to MDC");		

		MDC.put(RequestCorrelation.CORRELATION_ID_HEADER, RequestCorrelation.getId());
		filterChain.doFilter(httpServletRequest, servletResponse);
	}

	@Override
	public void destroy() {
    	LOGGER.info("Unregistering MDCFilter filter");
	}

}