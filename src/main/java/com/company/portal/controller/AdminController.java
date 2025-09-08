package com.company.portal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/admin")
    public String adminPage(Model model) throws IOException {
        Path dir = Paths.get(uploadDir);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        List<String> files = Files.list(dir)
                .filter(Files::isRegularFile)
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());
        model.addAttribute("files", files);
        return "admin";
    }

    @PostMapping("/admin/upload")
    public String handleFileUpload(MultipartFile file, Model model) throws IOException {
        if (file == null || file.isEmpty()) {
            model.addAttribute("error", "파일을 선택하세요.");
            return "redirect:/admin";
        }
        Path dir = Paths.get(uploadDir);
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        String cleanFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path target = dir.resolve(cleanFilename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return "redirect:/admin";
    }

    @GetMapping("/uploads/{filename}")
    @ResponseBody
    public Resource serveFile(@PathVariable String filename) throws MalformedURLException {
        Path file = Paths.get(uploadDir).resolve(filename);
        return new UrlResource(file.toUri());
    }
}


