package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface ILocationService {
    List<Location> findAll();
    Optional<Location> getLocationById(Long id);
    void  addLocation(Location location);
}
