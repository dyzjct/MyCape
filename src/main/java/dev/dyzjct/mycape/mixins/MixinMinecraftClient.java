package dev.dyzjct.mycape.mixins;

import dev.dyzjct.mycape.MyCape;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Inject(method = "run", at = @At("HEAD"))
    public void run(CallbackInfo ci) {
        MyCape.load();
    }
}
