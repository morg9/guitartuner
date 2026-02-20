package com.morg9.guitartuner.audio;

public interface AudioDataListener {

	void onAudioFrame(byte[] buffer, int bytesRead);
}
