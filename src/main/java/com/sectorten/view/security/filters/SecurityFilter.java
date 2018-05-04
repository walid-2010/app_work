/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.security.filters;

import com.sectorten.view.backing.Security.UserManager;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class SecurityFilter implements Filter {

    @Inject
    protected UserManager userManager;

    protected boolean excludeFromFilter(String path) {
        return path.startsWith(ResourceHandler.RESOURCE_IDENTIFIER)
                || path.startsWith("/loginError.xhtml")
                || path.startsWith("/Security.html")
                || path.contains("webresources");
    }

    protected void goToAccessDenied(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/Security.html");
    }
}
