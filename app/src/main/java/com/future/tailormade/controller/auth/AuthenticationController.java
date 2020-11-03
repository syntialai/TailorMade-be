package com.future.tailormade.controller.auth;

import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import com.future.tailormade.command.auth.RefreshTokenCommand;
import com.future.tailormade.command.auth.SignInCommand;
import com.future.tailormade.command.auth.SignUpCommand;
import com.future.tailormade.constants.ApiPath;
import com.future.tailormade.payload.request.auth.RefreshTokenRequest;
import com.future.tailormade.payload.request.auth.SignInRequest;
import com.future.tailormade.payload.request.auth.SignUpRequest;
import com.future.tailormade.payload.response.auth.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class AuthenticationController {

    @Autowired
    private CommandExecutor commandExecutor;

    @GetMapping(ApiPath.USERS_REFRESH_TOKEN)
    public Mono<Response<TokenResponse>> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest
    ) {
        return commandExecutor.execute(RefreshTokenCommand.class, refreshTokenRequest)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @PostMapping(ApiPath.USERS_SIGN_IN)
    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<TokenResponse>> signIn(
            @RequestBody SignInRequest signInRequest
    ) {
        return commandExecutor.execute(SignInCommand.class, signInRequest)
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @PostMapping(ApiPath.USERS_SIGN_UP)
    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response> signUp(@RequestBody SignUpRequest signUpRequest) {
        return commandExecutor.execute(SignUpCommand.class, signUpRequest)
                .subscribeOn(Schedulers.elastic());
    }
}