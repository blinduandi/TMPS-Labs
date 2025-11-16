package adapter;

/**
 * Audio Adapter - adapts third-party audio library to our game interface
 */
public class AudioSystemAdapter implements GameAudioManager {
    private final ThirdPartyAudioLib audioLib;
    private boolean musicPlaying = false;
    
    public AudioSystemAdapter(ThirdPartyAudioLib audioLib) {
        this.audioLib = audioLib;
        this.audioLib.initializeAudioEngine();
    }
    
    @Override
    public void playBackgroundMusic(String musicName) {
        // Convert to third-party format and add .mp3 extension
        String filename = "music/" + musicName + ".mp3";
        audioLib.playSound(filename, 0.7f, true); // loop background music
        musicPlaying = true;
        System.out.println("🎵 Adapted background music: " + musicName);
    }
    
    @Override
    public void playSoundEffect(String effectName) {
        // Convert to third-party format
        String filename = "effects/" + effectName + ".wav";
        audioLib.playSound(filename, 1.0f, false); // don't loop effects
        System.out.println("🔊 Adapted sound effect: " + effectName);
    }
    
    @Override
    public void stopMusic() {
        if (musicPlaying) {
            audioLib.stopAllSounds();
            musicPlaying = false;
            System.out.println("🔇 Adapted music stop");
        }
    }
    
    @Override
    public void setVolume(int volumeLevel) {
        // Convert 0-100 scale to 0.0-1.0 scale
        float normalizedVolume = Math.max(0, Math.min(100, volumeLevel)) / 100.0f;
        audioLib.setMasterVolume(normalizedVolume);
        System.out.println("🔊 Adapted volume: " + volumeLevel + "% -> " + normalizedVolume);
    }
}