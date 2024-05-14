package com.ApiAgenda.myAgenda.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ApiAgenda.myAgenda.Entity.Contact;

import java.util.Optional;

@Repository
public interface contactRepository extends CrudRepository<Contact, Integer> {

    @Query("SELECT c FROM Contact c WHERE c.email = ?1")
    Optional<Contact> getByEmail(String email);

}
