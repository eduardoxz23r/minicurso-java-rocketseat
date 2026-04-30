package com.carlos.minicursorocketseatjava.Filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.carlos.minicursorocketseatjava.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;


@Component  // Registra o filtro no Spring automaticamente
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;  // acessa o banco pra buscar usuário

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {

            var authorization = request.getHeader("Authorization");


            var authEncoded = authorization.substring("Basic".length()).trim();


            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String username = credentials[0]; // "carlos"
            String password = credentials[1]; // "1234"

            // Busca o usuário no banco
            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                // usuário não existe → bloqueia
                response.sendError(401);
            } else {
                var passwordVerify = BCrypt.verifyer()
                        .verify(password.toCharArray(), user.getPassword());

                if (passwordVerify.verified) {
                    // senha correta → deixa passar
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    // senha errada → bloqueia
                    response.sendError(401);
                }
            }

        } else {
            // outras rotas → deixa passar sem autenticação
            filterChain.doFilter(request, response);
        }
    }
}