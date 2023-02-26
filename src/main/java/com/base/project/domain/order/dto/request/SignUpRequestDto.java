package com.base.project.domain.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
