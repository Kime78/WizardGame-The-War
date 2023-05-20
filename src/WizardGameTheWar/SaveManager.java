package WizardGameTheWar;

import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.Obstacles.Obstacle;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.Spells.EquipableSpell;
import WizardGameTheWar.GameObjects.Spells.SpellFactory;
import WizardGameTheWar.Levels.Level;
import WizardGameTheWar.Levels.LevelLoader;

import java.sql.*;
import java.util.Objects;

class Save {
    public Player player;
    public int currentLevel;
    public Save(Player player, int currentLevel) {
        this.player = player;
        this.currentLevel = currentLevel;
    }
}
public class SaveManager
{
    public static Save loadSaveFromDB(int slot) {
        int id = 0;
        String levelString = null;
        Connection c = null;
        Statement stmt = null;
        Player player = new Player(0, 0);
        int current_level = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:saves.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Save WHERE slot = " + slot + ";");
            int mana = 0;
            int health = 0;
            int max_mana = 0;
            int max_health = 0;

            String equipedSpell1Name = "";
            int equipedSpell1Level = 0;
            String equipedSpell2Name = "";
            int equipedSpell2Level = 0;
            String equipedSpell3Name = "";
            int equipedSpell3Level = 0;
            String equipedSpell4Name = "";
            int equipedSpell4Level = 0;
            int x = 0;
            int y = 0;
            while ( rs.next() ) {
                mana = rs.getInt("mana");
                health = rs.getInt("health");
                max_mana = rs.getInt("max_mana");
                max_health = rs.getInt("max_health");
                current_level = rs.getInt("currentLevel");
                equipedSpell1Name = rs.getString("equipedSpell1Name");
                equipedSpell1Level = rs.getInt("equipedSpell1Level");
                equipedSpell2Name = rs.getString("equipedSpell2Name");
                equipedSpell2Level = rs.getInt("equipedSpell2Level");
                equipedSpell3Name = rs.getString("equipedSpell3Name");
                equipedSpell3Level = rs.getInt("equipedSpell3Level");
                equipedSpell4Name = rs.getString("equipedSpell4Name");
                equipedSpell4Level = rs.getInt("equipedSpell4Level");
                x = rs.getInt("x");
                y = rs.getInt("y");
            }

            player.mana = mana;
            player.maxMana = max_mana;
            player.health = health;
            player.maxHealth = max_health;
            if(!Objects.equals(equipedSpell1Name, "null")) {
                player.equipedSpells[0] = new EquipableSpell(SpellFactory.createSpell(equipedSpell1Name, 0, 0), equipedSpell1Level);
            }
            else
                player.equipedSpells[0] = null;

            if(!Objects.equals(equipedSpell2Name, "null")) {
                player.equipedSpells[1] = new EquipableSpell(SpellFactory.createSpell(equipedSpell2Name, 0, 0), equipedSpell2Level);
            }
            else
                player.equipedSpells[1] = null;

            if(!Objects.equals(equipedSpell3Name, "null")) {
                player.equipedSpells[2] = new EquipableSpell(SpellFactory.createSpell(equipedSpell3Name, 0, 0), equipedSpell3Level);
            }
            else
                player.equipedSpells[2] = null;

            if(!Objects.equals(equipedSpell4Name, "null")) {
                player.equipedSpells[3] = new EquipableSpell(SpellFactory.createSpell(equipedSpell4Name, 0, 0), equipedSpell4Level);
            }
            else
                player.equipedSpells[3] = null;
            player.x = x;
            player.y = y;

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Load save success");
        return new Save(player, current_level);
    }

    public static void writeSaveToDB(Player player, int slot, int currentLevel) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:saves.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String spell1Name = player.equipedSpells[0] != null ? player.equipedSpells[0].spell.name : "null";
            int spell1Level = player.equipedSpells[0] != null ? player.equipedSpells[0].level : 0;
            String spell2Name = player.equipedSpells[1] != null ? player.equipedSpells[1].spell.name : "null";
            int spell2Level = player.equipedSpells[1] != null ? player.equipedSpells[1].level : 0;
            String spell3Name = player.equipedSpells[2] != null ? player.equipedSpells[2].spell.name : "null";
            int spell3Level = player.equipedSpells[2] != null ? player.equipedSpells[2].level : 0;
            String spell4Name = player.equipedSpells[3] != null ? player.equipedSpells[3].spell.name : "null";
            int spell4Level = player.equipedSpells[3] != null ? player.equipedSpells[3].level : 0;

            String sql = "UPDATE Save set " +
                    "mana = " + player.mana + "," +
                    "health = " + player.health + "," +
                    "max_mana = " + player.maxMana + "," +
                    "max_health = " + player.maxHealth + "," +
                    "currentLevel = " + currentLevel + "," +
                    "equipedSpell1Name = " + "'" + spell1Name +  "'" + "," +
                    "equipedSpell1Level = " + spell1Level + "," +
                    "equipedSpell2Name = " + "'" + spell2Name +  "'" +"," +
                    "equipedSpell2Level = " + spell2Level + "," +
                    "equipedSpell3Name = " + "'" + spell3Name +  "'" + "," +
                    "equipedSpell3Level = " + spell3Level + "," +
                    "equipedSpell4Name = " + "'" + spell3Name +  "'" + "," +
                    "equipedSpell4Level = " + spell4Level + "," +
                    "x = " + player.x + "," +
                    "y = " + player.y +
                    " where slot = " + slot + ";";

            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Write Save success");
    }
}





