package com.gestNutri.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String homeHtml() {
        return """
            <!DOCTYPE html>
            <html lang="fr">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Aviform - API en ligne</title>
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
                        background: #0f172a;
                        color: #f8fafc;
                        margin: 0;
                        padding: 0;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        min-height: 100vh;
                    }
                    .card {
                        background: #1e293b;
                        border: 1px solid #334155;
                        border-radius: 16px;
                        padding: 40px;
                        max-width: 520px;
                        width: 90%;
                        box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.4);
                    }
                    .badge {
                        display: inline-flex;
                        align-items: center;
                        gap: 8px;
                        background: rgba(34, 197, 94, 0.15);
                        color: #4ade80;
                        font-size: 13px;
                        font-weight: 600;
                        padding: 6px 14px;
                        border-radius: 9999px;
                        margin-bottom: 20px;
                    }
                    .dot {
                        width: 8px;
                        height: 8px;
                        background: #22c55e;
                        border-radius: 50%;
                        display: inline-block;
                        box-shadow: 0 0 10px #22c55e;
                    }
                    h1 {
                        margin: 0 0 12px 0;
                        font-size: 26px;
                        font-weight: 700;
                        color: #ffffff;
                    }
                    p {
                        color: #94a3b8;
                        font-size: 15px;
                        line-height: 1.6;
                        margin: 0 0 24px 0;
                    }
                    .links {
                        display: flex;
                        flex-direction: column;
                        gap: 12px;
                    }
                    a {
                        display: inline-block;
                        color: #38bdf8;
                        text-decoration: none;
                        font-size: 14px;
                        font-weight: 500;
                        transition: color 0.2s;
                    }
                    a:hover {
                        color: #7dd3fc;
                        text-decoration: underline;
                    }
                </style>
            </head>
            <body>
                <div class="card">
                    <div class="badge"><span class="dot"></span> En ligne</div>
                    <h1>Aviform Backend API</h1>
                    <p>Le serveur d'API Aviform fonctionne correctement. Cette URL héberge les services REST backend de l'application.</p>
                    <div class="links">
                        <a href="/actuator/health">👉 Vérifier l'état de santé (/actuator/health)</a>
                        <a href="/api/auth/login">🔑 Endpoint d'authentification (/api/auth/login)</a>
                    </div>
                </div>
            </body>
            </html>
            """;
    }

    @GetMapping(value = { "/", "/api" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> homeJson() {
        return ResponseEntity.ok(Map.of(
            "name", "Aviform API",
            "status", "UP",
            "message", "Le serveur d'API Aviform est opérationnel.",
            "health", "/actuator/health",
            "version", "0.0.1"
        ));
    }
}
