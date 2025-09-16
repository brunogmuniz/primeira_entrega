package br.csi.trilhagaucha.service;


import br.csi.trilhagaucha.model.Usuario;
import br.csi.trilhagaucha.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {this.usuarioRepository = usuarioRepository;}

    public Usuario login(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha))
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));
    }

    public void salvar(Usuario usuario) {this.usuarioRepository.save(usuario);}

    public List<Usuario> getUsuarios() {return usuarioRepository.findAll();}

    public Usuario getUsuario(Long id) {return usuarioRepository.findById(id).get();}

    public void atualizar(Usuario usuario) {
        Usuario usuarioAtual = usuarioRepository.findById(usuario.getId()).get();
        usuarioAtual.setNome(usuario.getNome());
        usuarioAtual.setEmail(usuario.getEmail());
        usuarioAtual.setSenha(usuario.getSenha());
        usuarioRepository.save(usuarioAtual);
    }
}
