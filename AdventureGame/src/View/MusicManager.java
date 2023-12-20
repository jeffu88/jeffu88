package View;

import javafx.scene.media.AudioClip;
/** MusicManager manages all of the sound effects and music. */
class MusicManager {
    final static String MENU_THEME = "resources/Sounds/title_theme.mp3";
    final static String MAIN_THEME = "resources/Sounds/mecha_collection.mp3";
    final static String SECRET_THEME = "resources/Sounds/Doot.mp3";
    final static String CLICK_SOUND = "resources/Sounds/clickSound.mp3";
    private String gameTheme;

    private AudioClip currentlyPlaying;

    MusicManager() {
        currentlyPlaying = new AudioClip(this.getClass().getResource(MAIN_THEME).toExternalForm());
        gameTheme = MAIN_THEME;
    }

    void playMainTheme() {
        currentlyPlaying.stop();
        currentlyPlaying = new AudioClip(this.getClass().getResource(gameTheme).toExternalForm());
        currentlyPlaying.setCycleCount(AudioClip.INDEFINITE);
        currentlyPlaying.play(0.8);
    }

    void playMenuTheme() {
        currentlyPlaying.stop();
        currentlyPlaying = new AudioClip(this.getClass().getResource(MENU_THEME).toExternalForm());
        currentlyPlaying.setCycleCount(AudioClip.INDEFINITE);
        currentlyPlaying.play(0.3);
    }

    void stopMusic() {
        currentlyPlaying.stop();
    }

    void playClickSound() {
        AudioClip click = new AudioClip(this.getClass().getResource(CLICK_SOUND).toExternalForm());
        click.setCycleCount(1);
        click.play(0.4);
    }

    /** Method used to create enable easter egg. */
    public void enableSecretTheme() {
        gameTheme = SECRET_THEME;
    }
}
