package sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import sciwhiz12.snowyweaponry.Reference;
import sciwhiz12.snowyweaponry.SnowyWeaponry;

import static sciwhiz12.snowyweaponry.Reference.Items;

public class Languages extends LanguageProvider {
    public Languages(DataGenerator gen) {
        super(gen, SnowyWeaponry.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.snowy_weapons", "Snowy Weapons");

        add(Items.DIAMOND_CHUNK, "Diamond Chunk");
        add(Items.NETHERITE_NUGGET, "Netherite Nugget");

        add(Items.IRON_CORED_SNOWBALL, "Iron-cored Snowball");
        add(Items.GOLD_CORED_SNOWBALL, "Gold-cored Snowball");
        add(Items.DIAMOND_CORED_SNOWBALL, "Diamond-cored Snowball");
        add(Items.NETHERITE_CORED_SNOWBALL, "Netherite-cored Snowball");

        add(Items.WAFER_CONE, "Wafer Cone");
        add(Items.SNOW_CONE, "Snow Cone");
        add(Items.GOLDEN_SNOW_CONE, "Golden Snow Cone");

        add(Reference.EntityTypes.CORED_SNOWBALL, "Snowball");

        add("death.attack.snowball", "%1$s was snowballed to death by %2$s");
        add("death.attack.snowball.item", "%1$s was snowballed to death by %2$s using %3$s");
    }
}
