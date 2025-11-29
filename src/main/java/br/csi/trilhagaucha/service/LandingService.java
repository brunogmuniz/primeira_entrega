package br.csi.trilhagaucha.service;


import br.csi.trilhagaucha.model.userLanding.UserLanding;
import br.csi.trilhagaucha.repository.UserLandingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandingService {

    @Autowired
    private UserLandingRepository userLandingRepository;

    public void save(UserLanding userLanding) {
        this.userLandingRepository.save(userLanding);
    }

}
