package org.dcwloadassurant.com.Dcwload.servcie;

import org.dcwloadassurant.com.Dcwload.Dcwentity;
import org.dcwloadassurant.com.Dcwload.Entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface dcwserivces {



    void  update(Dcwentity dcwentit);
    //void  delete(Integer id);
    List<Dcwentity> getall();

    //Dcwentity getonedcw(Integer id);




    Dcwentity getonedcw(Integer id, Integer groupid, Integer variableid);

    void updatedcwprojects(Dcwentity cisdata);

    List<Dcwentity> getbyflexjobname(String jobname);

    List<String> getcopychangejob(Integer groupId);


    Page<AssurantEnvelopeProfile> findByCorrel(Integer correlId,Pageable pageable);


    void saveEnvelop(AssurantEnvelopeProfile profile);

    Page<AssurantEnvelopeProfile> getAllEnvelopes(Pageable pageable);

//    List<ExceptionList_Unbundlejob> getAllunbundlejobs();

    Page<ExceptionList_Unbundlejob>  getAllunbundlejobs(Pageable pageable);




    void savenUnjob(ExceptionList_Unbundlejob exception);

    Optional<ExceptionList_Unbundlejob> findByUnbjobId(Long id);

    void deleteByUnbjobId(Long id);

    void addProfile(AssurantEnvelopeProfile profile);

    String checkflexjob(String flexjob);

    void savenewaddedjob(String flexjobname);

    Page<ExceptionList_Unbundlejob> searchUnbundlejobsByFlexjobName(String search, Pageable pageable);

    List<List<Object>> loaddumpofjobs(String flexjobs);

    void addInsertProfile(AssurentInsertProfile profile);

    Page<AssurentInsertProfile> findByProfileCorrel(Integer correl, Pageable pageable);

    Page<AssurentInsertProfile> getAllInserProfiles(Pageable pageable);

    void saveInsertEnvelop(AssurentInsertProfile profile);

   // Page<AssurantBRECatalog> findByBreCode(Integer code, Pageable pageable);

    Page<AssurantBRECatalog> findByBreCode(String code, Pageable pageable);

    Page<AssurantBRECatalog> getAllbrecatalog(Pageable pageable);

    void saveBreCatalog(AssurantBRECatalog profile);

    void addbrecatalog(AssurantBRECatalog profile);

    Page<Assurantdistrubutionprint> findByGroupid(Integer groupId, Pageable pageable);

    Page<Assurantdistrubutionprint> getAlldistrubutionprint(Pageable pageable);

    void savedistrubutionprint(Assurantdistrubutionprint profile);

    List<String> getallflexjob();

    void loadDcwStock();

    void insertProduct(String agndpmt);

    void createProduct();
}
