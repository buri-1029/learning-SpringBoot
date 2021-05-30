package com.test.jwtlogin.model.dto.request;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}
