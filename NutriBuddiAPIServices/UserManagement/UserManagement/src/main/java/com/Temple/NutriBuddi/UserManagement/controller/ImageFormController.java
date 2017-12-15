package com.Temple.NutriBuddi.UserManagement.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;
import com.Temple.NutriBuddi.UserManagement.repository.ImageFormRepository;

@Controller
@RequestMapping(path="/imageForm")
public class ImageFormController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageFormRepository imageFormRepository;

    static Logger LOG = Logger.getLogger(EatsController.class.getName());

    @GetMapping(path="/deleteImage")
    @ResponseBody
    public ResponseEntity<Object> deleteImage(@RequestParam int id){
        ResponseEntity response;

        try {
            imageFormRepository.deleteById(id);
            response = new ResponseEntity<>("Image successfully deleted.", HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>("Error with request", HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
