package br.csi.trilhagaucha.repository;

import br.csi.trilhagaucha.model.userLanding.UserLanding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLandingRepository extends JpaRepository<UserLanding, Long> {

}
