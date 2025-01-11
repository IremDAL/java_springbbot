package com.iremdal.todo.business.services.interfaces;

import com.iremdal.todo.business.dto.UsersDto;
import com.iremdal.todo.business.services.ICrudService;
import com.iremdal.todo.business.services.IModelMapperService;
import com.iremdal.todo.data.entity.UsersEntity;

public interface IUsersService<D,E> extends ICrudService<D,E> , IModelMapperService<D,E> {

}
