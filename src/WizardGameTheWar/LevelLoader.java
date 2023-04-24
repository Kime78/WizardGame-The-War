package WizardGameTheWar;

import WizardGameTheWar.GameObjects.Backgrounds.BackgroundFactory;
import WizardGameTheWar.GameObjects.Backgrounds.Grass;
import WizardGameTheWar.GameObjects.Enemies.EnemyFactory;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Obstacles.ObstacleFactory;

public class LevelLoader {
    public static Level loadLevelFromString(String levelString) {
        Level level = new Level();
        //levelType levelID numOfObstacles obstacleX obstacleY numOfEnemies enemyName enemyX enemyY pathToLevelID(0 if it's not linked)
        String[] parts = levelString.split(" ");
        int currentIndex = 0;
        switch (parts[currentIndex++]) {
            case "Campie": {
                level.type = LevelType.Campie;
                break;
            }
            case "Padure": {
                level.type = LevelType.Padure;
                break;
            }
            case "Pestera": {
                level.type = LevelType.Pestera;
                break;
            }
            default: {
                throw new RuntimeException("Invalid Level!");
            }
        }
        level.id = Integer.parseInt(parts[currentIndex++]);
        int numOfObstacles = Integer.parseInt(parts[currentIndex++]);
        for(int i = 0 ; i <= 800 / 48; i++) {
            for(int j = 0; j <= 600 / 48; j++) {
                //Tile.backgroundGrassTile.Draw(g, i * 48, j * 48);
                //GameObjectManager.spawn(new Grass(i * 48, j * 48));
                level.backgrounds.add(BackgroundFactory.createBackground(level.type, i * 48, j * 48));
                //gameObjects.add();
            }
        }

        for(int i = 1; i <= numOfObstacles; i++) {
            int x = Integer.parseInt(parts[currentIndex++]);
            int y = Integer.parseInt(parts[currentIndex++]);

            level.objects.add(ObstacleFactory.createObstacle(level.type, x, y)); //FIXME: this needs to be able to load different Obstacles
        }

        int numOfEnemies = Integer.parseInt(parts[currentIndex++]);

        for(int i = 1; i <= numOfEnemies; i++) {
            String name = parts[currentIndex++];
            int x = Integer.parseInt(parts[currentIndex++]);
            int y = Integer.parseInt(parts[currentIndex++]);

            level.objects.add(EnemyFactory.createEnemy(name, x, y));
        }

        level.links[0] = Integer.parseInt(parts[currentIndex++]);
        level.links[1] = Integer.parseInt(parts[currentIndex++]);
        level.links[2] = Integer.parseInt(parts[currentIndex++]);
        level.links[3] = Integer.parseInt(parts[currentIndex++]);

        //FIXME: now we should border the level Accordingly

        return level;
    }
}
