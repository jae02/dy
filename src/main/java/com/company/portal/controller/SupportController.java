package com.company.portal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class SupportController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/support/faq")
    public String faq(Model model) {
        return "support-faq";
    }

    @GetMapping("/support/pds")
    public String pds(Model model) {
        return "support-pds";
    }

    @GetMapping("/support/contact")
    public String contact(Model model) {
        return "support-contact";
    }

    @PostMapping("/support/contact")
    public String submitContact(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message,
            @RequestParam(required = false) MultipartFile attachment
    ) throws IOException {
        Path baseDir = Paths.get(uploadDir, "contact");
        if (!Files.exists(baseDir)) {
            Files.createDirectories(baseDir);
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String metaFileName = timestamp + "-contact.txt";
        Path metaFile = baseDir.resolve(metaFileName);

        StringBuilder sb = new StringBuilder();
        sb.append("name=").append(name).append('\n');
        sb.append("email=").append(email).append('\n');
        sb.append("subject=").append(subject).append('\n');
        sb.append("message=\n").append(message).append('\n');

        Files.write(metaFile, sb.toString().getBytes(StandardCharsets.UTF_8));

        if (attachment != null && !attachment.isEmpty()) {
            String clean = StringUtils.cleanPath(attachment.getOriginalFilename());
            Path target = baseDir.resolve(timestamp + "-" + clean);
            Files.copy(attachment.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        }

        return "redirect:/support/contact?success=1";
    }
}


