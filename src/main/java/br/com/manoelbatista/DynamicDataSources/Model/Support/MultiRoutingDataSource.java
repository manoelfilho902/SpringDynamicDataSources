/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.manoelbatista.DynamicDataSources.Model.Support;

import br.com.manoelbatista.DynamicDataSources.Config.DataBaseContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 *
 */
public class MultiRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataBaseContextHolder.getCurrentDataSource();
    }
    
    

}