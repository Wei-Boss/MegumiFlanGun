package com.weiboss.megumi.megumiflangun.util;

import org.bukkit.entity.Entity;
import java.lang.reflect.Method;


public class ReflectionUtil {

    private final String nms;
    private final String cb;
    private Class entity;
    private Class craftEntity;
    private Method getHandle;
    private Method getBukkitEntity;

    public ReflectionUtil(String version) {
        this.nms = ("net.minecraft.server." + version);
        this.cb = ("org.bukkit.craftbukkit." + version);
        try {
            this.entity = find(this.nms + ".Entity");
            this.craftEntity = find(this.cb + ".entity.CraftEntity");
            this.getHandle = this.craftEntity.getDeclaredMethod("getHandle");
            this.getHandle.setAccessible(true);
            this.getBukkitEntity = this.entity.getDeclaredMethod("getBukkitEntity");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Entity getOwner(Entity entity) {
        try {
            Object objectA = getEntityBullet(entity);
            if (objectA == null) return null;
            Object objectB = objectA.getClass().getDeclaredField("owner").get(objectA);
            Entity e = (Entity) this.getBukkitEntity.invoke(objectB);
            return e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class find(String path) throws Exception {
        return getClass().getClassLoader().loadClass(path);
    }

    private Object getEntityBullet(Entity entity) {
        try {
            Object objectA = entity;
            Object objectB = this.getHandle.invoke(objectA);
            return objectB;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
