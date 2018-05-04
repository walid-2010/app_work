/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao;

import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.exception.BusinessLogicViolationException;
import com.sectorten.model.exception.EmptyResultSetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.swing.SortOrder;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author
 */
public abstract class AbstractDao<T> extends BaseDao {

    private Class<T> entityClass;

    private String directSuperClassName = "";  //added for multiple company

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
        directSuperClassName = entityClass.getGenericSuperclass().toString();//added for multiple company
    }

    public T findWithParents(T entity) {
        return entity;
    }

    public <T> List<T> executeNamedQuery(String queryName, Map<String, Object> parameters) throws DatabaseException, NoResultException {
        return executeNamedQuery(queryName, parameters, entityClass);
    }

    public <T> List<T> executeNamedQuery(String queryName, Map<String, Object> parameters, Class entityClass) throws DatabaseException, NoResultException {
        try {
            Query q = getEntityManager().createNamedQuery(queryName, entityClass);
            if (parameters != null) {
                for (String paramName : parameters.keySet()) {
                    Object value = parameters.get(paramName);
                    if (value instanceof Date) {
                        q.setParameter(paramName, (Date) value, TemporalType.TIMESTAMP);
                    } else {
                        q.setParameter(paramName, value);
                    }
                }
            }
            List<T> result = q.getResultList();
            if (result == null || result.isEmpty()) {
                // throw new EmptyResultSetException("message_noDataFound");
            }
            return result;
        } catch (NoResultException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessLogicViolationException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
        try {
            Query query = getEntityManager().createNamedQuery(namedQuery);
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = (T) query.getSingleResult();
        } catch (NoResultException e) {
            // info("No result found for named query: " + namedQuery);
        } catch (Exception e) {

        }
        return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    //CRUD functions
    public void create(T entity) {

        getEntityManager().persist(entity);

    }

    public T edit(T entity) {
        entity = getEntityManager().merge(entity);
        return entity;

    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public void remove(T entity) throws Exception {
        // getEntityManager().remove(getEntityManager().merge(entity));

        Object identifier = getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        T entityObject = find(identifier);
        if (entityObject == null) {
            throw new BusinessLogicViolationException("entity_not_found");
        }

        getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();

        if (directSuperClassName.contains(ConstantStrings.COMPANY_BASE_ENTITY)) {
            List<Predicate> predicates = new ArrayList<>();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);

            cq.where(getEntityManager().getCriteriaBuilder().and(predicates.toArray(new Predicate[predicates.size()])));
            cq.select(rt);
        } else {
            cq.select(cq.from(entityClass));
        }
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Methods added manually
     *
     *
     */
    /**
     * find entity by any search parameters and can order the result and can
     * fetch lazy relation and can be used for get data with start index and max
     * size
     *
     */
    public List<T> findAllEntities(Map searchParMap, List<String> fetchList, int startdIndex, int pageSize, String sortField, SortOrder sortOrder)
            throws EmptyResultSetException {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery criteriaQuery = cb.createQuery();

        if (criteriaQuery != null) {
            Root<T> entityRoot = criteriaQuery.from(entityClass);

            if (sortOrder == null) {
                sortOrder = SortOrder.ASCENDING;
            }

            List<Predicate> predicates = buildPredicateList(entityRoot, searchParMap, cb);
            if (!predicates.isEmpty()) {
                criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            }
            if (fetchList != null) {
                buildFetchList(entityRoot, fetchList);
            }
            criteriaQuery.select(entityRoot);

            if (sortField != null) {
                if (sortOrder.equals(SortOrder.ASCENDING)) {
                    criteriaQuery.orderBy(cb.asc(entityRoot.get(sortField)));
                } else if (sortOrder.equals(SortOrder.DESCENDING)) {
                    criteriaQuery.orderBy(cb.desc(entityRoot.get(sortField)));
                }
            }

            TypedQuery typedQuery = getEntityManager().createQuery(criteriaQuery);

            if (pageSize > 0) {
                typedQuery.setMaxResults(pageSize);
            }
            if (startdIndex > 0) {
                typedQuery.setFirstResult(startdIndex);
            }

            List<T> nResult = typedQuery.getResultList();

            if (nResult.isEmpty()) {
                //return new ArrayList<>();
                throw new EmptyResultSetException("error.no.data.found");
            }
            return nResult;
        }

        return new ArrayList<>();
    }

    public int findAllEntitiesCount(Map searchParMap, int startdIndex, int pageSize, String sortField, SortOrder sortOrder) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        if (criteriaQuery != null) {
            Root<T> entityRoot = criteriaQuery.from(entityClass);
            List<Predicate> predicates = buildPredicateList(entityRoot, searchParMap, cb);

            if (!predicates.isEmpty()) {
                criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
            }
            criteriaQuery.select(cb.count(entityRoot));

            TypedQuery typedQuery = getEntityManager().createQuery(criteriaQuery);

            Long count = (Long) typedQuery.getSingleResult();

            return count.intValue();
        }
        return 0;
    }

    /**
     * execute any named query with/without parameters return list of object as
     * a result
     *
     */
    public List<T> excuteNamedQuerymultipleResult(String namedQueryName, Map params)
            throws EmptyResultSetException {
        Query q = getEntityManager().createNamedQuery(namedQueryName, entityClass);
        String key;
        if (directSuperClassName.contains(ConstantStrings.COMPANY_BASE_ENTITY)) {
            if (params == null) {
                params = new HashMap();
            }

        }

        if (params != null) {
            for (Iterator<Object> itr = params.keySet().iterator(); itr.hasNext();) {
                key = (String) itr.next();
                q.setParameter(key, params.get(key));
            }

        }

        List<T> nResult = q.getResultList();
        if (nResult.isEmpty()) {
            // return new ArrayList<>();
            throw new EmptyResultSetException("error.no.data.found");
        }
        return nResult;
    }

    /**
     * execute any named query with/without parameters return single result of
     * type entity exception happen when add parameters to the map that didn't
     * exist on the named query so be careful
     *
     */
    public T excuteNamedQuerySingleResult(String namedQueryName, Map params)
            throws EmptyResultSetException {

        try {
            Query q = getEntityManager().createNamedQuery(namedQueryName, entityClass);

            String key;
            if (directSuperClassName.contains(ConstantStrings.COMPANY_BASE_ENTITY)) {
                if (params == null) {
                    params = new HashMap();
                }

            }
            if (params != null) {
                for (Iterator<Object> itr = params.keySet().iterator(); itr.hasNext();) {
                    key = (String) itr.next();
                    q.setParameter(key, params.get(key));
                }

            }

            return (T) q.getSingleResult();
        } catch (NoResultException e) {
            throw new EmptyResultSetException("error.no.data.found");
            // return null;
        }

    }

    /**
     * this method find all entities with depth mapping and can fetch any
     * relation for the selected object beanClass--> entity name i select it
     * fetchjoin-->map for the objects i want to fetch on the same query key-->
     * is the object i want to fetch(even if more one level depth) value--> is
     * the illus for that object example:Map fetchjoin=new HashMap();
     * fetchjoin.put("degreeId", "deg");
     *
     * propertyMap--> map with the condition i want key--> may take one of the
     * following property -->when i make a condition on a field inside my entity
     * ex.Map map=new HashMap(); map.put("name", "'xyz'"); (illus i put on
     * fetchjoin map).property --> when i make a condition on a field inside an
     * object i fetched it and gave it an illus ex: map.put("deg.name",
     * "'degree2'"); bean.object.property -->when i make a condition on a field
     * inside an object and don't want to fetch it note that i must but bean
     * keyword ex: map.put("bean.qualificationId.name", "'degree2'");
     * value-->the value of condition
     *
     */
    public List<T> findAllEntitiesWithDepthMapping(Class beanClass, Map fetchjoin, Map propertyMap, int startdIndex, int pageSize, List<Object> nOrder, SortOrder sortOrder)
            throws EmptyResultSetException {
        try {
            String key, value;
            String queryString = "";

            queryString += "select bean from " + beanClass.getSimpleName() + " bean ";

            if (fetchjoin != null) {
                for (Iterator<Object> itr = fetchjoin.keySet().iterator(); itr.hasNext();) {
                    key = (String) itr.next();
                    if (!key.contains(".")) {
                        queryString += " join fetch bean." + key;
                    } else {
                        queryString += " join fetch  " + key;//for more depth mapping
                    }
                    queryString += " " + fetchjoin.get(key); //illus
                    //q.setParameter(key, fetchjoin.get(key));
                }

            }

            boolean andFlag = false;

            if (directSuperClassName.contains(ConstantStrings.COMPANY_BASE_ENTITY)) {
                if (propertyMap == null) {
                    propertyMap = new HashMap();
                }

            }

            if (propertyMap != null) {

                for (Iterator<Object> itr = propertyMap.keySet().iterator(); itr.hasNext();) {
                    if (andFlag) {
                        queryString += " and";
                    } else {
                        queryString += " where";
                    }

                    key = (String) itr.next();
                    value = String.valueOf(propertyMap.get(key));

                    if (!key.contains(".")) {
                        queryString += " bean." + key;//fields on the entity
                    } else {
                        queryString += " " + key;//with illus
                    }
                    // queryString += " " + key;//" bean."+key;
                    if (!value.contains("!=") && !value.equals("<>") && !value.equals("is null") && !value.equals("is not null")
                            && !value.contains("like ") && !value.contains(">") && !value.contains("<") && value.indexOf("in ") != 0
                            && value.indexOf("not in ") != 0 && !value.contains(">=") && !value.contains("<=")) {
                        queryString += " = ";
                    }
                    queryString += " " + value + " ";

                    andFlag = true;
                }
            }

            if (nOrder != null) {
                queryString += " ORDER BY  ";
                String order = "";

                Iterator itr = nOrder.iterator();
                while (itr.hasNext()) {
                    order = (String) itr.next();
                    queryString += " bean." + order;
                    if (itr.hasNext()) {
                        queryString += " , ";
                    }
                }
                if (!sortOrder.equals(null)) {
                    if (sortOrder.equals(SortOrder.ASCENDING)) {
                        queryString += " asc ";
                    } else if (sortOrder.equals(SortOrder.DESCENDING)) {
                        queryString += " desc ";
                    }
                }
            }

            Query query = getEntityManager().createQuery(queryString);

            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }
            if (startdIndex > 0) {
                query.setFirstResult(startdIndex);
            }

            List<T> nResult = query.getResultList();
            if (nResult.isEmpty()) {
                // return new ArrayList<>();
                throw new EmptyResultSetException("error.no.data.found");
            }
            return nResult;
            // return getEntityManager().createQuery(sb).getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(Object id) throws Exception {
        T entity = this.find(id);
        this.remove(entity);

    }

    public List<T> testJoinFetchWithDepthMapping() {

        String queryString = "";
        queryString += " select e from Employee e join fetch e.departmentId dept where dept.name='HR' ";

        Query query = getEntityManager().createQuery(queryString);
        List<T> nResult = query.getResultList();
        return nResult;
    }

    public List<Predicate> buildPredicateList(Root<T> entityRoot, Map searchParMap, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();
        String key;
        if (searchParMap != null) {
            for (Iterator<Object> itr = searchParMap.keySet().iterator(); itr.hasNext();) {
                key = (String) itr.next();
                predicates.add(cb.equal(entityRoot.get(key), searchParMap.get(key)));
            }

        }

        return predicates;
    }

    private Root<T> buildFetchList(Root<T> entityRoot, List<String> fetchList) {
        String obj;
        for (Iterator<String> itr = fetchList.iterator(); itr.hasNext();) {
            obj = (String) itr.next();
            entityRoot.fetch(obj);
        }

        return entityRoot;
    }

    public Class getEntityClassByName(String entityName) {

        Class myclass = null;

        Set<EntityType<?>> enTYpes = getEntityManager().getMetamodel().getEntities();

        for (EntityType en : enTYpes) {

            if (en.getName().equals(entityName)) {
                myclass = en.getJavaType();
                break;
            }

        }
        return myclass;

    }

    private List<Predicate> getPredicates(CriteriaBuilder cb, Root<T> entityRoot, Map<String, Object> filters) {
        javax.persistence.metamodel.Metamodel entityModel = this.getEntityManager().getMetamodel();
        javax.persistence.metamodel.ManagedType<T> entityType = entityModel.managedType(entityClass);
        String fieldTypeName;
        // Add predicates (WHERE clauses) based on filters map
        List<javax.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();
        for (String s : filters.keySet()) {
            javax.persistence.criteria.Path<Object> pkFieldPath = null;
            if (s.contains(".")) {
                String[] fileds = s.split("\\.");
                pkFieldPath = entityRoot.get(fileds[0]);
                for (int i = 1; i < fileds.length; i++) {
                    pkFieldPath = pkFieldPath.get(fileds[i]);

                }
                fieldTypeName = "String";
            } else {
                pkFieldPath = entityRoot.get(s);
                fieldTypeName = entityType.getAttribute(s).getJavaType().getName();
            }
            if (pkFieldPath != null && fieldTypeName != null) {
                if (fieldTypeName.contains("String")) {
                    predicates.add(cb.like(cb.lower((javax.persistence.criteria.Expression) pkFieldPath), "%" + filters.get(s).toString().trim().toLowerCase() + "%"));
                } else if (fieldTypeName.contains("Date")) {

                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

                    String dateField = dateFormatter.format(filters.get(s));

                    Expression<String> dateStringExpr = cb.function("TO_CHAR", String.class, pkFieldPath, cb.literal(dateFormatter.toPattern()));
                    predicates.add(cb.equal(dateStringExpr, dateField));

                    //predicates.add(cb.equal(cb.function("TO_DATE", Date.class, pkFieldPath), (filters.get(s))));
                } else {
                    javax.persistence.criteria.Expression<?> filterExpression = getCastExpression((filters.get(s).toString()).trim(), fieldTypeName, cb);
                    if (filterExpression != null) {
                        predicates.add(cb.equal((javax.persistence.criteria.Expression<?>) pkFieldPath, filterExpression));
                    } else {
                        predicates.add(cb.equal((javax.persistence.criteria.Expression<?>) pkFieldPath, (filters.get(s))));
                    }
                }
            }
        }
        return predicates;
    }

    private Expression<?> getCastExpression(String searchValue, String typeName, CriteriaBuilder cb) {
        javax.persistence.criteria.Expression<?> expression = null;
        switch (typeName) {
            case "short":
                expression = cb.literal(Short.parseShort(searchValue));
                break;
            case "byte":
                expression = cb.literal(Byte.parseByte(searchValue));
                break;
            case "int":
                expression = cb.literal(Integer.parseInt(searchValue));
                break;
            case "long":
                expression = cb.literal(Long.parseLong(searchValue));
                break;
            case "float":
                expression = cb.literal(Float.parseFloat(searchValue));
                break;
            case "double":
                expression = cb.literal(Double.parseDouble(searchValue));
                break;
            case "boolean":
                expression = cb.literal(Boolean.parseBoolean(searchValue));
                break;
            default:
                break;
        }
        return expression;
    }

    public int count(Map<String, Object> filters, T searchingObject) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        cq.select(cb.count(entityRoot));
        List<javax.persistence.criteria.Predicate> predicates = findPredicateList(cb, entityRoot, filters, searchingObject);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public Long count(T searchingObject) {

        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        cq.select(cb.count(entityRoot));
        List<javax.persistence.criteria.Predicate> predicates = buildPredicateList(entityRoot, searchingObject, cb);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult());
    }

    private javax.persistence.criteria.Root<T> getFetchJoins(javax.persistence.criteria.Root<T> entityRoot, Map<String, Object> filters) {
        if (filters != null) {
            for (String s : filters.keySet()) {
                if (s.contains(".")) {
                    String[] items = s.split("\\.");
                    String embeddedIdField = items[0];
                    Fetch fetchs = entityRoot.fetch(embeddedIdField);
                    for (int i = 1; i < items.length - 1; i++) {
                        embeddedIdField = items[i];
                        fetchs.fetch(embeddedIdField);
                    }
                }

            }
        }
        return entityRoot;
    }

    public List<T> findRange(int first, int pageSize, String sortField, String sortOrder, Map<String, Object> filters, T searchingObject) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        javax.persistence.metamodel.Metamodel entityModel = this.getEntityManager().getMetamodel();
        javax.persistence.metamodel.ManagedType<T> entityType = entityModel.managedType(entityClass);
        entityRoot = getFetchJoins(entityRoot, filters);
        cq.select(entityRoot);
        List<javax.persistence.criteria.Predicate> predicates = findPredicateList(cb, entityRoot, filters, searchingObject);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        List<Order> sortOrderList = buildDefaultSortOrderList(entityRoot, cb);
        if (sortField != null && sortField.length() > 0) {
            sortOrderList = new ArrayList<>();
            Path<Object> pkFieldPath = findFieldPath(entityRoot, sortField, entityModel, entityType);
            // if (entityRoot.get(sortField) != null) {
            if (sortOrder.startsWith("ASC")) {
                sortOrderList.add(cb.asc(pkFieldPath));
            }
            if (sortOrder.startsWith("DESC")) {
                sortOrderList.add(cb.desc(pkFieldPath));
            }
            //}
        }
        cq.orderBy(sortOrderList);
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public List<T> findRange(int first, int pageSize, Map<String, String> sortFields, Map<String, Object> filters, T searchingObject) {

        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        javax.persistence.metamodel.Metamodel entityModel = this.getEntityManager().getMetamodel();
        javax.persistence.metamodel.ManagedType<T> entityType = entityModel.managedType(entityClass);
        cq.select(entityRoot);
        List<javax.persistence.criteria.Predicate> predicates = findPredicateList(cb, entityRoot, filters, searchingObject);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }

        List<Order> sortOrderList = buildDefaultSortOrderList(entityRoot, cb);
        if (sortFields != null && !sortFields.isEmpty()) {
            for (String sortField : sortFields.keySet()) {
                sortOrderList = new ArrayList<>();
                // if (entityRoot.get(sortField) != null) {
                Path<Object> pkFieldPath = findFieldPath(entityRoot, sortField, entityModel, entityType);
                String sortOrder = sortFields.get(sortField);
                if (sortOrder.startsWith("ASC")) {
                    sortOrderList.add(cb.asc(pkFieldPath));
                }
                if (sortOrder.startsWith("DESC")) {
                    sortOrderList.add(cb.desc(pkFieldPath));
                }
                //}
            }

        }
        cq.orderBy(sortOrderList);
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public List<Predicate> buildPredicateList(Root<T> entityRoot, T entity, CriteriaBuilder cb) {
        return new ArrayList<>();
    }

    public List<Predicate> findPredicateList(CriteriaBuilder cb, Root<T> entityRoot, Map<String, Object> filters, T searchingObject) {
        List<Predicate> returnedPredicatesList = getPredicates(cb, entityRoot, filters);
        if (searchingObject != null) {
            returnedPredicatesList.addAll(buildPredicateList(entityRoot, searchingObject, cb));
        }
        return returnedPredicatesList;
    }

    public List<Order> buildDefaultSortOrderList(Root<T> entityRoot, CriteriaBuilder cb) {
        return new ArrayList<>();
    }

    private Path<Object> findFieldPath(Root<T> entityRoot, String s, Metamodel entityModel, ManagedType<T> entityType) {
        Path<Object> pkFieldPath;
        String[] fields = s.split("\\.");
        int i = 0;
        pkFieldPath = entityRoot.get(fields[i]);
        ManagedType<?> entityTypeTemp = entityType;
        while (i + 1 < fields.length) {
            String embeddedIdField = fields[i++];
            String embeddedIdMember = fields[i];
            pkFieldPath = pkFieldPath.get(embeddedIdMember);
            entityTypeTemp = entityModel.managedType(entityTypeTemp.getAttribute(embeddedIdField).getJavaType());
        }
        return pkFieldPath;
    }
}
