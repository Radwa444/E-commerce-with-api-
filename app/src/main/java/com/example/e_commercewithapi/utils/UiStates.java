package com.example.e_commercewithapi.utils;

public class UiStates {
    public static class  Loading extends UiStates{ }
    public static class  Error extends  UiStates {
        public final String error;
        public Error(String error) {
            this.error = error;
        }
    }
    public  static class Success extends UiStates{
        public final String message;
        public Success(String message){
            this.message =message;
        }

    }
}
