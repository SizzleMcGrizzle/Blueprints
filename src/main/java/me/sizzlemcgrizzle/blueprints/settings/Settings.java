package me.sizzlemcgrizzle.blueprints.settings;

import org.bukkit.Material;
import org.mineacademy.fo.collection.StrictList;
import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {
	@Override
	protected int getConfigVersion() {
		return 0;
	}

	@Override
	protected String[] getHeader() {
		String[] header = new String[16];
		header[0] = "--------------------------------------------------------------------------------------";
		header[1] = "Welcome to the configuration for the Blueprints plugin";
		header[2] = "There is currently no online documentation, so help is as follows:";
		header[3] = "";
		header[4] = "Play_Sounds - Enable/disable playing sounds for certain things within the plugin.";
		header[5] = "Block: This section relates to everything block";
		header[6] = "  Show_Error_Preview - Enable/disable showing the error block defined below if there are blocks in the way.";
		header[7] = "  Error_Block - Using bukkit's block names, define the error block that will be shown.";
		header[8] = "  Block_Timeout - Amount of time in seconds the error blocks will be shown.";
		header[9] = "  No_Place_Outside_Claims - Enable/disable placing blueprints outside of claims.";
		header[10] = "  No_Place_Block_In_Way - Enable/disable cancelling the placement if there are blocks in the way";
		header[11] = "  No_Place_Admin_Claim - Enable/disable cancelling placement if ANY blocks go into an admin claim.";
		header[12] = "  Ignore_Blocks - A list of blocks that will be ignored if they are in the way.";
		header[13] = "";
		header[14] = "--------------------------------------------------------------------------------------";
		header[15] = "";
		return header;
	}

	public static Boolean PLAY_SOUNDS;

	private static void init() {
		pathPrefix(null);
		PLAY_SOUNDS = getBoolean("Play_Sounds");

	}

	public static class Messages {
		public static String MESSAGE_PREFIX;
		public static String RELOAD_SUCCESS;
		public static String BUILD_SUCCESS;
		public static String BLUEPRINT_NOT_IN_CLAIM;
		public static String PLAYER_NOT_TRUSTED;
		public static String BLUEPRINT_IN_ADMIN_CLAIM;
		public static String SHOW_ERROR_TRUE_MESSAGE;
		public static String SHOW_ERROR_FALSE_MESSAGE;

		private static void init() {
			pathPrefix("Messages");
			MESSAGE_PREFIX = getString("Message_Prefix");
			RELOAD_SUCCESS = getString("Reload_Success");
			BUILD_SUCCESS = getString("Build_Success");
			BLUEPRINT_NOT_IN_CLAIM = getString("Blueprint_Not_In_Claim");
			PLAYER_NOT_TRUSTED = getString("Player_Not_Trusted");
			BLUEPRINT_IN_ADMIN_CLAIM = getString("Blueprint_In_Admin_Claim");
			SHOW_ERROR_TRUE_MESSAGE = getString("Show_Error_True_Message");
			SHOW_ERROR_FALSE_MESSAGE = getString("Show_Error_False_Message");

		}

	}

	public static class Block {
		public static Boolean SHOW_ERROR_PREVIEW;
		public static Integer BLOCK_TIMEOUT;
		public static Material ERROR_BLOCK;
		public static StrictList<Material> IGNORE_BLOCKS;
		public static Boolean NO_PLACE_OUTSIDE_CLAIMS;
		public static Boolean NO_PLACE_BLOCK_IN_WAY;
		public static Boolean NO_PLACE_ADMIN_CLAIM;

		private static void init() {
			pathPrefix("Block");

			SHOW_ERROR_PREVIEW = getBoolean("Show_Error_Preview");
			BLOCK_TIMEOUT = getInteger("Block_Timeout");
			ERROR_BLOCK = Material.getMaterial(getString("Error_Block"));
			IGNORE_BLOCKS = getMaterialList("Ignore_Blocks");
			NO_PLACE_OUTSIDE_CLAIMS = getBoolean("No_Place_Outside_Claims");
			NO_PLACE_BLOCK_IN_WAY = getBoolean("No_Place_Block_In_Way");
			NO_PLACE_ADMIN_CLAIM = getBoolean("No_Place_Admin_Claim");

		}
	}


}