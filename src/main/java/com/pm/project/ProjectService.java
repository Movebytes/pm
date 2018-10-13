package com.pm.project;

import com.pm.project.entity.ProjectEntity;
import com.pm.shared.AbstractService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProjectService extends AbstractService<ProjectEntity, Integer> {
    @PersistenceContext
    private EntityManager em;

    public List<ProjectEntity> getAll() {
        return em.createQuery(ProjectQuery.ALL)
                .getResultList();
    }

    public ProjectEntity getEntityById(Integer id) {
        return em.find(ProjectEntity.class, id);
    }

    public ProjectEntity update(ProjectEntity entity) {
        return em.merge(entity);
    }

    public void delete(ProjectEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public void create(ProjectEntity entity) {
        em.persist(entity);
    }
}
