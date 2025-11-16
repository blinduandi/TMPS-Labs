package adapter;

/**
 * Third-party audio library with different interface
 */
public class ThirdPartyAudioLib {
    
    public void initializeAudioEngine() {
        System.out.println("🔊 Third-party audio engine initialized");
    }
    
    public void playSound(String filename, float volume, boolean loop) {
        System.out.println("▶️ Playing: " + filename + " (vol:" + volume + ", loop:" + loop + ")");
    }
    
    public void stopAllSounds() {
        System.out.println("⏹️ All sounds stopped");
    }
    
    public void setMasterVolume(float volume) {
        System.out.println("🔊 Master volume set to: " + volume);
    }
}