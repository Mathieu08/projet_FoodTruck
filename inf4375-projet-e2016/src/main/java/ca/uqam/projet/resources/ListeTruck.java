package ca.uqam.projet.resources;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListeTruck {
	@JsonProperty("type") String type;
	@JsonProperty("features") FoodTruck truck[];

    public List<FoodTruck> getTruck() { return Arrays.asList(truck);}

}