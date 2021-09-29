package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Location;
import io.quarkus.security.Authenticated;

@Authenticated
@ApplicationScoped
public class LocationService implements IEntityService<Location> {
    @Inject
    private EntityManager entityManager;

    @Transactional 
    public Location create(Location location) {
        entityManager.persist(location);
        return location;
    }

    @SuppressWarnings("unchecked")
    public List<Location> list() {
        Query query = entityManager.createQuery("FROM Location");
        return query.getResultList();
    }
    
    public Location get(Long id) {
        return entityManager.find(Location.class, id);
    }

    @Transactional
    public void delete(Long id) {
        Location found = get(id);
        entityManager.remove(found);
    }

    @Transactional
    public Location update(Location location) {
        return entityManager.merge(location);
    }
}
