package org.dcwloadassurant.com.Dcwload.repository;

import org.dcwloadassurant.com.Dcwload.Entity.AssurantBRECatalog;
import org.dcwloadassurant.com.Dcwload.Entity.AssurantEnvelopeProfile;
import org.dcwloadassurant.com.Dcwload.Entity.Assurantdistrubutionprint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface assurantBreCatalogRepo extends JpaRepository<AssurantBRECatalog,Integer> {

    Page<AssurantBRECatalog> findByCode(String code, Pageable pageable);
}
