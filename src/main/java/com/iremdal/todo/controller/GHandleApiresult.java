package com.iremdal.todo.controller;


import com.iremdal.todo.error.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;




// LOMBOK
@RequiredArgsConstructor
@Log4j2
// ApiResult için Generics yapımız için kullanıyoruz.
public class GHandleApiresult {

    // Injection
    private static final MessageSource messageSource = null;
    private ApiResult apiResult;
    private static String message; // Message

    // Generic ResponseEntity ApiResult
    //public static final  <T> ResponseEntity<?> genericsHandleApiResult(){
    public static final <T> ResponseEntity<ApiResult> genericsHandleApiResult(
            String path,
            int tryStatusCode,
            int catchStatusCode,
            Supplier<T> supplier // Lambda Expression'ı kullanmak için
    ) {

        // İşlem gerçekleştirecek lamba ifadesi
        T data = supplier.get();

        try {
            // MESSAGE
            //message= messageSource.getMessage("generics.api.try.status.code",null, LocaleContextHolder.getLocale());

            // Başarılıysa ApiResult Nesnesini oluştur
            ApiResult apiResult = ApiResult.builder()
                    .status(tryStatusCode)
                    .message("message")
                    .path(path)
                    .validationErrors(Map.of("data", data))
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            // return new ResponseEntity<>(addressDtoApiCreate,HttpStatus.CREATED);
            // return ResponseEntity.status(201).body(addressDtoApiCreate);
            // return ResponseEntity.status(HttpStatus.CREATED).body(addressDtoApiCreate);
            // return ResponseEntity.ok(iAddressService.).body(addressDtoApiCreate);
            // return ResponseEntity.ok(addressDtoApiCreate);
            return ResponseEntity.status(tryStatusCode).body(apiResult);
        } catch (Exception e) {
            // MESSAGE
            //message= messageSource.getMessage("generics.api.catch.status.code",null, LocaleContextHolder.getLocale());

            // Başarısızsa ApiResult Nesnesini oluştur
            ApiResult apiResult = ApiResult.builder()
                    .status(catchStatusCode)
                    .message("message")
                    .path(path)
                    .validationErrors(Map.of("data", data))
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(catchStatusCode).body(apiResult);
        } //end catch
    } // end method(genericsHandleApiResult)
} //end class


enum EMyspecialStatusCode {
    OK(200, "Ok"), CREATED(201, "Created"), NOCONTENT(204, "No Content");

    // Field
    private final int key;
    private final String value;

    // Constuctor
    private EMyspecialStatusCode(int key, String value) {
        this.key = key;
        this.value = value;
    }

    // Getter And Setter
    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}