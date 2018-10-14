package com.pm.user;

import com.pm.shared.AbstractService;
import com.pm.user.entity.UserEntity;
import com.pm.user.model.UserStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserService extends AbstractService<UserEntity, Integer> {
    @PersistenceContext
    private EntityManager em;

    public List<UserEntity> getAll() {
        return em.createQuery(UserQuery.ALL)
                .getResultList();
    }

    public List<UserEntity> getActiveUsers() {
        return em.createQuery(UserQuery.ALL_ACTIVE)
                .setParameter(1, UserStatus.ACTIVE)
                .getResultList();
    }

    public UserEntity getEntityById(Integer id) {
        return em.find(UserEntity.class, id);
    }

    public UserEntity update(UserEntity entity) {
        return em.merge(entity);
    }

    public void delete(UserEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public void create(UserEntity entity) {
        em.persist(entity);
    }
}
