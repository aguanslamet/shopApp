package com.shopApp.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefaultResponse<T> {

    private T message;
    private T statusCode;
    private T data;

}
