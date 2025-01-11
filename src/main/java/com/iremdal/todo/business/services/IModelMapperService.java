package com.iremdal.todo.business.services;

public interface IModelMapperService<D,E> {

    // Model Mapper
    public D entityToDto(E e);
    public E dtoToEntity(D d);
}
