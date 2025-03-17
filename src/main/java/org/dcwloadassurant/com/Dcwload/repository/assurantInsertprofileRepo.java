package org.dcwloadassurant.com.Dcwload.repository;

import org.dcwloadassurant.com.Dcwload.Entity.AssurantEnvelopeProfile;
import org.dcwloadassurant.com.Dcwload.Entity.AssurentInsertProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface assurantInsertprofileRepo extends JpaRepository<AssurentInsertProfile,Integer> {

    Page<AssurentInsertProfile> findByProfilecorrel(Integer profilecorrel, Pageable pageable);
}
