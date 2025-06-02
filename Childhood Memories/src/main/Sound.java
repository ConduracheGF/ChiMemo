package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound() {
        soundURL[0] = getClass().getResource("/Sound/MelodieFundal.wav");
        soundURL[1] = getClass().getResource("/Sound/Bataie/swingSword.wav");
        soundURL[2] = getClass().getResource("/Sound/Bataie/receivedamage.wav");
        soundURL[3] = getClass().getResource("/Sound/Bataie/hitmonster.wav");
        soundURL[4] = getClass().getResource("/Sound/Bataie/pains.wav");
        soundURL[5] = getClass().getResource("/Sound/Bataie/fireballSound.wav");
        soundURL[6] = getClass().getResource("/Sound/itemPickUp.wav");
        soundURL[7] = getClass().getResource("/Sound/Naruto/hitNaruto.wav");
        soundURL[8] = getClass().getResource("/Sound/Naruto/hurtNaruto.wav");
        soundURL[9] = getClass().getResource("/Sound/Sonic/SonicHurt.wav");
        soundURL[10] = getClass().getResource("/Sound/Sonic/SonicDeath.wav");
        soundURL[11] = getClass().getResource("/Sound/teleport.wav");
        soundURL[12] = getClass().getResource("/Sound/Mario/marioHit.wav");
        soundURL[13] = getClass().getResource("/Sound/Mario/MarioDies1.wav");
        soundURL[14] = getClass().getResource("/Sound/Mario/marioDies.wav");

    }
    public void setFile(int i){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e){
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void checkVolume() {
        switch (volumeScale) {
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        if(fc!=null) {
            fc.setValue(volume);
        }
    }
}
