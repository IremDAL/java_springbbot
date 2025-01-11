package com.iremdal.todo.business.services.interfaces;

import com.iremdal.todo.business.services.ICrudService;
import com.iremdal.todo.business.services.IModelMapperService;

public interface ITodoService <D,E> extends ICrudService<D,E>, IModelMapperService<D,E> {
}
