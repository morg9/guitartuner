package com.morg9.guitartuner;

import com.morg9.guitartuner.audio.AudioCapture;
import com.morg9.guitartuner.signal.AudioFrameConverter;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Guitar Tuner Starting...");
        AudioCapture capture = new AudioCapture();
        AudioFrameConverter converter = new AudioFrameConverter();
        
        capture.setListener(converter);
        
        capture.start();
        
        System.out.println("Press Enter to stop");
        try {
        	System.in.read();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        capture.stop();
        
        System.out.println("Audio capture stopped, exiting.");
    }
}
