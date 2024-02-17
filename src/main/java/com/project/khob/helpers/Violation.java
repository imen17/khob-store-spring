package com.project.khob.helpers;

import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Violation {
    private  String fieldName;
    private  String message;
}
