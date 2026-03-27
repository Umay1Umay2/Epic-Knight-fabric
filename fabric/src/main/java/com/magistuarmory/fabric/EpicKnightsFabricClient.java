package com.magistuarmory.fabric;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.event.ClientEvents;
import net.fabricmc.api.ClientModInitializer;

public class EpicKnightsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EpicKnights.checkBetterCombatOrEpicFightInstalled();
        ClientEvents.init();
        // ModModels initialization disabled - rendering system requires 1.21.4 compatibility fixes
        // This will be re-enabled once rendering subsystem is updated
    }
}
