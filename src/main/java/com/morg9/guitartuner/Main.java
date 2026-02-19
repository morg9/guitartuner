package com.morg9.guitartuner;

import com.morg9.guitartuner.audio.AudioCapture;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Guitar Tuner Starting...");
        AudioCapture capture = new AudioCapture();
        capture.start();
    }
}
