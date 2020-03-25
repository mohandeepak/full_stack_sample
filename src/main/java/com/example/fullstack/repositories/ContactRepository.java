package com.example.fullstack.repositories;

import com.example.fullstack.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact,String> {
    @Override
    void delete(Contact deleted);
}
