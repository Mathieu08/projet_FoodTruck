package ca.uqam.projet.controllers;

import java.util.*;
import java.time.*;

import ca.uqam.projet.repositories.*;
import ca.uqam.projet.resources.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CitationController {

  @Autowired CitationRepository repository;
  @Autowired FoodTruckController repo;

  @RequestMapping("/citations")
  public List<Citation> findAll() {
    return repository.findAll();
  }

  @RequestMapping("/citations/{id}")
  public Citation findById(@PathVariable("id") int id) {
    return repository.findById(id);
  }

  @RequestMapping("/trucks")
  public List<Truck> findAllT() {
  	return repo.findAll();
  }

  @RequestMapping("/horaires-camions")
  public List<Truck> findAllByDate(@RequestParam(value = "du") String dateDebut,
                                   @RequestParam(value = "au") String dateFin) {
      return repo.findAllByDate(dateDebut, dateFin);
  }

}
