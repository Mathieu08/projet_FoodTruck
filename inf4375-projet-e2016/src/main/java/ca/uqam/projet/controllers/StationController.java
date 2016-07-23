package ca.uqam.projet.controllers;

import java.util.*;
import java.time.*;

import ca.uqam.projet.repositories.*;
import ca.uqam.projet.tasks.*;
import ca.uqam.projet.resources.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class StationController {

  @Autowired StationRepository repoStations;

  @RequestMapping("/stations")
  public List<StationBixi> findAllByDistance(@RequestParam(value = "lon") Double lon,
                                             @RequestParam(value = "lat") Double lat) {
      return repoStations.findAllByDistance(lon, lat);
  }

}
