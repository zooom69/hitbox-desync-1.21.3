package me.aidan.sydney.modules.impl.movement;

import me.aidan.sydney.events.SubscribeEvent;
import me.aidan.sydney.events.impl.TickEvent;
import me.aidan.sydney.modules.Module;
import me.aidan.sydney.modules.RegisterModule;
import net.minecraft.util.math.Vec3d;

@RegisterModule(name = "HitboxDesync", description = "Precisely offsets your position to glitch out Minecraft hitbox calculations.", category = Module.Category.MOVEMENT)
public class HitboxDesyncModule extends Module {
    @SubscribeEvent(priority = Integer.MAX_VALUE)
    public void onTick(TickEvent event) {
        if (getNull()) {
            setToggled(false);
            return;
        }

        Vec3d vec3d = mc.player.getBlockPos().toCenterPos();

        boolean flagX = (vec3d.x - mc.player.getX()) > 0;
        boolean flagZ = (vec3d.z - mc.player.getZ()) > 0;

        double x = vec3d.x + 0.20000000009497754 * (flagX ? -1 : 1);
        double z = vec3d.z + 0.2000000000949811 * (flagZ ? -1 : 1);

        mc.player.setPosition(x, mc.player.getY(), z);

        setToggled(false);
    }
}
