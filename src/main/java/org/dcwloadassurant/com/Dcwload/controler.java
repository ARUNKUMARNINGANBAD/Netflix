package org.dcwloadassurant.com.Dcwload;


import org.dcwloadassurant.com.Dcwload.Entity.*;
import org.dcwloadassurant.com.Dcwload.appconfig.DataInitializer;
import org.dcwloadassurant.com.Dcwload.exception.Dcwnotexcepection;
import org.dcwloadassurant.com.Dcwload.servcie.CSVService;
import org.dcwloadassurant.com.Dcwload.wrapper.CSVDataWrapper;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.dcwloadassurant.com.Dcwload.servcie.dcwserivces;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@SessionAttributes("csvDataWrapper")
public class controler {

    @Autowired
    private CSVService csvService;
    @Autowired
    private dcwserivces service;

    @Autowired
    private DataInitializer dataInitializer;

    @GetMapping({"/dashboard"})
    public  String dashboard(Principal p, Model model, HttpSession session
                             ) {
       // session.setMaxInactiveInterval(30);
        model.addAttribute("username",p.getName());

        return "Dashboard";

    }
    @GetMapping({"/dashboard/def"})
    public  String dashboarddef(Principal p, Model model, HttpSession session
    ) {
        // session.setMaxInactiveInterval(30);

        return "default";

    }

    @GetMapping("/dashboard/search")
    @ResponseBody
    public List<String> search(@RequestParam("query") String query) {

        // Return filtered results based on the query
        return dataInitializer.getFlexnames().stream()
                .filter(item -> item.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/dashboard/all")
    public  String showalldata(
            @RequestParam(required = false) String message,
            Model model, HttpSession seesion, Principal p) {

        model.addAttribute("username",p.getName());
        model.addAttribute("message",message);
        System.out.println(javax.servlet.ServletContext.class.getProtectionDomain().getCodeSource());

        return "home";

    }

    @RequestMapping("/dashboard/loadcsvfile")
    public String index(Model model) {
        model.addAttribute("message", null);
        return "csvfileload"; // This will map to the Thymeleaf template `index.html`
    }


    @PostMapping("/dashboard/upload-csv")
    public String uploadCSV(@RequestParam("file") MultipartFile file, Model model) {
        List<List<String>> csvData = new ArrayList<>();
        List<String> headers = new ArrayList<>();



        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (isHeader) {
                    for (String header : values) {
                        headers.add(header.trim());
                    }
                    isHeader = false;
                } else {
                    List<String> row = new ArrayList<>();
                    for (String value : values) {
                        row.add(value.trim());
                    }
                    csvData.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload CSV file: " + e.getMessage());
            return "index";
        }

        // Store data in the wrapper
        CSVDataWrapper csvDataWrapper = new CSVDataWrapper();
        csvDataWrapper.setCsvData(csvData);
         //RedirectAttributes attributes
        for (List<String> lisdata: csvData
             ) {
                String resofdupjob =   service.checkflexjob(lisdata.get(2));
                if(Objects.equals(resofdupjob, "DuplicateJOb")){
                    model.addAttribute("message","Already job name "+lisdata.get(2) + " exists" +", Please correct the csv file and try again"  );
                    return "csvfileload";
            }
        }


        model.addAttribute("headers", headers);
        model.addAttribute("csvDataWrapper", csvDataWrapper); // Save data to session
        return "csvfileload"; // Show proofreading page
    }

    @PostMapping("/dashboard/process-csv")
    public String processCSV(@ModelAttribute("csvDataWrapper") CSVDataWrapper csvDataWrapper, SessionStatus status, RedirectAttributes redirectAttributes) {
        // Process the CSV data
        csvService.insertnewjobs(csvDataWrapper.getCsvData());

        // Clear session attributes
        status.setComplete();

        // Add success message for redirection
        redirectAttributes.addFlashAttribute("message", "CSV data successfully processed!");
        return "redirect:/dashboard/loadcsvfile"; // Redirect to the upload page
    }


    @GetMapping("/dashboard/searchByGroupId")
    public  String searchdata(
            @RequestParam(required = false) String variablename,
            @RequestParam String flexjobname,
            Model model) {

         List<Dcwentity> list = service.getbyflexjobname(flexjobname);
        List<Dcwentity> filteredList = list.stream()
                .filter(data ->
                         (variablename == null || data.getVariable_name().contains(variablename)))
                .collect(Collectors.toList());


        //System.out.println(filteredList);


        model.addAttribute("list",list);
        // model.addAttribute("message",message);

        return "home";

    }


    @GetMapping("/dashboard/edit")
    public String showedit(@RequestParam(required = false) Integer id,
            @RequestParam("groupid") Integer groupId,
                           @RequestParam("variableid") Integer variableId, Model model) {
        String page = null;
        try {
            Dcwentity cisdata = service.getonedcw(id,groupId,variableId);


            model.addAttribute("cisproject",cisdata);

            System.out.print(cisdata);
            page = "editpage";
        }
        catch (Dcwnotexcepection e) {
            // TODO: handle exception
            e.printStackTrace();

            model.addAttribute("message", e.getMessage());
            page = "redirect:/dashboard/all";
        }
        return page;
    }

    @GetMapping("/dashboard/copychange")
    public String copychange(@RequestParam(required = false) Integer group_id, Model model) {
        String page = null;
        try {
            List<String> dataList = service.getcopychangejob(group_id);


            model.addAttribute("dataList",dataList);

            System.out.print(dataList);
            page = "copyinsertjob";
        }
        catch (Dcwnotexcepection e) {
            // TODO: handle exception
            e.printStackTrace();

            model.addAttribute("message", e.getMessage());
            page = "redirect:/dashboard";
        }
        return page;
    }


    @PostMapping("/dashboard/upd")
    public String upateproject(@ModelAttribute Dcwentity cisdata,
                               RedirectAttributes attributes) {

        service.updatedcwprojects(cisdata);
        System.out.print(cisdata);
        //attributes.addAttribute("", attributes)
        attributes.addFlashAttribute("success",true);
        return "redirect:/dashboard/all";
    }



    @PostMapping("/dashboard/insertjobs")
    public String insertjob(@RequestParam  Map<String, String> formData
                            ,RedirectAttributes attributes) {
        try {
            List<String> valuesList = new ArrayList<>();
            //List<String> valuesList = formData;
            System.out.println(valuesList);
            for (Map.Entry<String, ?> entry : formData.entrySet()) {
              //  System.out.println("Key --->" + entry.getKey() + "value  -->" + entry.getValue());
                Object value = entry.getValue();
                if (value instanceof String) {
                    valuesList.add((String) value);
                } else if (value instanceof String[]) {
                    for (String val : (String[]) value) {
                        valuesList.add(val);
                    }
                } else {
                    throw new IllegalArgumentException("Unexpected value type: " + value.getClass());
                }
            }

            /// Exclude the 0th element
            List<String> sublist = valuesList.size() > 1 ? valuesList.subList(1, valuesList.size()) : new ArrayList<>();
          //  System.out.println( "List Data --->" + sublist);
            List<List<String>> valuesArray = new ArrayList<>();
            valuesArray.add(sublist);
            //System.out.println(valuesArray.get(0).get(2));


            System.out.println( "List On List Data --->" + valuesArray);


          String resofdupjob =   service.checkflexjob(valuesArray.get(0).get(2));//it will check the job is already exists or not
            if(!Objects.equals(resofdupjob, "DuplicateJOb")){
             csvService.insertnewjobs(valuesArray);
            attributes.addFlashAttribute("message","New Job Added successfully");
           //dcwserivces.savenewaddedjob(valuesArray[2]);
                service.savenewaddedjob(valuesArray.get(0).get(2));
            return "redirect:/dashboard";}
            else{
                attributes.addAttribute("message","Already job name exists");
                return "redirect:/dashboard/all";
            }
        } catch (Exception e) {
            return "Error processing form data: " + e.getMessage();
        }
    }

    @GetMapping("/dashboard/insert")
    public String insert() {
        return "insertjob";
    }
    //region envelop page
    @PostMapping("/dashboard/envelop/add")
    public String addProfile(@ModelAttribute AssurantEnvelopeProfile profile) {
        service.addProfile(profile);
        return "redirect:/dashboard/envelop";
    }

    @GetMapping("/dashboard/envelop")
    public String getReport(@RequestParam(value = "correl", required = false) Integer correl, Model model,
                            @PageableDefault(page = 0, size = 3) Pageable pageable,
                            //@RequestParam(required = false) Integer size,
                            @RequestParam(required = false) String message
    ) {

        Page<AssurantEnvelopeProfile> page;
        if (correl != null) {
           // List<AssurantEnvelopeProfile> profiless = service.findByCorrel(correl);
            page = service.findByCorrel(correl,pageable);
            //page = service.getAllEnvelopes(pageable);
            model.addAttribute("profiles", page);

        } else {
            //profiles = repository.findAll();
            System.out.println("not null");
             page = service.getAllEnvelopes(pageable);
            model.addAttribute("profiles", page);
        }
        model.addAttribute("exceptions", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("correl", correl);// Add correl to model for form binding
        return "envelopspage";
    }


    @PostMapping("/dashboard/envelop/update")
    public String updateProfile(@ModelAttribute AssurantEnvelopeProfile profile) {
        System.out.println("here issee");
        service.saveEnvelop(profile);
        return "redirect:/dashboard/envelop";
    }


    @PostMapping("/dashboard/insertprofile/add")
    public String addInsertProfile(@ModelAttribute AssurentInsertProfile profile) {
        service.addInsertProfile(profile);
        return "redirect:/dashboard/insertprofile";
    }

    @GetMapping("/dashboard/insertprofile")
    public String getprofileReport(@RequestParam(value = "correl", required = false) Integer correl, Model model,
                            @PageableDefault(page = 0, size = 3) Pageable pageable,
                            //@RequestParam(required = false) Integer size,
                            @RequestParam(required = false) String message
    ) {

        Page<AssurentInsertProfile> page;
        if (correl != null) {
            // List<AssurantEnvelopeProfile> profiless = service.findByCorrel(correl);
            page = service.findByProfileCorrel(correl,pageable);
            //page = service.getAllEnvelopes(pageable);
            model.addAttribute("profiles", page);

        } else {
            //profiles = repository.findAll();
            System.out.println("not null");
            page = service.getAllInserProfiles(pageable);
            model.addAttribute("profiles", page);
        }
        model.addAttribute("exceptions", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("correl", correl);// Add correl to model for form binding
        return "insertprofilepage";
    }

    @PostMapping("/dashboard/insertprofile/update")
    public String updateInsertProfile(@ModelAttribute AssurentInsertProfile profile) {
        System.out.println("here issee");
        service.saveInsertEnvelop(profile);
        return "redirect:/dashboard/insertprofile";
    }
    //end region


    @PostMapping("/dashboard/brecatalog/add")
    public String addbrecatalog(@ModelAttribute AssurantBRECatalog profile) {
        service.addbrecatalog(profile);
        return "redirect:/dashboard/brecatalog";
    }

    @GetMapping("/dashboard/brecatalog")
    public String getbrecatalog(@RequestParam(value = "code", required = false) String code, Model model,
                                   @PageableDefault(page = 0, size = 3) Pageable pageable,
                                   //@RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) String message
    ) {

        Page<AssurantBRECatalog> page;
        if (code != null) {
            // List<AssurantEnvelopeProfile> profiless = service.findByCorrel(correl);
            page = service.findByBreCode(code,pageable);
            //page = service.getAllEnvelopes(pageable);
            model.addAttribute("profiles", page);

        } else {
            //profiles = repository.findAll();
            System.out.println("not null");
            page = service.getAllbrecatalog(pageable);
            model.addAttribute("profiles", page);
        }
        model.addAttribute("exceptions", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("correl", code);// Add correl to model for form binding
        return "brecatalog";
    }

    @PostMapping("/dashboard/brecatalog/update")
    public String updatebrecatalog(@ModelAttribute AssurantBRECatalog profile) {
        System.out.println("here issee");
        service.saveBreCatalog(profile);
        return "redirect:/dashboard/brecatalog";
    }



    @GetMapping("/dashboard/exceptions")
    public String showAll(
            @PageableDefault(page = 0, size = 3) Pageable pageable,
            @RequestParam(required = false) String message,
            @RequestParam(required = false) String search,
            Model model)
    {
        Page<ExceptionList_Unbundlejob> page;

        if (search != null && !search.isEmpty()) {
            // Fetch data from DB using service with search criteria
            page = service.searchUnbundlejobsByFlexjobName(search, pageable);
        } else {
            // Fetch all data from DB using service
            page = service.getAllunbundlejobs(pageable);
        }

        // Send this data to UI/View
        model.addAttribute("exceptions", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("search", search != null ? search : ""); // Ensure 'search' is initialized

        // Go to UI Page
        return "getallUnbundlejob";
    }



    @GetMapping("/dashboard/exceptions/add")
    public String addForm(Model model) {
        model.addAttribute("exception", new ExceptionList_Unbundlejob());
        return "addnewubjobs";
    }

    @PostMapping("/dashboard/exceptions/save")
    public String save(@ModelAttribute ExceptionList_Unbundlejob exceptionList) {
        service.savenUnjob(exceptionList);
        return "redirect:/dashboard/exceptions";
    }

    @GetMapping("/dashboard/exceptions/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("exception", service.findByUnbjobId(id));
        return "addnewubjobs";
    }

    @GetMapping("/dashboard/exceptions/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteByUnbjobId(id);
        return "redirect:/dashboard/exceptions";
    }

    @GetMapping("/dashboard/loaddumpjobs")
    public String loaddump(@RequestParam(required = false) String flexjobs, Model model) {
        System.out.println(flexjobs);
        String data =       String.valueOf(flexjobs);
        // Handle null or empty input
        if (flexjobs == null || flexjobs.isEmpty()) {
            System.out.println("No flexjobs parameter provided.");
            model.addAttribute("list", new ArrayList<>()); // Add an empty list to the model
            return "dumploadjob";
        }

        // Process the non-null flexjobs string
        //String parsedString = "'" + flexjobs.replace(",", "','") + "'";
        System.out.println(data);

        // Assuming service.loaddumpofjobs is implemented to handle such a case
        List<List<Object>> list = service.loaddumpofjobs(data);


        System.out.println(list);



        model.addAttribute("list", list);

        return "dumploadjob";
    }


    @GetMapping("/dashboard/distrubutionprint")
    public String getdistrubutionprint(@RequestParam(value = "groupid", required = false) Integer groupid, Model model,
                                   @PageableDefault(page = 0, size = 3) Pageable pageable,
                                   //@RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) String message
    ) {

        Page<Assurantdistrubutionprint> page;
        if (groupid != null) {
            // List<AssurantEnvelopeProfile> profiless = service.findByCorrel(correl);
            page = service.findByGroupid(groupid,pageable);
            //page = service.getAllEnvelopes(pageable);
            model.addAttribute("profiles", page);

        } else {
            //profiles = repository.findAll();
            System.out.println("not null");
            page = service.getAlldistrubutionprint(pageable);
            model.addAttribute("profiles", page);
        }
        model.addAttribute("exceptions", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("correl", groupid);// Add correl to model for form binding
        return "distrubutionprint";
    }

    @PostMapping("/dashboard/distrubutionprint/update")
    public String updatedistrubutionprint(@ModelAttribute Assurantdistrubutionprint profile) {
        System.out.println("here issee");

        service.savedistrubutionprint(profile);
        return "redirect:/dashboard/distrubutionprint";
    }

    @GetMapping("/dashboard/loaddcw")
    public String loadpic(Model model) {
        // Perform operation logic here
        service.loadDcwStock();
        return "loaddcw";
    }


    @GetMapping("/dashboard/lds")
    public String loadDcwStock(Model model) {
        // Perform operation logic here
        service.loadDcwStock();
        return "redirect:/dashboard/loaddcw?success=true";
    }

    @GetMapping("/dashboard/iproduct")
    public String insertProduct(@RequestParam String agndpmt, Model model) {
        // Perform operation logic here
        service.insertProduct(agndpmt);
        return "redirect:/dashboard/loaddcw?success=true";
    }

    @GetMapping("/dashboard/cproduct")
    public String createProduct(Model model) {
        // Perform operation logic here
        service.createProduct();
        return "redirect:/dashboard/loaddcw?success=true";
    }

    //endregion

}
