package com.example.e_commercewithapi.data.models.SignUp;

public class ResponseSignUp {
//    {
//        "email": "nico@gmail.com",
//            "password": "1234",
//            "name": "Nicolas",
//            "avatar": "https://i.imgur.com/yhW6Yw1.jpg",
//            "role": "customer",
//            "id": 24
//    }
   private String email ;
   private String password;
   private String name;
   private String avatar;
   private String role;
   private int id;
  public ResponseSignUp(String email,String password,String name,String avatar,String role,int id){
      this.email=email;
      this.password=password;
      this.name=name;
      this.avatar=avatar;
      this.role=role;
      this.id=id;
  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
