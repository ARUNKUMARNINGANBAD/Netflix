package org.dcwloadassurant.com.Dcwload.repository;

import org.dcwloadassurant.com.Dcwload.Entity.ExceptionList_Unbundlejob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface assurantUnbjobsRepo extends JpaRepository<ExceptionList_Unbundlejob,Long> {

    Page<ExceptionList_Unbundlejob> findByFlexjobnameContainingIgnoreCase(String flexjobname, Pageable pageable);

}
