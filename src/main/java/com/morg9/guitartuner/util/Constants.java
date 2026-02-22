package com.morg9.guitartuner.util;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;

public class Constants {
	public static final int SAMPLE_RATE = 44100;
	public static final int SAMPLE_SIZE = 16; // bits per sample
	public static final int CHANNELS = 1; // mono
	public static final boolean BIG_ENDIAN = false;
	
	public static final Encoding ENCODING = AudioFormat.Encoding.PCM_SIGNED;
	
	public static final int FRAME_SIZE = 8192; // samples per analysis window
	public static final int BUFFER_SIZE = FRAME_SIZE * 2; // 16-BIT = 2 bytes per sample
	
	public static final double MIN_DETECTABLE_FREQUENCY = 70.0;
	public static final double MAX_DETECTABLE_FREQUENCY = 400.0;
}