package data.web;

import data.model.DataSets;
import data.model.DataStats;
import data.services.FileStorage;
import data.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Controller
public class DataController {

    @Autowired
    FileStorage fileStorage;

    @RequestMapping(value = "/stats/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataStats> getDatasetStats(@PathVariable int id) {

        DataSets dataSets = JsonParser.parseJson("data.json");
        for (DataStats dataStats : dataSets.getDataStats()) {
            if (dataStats.getId() == id) {
                return new ResponseEntity<>(dataStats, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public String index() {
        return "multipartfile/uploadfile.html";
    }

    @PostMapping("/")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
        try {
            fileStorage.store(file);
            model.addAttribute("message", "File uploaded successfully! -> filename = " + file.getOriginalFilename());
        } catch (Exception e) {
            model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
        }
        return "multipartfile/uploadfile.html";
    }



}
