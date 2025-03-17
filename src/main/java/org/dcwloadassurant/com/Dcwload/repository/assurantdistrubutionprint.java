package org.dcwloadassurant.com.Dcwload.repository;


import org.dcwloadassurant.com.Dcwload.Entity.Assurantdistrubutionprint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface assurantdistrubutionprint extends JpaRepository<Assurantdistrubutionprint, Integer> {

    Page<Assurantdistrubutionprint> findByGroupid(Integer groupid, Pageable pageable);
}
