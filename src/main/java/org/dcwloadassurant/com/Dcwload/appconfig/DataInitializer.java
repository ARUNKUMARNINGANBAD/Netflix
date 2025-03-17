package org.dcwloadassurant.com.Dcwload.appconfig;

import lombok.Data;
import org.dcwloadassurant.com.Dcwload.servcie.dcwserivces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Data
public class DataInitializer {



    private final dcwserivces service;


    private List<String> flexnames;

    public DataInitializer(dcwserivces service, List<String> flexnames) {
        this.service = service;

        this.flexnames = flexnames;
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing data on startup...");
        flexnames= service.getallflexjob();
    }
}
