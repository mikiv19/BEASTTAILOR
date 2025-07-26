package com.beasttailor.api.config;
import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.repository.ClothingItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    }
}