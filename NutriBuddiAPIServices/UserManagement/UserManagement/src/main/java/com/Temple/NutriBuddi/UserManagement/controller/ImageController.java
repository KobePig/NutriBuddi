package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.model.Image;
import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.repository.EatsRepository;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;
import com.Temple.NutriBuddi.UserManagement.repository.ImageRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.rowset.serial.SerialBlob;
import javax.xml.ws.Response;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("imageClassifier")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EatsRepository eatsRepository;
    @Autowired
    private FoodRepository foodRepository;

    private static Logger LOG = Logger.getLogger(ImageController.class.getName());


    @GetMapping(path="/addNewImage") // Map ONLY GET Requests
    @ResponseBody
    public ResponseEntity<Object> addNewImage(@RequestParam String email,
                                              @RequestParam String foodName,
                                              @RequestParam String fileName,
                                              @RequestParam String numServing,
                                              @RequestParam String latitude,
                                              @RequestParam String longitude) {
        ResponseEntity response;
        Food food;
        User user;
        Eats eats;
        Image image;
        double lat;
        double lng;
        int classificationNumber;
        if(email.equals("") || email == null){
            response = new ResponseEntity<>("Email must not be empty", HttpStatus.NOT_ACCEPTABLE);

        } else if(foodName.equals("") || foodName == null){
            response = new ResponseEntity<>("Food name must not be empty", HttpStatus.NOT_ACCEPTABLE);
        } else if(fileName.equals("") || fileName == null){
            response = new ResponseEntity<>("File name must not be empty", HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (foodName.toLowerCase().equals("unknown")) {
                classificationNumber = 0;
            } else  {
                classificationNumber = 1;   //known food item
            }

            food = foodRepository.findByFoodName(foodName);
            if (food == null) {
                //TODO: add ability to self-heal by pulling & parsing USDA database json;
                //Check if it is a valid food by checking online using foodName
                //if it is a real type of food, then add it to the database
                //else if it is unknown add food group
                //else if it's fake tell user that this isn't a known/real food item

                //check if unknown field exists; if not, create it in food database
                if (foodRepository.findByFoodName("unknown") == null) {
                    food = new Food("unknown", "mg", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                    foodRepository.save(food);  //save because id isn't assigned to food else cascade error
                }
            }

            user = userRepository.findByEmail(email);
            if (user == null) {
                return new ResponseEntity<>("User not found with provided email", HttpStatus.CONFLICT);
            }

            try {
                image = new Image(foodName, fileName);

                if(latitude != "" || longitude != ""){
                    try{
                        lat = Double.parseDouble(latitude);
                        lng = Double.parseDouble(longitude);
                        image = new Image(foodName, fileName, lat, lng);
                    } catch (IllegalFormatException e){
                        return new ResponseEntity<>("Coordinates must be a number", HttpStatus.NOT_ACCEPTABLE);
                    }
                }

                imageRepository.save(image);
                eats = new Eats(user, Integer.parseInt(numServing), food, image, classificationNumber);
                eatsRepository.save(eats);
                response = new ResponseEntity<>("Image saved", HttpStatus.OK);
            } catch (NumberFormatException e) {
                LOG.info(e.getMessage());
                response = new ResponseEntity<>("Error casting number of servings", HttpStatus.CONFLICT);
            }
        }
        return response;
    }

    @GetMapping(path="/deleteImage")
    @ResponseBody
    public ResponseEntity<Object> deleteImageResource (@RequestParam String email,
                                                       @RequestParam String fileName) {
        ResponseEntity response = null;
        User user;

        if(email.equals("") || email == null){
            response = new ResponseEntity<>("Email must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }
        if(fileName.equals("") || fileName == null){
            response = new ResponseEntity<>("File name must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        user = userRepository.findByEmail(email);
        if(user == null){
            response = new ResponseEntity<>("User not found with provided email", HttpStatus.CONFLICT);
        }

        try{
            imageRepository.deleteByFileName(fileName);
        }catch(Exception e){
            response = new ResponseEntity<>("Image not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping(path="/updateImage")
    @ResponseBody
    public ResponseEntity<Object> updateImageResource (@RequestParam String email,
                                                       @RequestParam String oldFileName,
                                                       @RequestParam String newFileName,
                                                       @RequestParam String latitude,
                                                       @RequestParam String longitude) {
        Image currentImage;
        ResponseEntity response = null;
        User user;

        if(email.equals("") || email == null){
            response = new ResponseEntity<>("Email must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }
        if(oldFileName.equals("") || oldFileName == null){
            response = new ResponseEntity<>("File name must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        user = userRepository.findByEmail(email);
        if(user == null){
            response = new ResponseEntity<>("User not found with provided email", HttpStatus.CONFLICT);
        }

        try{
            currentImage = imageRepository.findByFileName(oldFileName);
            currentImage.setFileName(newFileName);

            if(latitude != "" || longitude != ""){
                try{
                    Double lat = Double.parseDouble(latitude);
                    Double lng = Double.parseDouble(longitude);

                    currentImage.setLatitude(lat);
                    currentImage.setLongitude(lng);
                } catch (IllegalFormatException e){
                    return new ResponseEntity<>("Coordinates must be a number", HttpStatus.NOT_ACCEPTABLE);
                }
            }
        }catch(Exception e){
            response = new ResponseEntity<>("Image not found", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping(path="/findImagesByEmail")
    @ResponseBody
    public ResponseEntity<Object> queryForImageResourceByEmail(@RequestParam String email){
        if(email.equals("") || email == null){
            return new ResponseEntity<>("Email must not be empty", HttpStatus.NOT_ACCEPTABLE);
        }
        List<Image> imageList = imageRepository.findByEmail(email);
        if(imageList == null){
            return new ResponseEntity<>("No image found", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(imageList, HttpStatus.OK);
        }
    }
}
