package de.blockschmiede.breedcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import de.blockschmiede.breedcraft.blocks.CornCrop;
import de.blockschmiede.breedcraft.blocks.Herbs;
import de.blockschmiede.breedcraft.blocks.SoyCrop;
import de.blockschmiede.breedcraft.entities.BreedCow;
import de.blockschmiede.breedcraft.events.BoneMealEventHandler;
import de.blockschmiede.breedcraft.items.BrandingIronHot;
import de.blockschmiede.breedcraft.items.ItemHerbs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "breedcraft", name = "BreedCraft", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class BreedCraft {

	private static final String[] herbNames = { "Thyme", "Valerian", "Aloe",
			"Fennel", "St John's wort", "Chamomile", "Yarrow" };

	int fertileSoilID, cornCropID, soyCropID, herbCropID;
	int cornSeedsID, cornCobID, soyBeansID, brandingIronID, brandingIronHotID;

	public static Block fertileSoil, cornCrop, soyCrop, herbCrop;
	public static ItemSeeds cornSeeds, soyBeans;
	public static Item cornCob, brandingIron, brandingIronHot;
	public static final CreativeTabs breedTab = new CreativeTabs("BreedCraft") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Item.wheat, 1, 0);
		}
	};

	// The instance of your mod that Forge uses.
	@Instance("BreedCraft")
	public static BreedCraft instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "de.blockschmiede.breedcraft.client.ClientProxy", serverSide = "de.blockschmiede.breedcraft.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		fertileSoilID = config.getBlock("FertileSoil", 1337).getInt();
		cornCropID = config.getBlock("CornCrop", 1338).getInt();
		soyCropID = config.getBlock("SoyCrop", 1339).getInt();
		herbCropID = config.getBlock("HerbCrop", 1340).getInt();

		// config.getItem("Fennel Blossom", 17300);
		cornSeedsID = config.getItem("cornSeeds", 17300).getInt();
		cornCobID = config.getItem("cornCob", 17301).getInt();
		soyBeansID = config.getItem("soyBeans", 17302).getInt();
		brandingIronID = config.getItem("brandingIron", 17303).getInt();
		brandingIronHotID = config.getItem("brandingIronHot", 17304).getInt();

		config.save();

		MinecraftForge.EVENT_BUS.register(new BoneMealEventHandler());

	}

	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();

		LanguageRegistry.instance().addStringLocalization(
				"itemGroup.BreedCraft", "en_US", "BreedCraft");

		fertileSoil = new Block(fertileSoilID, 0, Material.ground)
				.setTextureFile(CommonProxy.BLOCK_PNG).setCreativeTab(breedTab)
				.setBlockName("fertileSoil").setHardness(0.5F)
				.setStepSound(Block.soundGravelFootstep);

		MinecraftForge.setBlockHarvestLevel(fertileSoil, "shovel", 0);
		GameRegistry.registerBlock(fertileSoil, "fertileSoil");
		LanguageRegistry.addName(fertileSoil, "Fertile Soil");

		cornCrop = new CornCrop(cornCropID);
		GameRegistry.registerBlock(cornCrop, "cornCrop");
		soyCrop = new SoyCrop(soyCropID);
		GameRegistry.registerBlock(soyCrop, "soyCrop");

		herbCrop = new Herbs(herbCropID);
		GameRegistry.registerBlock(herbCrop, ItemHerbs.class, "herbs");
		for (int ix = 0; ix < 7; ix++) {
			ItemStack herbCropStack = new ItemStack(herbCrop, 1, ix);
			LanguageRegistry.addName(herbCropStack,
					herbNames[herbCropStack.getItemDamage()]);
		}

		cornSeeds = (ItemSeeds) new ItemSeeds(cornSeedsID, cornCrop.blockID,
				Block.tilledField.blockID).setItemName("cornSeeds")
				.setIconIndex(2).setTextureFile(CommonProxy.ITEMS_PNG)
				.setCreativeTab(breedTab);
		LanguageRegistry.addName(cornSeeds, "Corn Seeds");

		soyBeans = (ItemSeeds) new ItemSeeds(soyBeansID, soyCrop.blockID,
				Block.tilledField.blockID).setItemName("soyBeans")
				.setIconIndex(4).setTextureFile(CommonProxy.ITEMS_PNG)
				.setCreativeTab(breedTab);
		LanguageRegistry.addName(soyBeans, "Soy Beans");

		cornCob = new Item(cornCobID).setItemName("cornCob")
				.setTextureFile(CommonProxy.ITEMS_PNG).setCreativeTab(breedTab)
				.setIconIndex(3);
		LanguageRegistry.addName(cornCob, "Corn Cob");

		brandingIron = new Item(brandingIronID).setItemName("brandingIron")
				.setTextureFile(CommonProxy.ITEMS_PNG).setCreativeTab(breedTab)
				.setIconIndex(5);
		LanguageRegistry.addName(brandingIron, "Branding Iron");

		brandingIronHot = new BrandingIronHot(brandingIronHotID);
		LanguageRegistry.addName(brandingIronHot, "Hot Branding Iron");

		GameRegistry.addRecipe(new ItemStack(fertileSoil, 4), "ddd", "dbd",
				"ddd", 'd', new ItemStack(Block.dirt, 1), 'b', new ItemStack(
						Item.dyePowder, 1, 15));
		GameRegistry.addShapelessRecipe(new ItemStack(cornSeeds, 4),
				new ItemStack(cornCob));

		GameRegistry.addRecipe(new ItemStack(brandingIron), " ii", " si",
				"s  ", 'i', new ItemStack(Item.ingotIron), 's', new ItemStack(
						Item.stick));
		GameRegistry.addSmelting(brandingIron.itemID, new ItemStack(brandingIronHot), 0.0F);
		
		EntityRegistry.registerModEntity(BreedCow.class, "Breed Cow", 1, this, 80, 3, true);

	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}