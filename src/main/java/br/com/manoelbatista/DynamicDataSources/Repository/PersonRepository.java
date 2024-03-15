/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.manoelbatista.DynamicDataSources.Repository;

import br.com.manoelbatista.DynamicDataSources.Model.Entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
    
}
