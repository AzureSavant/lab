package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Location;

import java.util.List;

public interface ILocationService {
    public List<Location> findAll();
    public Location getLocationById(int id);
}
