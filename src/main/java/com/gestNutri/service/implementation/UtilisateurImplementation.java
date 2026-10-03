package com.gestNutri.service.implementation;

import com.gestNutri.dto.resquest.UtilisateurResquest;
import com.gestNutri.dto.resquest.UtilisateurMiseAJourResquest;
import com.gestNutri.dto.response.UtilisateurResponse;
import com.gestNutri.entities.Utilisateur;
import com.gestNutri.exception.ResourceNotFoundException;
import com.gestNutri.repository.UtilisateurRepository;
import com.gestNutri.service.UtilisateurService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurImplementation implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurImplementation(UtilisateurRepository utilisateurRepository,
                                      PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UtilisateurResponse create(UtilisateurResquest request) {
        if (utilisateurRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe deja.");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(request.email());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.motDePasse()));
        utilisateur.setRole(request.role());

        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return new UtilisateurResponse(saved.getId(), saved.getEmail(), saved.getRole());
    }

    @Override
    public UtilisateurResponse findById(Long id) {
        return utilisateurRepository.findById(id)
                .map(utilisateur -> new UtilisateurResponse(utilisateur.getId(), utilisateur.getEmail(), utilisateur.getRole()))
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouve."));
    }

    @Override
    public UtilisateurResponse update(Long id, UtilisateurMiseAJourResquest request) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouve."));
        utilisateurRepository.findByEmail(request.email())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Un utilisateur avec cet email existe deja.");
                });

        utilisateur.setEmail(request.email());
        utilisateur.setRole(request.role());
        if (request.motDePasse() != null && !request.motDePasse().isBlank()) {
            utilisateur.setMotDePasse(passwordEncoder.encode(request.motDePasse()));
        }

        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return new UtilisateurResponse(saved.getId(), saved.getEmail(), saved.getRole());
    }

    @Override
    public void delete(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur non trouve.");
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public List<UtilisateurResponse> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(u -> new UtilisateurResponse(u.getId(), u.getEmail(), u.getRole()))
                .toList();
    }
}
