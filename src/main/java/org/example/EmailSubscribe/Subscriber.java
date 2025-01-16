package org.example.EmailSubscribe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Subscriber {
    private int userId;
    private String name;
    private String email;
}
