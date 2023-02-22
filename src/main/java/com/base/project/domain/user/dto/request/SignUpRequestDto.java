package com.base.project.domain.user.dto.request;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequestDto {
    String email;
    String password;
    String name;
}
