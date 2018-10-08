package com.weiboss.megumi.megumiitem.file;

import com.weiboss.megumi.megumiitem.Main;
import com.weiboss.megumi.megumiitem.constructor.WearRate;
import com.weiboss.megumi.megumiitem.util.RegularUtil;
import com.weiboss.megumi.megumiitem.constructor.RangeValue;
import com.weiboss.megumi.megumiitem.constructor.Template;
import com.weiboss.megumi.megumiitem.util.WeiUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

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
            else {
                updateConfig();
                plugin.getLogger().info("File: Load config.yml file successfully");
            }
            if (!messageFile.exists()) {
                messageFile.getParentFile().mkdirs();
                WeiUtil.copyFile(plugin.getResource("message.yml"), messageFile);
                plugin.getLogger().info("File: Create message.yml file successfully");
            }
            else {
                updateMessage();
                plugin.getLogger().info("File: Load message.yml file successfully");
            }
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
            String id = yaml.getString("Item.ID");
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

    private void updateConfig() {
        config.addDefault("Prefix", "[MegumiFlanGun]");
        config.addDefault("Config.PreviewDisplay", "&8&l[预览效果&8&l]");
        config.addDefault("Config.Vampire.Enable", false);
        config.addDefault("Config.Vampire.Time", 1);
        config.addDefault("Config.Vampire.Amount", 10);
        config.addDefault("Config.Vampire.MaxValue", 20);
        config.addDefault("Config.NotTransferable", "&e&l[&c&l!&e&l] &7禁止交易");
        config.addDefault("Config.LevelLimit", "&e&l[&c&l!&e&l] &b&oLv.%s% &3级以上可以使用");
        config.addDefault("Config.StatTrak", "&c该物品具有 StatTrak™ 技术");
        config.addDefault("Config.StatTrakStat", "&cStatTrak? 已认证杀敌数: %value%");
        config.addDefault("Config.StatTrakTag", "(StatTrak™)");
        config.addDefault("Config.WearValue", "&3磨损: %value%");
        config.addDefault("Config.TransferableBlackList", Collections.singletonList("/help"));
        config.addDefault("Config.Attribute.Prefix", "&a∵ &3");
        config.addDefault("Config.Attribute.Part", "&f: ");
        config.addDefault("Config.Attribute.Positive", "&a+");
        config.addDefault("Config.Attribute.Negative", "&c");
        config.addDefault("Config.Attribute.Multiply", "&3x");
        config.addDefault("Config.Attribute.Rate","%");
        config.addDefault("Formats.Health", "血量");
        config.addDefault("Formats.Damage", "物理伤害");
        config.addDefault("Formats.Defense", "物理防御");
        config.addDefault("Formats.IgnoreDamage", "无视伤害");
        config.addDefault("Formats.IgnoreDefense", "无视防御");
        config.addDefault("Formats.Vampire", "吸血");
        config.addDefault("Formats.HitChance", "命中率");
        config.addDefault("Formats.DodgeChance", "闪避率");
        config.addDefault("Formats.WreckChance", "破击率");
        config.addDefault("Formats.BlockChance", "格挡率");
        config.addDefault("Formats.BlockRate", "格挡强度");
        config.addDefault("Formats.CritChance", "暴击率");
        config.addDefault("Formats.CritRate", "暴击强度");
        config.addDefault("Formats.AntiknockChance", "抗爆率");
        config.addDefault("Formats.MovementSpeed", "移动速度");
        config.addDefault("SoulBound.UnBound", "&e&l[&c&l!&e&l] &7请绑定后使用");
        config.addDefault("SoulBound.Bound", "&6灵魂绑定: &7&o%s%");
        config.addDefault("SoulBound.OpLock", false);
        config.addDefault("SoulBound.DeathDrop", false);
        config.addDefault("SoulBound.Bind.OnItemDrop", true);
        config.addDefault("SoulBound.Bind.OnItemPickup", true);
        config.addDefault("SoulBound.Bind.OnItemClick", true);
        config.addDefault("SoulBound.Bind.OnItemInteract", true);
        config.addDefault("Wear.FactoryNew.Name", "&8&l[&e崭新出厂&8&l]");
        config.addDefault("Wear.FactoryNew.Min", 0);
        config.addDefault("Wear.FactoryNew.Max", 0.0699999);
        config.addDefault("Wear.MinimalWear.Name", "&8&l[&b略有磨损&8&l]");
        config.addDefault("Wear.MinimalWear.Min", 0.07);
        config.addDefault("Wear.MinimalWear.Max", 0.14999);
        config.addDefault("Wear.FieldTested.Name", "&8&l[&a久经沙场&8&l]");
        config.addDefault("Wear.FieldTested.Min", 0.15);
        config.addDefault("Wear.FieldTested.Max", 0.37999);
        config.addDefault("Wear.WellWorn.Name", "&8&l[&3破碎不堪&8&l]");
        config.addDefault("Wear.WellWorn.Min", 0.38);
        config.addDefault("Wear.WellWorn.Max", 0.44999);
        config.addDefault("Wear.BattleScarred.Name", "&8&l[&7战痕累累&8&l]");
        config.addDefault("Wear.BattleScarred.Min", 0.45);
        config.addDefault("Wear.BattleScarred.Max", 1);
        config.addDefault("Gui.SoulBound.Title", "&7&l[&c&l灵魂绑定&7&l]");
        config.addDefault("Gui.SoulBound.Confirm.ID", 1);
        config.addDefault("Gui.SoulBound.Confirm.Data", 0);
        config.addDefault("Gui.SoulBound.Confirm.Name", "&8&l[&a确认&8&l]");
        config.addDefault("Gui.SoulBound.Confirm.Lore", Collections.singletonList("Confirm Bind"));
        config.addDefault("Gui.SoulBound.Other.ID", 160);
        config.addDefault("Gui.SoulBound.Other.Data", 11);
        try {
            config.load(configFile);
            config.options().copyDefaults(true);
            config.save(configFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMessage() {
        message.addDefault("AdminHelp", Arrays.asList(
                "/gun get <template id> <amount> - &b获得模板物品",
                "/gun give <player> <template id> <amount> - &b给予玩家模板物品",
                "/gun attr - 查看所有属性",
                "/gun attr <属性> <数值> - 给手中武器添加属性"));
        message.addDefault("Message.NoPermission", "&c没有权限: %s%");
        message.addDefault("Message.InvalidTemplate", "&c获取物品失败,请检查是否有该物品模板");
        message.addDefault("Message.InvalidValue", "&c无效值: %s%");
        message.addDefault("Message.OfflinePlayer", "&c该玩家不在线");
        message.addDefault("Message.NeedBind", "&b请打开背包右键 %item% 绑定后使用");
        message.addDefault("Message.IllegalUser", "&b你不是 %item% 该物品的绑定者");
        message.addDefault("Message.UnableDrop", "&c你无法丢弃绑定后的物品");
        message.addDefault("Message.SuccessBind", "&a物品绑定成功");
        message.addDefault("Message.LevelLimit", "&c你的等级未达到 %item% 的使用限制");
        message.addDefault("Message.NoTransferable", "&c该物品无法交易");
        message.addDefault("Message.Kill", "%player% 被 %killer% 使用 %item% 杀死");
        message.addDefault("Combat.Damage", "&c&l-%value%❤");
        message.addDefault("Combat.Dodge", "&e闪避");
        message.addDefault("Combat.Crit", "&6&l暴击 &c&l-%value%❤");
        message.addDefault("Combat.Block", "&7&l格挡 &c&l-%value%❤");
        try {
            message.load(messageFile);
            message.options().copyDefaults(true);
            message.save(messageFile);
        }
        catch (Exception e) {
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
