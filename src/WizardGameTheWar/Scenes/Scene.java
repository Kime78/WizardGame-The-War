package WizardGameTheWar.Scenes;

public abstract class Scene {
    private SceneManager sceneManager;
    Scene(SceneManager manager) {
        sceneManager = manager;
    }
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
