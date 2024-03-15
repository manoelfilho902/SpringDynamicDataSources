package br.com.manoelbatista.DynamicDataSources.Controller;

import br.com.manoelbatista.DynamicDataSources.Config.DataBaseContextHolder;
import br.com.manoelbatista.DynamicDataSources.Config.DataConfig;
import br.com.manoelbatista.DynamicDataSources.Model.Support.DataSetInfo;
import br.com.manoelbatista.DynamicDataSources.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manoel Batista <manoelbatista902@gmail.com>
 * Main controller to test
 */
@RestController
@RequestMapping("/")
@CrossOrigin
public class Main {
    @Autowired
    private PersonService service;
    /**
     * If application security is already configured, this endpoint must be released after authentication on AuthFilter, as it will not have a valid data source
     * @param key identificaton of this data source
     * @param dsi the data source info
     * @return
     */
    @PostMapping("/add_new_ds")
    public ResponseEntity<Object> addNewDS(@RequestParam("key") String key, @RequestBody DataSetInfo dsi) {
        DataConfig.AddNewDataSource(key, dsi);
        
        return ResponseEntity.ok(key);
    }
    
    @GetMapping("/test_new_ds")
    public ResponseEntity<Object> testNewDS(@RequestParam("key") String key){
        DataBaseContextHolder.setCurrentDataSource(key);
        
        return ResponseEntity.ok(service.findById((long) 1));
    }
}
