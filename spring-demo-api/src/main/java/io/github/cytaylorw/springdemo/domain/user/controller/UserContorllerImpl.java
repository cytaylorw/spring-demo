package io.github.cytaylorw.springdemo.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cytaylorw.springdemo.core.response.ResponseBody;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import io.github.cytaylorw.springdemo.core.response.SimpleResponseBody;
import io.github.cytaylorw.springdemo.domain.user.request.UserCreateRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserPatchRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserPutRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserSearchRq;
import io.github.cytaylorw.springdemo.domain.user.response.DemoUserRsData;
import io.github.cytaylorw.springdemo.domain.user.service.UserService;

/**
 * User controller implementation
 * 
 * @author Taylor Wong
 *
 */
@RestController
@RequestMapping(path = "/users")
public class UserContorllerImpl implements UserContorller {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseBody<Page<DemoUserRsData>>> searchUsers(UserSearchRq param, Pageable pageable) {
        Page<DemoUserRsData> data = this.userService.search(param, pageable);
        return ResponseMessage.DEFAULT_QUERY_SUCCESS.toApiResponseEntity(data);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody<DemoUserRsData>> createUser(@RequestBody UserCreateRq request) {
        DemoUserRsData data = this.userService.create(request);
        return ResponseMessage.DEFAULT_CREATE_SUCCESS.toApiResponseEntity(data);
    }

    @Override
    @GetMapping("/{username}")
    public ResponseEntity<ResponseBody<DemoUserRsData>> getUser(@PathVariable String username) {
        DemoUserRsData data = this.userService.find(username);
        return ResponseMessage.DEFAULT_QUERY_SUCCESS.toApiResponseEntity(data);
    }

    @Override
    @PutMapping("/{username}")
    public ResponseEntity<ResponseBody<DemoUserRsData>> putUser(@PathVariable String username,
            @RequestBody UserPutRq request) {
        DemoUserRsData data = this.userService.put(username, request);
        return ResponseMessage.DEFAULT_UPDATE_SUCCESS.toApiResponseEntity(data);
    }

    @Override
    @PatchMapping("/{username}")
    public ResponseEntity<ResponseBody<DemoUserRsData>> patchUser(@PathVariable String username,
            @RequestBody UserPatchRq request) {
        DemoUserRsData data = this.userService.patch(username, request);
        return ResponseMessage.DEFAULT_UPDATE_SUCCESS.toApiResponseEntity(data);
    }

    @Override
    @DeleteMapping("/{username}")
    public ResponseEntity<SimpleResponseBody> deleteUser(@PathVariable String username) {
        this.userService.delete(username);
        return ResponseMessage.DEFAULT_DELETE_SUCCESS.toApiSimpleResponseEntity();
    }

}
