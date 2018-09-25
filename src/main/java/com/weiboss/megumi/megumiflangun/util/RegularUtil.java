package com.weiboss.megumi.megumiflangun.util;

import com.weiboss.megumi.megumiflangun.file.Config;
import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {

    /*判断是否为范围值字符串,例如字符串 “5:6” */
    public static Boolean isRange(String s) {
        Pattern pattern = Pattern.compile("([-\\+]?[.\\d]+):([-\\+]?[.\\d]+)");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /*判断这条字符串是否是属性格式*/
    public static Boolean isAttribute(String s) {
        String regularA = filter((Config.Attr.Prefix + "%attr%" + Config.Attr.Part + "%value%"))
                .replace("%attr%", "(\\S+)")
                .replace("%value%", "([-\\+]?[.\\d]+)");
        String regularB = filter((Config.Attr.Prefix + "%attr%" + Config.Attr.Part + "%value%" + Config.Attr.Rate))
                .replace("%attr%", "(\\S+)")
                .replace("%value%", "([-\\+]?[.\\d]+)");
        Pattern patternA = Pattern.compile(regularA);
        Matcher matcherA = patternA.matcher(filter(s));
        Pattern patternB = Pattern.compile(regularB);
        Matcher matcherB = patternB.matcher(filter(s));
        return (matcherA.matches() || matcherB.matches());
    }

    public static Boolean isCritRate(String s) {
        String regular = filter(Config.Attr.Prefix + "%attr%" + Config.Attr.Part + Config.Attr.Multiply + "%value%")
                .replace("%attr%", "(\\S+)")
                .replace("%value%", "([+]?[.\\d]+)");
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(filter(s));
        return matcher.matches();
    }

    /*判断字符串是否为等级限制格式*/
    public static Boolean isLevelLimit(String s) {
        String regular = filter(Config.Lore.LevelLimit).replace("%s%", "(\\d+)");
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(filter(s));
        return matcher.matches();
    }

    /*判断字符串是否是灵魂绑定格式*/
    public static Boolean isSoulBound(String s) {
        String regular = filter(Config.SoulBound.Bound).replace("%s%", "(\\S+)");
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(filter(s));
        return matcher.matches();
    }

    /*判断字符串是否是磨损值格式*/
    public static Boolean isWearValue(String s) {
        String regular = filter(Config.Lore.WearValue.replace("%value%", "([+]?[.\\d]+)"));
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(filter(s));
        return matcher.matches();
    }

    private static String filter(String s) {
        s = ChatColor.stripColor(s.replace(" ", ""));
        String regEx = "[`~!@#$^&*()\\-+={}':;,\\[\\].<>/?￥…（）_+|【】‘；：”“’。，、？\\s]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        return m.replaceAll("").trim();
    }
}
