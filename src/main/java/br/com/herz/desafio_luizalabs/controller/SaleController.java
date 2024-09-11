package br.com.herz.desafio_luizalabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.herz.desafio_luizalabs.exception.InvalidFileException;
import br.com.herz.desafio_luizalabs.exception.InvalidHeaderException;
import br.com.herz.desafio_luizalabs.service.SaleService;

@Controller
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            var total = service.uploadSales(file);
            model.addAttribute("totalRevenue", total);
        } catch (Exception e) {
            var errorMessage = "Não foi possível processar o arquivo.";
            
            if (!(e instanceof InvalidHeaderException || e instanceof InvalidFileException)) {
                errorMessage = errorMessage + " Ocorreu um erro inesperado.";
            }

            redirectAttributes.addFlashAttribute("error", errorMessage + " Motivo: " + e.getMessage());

            return "redirect:/upload";
        }
        return "upload";
    }

}
