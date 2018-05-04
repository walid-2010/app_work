/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.security.filters;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AuthenticationFilter extends SecurityFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        String path = ((HttpServletRequest) req).getServletPath();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding("UTF-8");
        boolean isloginRequest = request.getRequestURI().startsWith(request.getContextPath() + "/login.xhtml");
        boolean isResourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

        if (excludeFromFilter(path)) {
            chain.doFilter(req, res);
            return;
        }

        if ((userManager != null && userManager.isLoggedIn())
                || isloginRequest
                || isResourceRequest) {
            chain.doFilter(req, res);
            return;
        } else {
            String loginURL = request.getContextPath() + "/login.xhtml";
            response.sendRedirect(loginURL);
        }

    }

    @Override
    public void destroy() {

    }

}
