package br.csi.trilhagaucha.config;

import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class AdminCheckFilter extends OncePerRequestFilter {

    private final UsuarioRepository usuarioRepository;

    public AdminCheckFilter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/usuarios")) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Basic ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado");
                return;
            }

            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] parts = credentials.split(":", 2);
            String email = parts[0];
            String senha = parts[1];

            Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
            if (usuario == null || !usuario.getIsAdmin()) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso negado");
                return;
            }


            SecurityContextHolder.getContext().setAuthentication(new Authentication() {
                @Override public String getName() { return email; }
                @Override public Object getCredentials() { return senha; }
                @Override public Object getDetails() { return null; }
                @Override public Object getPrincipal() { return usuario; }
                @Override public boolean isAuthenticated() { return true; }
                @Override public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}
                @Override public java.util.Collection<org.springframework.security.core.GrantedAuthority> getAuthorities() { return java.util.List.of(); }
            });
        }

        filterChain.doFilter(request, response);
    }
}
