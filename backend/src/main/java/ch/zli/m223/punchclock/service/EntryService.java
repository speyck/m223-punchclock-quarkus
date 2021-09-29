package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;
import io.quarkus.security.Authenticated;

@Authenticated
@ApplicationScoped
public class EntryService implements IEntityService<Entry> {
    @Inject
    private EntityManager entityManager;

    @Transactional 
    public Entry create(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @SuppressWarnings("unchecked")
    public List<Entry> list() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }
    
    public Entry get(Long id) {
        return entityManager.find(Entry.class, id);
    }

    @Transactional
    public void delete(Long id) {
        Entry found = get(id);
        entityManager.remove(found);
    }

    @Transactional
    public Entry update(Entry entry) {
        return entityManager.merge(entry);
    }
}
