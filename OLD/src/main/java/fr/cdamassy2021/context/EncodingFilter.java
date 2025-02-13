/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cdamassy2021.context;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author plasse
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

  private final static String ENCODING = "UTF-8";

  @Override
  public void init(FilterConfig config) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request,
          ServletResponse response, FilterChain next)
          throws IOException, ServletException {
    // Respect the client-specified character encoding
    // (see HTTP specification section 3.4.1)
    if (null == request.getCharacterEncoding()) {
      request.setCharacterEncoding(ENCODING);
    }
    // Set the default response encoding
    response.setCharacterEncoding(ENCODING);
    next.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }
}
