package WizardGameTheWar.Levels;

import WizardGameTheWar.FactoryException;
import WizardGameTheWar.GameObjects.Backgrounds.BackgroundFactory;
import WizardGameTheWar.GameObjects.Enemies.Factories.EnemyFactory;
import WizardGameTheWar.GameObjects.Enemies.Factories.EnemyFactoryCreator;
import WizardGameTheWar.GameObjects.Obstacles.ObstacleFactory;

public class LevelLoader {
    public static Level loadLevelFromString(String levelString) throws InvalidLevelException {
        Level level = new Level();
        //levelID levelType numOfObstacles obstacleX obstacleY numOfEnemies enemyName enemyX enemyY pathToLevelID(0 if it's not linked)
        String[] parts = levelString.split(" ");
        int currentIndex = 0;
        level.id = Integer.parseInt(parts[currentIndex++]);
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
                throw new InvalidLevelException("Invalid Level!");
            }
        }

        int numOfObstacles = Integer.parseInt(parts[currentIndex++]);
        try {
            for (int i = 0; i <= 800 / 48; i++) {
                for (int j = 0; j <= 600 / 48; j++) {
                    //Tile.backgroundGrassTile.Draw(g, i * 48, j * 48);
                    //GameObjectManager.spawn(new Grass(i * 48, j * 48));
                    level.backgrounds.add(BackgroundFactory.createBackground(level.type, i * 48, j * 48));
                    //gameObjects.add();
                }
            }


            for (int i = 1; i <= numOfObstacles; i++) {
                int x = Integer.parseInt(parts[currentIndex++]);
                int y = Integer.parseInt(parts[currentIndex++]);

                level.objects.add(ObstacleFactory.createObstacle(level.type, x * 48, y * 48)); //FIXME: this needs to be able to load different Obstacles
            }

            int numOfEnemies = Integer.parseInt(parts[currentIndex++]);

            for (int i = 1; i <= numOfEnemies; i++) {
                String name = parts[currentIndex++];
                int x = Integer.parseInt(parts[currentIndex++]);
                int y = Integer.parseInt(parts[currentIndex++]);

                level.objects.add(new EnemyFactoryCreator().createEnemyFactory(name, x * 48, y * 48));
            }

            level.links[0] = Integer.parseInt(parts[currentIndex++]);
            level.links[1] = Integer.parseInt(parts[currentIndex++]);
            level.links[2] = Integer.parseInt(parts[currentIndex++]);
            level.links[3] = Integer.parseInt(parts[currentIndex++]);

            //FIXME: now we should border the level Accordingly
            //links are like this NORTH SOUTH WEST EAST
            for(int i = 0 ; i <= 816 / 48; i++) {
                //cel mai tumefiat if din viata mea
                if(level.links[0] != 0) {
                    if(!((i >= 816 / 48 / 2  - 1) && (i <= 816 / 48 / 2 + 1))) {
                        level.rim.add(ObstacleFactory.createObstacle(level.type, i * 48, 0));
                    }
                }
                else {
                        level.rim.add(ObstacleFactory.createObstacle(level.type, i * 48, 0));
                }
                if(level.links[1] != 0) {
                    if (!((i >= 816 / 48 / 2 - 1) && (i <= 816 / 48 / 2 + 1))) {
                        level.rim.add(ObstacleFactory.createObstacle(level.type, i * 48, 624 - 48));
                    }
                }
                else {
                    level.rim.add(ObstacleFactory.createObstacle(level.type, i * 48, 624 - 48));
                }
            }
            for(int i = 1; i < 624 / 48; i++) {
                if(level.links[2] != 0) {
                    if(!((i >= 624 / 48 / 2  - 1) && (i <= 624 / 48 / 2 + 1))) {
                        level.rim.add(ObstacleFactory.createObstacle(level.type, 0, i * 48));
                    }
                }
                else {
                    level.rim.add(ObstacleFactory.createObstacle(level.type, 0, i * 48));
                }
                if(level.links[3] != 0) {
                    if(!((i >= 624 / 48 / 2  - 1) && (i <= 624 / 48 / 2 + 1))) {
                        level.rim.add(ObstacleFactory.createObstacle(level.type, 816 - 48, i * 48));
                    }
                }
                else {
                    level.rim.add(ObstacleFactory.createObstacle(level.type, 816 - 48, i * 48));
                }
            }
        }
        catch (FactoryException e) {
            System.out.println(e);
        }
        return level;
    }
}
