package org.dcwloadassurant.com.Dcwload.repository;

import org.dcwloadassurant.com.Dcwload.Dcwentity;
import org.dcwloadassurant.com.Dcwload.Entity.NewAddedjobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public interface newaddedjob extends JpaRepository<NewAddedjobs, Integer> {


//    @Query(value = "EXEC loaddumpofnewjobs :flexjobs", nativeQuery = true)
//    List<Object[]> loaddumpofnewjobs(@Param("flexjobs") String flexjobs);

    @Query(nativeQuery = true, value = "exec loaddumpofnewjobs :flexjobs")
    List<Map<String, Object>> loaddumpofnewjobs(@Param("flexjobs") String flexjobs);

    @Query(nativeQuery = true, value = "exec insertproduct :flexjobs")
    void insertproduct(@Param("flexjobs") String flexjobs);


}
