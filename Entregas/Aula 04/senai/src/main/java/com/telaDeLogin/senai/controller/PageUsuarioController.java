package com.telaDeLogin.senai.controller;

import com.telaDeLogin.senai.services.UsuarioService;
import com.telaDeLogin.senai.sessao.SessaoDto;
import com.telaDeLogin.senai.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageUsuarioController {

    final UsuarioService service;

    public PageUsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLogin() {

        return "login";
    }

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/login";
    }


    @GetMapping("/home")
    public String getHome(HttpSession session,
                          Model model) {
        SessaoDto sessaoDto = SessaoUtil.ObterSessao(session);

        if (sessaoDto == null){
            return "redirect:/login";
        }

        model.addAttribute("usuarioLogado", sessaoDto);
        return "home";
    }

}
