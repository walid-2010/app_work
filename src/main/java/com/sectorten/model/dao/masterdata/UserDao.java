/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao.masterdata;

import com.sectorten.model.common.QueryNamesEnum;
import com.sectorten.model.dao.AbstractDao;
import com.sectorten.model.entities.masterdata.User;
import com.sectorten.model.entities.masterdata.User_;
import com.sectorten.model.exception.BusinessLogicViolationException;
import com.sectorten.model.exception.EmptyResultSetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author nbb
 */
@Stateless
public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User authunticateUser(String uName, String password) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<User> c = cb.createQuery(User.class);
            Root<User> varRoot = c.from(User.class);

            c.select(varRoot).distinct(true);

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(varRoot.get(User_.username), uName));
            predicates.add(cb.equal(varRoot.get(User_.password), password));
            c.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            TypedQuery typedQuery = getEntityManager().createQuery(c);
            return (User) typedQuery.getSingleResult();
        } catch (Exception exception) {
            throw exception;
        }

    }

    public User authenticateUser(String userName, String password) throws Exception, EmptyResultSetException,
            BusinessLogicViolationException {
        User user = null;
        try {
            List<User> userList = searchUsers(userName);
            if (userList != null && !userList.isEmpty()) {
                user = userList.get(0);
                if (!(password).equals(user.getPassword())) {
                    throw new EmptyResultSetException("error_invalidPassword");
                }
            } else {
                throw new EmptyResultSetException("error_invalidUserName");
            }
        } catch (BusinessLogicViolationException businessLogicViolationException) {
            throw businessLogicViolationException;
        } catch (EmptyResultSetException emptyResultSetException) {
            throw emptyResultSetException;
        } catch (Exception e) {

            throw e;
        }
        return user;
    }

    public User getUser(String userId) throws BusinessLogicViolationException {
        List<User> users = searchUsers(userId);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }

    private List<User> searchUsers(String userName) throws BusinessLogicViolationException {
        List<User> result = null;
        try {
            Map<String, Object> qParams = new HashMap<>();
            qParams.put("USER_NAME", userName);
            result = executeNamedQuery(QueryNamesEnum.USER_SEARCH_USER.getCode(), qParams);
        } catch (DatabaseException e) {

            throw new BusinessLogicViolationException("error_general");
        } catch (NoResultException ex) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<Predicate> buildPredicateList(Root<User> entityRoot, User userObj, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (userObj.getId() != null) {

            predicates.add(cb.equal(entityRoot.get(User_.id), userObj.getId()));

        } else {

            if (userObj.getFirstName() != null && !userObj.getFirstName().equals("")) {
                predicates.add(cb.like(entityRoot.get(User_.firstName), "%" + userObj.getFirstName() + "%"));

            }

            predicates.add(cb.equal(entityRoot.get(User_.isAdmin), userObj.getIsAdmin()));

            if (userObj.getSecondName() != null && !userObj.getSecondName().equals("")) {
                predicates.add(cb.like(entityRoot.get(User_.secondName), "%" + userObj.getSecondName() + "%"));

            }

            if (userObj.getLastName() != null && !userObj.getLastName().equals("")) {
                predicates.add(cb.like(entityRoot.get(User_.lastName), "%" + userObj.getLastName() + "%"));

            }
            if (userObj.getUsername() != null && !userObj.getUsername().equals("")) {
                predicates.add(cb.like(entityRoot.get(User_.username), "%" + userObj.getUsername() + "%"));

            }
        }

        return predicates;
    }

    @Override
    public List<Order> buildDefaultSortOrderList(Root<User> entityRoot, CriteriaBuilder cb) {

        List<Order> sortOrderList = new ArrayList<>();
        sortOrderList.add(cb.desc(entityRoot.get(User_.id)));

        return sortOrderList;

    }

}
