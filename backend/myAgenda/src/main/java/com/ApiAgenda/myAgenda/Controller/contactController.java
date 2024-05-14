package com.ApiAgenda.myAgenda.Controller;

import com.ApiAgenda.myAgenda.Service.contactService;
import com.ApiAgenda.myAgenda.Entity.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/contact")
public class contactController {
    @Autowired
    private contactService service;
    @GetMapping("/index")
    public String index(){
        return "Hello World";
    }
    @GetMapping("/get")
    public ResponseEntity<?> getContactById(Integer id){
        ResponseDTO response = new ResponseDTO();
        try {
            if(service.existContact(id)){
               return ResponseEntity.ok(service.getContact(id));
            }else{
                response.setMessage("Error, the contact does not exist with id:"+id);
                response.setStatus("200");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error tring to get contact");
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Contact>> getAll(){
        return ResponseEntity.ok(service.getAllContacts());
    }
    @PutMapping("/create")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody Contact contact){
        ResponseDTO response = new ResponseDTO();
        response.setMessage("Succesufully, contact save");
        response.setStatus(String.valueOf(HttpStatus.CREATED));
        if(service.isEmailAlreadyUsed(contact.getEmail())){
            response.setMessage("Error, the email already exist in your contacts");
            response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
        }else {
            Contact contactTemp = service.addContact(contact);
            if (contactTemp == null){
                response.setMessage("Error, contact do not save");
                response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
            }
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Contact contact){
        ResponseDTO response = new ResponseDTO();
        try {
            response.setMessage("Succesufully, contact updated");
            response.setStatus("200");
            if(service.existContact(contact.getIdContact())){
                Contact temp = service.getContact(contact.getIdContact());
                if(service.isEmailAlreadyUsed(contact.getEmail()) && !temp.getEmail().equals(contact.getEmail())){
                    response.setMessage("Error, the email already exist in your contacts");
                    response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
                }else {
                    Contact contactTemp = service.updateContact(contact);
                    if (contactTemp == null) {
                        response.setMessage("Error, contact not updated");
                        response.setStatus("200");
                    }
                }
            }else{
                response.setMessage("Error, contact does not exist");
                response.setStatus("200");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error tring to update contact");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(Integer id){
        ResponseDTO response = new ResponseDTO();
        try {

            if(service.existContact(id)){
                boolean isDeleted = service.deleteContact(id);
                if (isDeleted){
                    response.setMessage("Succesufully, contact deleted");
                    response.setStatus("200");
                }else{
                    response.setMessage("Error, contact not deleted");
                    response.setStatus("200");
                }
            }else{
                response.setMessage("Error, the contact does not exist with id:"+id);
                response.setStatus("200");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error tring to update contact");
        }
        return ResponseEntity.ok(response);
    }
}
