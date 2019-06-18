package com.anuravi98.pharmacybillingsystem;

import com.anuravi98.pharmacybillingsystem.presentation.startframes.LoginFrame;
import com.anuravi98.pharmacybillingsystem.service.firstrun.FirstRun;

import java.io.File;

public class Main {
    /**
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        File f = new File("billing.json");
        if (!f.exists()) {
            FirstRun fr = new FirstRun();
            fr.setUpProject();
        }
        LoginFrame lf = new LoginFrame();
    }
}
