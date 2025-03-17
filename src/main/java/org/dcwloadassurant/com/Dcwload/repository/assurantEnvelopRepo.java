package org.dcwloadassurant.com.Dcwload.repository;

import org.dcwloadassurant.com.Dcwload.Entity.AssurantEnvelopeProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface assurantEnvelopRepo extends JpaRepository<AssurantEnvelopeProfile,Integer> {

    Page<AssurantEnvelopeProfile> findByCorrel(Integer correl, Pageable pageable);


}
