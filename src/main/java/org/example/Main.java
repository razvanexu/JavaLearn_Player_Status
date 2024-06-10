package org.example;


import org.example.models.*;
import org.example.services.*;

public class Main {
    public static void main(String[] args) {


        IServices service = new Services();
        PlayerStatus player = new PlayerStatus(service);
        player.initPlayer("Jane Doe");
    }
}