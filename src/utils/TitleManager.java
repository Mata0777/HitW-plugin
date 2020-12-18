package utils;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Spigot API title class using NMS
 */
public class TitleManager {
	
	public void sendPacket(Player player, Object packet) {
	    try {
	        Object handle = player.getClass().getMethod("getHandle").invoke(player);
	        Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
	        playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static Class<?> getNMSClass(String name) {
	    try {
	        return Class.forName("net.minecraft.server."
	                + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public void send(Player player, String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime) {
	    try {
	        
	    	Constructor<?> delayConstructor = getNMSClass("PacketPlayOutTitle")
	    			.getConstructor(int.class, int.class, int.class);
	    	
	    	Object delayPacket = delayConstructor.newInstance(fadeInTime, showTime, fadeOutTime);
	       
	        
	        Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
	        		.invoke(null, "{\"text\": \"" + title + "\"}");
	        
	        Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
	        		getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"));
	        
	        Object titlePacket = titleConstructor.newInstance(
	                getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle);
	        
	        Object chatSubTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
	        		.invoke(null, "{\"text\": \"" + subtitle + "\"}");
	        
	        Object subtitlePacket = titleConstructor.newInstance(
	                getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatSubTitle);

	        sendPacket(player, delayPacket);
	        sendPacket(player, titlePacket);
	        sendPacket(player, subtitlePacket);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
