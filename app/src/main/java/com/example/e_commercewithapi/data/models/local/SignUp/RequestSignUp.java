package com.example.e_commercewithapi.data.models.local.SignUp;

public class RequestSignUp {
//     "name": "Nicolas",
//             "email": "nico@gmail.com",
//             "password": "1234",
//             "avatar": "https://picsum.photos/800"
    private String name;
    private String email;
    private String password;
    private String avatar;
    public RequestSignUp(String name, String email, String password, String avatar){
        this.name=name;
        this.email=email;
        this.password=password;
        this.avatar=avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
