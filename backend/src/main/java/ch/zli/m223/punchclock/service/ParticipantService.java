package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Participant;
import io.quarkus.security.Authenticated;

@Authenticated
@ApplicationScoped
public class ParticipantService implements IEntityService<Participant> {
    @Inject
    private EntityManager entityManager;

    @Transactional 
    public Participant create(Participant participant) {
        entityManager.persist(participant);
        return participant;
    }

    @SuppressWarnings("unchecked")
    public List<Participant> list() {
        Query query = entityManager.createQuery("FROM Participant");
        return query.getResultList();
    }
    
    public Participant get(Long id) {
        return entityManager.find(Participant.class, id);
    }

    @Transactional
    public void delete(Long id) {
        Participant found = get(id);
        entityManager.remove(found);
    }

    @Transactional
    public Participant update(Participant participant) {
        return entityManager.merge(participant);
    }
}
