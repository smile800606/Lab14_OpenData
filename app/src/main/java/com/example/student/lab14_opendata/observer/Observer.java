package com.example.student.lab14_opendata.observer;


public interface Observer {

    void OnError(String message);
    void OnCompleted();
}
