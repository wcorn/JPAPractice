package com.base.project.domain.user.dto.request;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequestDto {
    String email;
    String password;
    String name;
    String nickname;
}
