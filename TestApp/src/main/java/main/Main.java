package main;

import frontend.FrontendI;
import frontend.frontendServlets.FrontendServlets;

/**
 * Created by Dmitry on 16.04.2016.
 */
public class Main {

    /* точка входа в приложение */
    public static void main(String[] args) {
        FrontendI frontend = new FrontendServlets();
        frontend.start();
    }
}
