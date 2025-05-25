/**
 * Autor: Marco Ezequiel Cedro Barros Borges
 * Data: 25 de mai. de 2025
 */

package com.techchallenge.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
            <!DOCTYPE html>
            <html lang="pt-br">
            <head>
                <meta charset="UTF-8">
                <title>Servidor Ativo</title>
                <style>
                    body {
                        font-family: 'Segoe UI', sans-serif;
                        background: linear-gradient(to right, #0077b6, #00b4d8);
                        color: white;
                        text-align: center;
                        padding-top: 10%;
                    }

                    .container {
                        background-color: rgba(255, 255, 255, 0.1);
                        padding: 40px;
                        border-radius: 16px;
                        width: 60%;
                        margin: 0 auto;
                        box-shadow: 0 8px 16px rgba(0,0,0,0.3);
                    }

                    h1 {
                        font-size: 3em;
                        margin-bottom: 20px;
                    }

                    p {
                        font-size: 1.2em;
                        margin: 5px 0;
                    }

                    .footer {
                        margin-top: 40px;
                        font-size: 0.9em;
                        color: #e0f7fa;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>✅ Servidor Ativo</h1>
                    <p><strong>Aluno:</strong> Marco Ezequiel Cedro Barros Borges</p>
                    <p><strong>Curso:</strong> Pós Tech Java</p>
                    <p><strong>Instituição:</strong> FIAP</p>

                    <div class="footer">
                        © 2025 - Desenvolvido como parte do desafio técnico
                    </div>
                </div>
            </body>
            </html>
            """;
    }
}
