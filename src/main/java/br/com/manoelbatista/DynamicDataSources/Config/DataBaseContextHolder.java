/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.manoelbatista.DynamicDataSources.Config;

import br.com.manoelbatista.DynamicDataSources.Exception.DataSourceConfigErrorException;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
public class DataBaseContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>(); // string with the dataSource ID ex: primaryLocalDB

    /**
     * Used by spring data Thead to locate a requested DataSource, in web
     * context this have a good potential.
     *
     * @return
     */
    public static String getCurrentDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * this method change the data source to actual thead
     *
     * @param dataSetID
     */
    public static void setCurrentDataSource(String dataSetID) throws DataSourceConfigErrorException {
        //validate id
        if (DataConfig.KeyExists(dataSetID) && dataSetID != null) {
            CONTEXT_HOLDER.set(dataSetID);
        } else {
            throw new DataSourceConfigErrorException("The requested data source(%s) not exists!".formatted(dataSetID));
        }

    }

}
