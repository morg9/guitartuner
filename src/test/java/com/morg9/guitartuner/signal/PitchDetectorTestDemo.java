package com.morg9.guitartuner.signal;

import com.morg9.guitartuner.util.Constants;

public class PitchDetectorTestDemo {
	public static void main(String[] args) {
		double testFrequencyA2 = 110.0; // A2 note example
		double testFrequencyLowE = 82.0; // Low E example
		double testFrequencyG = 196.0; // G example
		
		int sampleRate = Constants.SAMPLE_RATE;
		int frameSize = 2048;
		
		double[] samples1 = generateSineWave(testFrequencyA2, sampleRate, frameSize);
		double[] samples2 = generateSineWave(testFrequencyLowE, sampleRate, frameSize);
		double[] samples3 = generateSineWave(testFrequencyG, sampleRate, frameSize);
		
		PitchDetector detector = new PitchDetector(sampleRate, Constants.MIN_DETECTABLE_FREQUENCY, Constants.MAX_DETECTABLE_FREQUENCY);
		
		double detectedPitch1 = detector.detectPitch(samples1);
		double detectedPitch2 = detector.detectPitch(samples2);
		double detectedPitch3 = detector.detectPitch(samples3);
		
		System.out.println("True frequency: " + testFrequencyA2);
		System.out.println("Detected frequency: " + detectedPitch1);
		
		System.out.println("True frequency: " + testFrequencyLowE);
		System.out.println("Detected frequency: " + detectedPitch2);
		
		System.out.println("True frequency: " + testFrequencyG);
		System.out.println("Detected frequency: " + detectedPitch3);
	}

	private static double[] generateSineWave(double frequency, int sampleRate, int sampleCount) {
		double[] samples = new double[sampleCount];
		
		for (int i = 0; i < sampleCount; i++) {
			samples[i] = Math.sin(2.0 * Math.PI * frequency * i / sampleRate);
		}
		return samples;
	}
}
