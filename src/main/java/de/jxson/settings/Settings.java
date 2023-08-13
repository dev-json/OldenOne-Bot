package de.jxson.settings;

import de.jxson.Bot;
import de.jxson.database.Database;
import de.jxson.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Settings {

    public static Pair<String, String> BOT_TOKEN = new Pair<>("BOT_TOKEN", null);

    private static final String FILE_NAME = "app.conf";

    private Settings() {}

    public static void load()
    {
        Properties properties = new Properties();
        File file = new File(FILE_NAME);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try(FileInputStream fis = new FileInputStream(FILE_NAME))
        {
            properties.load(fis);
            BOT_TOKEN.setValue(properties.getProperty(BOT_TOKEN.getPath()));
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public static Pair<?, ?> loadFromDatabase(Pair<String, ?> pair)
    {
        try(Connection connection = Bot.getBot().getDatabase().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT set_value FROM settings WHERE set_name = ?"))
        {
            ps.setString(1, pair.getPath());
            ps.setMaxRows(1);
            ResultSet set = ps.executeQuery();
            if(set.next())
                return new Pair<>(pair.getPath(), set.getString(1));
        }
        catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

}
