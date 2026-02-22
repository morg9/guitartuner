package com.morg9.guitartuner.signal;

import com.morg9.guitartuner.audio.AudioCapture;

public class PitchDetectorTestWithMic {
	 public static void main(String[] args) {
	        System.out.println("Guitar Tuner Starting...");
	        AudioCapture capture = new AudioCapture();
	        AudioFrameConverter converter = new AudioFrameConverter();
	        PitchDetector detector = new PitchDetector(44100,60,400);
	        
	        converter.setListener(detector);
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
