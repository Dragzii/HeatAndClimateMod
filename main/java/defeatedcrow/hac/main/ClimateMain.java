/**
 * Copyright (c) defeatedcrow, 2016
 * URL:http://defeatedcrow.jp/modwiki/Mainpage
 * Please check the License.txt included in the package file of this Mod.
 */

package defeatedcrow.hac.main;

import java.nio.file.Paths;
import java.util.Calendar;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.fluid.FluidDictionaryDC;
import defeatedcrow.hac.core.recipe.RecipeJsonMaker;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.MainConfig;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.config.WorldGenConfig;
import defeatedcrow.hac.main.recipes.DCFluidFuelRegister;
import defeatedcrow.hac.main.recipes.OreDicRegister;
import defeatedcrow.hac.main.util.DCChunkloadContoroller;
import defeatedcrow.hac.main.worldgen.VeinTableRegister;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = ClimateMain.MOD_ID, name = ClimateMain.MOD_NAME,
		version = ClimateMain.MOD_MEJOR + "." + ClimateMain.MOD_MINOR + "." + ClimateMain.MOD_BUILD,
		dependencies = ClimateMain.MOD_DEPENDENCIES, acceptedMinecraftVersions = ClimateCore.MOD_ACCEPTED_MC_VERSIONS,
		useMetadata = true)
public class ClimateMain {
	public static final String MOD_ID = "dcs_climate";
	public static final String MOD_NAME = "HeatAndClimateMod";
	public static final int MOD_MEJOR = 2;
	public static final int MOD_MINOR = 7;
	public static final int MOD_BUILD = 6;
	public static final String MOD_DEPENDENCIES = "required-after:dcs_lib@[2.7.3,)";

	@SidedProxy(clientSide = "defeatedcrow.hac.main.client.ClientMainProxy",
			serverSide = "defeatedcrow.hac.main.CommonMainProxy")
	public static CommonMainProxy proxy;

	@Instance("dcs_climate")
	public static ClimateMain instance;

	public static final CreativeTabs tool = new CreativeTabClimateTool(MOD_ID);
	public static final CreativeTabs machine = new CreativeTabClimateMachine(MOD_ID + "_machine");
	public static final CreativeTabs food = new CreativeTabClimateFood(MOD_ID + "_food");
	public static final CreativeTabs build = new CreativeTabClimateBuild(MOD_ID + "_build");
	public static final CreativeTabs cont = new CreativeTabClimateContainer(MOD_ID + "_container");
	public static final CreativeTabs cloth = new CreativeTabClimateEquips(MOD_ID + "_clothing");

	public static final Calendar CAL = Calendar.getInstance();
	public static int month = 0;
	public static int day = 0;

	public static final String AUTHOR = "defeatedcrow";

	@EventHandler
	public void construction(FMLConstructionEvent event) {
		// TextureStitch
		proxy.loadConst();
		FluidRegistry.enableUniversalBucket();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// config
		MainConfig.INSTANCE.load(event.getModConfigurationDirectory());
		// api
		MainAPIManager.fuelRegister = new DCFluidFuelRegister();
		MainAPIManager.veinRegister = VeinTableRegister.INSTANCE;
		MainAPIManager.isLoaded = true;

		// integration
		DCIntegrationCore.loadedCheck();

		// Material
		proxy.loadMaterial();
		// TileEntity
		proxy.loadTE();
		// Entity
		proxy.loadEntity();
		// potion
		proxy.loadPotion();
		// enchant
		proxy.loadEnchantment();
		// villager
		if (ModuleConfig.village) {
			proxy.loadVillager();
		}
		// achievement
		// AchievementClimate.load();
		OreDicRegister.load();
		// loader
		DCChunkloadContoroller.getInstance().preInit();

		DCIntegrationCore.INSTANCE.loadPre();

		if (ClimateCore.isDebug) {
			RecipeJsonMaker.canUse = false;
			RecipeJsonMaker.canDeprecate = true;
			RecipeJsonMaker.dir = Paths
					.get("E:\\modding\\1.12.1\\hac_main_2\\src\\main\\resources\\assets\\dcs_climate\\recipes\\");
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		// milk
		Fluid milk = FluidRegistry.getFluid("milk");
		if (milk != null) {
			MainInit.milk = milk;
			FluidDictionaryDC.registerFluidDic(milk, "milk");
		}

		// config
		MainCoreConfig.INSTANCE.leadBlockNames();
		WorldGenConfig.INSTANCE.leadBlockNames();

		// WorldGen
		proxy.loadWorldGen();
		// event
		proxy.loadInit();

		// other things
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		DCIntegrationCore.INSTANCE.loadInit();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Recipes
		proxy.loadRecipes();
		DCIntegrationCore.INSTANCE.loadPost();

		// WorldGen
		proxy.loadWorldGenPost();

		// date
		month = CAL.get(CAL.MONTH);
		day = CAL.get(CAL.DATE);
	}

}
