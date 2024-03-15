/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.manoelbatista.DynamicDataSources.Service;

import br.com.manoelbatista.DynamicDataSources.Model.Entity.Person;
import br.com.manoelbatista.DynamicDataSources.Repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manoel Batista <manoelbatista902@gmail.com>
 */
@Service
public class PersonService{
    @Autowired
    private PersonRepository repository;

    
    public <S extends Person> S save(S entity) {
        return repository.save(entity);
    }

    
    public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    
    public Optional<Person> findById(Long id) {
        return repository.findById(id);
    }

    
    
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    
      
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    
    public void delete(Person entity) {
        repository.delete(entity);
    }
   
}
