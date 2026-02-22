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
		// TODO
		return null;
	}
	
	private int findBestLag(double[] autocorrelation) {
		int minLag = (int) (sampleRate / maxFrequency);
		int maxLag = (int) (sampleRate / minFrequency);
		
		double maxValue = Double.NEGATIVE_INFINITY;
		int bestLag = -1;
		
		for (int lag = minLag; lag <= maxLag && lag < autocorrelation.length; lag++) {
			if (autocorrelation[lag] > maxValue) {
				maxValue = autocorrelation[lag];
				bestLag = lag;
			}
		}
		return bestLag;
	}
}
    