package br.csi.trilhagaucha.service;


import br.csi.trilhagaucha.model.usuario.Usuario;
import br.csi.trilhagaucha.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario login(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> passwordEncoder.matches(senha, usuario.getSenha()))
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));
    }

    public void salvar(Usuario usuario) {this.usuarioRepository.save(usuario);}

    public List<Usuario> getUsuarios() {return usuarioRepository.findAll();}

    public Usuario getUsuario(Long id) {return usuarioRepository.findById(id).get();}

    public void atualizar(Usuario usuario) {
        Usuario usuarioAtual = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioAtual.setNome(usuario.getNome());
        usuarioAtual.setEmail(usuario.getEmail());

        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioAtual.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        usuarioRepository.save(usuarioAtual);
    }

    public Usuario cadastrar(@Valid Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        usuarioRepository.delete(usuario);
    }

    public void excluirUUID(String uuid) {
        usuarioRepository.deleteUsuarioByUuid(java.util.UUID.fromString(uuid));
    }

    public void atualizarUUID(Usuario usuario) {
        Usuario usuarioAtual = usuarioRepository.findByUuid(usuario.getUuid())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioAtual.setNome(usuario.getNome());
        usuarioAtual.setEmail(usuario.getEmail());

        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioAtual.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        usuarioRepository.save(usuarioAtual);
    }

    public Usuario getUsuarioUUID(UUID uuid) {
        return usuarioRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
