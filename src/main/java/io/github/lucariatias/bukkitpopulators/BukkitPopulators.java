package io.github.lucariatias.bukkitpopulators;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.jacekk.bukkit.skylandsplus.generation.BiomePopulator;
import uk.co.jacekk.bukkit.skylandsplus.generation.EndTowerPopulator;
import uk.co.jacekk.bukkit.skylandsplus.generation.NetherFirePopulator;
import uk.co.jacekk.bukkit.skylandsplus.generation.NetherGlowstonePopulator;
import uk.co.jacekk.bukkit.skylandsplus.generation.NetherSoulSandPopulator;
import uk.co.jacekk.bukkit.skylandsplus.generation.SnowPopulator;

public class BukkitPopulators extends JavaPlugin {

	@Override
	public void onEnable() {
		PluginManager pluginManager = getServer().getPluginManager();
		Plugin skylandsPlus = pluginManager.getPlugin("SkylandsPlus");
		if (skylandsPlus != null) {
			this.getLogger().info("Loading populators from SkylandsPlus");
		}
	}

	/**
	 * Gets the "normal" populators for a particular world.
	 * 
	 * This function works like the ChunkGenerator function, but is not called
	 * by bukkit directly.
	 * 
	 * @param world the world
	 * @return the normal populators for a world
	 * @see ChunkGenerator#getDefaultPopulators(World)
	 */
	public List<BlockPopulator> getDefaultPopulators(World world) {
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();

		PluginManager pluginManager = this.getServer().getPluginManager();
		Plugin skylandsPlus = pluginManager.getPlugin("SkylandsPlus");

		switch (world.getEnvironment()) {
			case NORMAL:
				populators.add(new BiomeTreePopulator());
				populators.add(new OrePopulator(Material.COAL_ORE, Material.STONE, 8, 8));
				populators.add(new OrePopulator(Material.IRON_ORE, Material.STONE, 6, 6));
				populators.add(new OrePopulator(Material.LAPIS_ORE, Material.STONE, 2, 4));
				populators.add(new OrePopulator(Material.GOLD_ORE, Material.STONE, 4, 4));
				populators.add(new OrePopulator(Material.DIAMOND_ORE, Material.STONE, 3, 4));
				populators.add(new OrePopulator(Material.REDSTONE_ORE, Material.STONE, 4, 16));
				populators.add(new OrePopulator(Material.EMERALD_ORE, Material.STONE, 1, 3));
				if (skylandsPlus != null) {
					populators.add(new BiomePopulator());
					populators.add(new SnowPopulator());
				}
				break;
			case THE_END:
				if (skylandsPlus != null) {
					populators.add(new EndTowerPopulator(world));
				}
				break;
			case NETHER:
				populators.add(new OrePopulator(Material.QUARTZ_ORE, Material.NETHERRACK, 16, 8));
				if (skylandsPlus != null) {
					populators.add(new NetherSoulSandPopulator(world));
					populators.add(new NetherFirePopulator(world));
					populators.add(new NetherGlowstonePopulator(world));
				}
				break;
		}

		return populators;
	}

	/**
	 * Get all populators available
	 * 
	 * @param world the world
	 * @return all the populators available for the world
	 */
	public List<BlockPopulator> getAllPopulators(World world) {
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();

		populators.add(new BiomeTreePopulator());
		populators.add(new BiomeTreePopulator());
		populators.add(new OrePopulator(Material.COAL_ORE, Material.STONE, 8, 8));
		populators.add(new OrePopulator(Material.IRON_ORE, Material.STONE, 6, 6));
		populators.add(new OrePopulator(Material.LAPIS_ORE, Material.STONE, 2, 4));
		populators.add(new OrePopulator(Material.GOLD_ORE, Material.STONE, 4, 4));
		populators.add(new OrePopulator(Material.DIAMOND_ORE, Material.STONE, 3, 4));
		populators.add(new OrePopulator(Material.REDSTONE_ORE, Material.STONE, 4, 16));
		populators.add(new OrePopulator(Material.EMERALD_ORE, Material.STONE, 1, 3));
		populators.add(new OrePopulator(Material.QUARTZ_ORE, Material.NETHERRACK, 16, 8));

		PluginManager pluginManager = getServer().getPluginManager();

		// SkylandsPlus
		Plugin skylandsPlus = pluginManager.getPlugin("SkylandsPlus");
		if (skylandsPlus != null) {
			populators.add(new BiomePopulator());
			populators.add(new SnowPopulator());
			populators.add(new EndTowerPopulator(world));
			populators.add(new NetherSoulSandPopulator(world));
			populators.add(new NetherFirePopulator(world));
			populators.add(new NetherGlowstonePopulator(world));
		}
		return populators;
	}
}
