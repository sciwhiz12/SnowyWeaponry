package tk.sciwhiz12.snowyweaponry.util;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * General utility methods.
 *
 * @author SciWhiz12
 */
public final class Util {
    // Prevent instantiation
    private Util() {
    }

    /**
     * A fix for IntelliJ IDEA's "Constant conditions" inspection. Used for
     * {@link net.minecraftforge.registries.ObjectHolder} fields.
     *
     * @return {@code null}
     */
    @NonNull
    public static <T> T Null() {
        //noinspection ConstantConditions
        return null;
    }
}
