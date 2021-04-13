package utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Manage in game ranks
 * @author Blackoutburst
 */
public class RankManager {
	
	/**
	 * Load ranks from Hypixel API
	 * @param player player who will get the rank
	 * @return the rank
	 * @author Blackoutburst
	 */
	public static String loadRank(Player player) {
		String[] value;
		String rank = "";
		int qualification = 0;
		int finals = 0;
		String output = Request.getPlayerInfo(player.getUniqueId().toString().replace("-", "").toString());
		
		value = output.split(",");
		for (int i = 0; i < value.length; i++) {
			if (value[i].contains("hitw_record_q")) {
				qualification = Integer.valueOf(value[i].replaceAll("[^0-9]", ""));
			}
			if (value[i].contains("hitw_record_f")) {
				finals = Integer.valueOf(value[i].replaceAll("[^0-9]", ""));
			}
		}
		if (player.getUniqueId().toString().replace("-", "").toString().equals("9293868b414c42b2bd8e3bcb791247b9")) {
			rank = ChatColor.DARK_RED+"["+ChatColor.DARK_GRAY+"Yaku"+ChatColor.DARK_RED+"]"+ChatColor.DARK_BLUE;
		} else {
			rank = getRank(player, qualification, finals);
		}
		
		setDisplayName(player, rank + player.getName() + ChatColor.RESET);
		return (rank);
	}
	
	/**
	 * Get rank from hitW score
	 * @param player player who will get a rank
	 * @param qualification qualification score
	 * @param finals finals score
	 * @return rank name
	 * @author Blackoutburst
	 */
	private static String getRank(Player player, int qualification, int finals) {
		if (qualification >= 500 || finals >= 500) {
			return(ChatColor.DARK_GREEN + "[500+] ");
			
		} else if (qualification >= 475 || finals >= 475) {
			return(ChatColor.GREEN + "[450"+ ChatColor.DARK_GREEN +"+"+ChatColor.GREEN+"] ");
			
		} else if (qualification >= 450 || finals >= 450) {
			return(ChatColor.GREEN + "[450+] ");
			
		} else if (qualification >= 425 || finals >= 425) {
			return(ChatColor.DARK_AQUA + "[400"+ ChatColor.GREEN +"+"+ChatColor.DARK_AQUA+"] ");
			
		} else if (qualification >= 400 || finals >= 400) {
			return(ChatColor.DARK_AQUA + "[400+] ");
			
		} else if (qualification >= 375 || finals >= 375) {
			return(ChatColor.BLUE + "[350"+ ChatColor.DARK_AQUA +"+"+ChatColor.BLUE+"] ");
			
		} else if (qualification >= 350 || finals >= 350) {
			return(ChatColor.BLUE + "[350+] ");
			
		} else if (qualification >= 325 || finals >= 325) {
			return(ChatColor.DARK_PURPLE + "[300"+ ChatColor.BLUE +"+"+ChatColor.DARK_PURPLE+"] ");
			
		} else if (qualification >= 300 || finals >= 300) {
			return(ChatColor.DARK_PURPLE + "[300+] ");
			
		} else if (qualification >= 275 || finals >= 275) {
			return(ChatColor.LIGHT_PURPLE + "[250"+ ChatColor.DARK_PURPLE +"+"+ChatColor.LIGHT_PURPLE+"] ");
			
		} else if (qualification >= 250 || finals >= 250) {
			return(ChatColor.LIGHT_PURPLE + "[250+] ");
			
		} else if (qualification >= 225 || finals >= 225) {
			return(ChatColor.DARK_RED + "[200"+ ChatColor.LIGHT_PURPLE +"+"+ChatColor.DARK_RED+"] ");
			
		} else if (qualification >= 200 || finals >= 200) {
			return(ChatColor.DARK_RED + "[200+] ");
			
		} else if (qualification >= 175 || finals >= 175) {
			return(ChatColor.RED + "[150"+ ChatColor.DARK_RED +"+"+ChatColor.RED+"] ");
			
		} else if (qualification >= 150 || finals >= 150) {
			return(ChatColor.RED + "[150+] ");
			
		} else if (qualification >= 125 || finals >= 125) {
			return(ChatColor.GOLD + "[100"+ ChatColor.RED +"+"+ChatColor.GOLD+"] ");
			
		} else if (qualification >= 100 || finals >= 100) {
			return(ChatColor.GOLD + "[100+] ");
			
		} else if (qualification >= 75 || finals >= 75) {
			return(ChatColor.YELLOW + "[50"+ ChatColor.GOLD +"+"+ChatColor.YELLOW+"] ");
			
		} else if (qualification >= 50 || finals >= 50) {
			return(ChatColor.YELLOW + "[50+] ");
			
		} else {
			return(ChatColor.GRAY+"");
		}
	}
	
	/**
	 * Change user name in chat and tab
	 * @param player player who get the rank
	 * @param str rank name
	 * @author Blackoutburst
	 */
	private static void setDisplayName(Player player, String str) {
		player.setDisplayName(str);
		player.setPlayerListName(str);
	}
}
