package com.workintech.s18d2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SuccessResponse<T> {
    private String message;
    private T data;
}
