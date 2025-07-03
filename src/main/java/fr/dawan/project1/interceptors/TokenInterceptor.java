package fr.dawan.project1.interceptors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*if(!request.getMethod().equals("OPTIONS")) {
            if (!request.getRequestURI().equals("/authenticate")
                    || !request.getRequestURI().equals("/reset-password")) {
                String authorization = request.getHeader("Authorization");

                //extraire le token

                //vérifier sa validité et sa signature

                //ajouter d'autres vérifications (rôle/droits d'accès)
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
        }*/

        return true;
    }
}
