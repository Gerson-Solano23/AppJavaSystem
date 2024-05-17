package com.ApiAgenda.myAgenda.Service;

import com.ApiAgenda.myAgenda.DTO.ContactDTO;
import com.ApiAgenda.myAgenda.Entity.Contact;
import com.ApiAgenda.myAgenda.Repository.contactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class contactService {
    @Autowired
    private contactRepository contactRepository;

    public Contact addContact(ContactDTO contactDTO){
        ModelMapper  mapper = new ModelMapper();
        Contact contact = new Contact();
        mapper.map(contactDTO, contact);
        return contactRepository.save(contact);
    }
    public Contact getContact(Integer idContact){return contactRepository.findById(idContact).orElse(null);}
    public List<Contact> getAllContacts(){return (List<Contact>) contactRepository.findAll();}
    public Contact updateContact(int id, ContactDTO contactDTO){
        ModelMapper  mapper = new ModelMapper();

        Contact existContact = contactRepository.findById(id).orElse(null);
        mapper.map(contactDTO, existContact);

        return contactRepository.save(existContact);
    }

    public boolean deleteContact(Integer id){
        boolean result = true;
        try {
            contactRepository.deleteById(id);
            if (existContact(id)) result = false;
        }catch (Exception e) {
            result = false;
        }
        return result;
    }
    public boolean existContact(Integer id){
        return contactRepository.existsById(id);
    }

    public boolean isEmailAlreadyUsed(String email){
        Optional<Contact> contact = contactRepository.getByEmail(email);
        return contact.isPresent();
    }
}
