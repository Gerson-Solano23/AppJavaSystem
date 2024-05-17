package com.ApiAgenda.myAgenda.Controller;

import com.ApiAgenda.myAgenda.DTO.ContactDTO;
import com.ApiAgenda.myAgenda.DTO.ResponseDTO;
import com.ApiAgenda.myAgenda.Service.contactService;
import com.ApiAgenda.myAgenda.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
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
                response.setStatus(String.valueOf(HttpStatus.NO_CONTENT));
            }
        }catch (Exception e){
            response.setMessage("Error trying to get contact... \n"+e.getMessage());
            response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Contact>> getAll(){
        return ResponseEntity.ok(service.getAllContacts());
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCategory(@RequestBody ContactDTO contact){
        ResponseDTO response = new ResponseDTO();
        try{
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
        }catch (Exception e){
            response.setMessage("Error trying to create contact..." +e.getMessage());
            response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        }
        
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody ContactDTO contact, @RequestParam Integer id){
        ResponseDTO response = new ResponseDTO();
        try {
            response.setMessage("Succesufully, contact updated");
            response.setStatus("200");
            if(service.existContact(id)){
                Contact temp = service.getContact(id);
                if(service.isEmailAlreadyUsed(contact.getEmail()) && !temp.getEmail().equals(contact.getEmail())){
                    response.setMessage("Error, the email already exist in your contacts");
                    response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
                }else {
                    Contact contactTemp = service.updateContact(temp.getIdContact(), contact);
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
            response.setMessage("Error trying to update contact..." +e.getMessage());
            response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
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
            response.setMessage("Error trying to delete contact..." +e.getMessage());
            response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return ResponseEntity.ok(response);
    }
}
