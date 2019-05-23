package com.gnnu.redis.entry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String uname;
    private String pwd;
}
