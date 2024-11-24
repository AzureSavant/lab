package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Location;

import java.util.List;

public interface ILocationService {
    List<Location> findAll();
    Location getLocationById(Long id);
    void  addLocation(Location location);
}
