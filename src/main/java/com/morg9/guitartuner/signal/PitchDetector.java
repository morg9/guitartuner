package com.morg9.guitartuner.signal;

public class PitchDetector {
	private final int sampleRate;
	private final double minFrequency;
	private final double maxFrequency;
	
	public PitchDetector(int sampleRate, double minFrequency, double maxFrequency) {
		this.sampleRate = sampleRate;
		this.minFrequency = minFrequency;
		this.maxFrequency = maxFrequency;
	}
	
	public double detectPitch(double[] samples) {
		double[] autocorrelation = computeAutocorrelation(samples);
		int bestLag = findBestLag(autocorrelation);
		
		if (bestLag == -1) {
			return -1;
		}
		return sampleRate / (double) bestLag;
	}
	
	private double[] computeAutocorrelation(double[] samples) {
		int sampleCount = samples.length;
		double[] autocorrelation = new double[sampleCount];
		int maxLag = (int) (sampleRate / minFrequency);
		
		for (int lag = 0; lag <= maxLag && lag < sampleCount; lag++) {
			double correlationSum = 0.0;
			double originalEnergy = 0.0;
			double shiftedEnergy = 0.0;
			
			for (int i = 0; i < sampleCount - lag; i++) {
				double originalSample = samples[i];
				double shiftedSample = samples[i + lag];
				
				correlationSum += originalSample * shiftedSample;
				
				originalEnergy += originalSample * originalSample;
				shiftedEnergy += shiftedSample * shiftedSample;
			}
			
			if (originalEnergy > 0 && shiftedEnergy > 0) {
				autocorrelation[lag] = correlationSum / Math.sqrt(originalEnergy * shiftedEnergy);
			} else {
				autocorrelation[lag] = 0.0;
			}
		}
		return autocorrelation;
	}
	
	private int findBestLag(double[] autocorrelation) {
		
		int minLag = (int) (sampleRate / maxFrequency);
		int maxLag = (int) (sampleRate / minFrequency);
		
		double threshold = 0.3;
		
		int lag = minLag; // skip initial downward slope after lag 0
		
		while (lag + 1 < autocorrelation.length && autocorrelation[lag] > autocorrelation[lag + 1]) {
			lag++;
		}
		
		for (; lag + 1 <= maxLag && lag + 1 < autocorrelation.length; lag++) {
			if (autocorrelation[lag] > threshold && autocorrelation[lag] > autocorrelation[lag-1] && autocorrelation[lag] > autocorrelation[lag + 1]) {
				return lag;
			}
		}

		return -1;
	}
}
    