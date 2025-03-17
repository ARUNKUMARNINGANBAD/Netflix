package org.dcwloadassurant.com.Dcwload.servcie;

import org.dcwloadassurant.com.Dcwload.Entity.*;
import org.dcwloadassurant.com.Dcwload.exception.Dcwnotexcepection;
import org.dcwloadassurant.com.Dcwload.repository.dcwreposiitory;
import org.dcwloadassurant.com.Dcwload.repository.assurantEnvelopRepo;
import org.dcwloadassurant.com.Dcwload.repository.assurantUnbjobsRepo;
import org.dcwloadassurant.com.Dcwload.repository.assurantInsertprofileRepo;
import org.dcwloadassurant.com.Dcwload.repository.assurantdistrubutionprint;
import org.dcwloadassurant.com.Dcwload.repository.assurantBreCatalogRepo;
import org.dcwloadassurant.com.Dcwload.repository.newaddedjob;
import org.dcwloadassurant.com.Dcwload.Dcwentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class dcwserivcesImpl implements dcwserivces {

    @Autowired
    private dcwreposiitory repo;

    @Autowired
    private assurantEnvelopRepo envelopRepo;

    @Autowired
    private assurantInsertprofileRepo  assurantInsertprofileRepo;

    @Autowired
    private assurantBreCatalogRepo assurantBreCatalogRepo;

    @Autowired
    private assurantUnbjobsRepo unbjobRepo;

    @Autowired
    private assurantdistrubutionprint assurantdistrubutionprint;

    @Autowired
    private newaddedjob newjobrepo;


    @Override
    public void update(Dcwentity dcwentit) {

    }

    @Override
    public List<Dcwentity> getall() {
        return repo.findAll();
    }



    @Override
    public Dcwentity getonedcw(Integer id,Integer groupid,Integer variableid) {
//        Optional<Dcwentity> opt = repo.findById(id);
//        if(opt.isPresent())
//        {
//            Dcwentity e = opt.get();
//            return e;
//        }
//        else
//            throw new Dcwnotexcepection("DCW data not found"+id);

        Optional<Dcwentity> optionalDcwentity = Optional.ofNullable(repo.getonedcwentity(id, groupid, variableid));

        if (optionalDcwentity.isPresent()) {
            return optionalDcwentity.get();
        } else {
            throw new Dcwnotexcepection("DCW data not found for ID: " + id);
        }
    }

    @Override
    public void updatedcwprojects(Dcwentity dcwentity) {

        //repo.save(dcwentity);
        repo.updateonedcwentity(dcwentity.getVariable_value(),dcwentity.getId(),dcwentity.getGroup_id(),dcwentity.getVariable_id());
    }

    @Override
    public List<Dcwentity> getbyflexjobname(String jobname) {
    return repo.getlistByvariable_value(jobname);

    }

    @Override
    public List<String> getcopychangejob(Integer groupId) {
       return repo.getcopychangejob(groupId);
       // return null;
    }

    @Override
    public Page<AssurantEnvelopeProfile> findByCorrel(Integer correlId, Pageable pageable) {

        return envelopRepo.findByCorrel(correlId,pageable);
    }

    @Override
    public void addProfile(AssurantEnvelopeProfile profile) {
       envelopRepo.save(profile);
    }

    @Override
    public String checkflexjob(String flexjobname) {
        List<Dcwentity> opt =  repo.checkflexjobname(flexjobname);

        if(!opt.isEmpty())
        {
           // Dcwentity e = opt.get();
            return "DuplicateJOb";
        }
        else
            return "nonDuplicateJOb";
    }

    @Override
    public void savenewaddedjob(String flexjobname) {
        NewAddedjobs newJob = new NewAddedjobs();
        newJob.setFlexjobname(flexjobname);
        newjobrepo.save(newJob);
    }

    @Override
    public Page<ExceptionList_Unbundlejob> searchUnbundlejobsByFlexjobName(String search, Pageable pageable) {
        return unbjobRepo.findByFlexjobnameContainingIgnoreCase(search, pageable);
    }

    @Override
    public  List<List<Object>>  loaddumpofjobs(String flexjobs) {
        System.out.println("In impl class ----> " + flexjobs);
      String[] tflexjobs =   flexjobs.split(",");


        try {
           // List<Map<String, Object>> results = newjobrepo.loaddumpofnewjobs(flexjobs);

            List<Map<String, Object>> results = newjobrepo.loaddumpofnewjobs(flexjobs);
            return prepareDataAsList(results);
        } catch (Exception e) {
            System.err.println("Error while fetching jobs: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<List<Object>> prepareDataAsList(List<Map<String, Object>> results) {
        List<List<Object>> finalData = new ArrayList<>();

        if (results == null || results.isEmpty()) {
            return finalData; // Return an empty list if no results
        }

        // Extract keys from the first map as the header row
        Map<String, Object> firstRow = results.get(0);
        List<Object> header = new ArrayList<>(firstRow.keySet());
        finalData.add(header); // Add header to final data

        // Collect all rows of values
        for (Map<String, Object> result : results) {
            List<Object> row = new ArrayList<>();
            for (Object key : header) {
                row.add(result.get(key)); // Add values in the same key order as header
            }
            finalData.add(row);
        }

        return finalData;
    }



    @Override
    public void addInsertProfile(AssurentInsertProfile profile) {
        assurantInsertprofileRepo.save(profile);

    }

    @Override
    public Page<AssurentInsertProfile> findByProfileCorrel(Integer correl, Pageable pageable) {


        return assurantInsertprofileRepo.findByProfilecorrel(correl,pageable);
    }

    @Override
    public Page<AssurentInsertProfile> getAllInserProfiles(Pageable pageable) {


        return assurantInsertprofileRepo.findAll(pageable);
    }

    @Override
    public void saveInsertEnvelop(AssurentInsertProfile profile) {

        assurantInsertprofileRepo.save(profile);
    }



    @Override
    public Page<AssurantBRECatalog> findByBreCode(String code, Pageable pageable) {
        return assurantBreCatalogRepo.findByCode(code,pageable);
    }

    @Override
    public Page<AssurantBRECatalog> getAllbrecatalog(Pageable pageable) {
        return assurantBreCatalogRepo.findAll(pageable);
    }

    @Override
    public void saveBreCatalog(AssurantBRECatalog profile) {
        assurantBreCatalogRepo.save(profile);

    }

    @Override
    public void addbrecatalog(AssurantBRECatalog profile) {
        assurantBreCatalogRepo.save(profile);
    }

    @Override
    public Page<Assurantdistrubutionprint> findByGroupid(Integer groupId, Pageable pageable) {
        return assurantdistrubutionprint.findByGroupid(groupId,pageable);
    }

    @Override
    public Page<Assurantdistrubutionprint> getAlldistrubutionprint(Pageable pageable) {
        return assurantdistrubutionprint.findAll(pageable);
    }

    @Override
    public void savedistrubutionprint(Assurantdistrubutionprint profile) {
        assurantdistrubutionprint.save(profile);

    }

    @Override
    public List<String> getallflexjob() {
        return repo.getallflexjobs();
    }

    @Override
    public void loadDcwStock() {

    }

    @Override
    public void insertProduct(String agndpmt) {
         newjobrepo.insertproduct(agndpmt);
    }

    @Override
    public void createProduct() {

    }

    @Override
    public void saveEnvelop(AssurantEnvelopeProfile Envelopprofile) {
        envelopRepo.save(Envelopprofile);
    }

    @Override
    public Page<AssurantEnvelopeProfile> getAllEnvelopes(Pageable pageable) {

       return envelopRepo.findAll(pageable);
    }



    @Override
    public Page<ExceptionList_Unbundlejob> getAllunbundlejobs(Pageable pageable) {
        Page<ExceptionList_Unbundlejob> page = unbjobRepo.findAll(pageable);
        return page;
    }
    @Override
    public void savenUnjob(ExceptionList_Unbundlejob Exception) {
        unbjobRepo.save(Exception);
    }

    @Override
    public Optional<ExceptionList_Unbundlejob> findByUnbjobId(Long id) {
        return unbjobRepo.findById(id);
    }

    @Override
    public void deleteByUnbjobId(Long id) {
     //  unbjobRepo.deleteById(Math.toIntExact(id));
        unbjobRepo.deleteById(id);

    }



}
