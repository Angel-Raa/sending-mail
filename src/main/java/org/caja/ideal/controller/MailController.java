package org.caja.ideal.controller;

import org.caja.ideal.api.ApiResponse;
import org.caja.ideal.models.EmailDto;
import org.caja.ideal.models.EmailFileDto;
import org.caja.ideal.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private IEmailService service;

    @GetMapping("/home")
    public String getHome() {
        return "Hola Mundo ";
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<ApiResponse> receiveRequestEmail(@RequestBody EmailDto emailDto) {
        System.out.println("Message received");
        System.out.println(emailDto.toString());
        service.sendEmail(emailDto.toUser(), emailDto.subject(), emailDto.message());
        ApiResponse api = new ApiResponse("Message sent successfully", HttpStatus.OK);
        return ResponseEntity.ok(api);
    }

    @PostMapping("/sendEmailFile")
    public ResponseEntity<ApiResponse> receiveRequestEmailWithFile(@ModelAttribute EmailFileDto emailFileDto) {
        try {
            String fileName = emailFileDto.getFile().getOriginalFilename();
            Path path = Paths.get("src/mail/resources/files/" + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(emailFileDto.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File file = path.toFile();
            service.sendEmailWithFile(emailFileDto, file);
            ApiResponse api = new ApiResponse("Message sent successfully", HttpStatus.OK);
            return ResponseEntity.ok(api);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
        }

    }
}
