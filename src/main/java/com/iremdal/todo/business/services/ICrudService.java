package com.iremdal.todo.business.services;

import java.util.List;

public interface ICrudService<D,E>  {
    public D objectServiceCreate(D d);

    public List<D> objectServiceList();

    public D objectServiceUpdate(Long id, D d);
    public D objectServiceFindById(Long id);
    public D objectServiceDelete(Long id);
}
