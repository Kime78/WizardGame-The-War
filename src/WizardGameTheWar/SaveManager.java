package WizardGameTheWar;

import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.Obstacles.Obstacle;
import WizardGameTheWar.Levels.Level;
import WizardGameTheWar.Levels.LevelLoader;

import java.sql.*;
public class SaveManager
{
    public static Level loadSaveFromDB() {
        int id = 0;
        String levelString = null;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:saves.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Saves;" );

            while ( rs.next() ) {
                id = rs.getInt("id");
                levelString = rs.getString("LevelString");
                System.out.println( "ID = " + id );
                System.out.println( "LEVELSTRING = " + levelString );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return LevelLoader.loadLevelFromString(id + " " + levelString);
    }

    public static void writeLevelToDB(Level level) {
        Connection c = null;
        Statement stmt = null;
        try {

            String levelString = "id ";
            switch (level.type) {
                case Campie: levelString += "Campie "; break;
                case Padure: levelString += "Padure "; break;
                case Pestera: levelString += "Pestera "; break;
            }
            int numOfObstacles = 0;
            for(GameObject obj : level.objects) {
                if(obj instanceof Obstacle)
                    numOfObstacles++;
            }
            levelString += numOfObstacles + " ";
            for(GameObject obstacle : level.objects) {
                if(obstacle instanceof Obstacle)
                    levelString += obstacle.x / 48 + " " + obstacle.y / 48 + " ";
            }
            int numOfEnemies = 0;
            for(GameObject obj : level.objects) {
                if(obj instanceof Obstacle)
                    numOfEnemies++;
            }
            levelString += numOfEnemies + " ";
            for(GameObject enemy : level.objects) {
                if(enemy instanceof Enemy)
                levelString += ((Enemy) enemy).name + " " + enemy.x / 48 + " " + enemy.y / 48 + " ";
            }
            levelString += "id1 id2 id3 id4";

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:saves.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO SAVES (ID, LevelString) " +
                    "VALUES (4,'" + levelString + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}





