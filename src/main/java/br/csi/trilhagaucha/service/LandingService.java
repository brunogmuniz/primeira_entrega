package br.csi.trilhagaucha.service;


import br.csi.trilhagaucha.model.userLanding.UserLanding;
import br.csi.trilhagaucha.repository.UserLandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LandingService {

    @Autowired
    private UserLandingRepository userLandingRepository;

    public void save(UserLanding userLanding) {
        this.userLandingRepository.save(userLanding);
    }

    public List<UserLanding> findAll() {
        return this.userLandingRepository.findAll();
    }

    public Optional<UserLanding> findById(long id) {
        return this.userLandingRepository.findById(id);
    }
    public void delete(long id) {
        this.userLandingRepository.deleteById(id);
    }
}
