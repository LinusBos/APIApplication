package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private int userBalance;
    public User(int userId, String userName, int userBalance, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userBalance = userBalance;
        this.userPassword = userPassword;

    }
}
