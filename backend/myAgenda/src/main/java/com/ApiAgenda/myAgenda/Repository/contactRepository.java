package com.ApiAgenda.myAgenda.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ApiAgenda.myAgenda.Entity.Contact;
@Repository
public interface contactRepository extends CrudRepository<Contact, Integer> {

}
