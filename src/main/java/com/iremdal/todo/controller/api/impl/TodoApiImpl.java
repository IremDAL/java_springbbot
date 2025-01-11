package com.iremdal.todo.controller.api.impl;

import com.iremdal.todo.business.dto.TodoDto;
import com.iremdal.todo.business.services.interfaces.ITodoService;
import com.iremdal.todo.controller.api.interfaces.ITodoApi;
import com.iremdal.todo.error.ApiResult;
import com.iremdal.todo.exception._404_NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Log4j2

// Api: Dış dünyaya açılan kapı
@RestController
@RequestMapping("/api/todo/v1.0.0")

public class TodoApiImpl implements ITodoApi<TodoDto> {
    private final ITodoService iTodoService;
    private final MessageSource messageSource;

    // ApiResult Instance
    private ApiResult apiResult;

    @PostMapping("/create")
    @Override
    public ResponseEntity<?> objectApiCreate(@Valid @RequestBody TodoDto todoDto) {
        TodoDto todoDtoCreate= (TodoDto) iTodoService.objectServiceCreate(todoDto);
        return  ResponseEntity.ok(todoDtoCreate);
    }

    @GetMapping(value = "/list")
    @Override
    public ResponseEntity<List<TodoDto>> objectApiList() {
        List<TodoDto> todoDtoList = iTodoService.objectServiceList();
        // Stream Value
        return ResponseEntity.ok(todoDtoList);
    }
    @GetMapping({"/find/","/find/{id}"})
    @Override
    public ResponseEntity<?> objectApiFindById(@PathVariable(name="id",required = false) Long id) {
        String message="";
        if(id ==null){
            throw new NullPointerException("Null Pointer Exception: Null değer");
        }else if(id==0){
            throw new _404_NotFoundException("Bad Request Exception: Kötü istek");
        } else if(id<0){
            // Config ApiResultValidationMessage
            // resource/ValidationMessages/ValidationMessages.properties => error.unauthorized
            message= messageSource.getMessage("error.unauthorized",null, LocaleContextHolder.getLocale());
            apiResult= new ApiResult();
            apiResult.setError("unAuthorized: Yetkisiz Giriş");
            apiResult.setPath("/api/customer/v1.0.0/find");
            apiResult.setStatus(HttpStatus.UNAUTHORIZED.value());
            apiResult.setMessage(message);
            return ResponseEntity.ok(apiResult);
        }
        // customer Find By Id
        TodoDto todoDtoFind= (TodoDto) iTodoService.objectServiceFindById(id);
        return ResponseEntity.ok(todoDtoFind);
    }
    @PutMapping({"/update/","/update/{id}"})
    @Override
    public ResponseEntity<?> objectApiUpdate(@PathVariable(name = "id",required = false)  Long id, @Valid @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(iTodoService.objectServiceUpdate(id, todoDto));
    }
    @DeleteMapping({"/delete/","/delete/{id}"})
    @Override
    public ResponseEntity<?> objectApiDelete(@PathVariable(name = "id",required = false) Long id) {
        return ResponseEntity.ok(iTodoService.objectServiceDelete(id));
    }
}
