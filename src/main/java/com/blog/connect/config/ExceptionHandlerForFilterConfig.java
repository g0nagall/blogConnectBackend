package com.blog.connect.config;

import com.blog.connect.web.exceptions.exceptions.AbstractOpenMindException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

/** Exception handler class for filters. */
@Component
public class ExceptionHandlerForFilterConfig extends OncePerRequestFilter {

  @Autowired
  @Qualifier("handlerExceptionResolver")
  private HandlerExceptionResolver resolver;

  /** Method to resolve exception from filters using existing handler. */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      filterChain.doFilter(request, response);
    } catch (AbstractOpenMindException e) {
      resolver.resolveException(request, response, null, e);
    }
  }
}
