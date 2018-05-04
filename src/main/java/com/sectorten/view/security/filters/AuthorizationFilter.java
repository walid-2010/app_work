/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.security.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "authorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter extends SecurityFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        String path = ((HttpServletRequest) req).getServletPath();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding("UTF-8");
        //response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        //response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        if (excludeFromFilter(path)) {
            chain.doFilter(req, res);
            return;
        }

        // if not logged in
        if (userManager == null || !userManager.isLoggedIn()) {
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }

        if (userManager.canAccess(path) || path.startsWith("/index.xhtml")) {
            chain.doFilter(request, response);
            return;
        } else {
            goToAccessDenied(request, response);
            return;
        }
    }

    @Override
    public boolean excludeFromFilter(String path) {
        return super.excludeFromFilter(path)
                || path.startsWith("/login.xhtml")
                || path.contains("resources/img/")
                || path.contains("/Security.html")
                || path.contains("/loginError.xhtml")
                || path.startsWith("/images");// add more page to exclude here
    }

    @Override
    public void destroy() {
    }
}
