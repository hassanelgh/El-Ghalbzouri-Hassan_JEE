package com.example.user.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "USERS")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    private String userId;

    @Column(unique = true ,length = 20, name = "USER_NAME")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Collection<Role> roles=new ArrayList<>();
}
