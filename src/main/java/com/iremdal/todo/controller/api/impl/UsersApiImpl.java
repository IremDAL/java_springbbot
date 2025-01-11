package com.iremdal.todo.controller.api.impl;

import com.iremdal.todo.business.dto.UsersDto;
import com.iremdal.todo.business.services.interfaces.IUsersService;
import com.iremdal.todo.controller.api.interfaces.IUsersApi;
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
@RequestMapping("/api/users/v1.0.0")
public class UsersApiImpl implements IUsersApi<UsersDto> {

    private final IUsersService iUsersService;
    private final MessageSource messageSource;

    // ApiResult Instance
    private ApiResult apiResult;

    @PostMapping("/create")
    @Override
    public ResponseEntity<?> objectApiCreate(@Valid @RequestBody UsersDto usersDto) {
        UsersDto usersDtoCreate= (UsersDto) iUsersService.objectServiceCreate(usersDto);
        return  ResponseEntity.ok(usersDtoCreate);
    }

    @GetMapping(value = "/list")
    @Override
    public ResponseEntity<List<UsersDto>> objectApiList() {
        List<UsersDto> usersDtoList = iUsersService.objectServiceList();
        // Stream Value
        return ResponseEntity.ok(usersDtoList);
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
        UsersDto usersDtoFind= (UsersDto) iUsersService.objectServiceFindById(id);
        return ResponseEntity.ok(usersDtoFind);
    }

    @PutMapping({"/update/","/update/{id}"})
    @Override
    public ResponseEntity<?> objectApiUpdate(@PathVariable(name = "id",required = false)  Long id, @Valid @RequestBody UsersDto usersDto) {
        return ResponseEntity.ok(iUsersService.objectServiceUpdate(id, usersDto));
    }

    @DeleteMapping({"/delete/","/delete/{id}"})
    @Override
    public ResponseEntity<?> objectApiDelete(@PathVariable(name = "id",required = false) Long id) {
        return ResponseEntity.ok(iUsersService.objectServiceDelete(id));
    }
}
