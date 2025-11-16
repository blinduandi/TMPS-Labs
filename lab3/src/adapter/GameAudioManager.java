package adapter;

/**
 * Our game's audio interface
 */
public interface GameAudioManager {
    void playBackgroundMusic(String musicName);
    void playSoundEffect(String effectName);
    void stopMusic();
    void setVolume(int volumeLevel); // 0-100
}