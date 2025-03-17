package org.dcwloadassurant.com.Dcwload.repository;

import org.dcwloadassurant.com.Dcwload.Dcwentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


public interface dcwreposiitory extends JpaRepository<Dcwentity, Integer> {

    @Query(value = "SELECT * FROM company_variables_groups_values WHERE group_id IN (SELECT group_id FROM company_variables_groups_values WHERE variable_value LIKE %:variablevalues% and  variable_name = 'FLEX Job Name') order by id asc ", nativeQuery = true)
    List<Dcwentity> getlistByvariable_value(@Param("variablevalues") String variablevalues);

    @Query(value = "select variable_value from company_variables_groups_values where group_id =:groupid order by id asc", nativeQuery = true)
    List<String> getcopychangejob(@Param("groupid") Integer groupid);

    @Query(value = "select * from company_variables_groups_values where variable_value =:flexjobname", nativeQuery = true)
    List<Dcwentity> checkflexjobname(@Param("flexjobname") String flexjobname);


    @Query(value = "select variable_value from company_variables_groups_values where variable_id = 3 order by id desc", nativeQuery = true)
    List<String> getallflexjobs();

    @Query(value = "select top 1 * from company_variables_groups_values where id =:id and group_id =:groupid and variable_id =:variableid ", nativeQuery = true)
    Dcwentity getonedcwentity(@Param("id") Integer id,@Param("groupid") Integer groupid,@Param("variableid") Integer variableid);

   // @Query(value = "update company_variables_groups_values set variable_value =: variablevalue where id =:id and group_id =:groupid and variable_id =:variableid ",nativeQuery = true)
//   @Query(
//           value = "UPDATE company_variables_groups_values SET variable_value = :variablevalue WHERE id = :id AND group_id = :groupid AND variable_id = :variableid",
//           nativeQuery = true
//   )
//    void updateonedcwentity(@Param("variablevalue") String variablevalue, @Param("id") Integer id,@Param("groupid") Integer groupid,@Param("variableid") Integer variableid);

    @Query(
            value = "UPDATE company_variables_groups_values SET variable_value = :variablevalue WHERE id = :id AND group_id = :groupid AND variable_id = :variableid",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    void updateonedcwentity(
            @Param("variablevalue") String variablevalue,
            @Param("id") Integer id,
            @Param("groupid") Integer groupid,
            @Param("variableid") Integer variableid
    );

}
