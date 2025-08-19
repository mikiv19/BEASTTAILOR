package com.beasttailor.api.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.repository.ClothingItemRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ClothingItemRepository clothingItemRepository;

    public DataSeeder(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }

    @Override
    @Transactional 
    public void run(String... args) throws Exception {
        if (clothingItemRepository.count() == 0) {
            System.out.println("No items found. Seeding initial data...");
            seedClothingItems();
            System.out.println("Initial data seeded successfully.");
        } else {
            System.out.println("Database already contains data. Skipping seed.");
        }
    }

    private void seedClothingItems() {
        // --- Shank & Bones (Goblin-Craft) ---
        ClothingItem goblinJerkin = new ClothingItem(
            "Scrap-Plate Jerkin",
            "A surprisingly sturdy jerkin made from scavenged metal plates and hardened leather. Smells faintly of rust and ambition.",
            "Shank & Bones",
            25.50,
            "CHEST",
            "/images/thumbnails/goblin_jerkin.webp",
            "/images/details/goblin_jerkin.webp"
        );
        goblinJerkin.setStrBonus(1);
        goblinJerkin.setAcBonus(1);
        goblinJerkin.setSpecialProperties("Intimidating");
        clothingItemRepository.save(goblinJerkin);

        // --- Fayr Moda (Elven-Woven) ---
        ClothingItem elvenCloak = new ClothingItem(
            "Cloak of the Moonwood",
            "Woven with threads that shimmer like moonlight on water. It's lighter than air and offers uncanny silence when moving.",
            "Fayr Moda",
            120.00,
            "CHEST", // Cloaks can be considered chest items or a separate slot
            "/images/thumbnails/elven_cloak.webp",
            "/images/details/elven_cloak.webp"
        );
        elvenCloak.setDexBonus(2);
        elvenCloak.setSpecialProperties("Advantage on Stealth checks");
        clothingItemRepository.save(elvenCloak);

        // --- ONYX (Drow-Spun) ---
        ClothingItem drowBoots = new ClothingItem(
            "Boots of the Underdark",
            "Crafted from silent, shadow-spun leather that makes no sound on stone floors. Favored by assassins and those who prefer not to be seen.",
            "ONYX",
            95.75,
            "FEET",
            "/images/thumbnails/drow_boots.webp",
            "/images/details/drow_boots.webp"
        );
        drowBoots.setDexBonus(1); // Specifically for stealth
        drowBoots.setSpecialProperties("Silent Footfalls");
        clothingItemRepository.save(drowBoots);

        // --- Sected (Noble-Born) ---
        ClothingItem paladinGauntlets = new ClothingItem(
            "Gauntlets of the Oath",
            "Impeccably crafted steel gauntlets with minimalist silver inlay. A symbol of duty and resolve, not gaudy decoration.",
            "Sected",
            150.00,
            "HANDS",
            "/images/thumbnails/paladin_gauntlets.webp",
            "/images/details/paladin_gauntlets.webp"
        );
        paladinGauntlets.setAcBonus(1);
        paladinGauntlets.setWisBonus(1);
        paladinGauntlets.setSpecialProperties("Resolute");
        clothingItemRepository.save(paladinGauntlets);

        // --- Signets (Gnome-Forged) ---
        ClothingItem gnomeGoggles = new ClothingItem(
            "Aether-Gazing Goggles",
            "A complex contraption of brass, gears, and finely ground lenses. Allows the wearer to perceive faint magical auras.",
            "Signets",
            250.00,
            "HEAD", // Or accessory
            "/images/thumbnails/gnome_goggles.webp",
            "/images/details/gnome_goggles.webp"
        );
        gnomeGoggles.setIntBonus(1);
        gnomeGoggles.setSpecialProperties("+2 to Arcana checks");
        clothingItemRepository.save(gnomeGoggles);

                ClothingItem sunstoneRegalia = new ClothingItem(
            "Regalia of the Sunstone King",
            "Armor reserved for the highest echelons of the royal guard. The golden pauldrons are shaped like solar flares, and the cloak is woven from solidified sunbeams.",
            "Sected",
            850.00,
            "CHEST",
            "https://cdna.artstation.com/p/assets/images/images/072/815/886/large/zahra-rezaei-zahra-rezaei-v-2.jpg?1708342263",
            "https://cdna.artstation.com/p/assets/images/images/072/815/886/large/zahra-rezaei-zahra-rezaei-v-2.jpg?1708342263"
        );
        sunstoneRegalia.setStrBonus(1);
        sunstoneRegalia.setConBonus(2);
        sunstoneRegalia.setWisBonus(1);
        sunstoneRegalia.setChaBonus(2);
        sunstoneRegalia.setAcBonus(2);
        sunstoneRegalia.setSpecialProperties("Allies within 10ft gain advantage on saves vs. fear");
        clothingItemRepository.save(sunstoneRegalia);

        // --- Sected (Noble-Born) ---
        ClothingItem priestessVestments = new ClothingItem(
            "War-Priestess's Vestments",
            "A fusion of practicality and station. The embossed leather cuirass provides vital protection, while the flowing azure dress and red cloak signify rank and conviction.",
            "Sected",
            550.00,
            "CHEST",
            "https://cdnb.artstation.com/p/assets/images/images/090/809/543/large/zahra-3d-1.jpg?1754976816", 
            "https://cdnb.artstation.com/p/assets/images/images/090/809/543/large/zahra-3d-1.jpg?1754976816"
        );
        priestessVestments.setStrBonus(1);
        priestessVestments.setConBonus(1);
        priestessVestments.setWisBonus(1);
        priestessVestments.setChaBonus(1);
        priestessVestments.setAcBonus(2);
        priestessVestments.setSpecialProperties("Grants allies within 10ft a +1 bonus to saves");
        clothingItemRepository.save(priestessVestments);
            
        // --- ONYX (Drow-Spun) ---
        ClothingItem twilightRobes = new ClothingItem(
            "Robes of the Twilight Orchid",
            "Woven from silks that shift in color from blood-red to deepest night. The silver embroidery depicts orchids that only bloom in moonless caverns.",
            "ONYX",
            480.00,
            "CHEST",
            "https://cdna.artstation.com/p/assets/images/images/057/657/478/large/shiva-004.jpg?1672272318",
            "https://cdna.artstation.com/p/assets/images/images/057/657/478/large/shiva-004.jpg?1672272318"
        );
        twilightRobes.setDexBonus(1);
        twilightRobes.setIntBonus(2);
        twilightRobes.setChaBonus(2);
        twilightRobes.setAcBonus(1);
        twilightRobes.setSpecialProperties("Grants Advantage on Charisma (Deception) checks");
        clothingItemRepository.save(twilightRobes);
            
        // --- Fayr Moda (Elven-Woven) ---
        ClothingItem wayfarerDress = new ClothingItem(
            "Wayfarer's Field Dress",
            "A practical yet elegant dress of durable homespun linen and wool. The corset provides support for long journeys, and the hooded capelet offers protection from sudden showers.",
            "Fayr Moda",
            85.00,
            "CHEST",
            "https://cdnb.artstation.com/p/assets/images/images/068/425/525/large/shiva-0.jpg?1697760071",
            "https://cdnb.artstation.com/p/assets/images/images/068/425/525/large/shiva-0.jpg?1697760071"
        );
        wayfarerDress.setDexBonus(1);
        wayfarerDress.setConBonus(1);
        wayfarerDress.setWisBonus(1);
        wayfarerDress.setSpecialProperties("Grants Advantage on Constitution saves vs. exhaustion");
        clothingItemRepository.save(wayfarerDress);
    }
}