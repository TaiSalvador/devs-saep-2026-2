package com.telaDeLogin.senai.controller;

import com.telaDeLogin.senai.dtos.UsuarioDto;
import com.telaDeLogin.senai.services.UsuarioService;
import com.telaDeLogin.senai.sessao.SessaoDto;
import com.telaDeLogin.senai.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String realizarLogin(@RequestParam String email,
                                @RequestParam String senha,
                                Model model,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {

        System.out.println("email =" + email + " senha = " + senha);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail(email);
        usuarioDto.setSenha(senha);

        UsuarioDto usuarioDtoRetorno = service.realizarLogin(usuarioDto);

        if (usuarioDtoRetorno.getNome() != null) {

            SessaoDto sessaoDto = new SessaoDto();

            sessaoDto.setId(usuarioDtoRetorno.getId());
            sessaoDto.setNome(usuarioDtoRetorno.getNome());
            sessaoDto.setEmail(usuarioDtoRetorno.getEmail());

            session.setAttribute("usuarioLogado", sessaoDto);

            redirectAttributes.addFlashAttribute(
                    "mensagem",
                    "Bem-Vindo, " + usuarioDtoRetorno.getNome()
            );

            return "redirect:/home";
        }

        model.addAttribute("erro", "E-mail ou senha inválido.");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        SessaoUtil.RemoverSessao(session);
        return "redirect:/login";
    }
}
