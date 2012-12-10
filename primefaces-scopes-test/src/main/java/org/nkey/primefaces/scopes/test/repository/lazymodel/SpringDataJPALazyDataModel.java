package org.nkey.primefaces.scopes.test.repository.lazymodel;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author m.nikolaev
 *         Date: 06.12.12
 *         Time: 22:45
 */
public class SpringDataJPALazyDataModel<T extends IdProvider> extends LazyDataModel<T> {
    private JpaRepository<T, Long> repository;

    public SpringDataJPALazyDataModel(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Object getRowKey(T object) {
        return object.getId();
    }

    @Override
    public T getRowData(String rowKey) {
        return repository.findOne(Long.parseLong(rowKey));
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        this.setRowCount((int) repository.count());
        this.setPageSize(pageSize);
        if (sortOrder == SortOrder.UNSORTED || StringUtils.isBlank(sortField)) {
            PageRequest request = new PageRequest(first / pageSize, pageSize);
            return repository.findAll(request).getContent();
        } else {
            Sort sort = new Sort(sortOrder == SortOrder.ASCENDING ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
            PageRequest request = new PageRequest(first / pageSize, pageSize, sort);
            return repository.findAll(request).getContent();
        }
    }

    @Override
    public void setRowIndex(int rowIndex) {
        /*
         * The following is in ancestor (LazyDataModel):
         * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
         */
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else
            super.setRowIndex(rowIndex % getPageSize());
    }
}
