package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.userLanding.UserLanding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserLandingRepository extends JpaRepository<UserLanding, Long> {

}