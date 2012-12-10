package org.nkey.primefaces.scopes.test.repository.lazymodel;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

/**
 * @author m.nikolaev Date: 11.12.12 Time: 3:01
 */
public class HibernateSearchLazyDataModel<T extends IdProvider> extends LazyDataModel<T> {
    private FullTextEntityManager fullTextEntityManager;
    private Class<T> clazz;


    public HibernateSearchLazyDataModel(FullTextEntityManager fullTextEntityManager, Class<T> clazz) {
        this.fullTextEntityManager = fullTextEntityManager;
        this.clazz = clazz;
    }

    @Override
    public Object getRowKey(T object) {
        return object.getId();
    }

    @Override
    public T getRowData(String rowKey) {
        return fullTextEntityManager.find(clazz, Long.parseLong(rowKey));
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        this.setPageSize(pageSize);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(clazz).get();

        Query luceneQuery = queryBuilder.all().createQuery();

        if (filters != null && !filters.isEmpty()) {
            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String field = entry.getKey();
                String value = entry.getValue();
                if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(value)) {
                    Query queryPart = queryBuilder.keyword().wildcard().onField(field)
                            .matching(String.format("%s*", value.toLowerCase().trim())).createQuery();
                    luceneQuery = queryBuilder.bool().must(luceneQuery).must(queryPart).createQuery();
                }
            }
        }


        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, clazz);
        if (sortOrder != SortOrder.UNSORTED && StringUtils.isNotBlank(sortField)) {
            Sort sort = new Sort(new SortField(sortField, SortField.STRING, sortOrder != SortOrder.ASCENDING));
            fullTextQuery = fullTextQuery.setSort(sort);
        }
        javax.persistence.Query query = fullTextQuery.setMaxResults(pageSize);
        query = query.setFirstResult(first);
        //noinspection UnnecessaryLocalVariable
        @SuppressWarnings("unchecked") List<T> result = query.getResultList();

        this.setRowCount(fullTextQuery.getResultSize());

        return result;

    }

    @Override
    public void setRowIndex(int rowIndex) {
        /*
         * The following is in ancestor (LazyDataModel):
         * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
         */
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
