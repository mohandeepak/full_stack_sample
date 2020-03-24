package com.example.fullstack.Controllers;

import com.example.fullstack.models.Contact;
import com.example.fullstack.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController //First step : Annotate as Rest Controller
public class ContactController {

    @Autowired  //Second Step: Autowire Repository
    ContactRepository contactRepository;

    //Third Step: Perform CRUD operations (get all, save, get using id, update and delete)

    @RequestMapping(method = RequestMethod.GET,value = "/contacts")
    public Iterable<Contact> getAllContact(){
        return contactRepository.findAll();//you could also just call a service here and implement the logic in service layer
    }

    @RequestMapping(method = RequestMethod.GET,value = "/contacts/{id}")
    public Optional<Contact> getContact(@PathVariable  String id){
        return contactRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/contacts")
    public Contact saveContact(@RequestBody Contact contact){
        return contactRepository.save(contact);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/contacts/{id}")
    public Contact updateContact(@PathVariable String id, @RequestBody Contact contact){
        Optional<Contact> optcontact = contactRepository.findById(id);
        Contact c= optcontact.get();
        if(contact.getName()!=null)
            c.setName(contact.getName());
        if(contact.getAddress() != null)
            c.setAddress(contact.getAddress());
        if(contact.getCity() != null)
            c.setCity(contact.getCity());
        if(contact.getPhone() != null)
            c.setPhone(contact.getPhone());
        if(contact.getEmail() != null)
            c.setEmail(contact.getEmail());
        contactRepository.save(c);
        return c;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/contacts/{id}")
    public String deleteContact(@PathVariable String id){
        Optional<Contact> optcontact =contactRepository.findById(id);
        Contact c= optcontact.get();
        contactRepository.delete(c);
        return  "";
    }

}
