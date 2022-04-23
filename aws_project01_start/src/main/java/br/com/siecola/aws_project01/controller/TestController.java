package br.com.siecola.aws_project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        LOG.info("Test controller - name: {}", name);

        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping("/dog/color")
    public ResponseEntity<?> dogColor() {
        LOG.info("Test controller - Always black!");

        return ResponseEntity.ok("Always black!");
    }

    @GetMapping("/dog")
    public ResponseEntity<List<String>> getAllDogs() {
        List<String> dogs = new ArrayList<String>();
        dogs.add("Shitzu");
        dogs.add("Labrador");
        dogs.add("Vira lata");
        return ResponseEntity.ok(dogs);
    }
}
