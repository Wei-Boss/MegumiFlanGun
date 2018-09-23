package com.weiboss.megumi.megumiflangun.file;

import com.weiboss.megumi.megumiflangun.Main;
import com.weiboss.megumi.megumiflangun.constructor.WearRate;
import com.weiboss.megumi.megumiflangun.util.RegularUtil;
import com.weiboss.megumi.megumiflangun.constructor.RangeValue;
import com.weiboss.megumi.megumiflangun.constructor.Template;
import com.weiboss.megumi.megumiflangun.util.WeiUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileManager {
    private Main plugin;
    private List<String> wears;
    private File configFile;
    private File messageFile;
    private YamlConfiguration config;
    private YamlConfiguration message;

    public FileManager(Main plugin) {
        this.plugin = plugin;
        this.wears = Arrays.asList("FactoryNew", "MinimalWear", "FieldTested", "WellWorn", "BattleScarred");
    }

    public void init() {
        config = new YamlConfiguration();
        message = new YamlConfiguration();
        configFile = new File(plugin.getDataFolder(), "config.yml");
        messageFile = new File(plugin.getDataFolder(), "message.yml");
        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                WeiUtil.copyFile(plugin.getResource("config.yml"), configFile);
                plugin.getLogger().info("File: Create config.yml file successfully");
            }
            else plugin.getLogger().info("File: Load config.yml file successfully");
            if (!messageFile.exists()) {
                messageFile.getParentFile().mkdirs();
                WeiUtil.copyFile(plugin.getResource("message.yml"), messageFile);
                plugin.getLogger().info("File: Create message.yml file successfully");
            }
            else plugin.getLogger().info("File: Load message.yml file successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        message = YamlConfiguration.loadConfiguration(messageFile);
    }

    public void initFolder() {
        File template = new File(plugin.getDataFolder(), "item");
        if (!template.exists()) createTemplate();
        File[] templates = template.listFiles();
        for (File f : templates) {
            if (!f.getName().endsWith(".yml")) continue;
            addTemplate(YamlConfiguration.loadConfiguration(f));
        }
        plugin.getLogger().info("%s% templates have been read".replace("%s%", String.valueOf(plugin.getMyPluginManager().getTemplateMap().size())));
    }

    private void createTemplate() {
        File file = new File(plugin.getDataFolder(), "item/template.yml");
        try {
            file.getParentFile().mkdirs();
            WeiUtil.copyFile(plugin.getResource("template.yml"), file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTemplate(YamlConfiguration yaml) {
        String tID = yaml.getString("ID");

        try {
            String name = WeiUtil.onReplace(yaml.getString("Name"));
            String suffix = WeiUtil.onReplace(yaml.getString("Suffix"));
            String levelLimit = yaml.getString("LevelLimit");
            Integer id = yaml.getInt("Item.ID");
            Integer data = yaml.getInt("Item.Data");
            String display = WeiUtil.onReplace(yaml.getString("Item.Display"));
            List<String> lore = WeiUtil.onReplace(yaml.getStringList("Item.Lore"));

            HashMap<String, RangeValue> attributes = new HashMap<>();
            for (String s : yaml.getConfigurationSection("Attribute").getKeys(false)) {
                String value = yaml.getString("Attribute." + s);
                attributes.put(s, getRange(value));
            }

            HashMap<String, Boolean> feature = new HashMap<>();
            for (String s : yaml.getConfigurationSection("Feature").getKeys(false)) {
                Boolean b = yaml.getBoolean("Feature." + s);
                feature.put(s, b);
            }

            List<WearRate> wear = new ArrayList<>();
            for (String s : wears) {
                Double value = yaml.getDouble("Wear." + s);
                wear.add(new WearRate(s, value));
            }

            plugin.getMyPluginManager().getTemplateMap().put(tID, new Template(name, suffix, getRange(levelLimit), id, data, display, lore, attributes, feature, wear));
        }
        catch (Exception e) {
            plugin.getLogger().info("Failed to read template %s%, please check the template file!".replace("%s%", tID));
            e.printStackTrace();
        }
    }

    private RangeValue getRange(String value) {
        Double min = 0d;
        Double max = null;
        if (RegularUtil.isRange(value)) {
            min = Double.valueOf(value.split(":")[0]);
            max = Double.valueOf(value.split(":")[1]);
        }
        else if (WeiUtil.isFloat(value)) {
            min = Double.valueOf(value);
            max = Double.valueOf(value);
        }
        return new RangeValue(min, max);
    }

    public String getAttrName(String type) {
        return WeiUtil.onReplace(config.getString("Formats." + type));
    }

    public List<String> getWears() {
        return wears;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public YamlConfiguration getMessage() {
        return message;
    }
}
