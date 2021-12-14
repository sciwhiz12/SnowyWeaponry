package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.Reference.Items;
import tk.sciwhiz12.snowyweaponry.SnowyWeaponry;

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
        add(Items.EXPLOSIVE_SNOWBALL, "Explosive Snowball");

        add(Items.WAFER_CONE, "Wafer Cone");
        add(Items.SNOW_CONE, "Snow Cone");
        add(Items.GOLDEN_SNOW_CONE, "Golden Snow Cone");
        add(Items.POTION_SNOW_CONE, "Dipped Snow Cone");

        add(Reference.EntityTypes.CORED_SNOWBALL, "Snowball");

        addDamageSourceTranslations();

        addPotionConeTranslations();
    }

    void addPotionConeTranslations() {
        final String baseTranslationKey = Items.POTION_SNOW_CONE.getDescriptionId();

        add(baseTranslationKey + ".effect.empty", "Uncraftable Dipped Snow Cone");
        add(baseTranslationKey + ".effect.water", "Wet Snow Cone");
        add(baseTranslationKey + ".effect.mundane", "Mundane Dipped Snow Cone");
        add(baseTranslationKey + ".effect.thick", "Thick Dipped Snow Cone");
        add(baseTranslationKey + ".effect.awkward", "Awkward Dipped Snow Cone");
        add(baseTranslationKey + ".effect.night_vision", "Snow Cone of Night Vision");
        add(baseTranslationKey + ".effect.invisibility", "Snow Cone of Invisibility");
        add(baseTranslationKey + ".effect.leaping", "Snow Cone of Leaping");
        add(baseTranslationKey + ".effect.fire_resistance", "Snow Cone of Fire Resistance");
        add(baseTranslationKey + ".effect.swiftness", "Snow Cone of Swiftness");
        add(baseTranslationKey + ".effect.slowness", "Snow Cone of Slowness");
        add(baseTranslationKey + ".effect.water_breathing", "Snow Cone of Water Breathing");
        add(baseTranslationKey + ".effect.healing", "Snow Cone of Healing");
        add(baseTranslationKey + ".effect.harming", "Snow Cone of Harming");
        add(baseTranslationKey + ".effect.poison", "Poisonous Snow Cone");
        add(baseTranslationKey + ".effect.regeneration", "Snow Cone of Regeneration");
        add(baseTranslationKey + ".effect.strength", "Snow Cone of Strength");
        add(baseTranslationKey + ".effect.weakness", "Snow Cone of Weakness");
        add(baseTranslationKey + ".effect.levitation", "Snow Cone of Levitation");
        add(baseTranslationKey + ".effect.luck", "Lucky Snow Cone");
        add(baseTranslationKey + ".effect.turtle_master", "Snow Cone of the Turtle Master");
        add(baseTranslationKey + ".effect.slow_falling", "Snow Cone of Slow Falling");
    }

    void addDamageSourceTranslations() {
        // Regular -cored snowballs
        add("death.attack.snowball", "%1$s was snowballed to death by %2$s");
        add("death.attack.snowball.item", "%1$s was snowballed to death by %2$s using %3$s");

        // Explosive snowballs
        add("death.attack.snowball.explosion", "%1$s blew up into icy pieces");
        add("death.attack.snowball.explosion.player", "%1$s was blown to icy pieces by %2$s");
        add("death.attack.snowball.explosion.player.item", "%1$s was blown to icy pieces by %2$s using %3$s");
    }
}
