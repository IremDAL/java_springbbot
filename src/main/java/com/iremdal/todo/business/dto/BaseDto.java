package com.iremdal.todo.business.dto;

import com.iremdal.todo.audit.AuditingAwareBaseDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter

abstract public class BaseDto extends AuditingAwareBaseDto implements Serializable {
    public static final Long serialVersionUID = 1L;

    protected Long id;

    //API çağrısı sırasında sistemin veriyi ilk kaydettiği zaman.
    protected Date systemCreatedDate;


}
