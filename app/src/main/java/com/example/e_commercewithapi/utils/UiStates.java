package com.example.e_commercewithapi.utils;

public class UiStates<T> {
    public static class  Loading<T> extends UiStates<T>{ }
    public static class  Error<T> extends  UiStates<T> {
        public final String error;
        public Error(String error) {
            this.error = error;
        }
    }
    public  static class Success<T> extends UiStates<T>{
        public final T message;
        public Success(T message){
            this.message =message;
        }

    }
}
