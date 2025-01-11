package com.iremdal.todo.audit;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter

abstract public class AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID = 1L;

    protected String createdBy;

    //Kullanıcı API çağrısında bir tarih belirttiyse (örneğin, "Dün oluşturuldu" bilgisi), bu tarih createdDate olarak saklanır.
    //protected String createdDate;

    protected String lastModifiedBy;

    protected String lastModifiedDate;
}
