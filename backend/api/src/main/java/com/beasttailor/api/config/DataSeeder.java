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
        // --- Sected (Noble-Born) ---
ClothingItem gauntletsOfSanTabbat = new ClothingItem(
    "Gauntlets of Saint Tabbat",
    "Sacred relics of a long-dead saint, these gauntlets are a masterpiece of the armorer's art. The engravings tell the story of the saint's deeds, and the gauntlets themselves radiate a holy, protective aura.",
    "Sected",
    1500.00,
    "HANDS",
    "https://cdna.artstation.com/p/assets/images/images/052/434/066/large/piotr-walczuk-01.jpg?1659779785",
    "https://cdna.artstation.com/p/assets/images/images/052/434/066/large/piotr-walczuk-01.jpg?1659779785"
);
gauntletsOfSanTabbat.setWisBonus(2);
gauntletsOfSanTabbat.setChaBonus(2);
gauntletsOfSanTabbat.setAcBonus(2);
gauntletsOfSanTabbat.setSpecialProperties("The wearer can cast 'Bless' once per day");
clothingItemRepository.save(gauntletsOfSanTabbat);

ClothingItem swashbucklerFinery = new ClothingItem(
    "Swashbuckler's Finery",
    "The dashing attire of a charismatic duelist. Features a fine linen shirt under a studded leather vest, designed for a perfect balance of style, protection, and freedom of movement.",
    "Sected",
    275.00,
    "CHEST",
    "https://cdnb.artstation.com/p/assets/images/images/072/001/227/large/shiva-000.jpg?1706420382",
    "https://cdnb.artstation.com/p/assets/images/images/072/001/227/large/shiva-000.jpg?1706420382"
);
swashbucklerFinery.setDexBonus(2);
swashbucklerFinery.setChaBonus(2);
swashbucklerFinery.setAcBonus(1);
swashbucklerFinery.setSpecialProperties("Grants a +2 bonus to Initiative rolls");
clothingItemRepository.save(swashbucklerFinery);

ClothingItem envoyDoublet = new ClothingItem(
    "Envoy's Crimson Doublet",
    "A doublet of fine, crimson wool with elegant leather lacing and subtle draconic embroidery. It is designed to be worn in court or during sensitive negotiations where making an impression is paramount.",
    "Sected",
    225.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/075/960/116/large/shiva-00.jpg?1715824768",
    "https://cdna.artstation.com/p/assets/images/images/075/960/116/large/shiva-00.jpg?1715824768"
);
envoyDoublet.setChaBonus(2);
envoyDoublet.setDexBonus(1);
envoyDoublet.setAcBonus(1);
envoyDoublet.setSpecialProperties("Grants Advantage on Charisma (Persuasion) checks");
clothingItemRepository.save(envoyDoublet);

ClothingItem layeredGorget = new ClothingItem(
    "Layered Leather Gorget",
    "A protective collar and spaulders made from multiple layers of hardened and boiled leather, laced tightly together. It offers superior protection for the neck and shoulders without the weight of steel.",
    "Sected",
    130.00,
    "SHOULDERS",
    "https://cdna.artstation.com/p/assets/images/images/064/894/642/large/zahra-3d-2.jpg?1689015939",
    "https://cdna.artstation.com/p/assets/images/images/064/894/642/large/zahra-3d-2.jpg?1689015939"
);
layeredGorget.setConBonus(1);
layeredGorget.setAcBonus(2);
layeredGorget.setSpecialProperties("Grants Advantage on saves against being choked or silenced");
clothingItemRepository.save(layeredGorget);

ClothingItem nightwardenGarb = new ClothingItem(
    "Night Warden's Garb",
    "The official attire of a high-ranking city warden. The hardened leather cuirass protects the vitals, while the heavy, flowing cape signifies authority and shields from the elements. To wear it is to command respect and fear.",
    "Sected",
    475.00,
    "CHEST",
    "https://cdnb.artstation.com/p/assets/images/images/069/979/491/large/zahra-3d-3.jpg?1701444349",
    "https://cdnb.artstation.com/p/assets/images/images/069/979/491/large/zahra-3d-3.jpg?1701444349"
);
nightwardenGarb.setConBonus(1);
nightwardenGarb.setWisBonus(1);
nightwardenGarb.setChaBonus(1);
nightwardenGarb.setAcBonus(2);
nightwardenGarb.setSpecialProperties("Grants Advantage on Wisdom (Insight) checks");
clothingItemRepository.save(nightwardenGarb);

ClothingItem marksmanTunic = new ClothingItem(
    "Marksman's Padded Tunic",
    "A finely crafted tunic of thick, padded canvas, reinforced with a studded leather gorget and straps. The tailored fit and sturdy bracers provide excellent mobility, making it a favorite among archers and duelists.",
    "Sected",
    190.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/076/782/956/large/zahra-3d-1.jpg?1717778473",
    "https://cdna.artstation.com/p/assets/images/images/076/782/956/large/zahra-3d-1.jpg?1717778473"
);
marksmanTunic.setDexBonus(2);
marksmanTunic.setConBonus(1);
marksmanTunic.setAcBonus(1);
marksmanTunic.setSpecialProperties("You gain a +1 bonus to attack rolls with ranged weapons");
clothingItemRepository.save(marksmanTunic);

ClothingItem duelistGloves = new ClothingItem(
    "Duelist's Padded Gloves",
    "Expertly crafted from hardened leather and quilted fabric, these gloves offer superior grip and protection without sacrificing dexterity. The fine stitching and brass studs mark them as officer's quality.",
    "Sected",
    115.00,
    "HANDS",
    "https://cdnb.artstation.com/p/assets/images/images/063/378/597/large/nasim-beyt-06.jpg?1685399325",
    "https://cdnb.artstation.com/p/assets/images/images/063/378/597/large/nasim-beyt-06.jpg?1685399325"
);
duelistGloves.setDexBonus(2);
duelistGloves.setAcBonus(1);
duelistGloves.setSpecialProperties("Grants Advantage on checks to avoid being disarmed");
clothingItemRepository.save(duelistGloves);

ClothingItem vindicatorGauntlets = new ClothingItem(
    "Vindicator's Gauntlets",
    "Masterwork steel gauntlets, blackened and adorned with intricate gold filigree. To wear them is to feel the weight of justice and the strength to enforce it.",
    "Sected",
    350.00,
    "HANDS",
    "https://cdna.artstation.com/p/assets/images/images/013/186/038/large/eric-j-fitch-gauntlet-0.jpg?1538467848",
    "https://cdna.artstation.com/p/assets/images/images/013/186/038/large/eric-j-fitch-gauntlet-0.jpg?1538467848"
);
vindicatorGauntlets.setStrBonus(1);
vindicatorGauntlets.setChaBonus(1);
vindicatorGauntlets.setAcBonus(1);
vindicatorGauntlets.setSpecialProperties("Cannot be unwillingly disarmed");
clothingItemRepository.save(vindicatorGauntlets);

ClothingItem sellswordGambeson = new ClothingItem(
    "Sellsword's Quilted Gambeson",
    "A thick, durable gambeson of blackened leather, worn by mercenaries and city guards alike. It provides excellent protection against cuts and blunt force, serving as a reliable alternative to heavy plate.",
    "Sected",
    95.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/085/198/102/large/zahra-3d-1.jpg?1740203731",
    "https://cdna.artstation.com/p/assets/images/images/085/198/102/large/zahra-3d-1.jpg?1740203731"
);
sellswordGambeson.setConBonus(2);
sellswordGambeson.setAcBonus(2);
sellswordGambeson.setSpecialProperties("Grants resistance to bludgeoning damage from non-magical attacks");
clothingItemRepository.save(sellswordGambeson);




        // --- Shank & Bones (Goblin-Craft) ---
ClothingItem journeymanPack = new ClothingItem(
    "Journeyman's Pack",
    "A satchel made from thick, road-worn leather that has survived countless journeys. The heavy stitching and robust buckles ensure it will never fail, no matter the hardship. It seems to hold more than it should.",
    "Shank & Bones",
    55.00,
    "ACCESSORY",
    "https://cdna.artstation.com/p/assets/images/images/083/857/630/large/javx-rajabzade-j6.jpg?1736953965",
    "https://cdna.artstation.com/p/assets/images/images/083/857/630/large/javx-rajabzade-j6.jpg?1736953965"
);
journeymanPack.setConBonus(1);
journeymanPack.setSpecialProperties("Increases the wearer's carrying capacity by 50 pounds");
clothingItemRepository.save(journeymanPack);

ClothingItem whelplingsHope = new ClothingItem(
    "Whelp's Hope Charm",
    "A petrified dragon egg from which a young whelp has not yet fully emerged. It radiates a powerful, protective aura, as if the nascent dragon within is shielding its keeper from harm.",
    "Shank & Bones",
    850.00,
    "ACCESSORY",
    "https://cdna.artstation.com/p/assets/images/images/073/425/622/large/thekoswog-1-23.jpg?1709636046",
    "https://cdna.artstation.com/p/assets/images/images/073/425/622/large/thekoswog-1-23.jpg?1709636046"
);
whelplingsHope.setConBonus(3);
whelplingsHope.setAcBonus(1);
whelplingsHope.setSpecialProperties("The wearer is immune to disease");
clothingItemRepository.save(whelplingsHope);

ClothingItem wyrmscaleArmor = new ClothingItem(
    "Wyrmscale Hauberk",
    "A heavy hauberk forged with interlocking, wave-like plates of blackened steel. The jagged maille on the shoulders and tassets is designed to catch and break blades, making the wearer a walking fortress.",
    "Shank & Bones",
    220.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/078/568/676/large/thekoswog-1-4.jpg?1722459580",
    "https://cdna.artstation.com/p/assets/images/images/078/568/676/large/thekoswog-1-4.jpg?1722459580"
);
wyrmscaleArmor.setStrBonus(2);
wyrmscaleArmor.setConBonus(1);
wyrmscaleArmor.setAcBonus(3);
wyrmscaleArmor.setDexBonus(-1);
wyrmscaleArmor.setSpecialProperties("Critical hits against the wearer become normal hits");
clothingItemRepository.save(wyrmscaleArmor);

ClothingItem ravagerMaille = new ClothingItem(
    "Ravager's Iron Hauberk",
    "A formidable hauberk of heavy, blackened iron chain and overlapping plates. The spiked pauldrons are designed to deter grapplers. This armor was not made for parades; it was made to outlast the enemy.",
    "Shank & Bones",
    180.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/078/568/730/large/thekoswog-1-29.jpg?1722459621",
    "https://cdna.artstation.com/p/assets/images/images/078/568/730/large/thekoswog-1-29.jpg?1722459621"
);
ravagerMaille.setStrBonus(2);
ravagerMaille.setConBonus(1);
ravagerMaille.setAcBonus(3);
ravagerMaille.setDexBonus(-1);
ravagerMaille.setSpecialProperties("Attacks of opportunity made against the wearer have disadvantage");
clothingItemRepository.save(ravagerMaille);

ClothingItem travelerBoots = new ClothingItem(
    "Well-Worn Traveler's Boots",
    "Soft, durable leather boots that have seen more roads than a king's carriage. The soles are thin but tough, ideal for feeling out uneven terrain and moving quietly.",
    "Shank & Bones",
    35.00,
    "FEET",
    "https://cdna.artstation.com/p/assets/images/images/084/423/142/large/nasim-beyt-012.jpg?1738323635",
    "https://cdna.artstation.com/p/assets/images/images/084/423/142/large/nasim-beyt-012.jpg?1738323635"
);
travelerBoots.setDexBonus(1);
travelerBoots.setConBonus(1);
travelerBoots.setSpecialProperties("Grants Advantage on Dexterity (Stealth) checks made in natural terrain");
clothingItemRepository.save(travelerBoots);
        
ClothingItem utilityPouch = new ClothingItem(
    "Reinforced Utility Pouch",
    "A robust leather pouch, reinforced with wood plates and brass rivets. Its surprisingly complex clasp is designed to keep contents safe from bumps, falls, and greedy fingers.",
    "Shank & Bones",
    42.50,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/081/359/815/large/nasim-beyt-00.jpg?1730061838",
    "https://cdnb.artstation.com/p/assets/images/images/081/359/815/large/nasim-beyt-00.jpg?1730061838"
);
utilityPouch.setConBonus(1);
utilityPouch.setSpecialProperties("Contents are protected from pickpocketing attempts");
clothingItemRepository.save(utilityPouch);

ClothingItem boneBracer = new ClothingItem(
    "Bracer of the Unbroken",
    "The preserved forearm of a forgotten chieftain, crudely reinforced with scrap iron and bound with rusted wire. The scratched runes seem to shift when not observed directly.",
    "Shank & Bones",
    165.50,
    "ACCESSORY",
    "https://cdna.artstation.com/p/assets/images/images/071/807/434/large/thomas-swijngedau-vignette.jpg?1705998597",
    "https://cdna.artstation.com/p/assets/images/images/071/807/434/large/thomas-swijngedau-vignette.jpg?1705998597"
);
boneBracer.setStrBonus(1);
boneBracer.setConBonus(2);
boneBracer.setChaBonus(-1);
boneBracer.setSpecialProperties("Grants Advantage on Intimidation (Fear) checks");
clothingItemRepository.save(boneBracer);

ClothingItem warBelt = new ClothingItem(
    "Ram-Skull War-Belt",
    "A thick, imposing belt of hardened hide, studded with iron rivets. The bleached skull of a great ram serves as the buckle, marking the wearer as a formidable and primal warrior.",
    "Shank & Bones",
    125.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/053/760/383/large/harris-baig-close-armor3-out.jpg?1662986332",
    "https://cdnb.artstation.com/p/assets/images/images/053/760/383/large/harris-baig-close-armor3-out.jpg?1662986332"
);
warBelt.setStrBonus(1);
warBelt.setConBonus(1);
warBelt.setAcBonus(1);
warBelt.setSpecialProperties("Allows the wearer to use a powerful headbutt charge once per day");
clothingItemRepository.save(warBelt);

ClothingItem boneMantle = new ClothingItem(
    "Krinshaw's Bone-Mantle",
    "The shoulder plate of a great Krinshaw beast, crudely fashioned into a pauldron. It is surprisingly light but incredibly dense, capable of turning aside blades and claws with ease.",
    "Shank & Bones",
    88.00,
    "SHOULDERS",
    "https://cdna.artstation.com/p/assets/images/images/053/760/376/large/harris-baig-close-armor-out.jpg?1662986410",
    "https://cdna.artstation.com/p/assets/images/images/053/760/376/large/harris-baig-close-armor-out.jpg?1662986410"
);
boneMantle.setStrBonus(1);
boneMantle.setConBonus(1);
boneMantle.setAcBonus(1);
boneMantle.setSpecialProperties("Grants Advantage on saves against being knocked prone");
clothingItemRepository.save(boneMantle);





        // --- Fayr Moda (Elven-Woven) ---
ClothingItem verdantEgg = new ClothingItem(
    "Verdant Egg of Life",
    "An ancient stone egg, covered in living moss that never withers. It radiates a gentle warmth and promotes unnatural vitality in all living things nearby. Druids consider it a sacred artifact of rebirth.",
    "Fayr Moda",
    980.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/073/425/801/large/thekoswog-1-2.jpg?1709636291",
    "https://cdnb.artstation.com/p/assets/images/images/073/425/801/large/thekoswog-1-2.jpg?1709636291"
);
verdantEgg.setConBonus(2);
verdantEgg.setWisBonus(2);
verdantEgg.setSpecialProperties("The wearer slowly regenerates health over time (1 HP per minute)");
clothingItemRepository.save(verdantEgg);

ClothingItem heartstoneOfTheWyrm = new ClothingItem(
    "Heartstone of the Wyrm",
    "A smooth, stone-like egg that is warm to the touch. It is empty, yet the faint, spectral image of a dragon can be seen circling within the light it emits. Holding it instills the wearer with draconic resilience and power.",
    "Fayr Moda",
    1200.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/073/425/627/large/thekoswog-1-1.jpg?1709636054",
    "https://cdnb.artstation.com/p/assets/images/images/073/425/627/large/thekoswog-1-1.jpg?1709636054"
);
heartstoneOfTheWyrm.setStrBonus(2);
heartstoneOfTheWyrm.setConBonus(2);
heartstoneOfTheWyrm.setChaBonus(2);
heartstoneOfTheWyrm.setSpecialProperties("Grants resistance to fire damage");
clothingItemRepository.save(heartstoneOfTheWyrm);

ClothingItem greenwoodGarb = new ClothingItem(
    "Greenwood Stalker's Garb",
    "A ranger's attire crafted from silent, forest-green wool and reinforced with a sturdy leather corset and pauldrons. Designed for long patrols and moving unseen through the wilds.",
    "Fayr Moda",
    245.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/079/035/368/large/shiva-006.jpg?1723768604",
    "https://cdna.artstation.com/p/assets/images/images/079/035/368/large/shiva-006.jpg?1723768604"
);
greenwoodGarb.setDexBonus(2);
greenwoodGarb.setConBonus(1);
greenwoodGarb.setWisBonus(1);
greenwoodGarb.setAcBonus(1);
greenwoodGarb.setSpecialProperties("Cannot be tracked by non-magical means in natural terrain");
clothingItemRepository.save(greenwoodGarb);

ClothingItem woodVambrace = new ClothingItem(
    "Vambrace of the Ironwood",
    "Crafted from the famously resilient ironwood tree and bound with hardened leather. This vambrace is surprisingly light, offering formidable protection against glancing blows and arrows.",
    "Fayr Moda",
    75.00,
    "HANDS",
    "https://cdnb.artstation.com/p/assets/images/images/080/857/195/large/nasim-beyt-00.jpg?1728698222",
    "https://cdnb.artstation.com/p/assets/images/images/080/857/195/large/nasim-beyt-00.jpg?1728698222"
);
woodVambrace.setConBonus(1);
woodVambrace.setAcBonus(1);
woodVambrace.setSpecialProperties("Grants resistance to piercing damage from non-magical projectiles");
clothingItemRepository.save(woodVambrace);

ClothingItem moonpetalRing = new ClothingItem(
    "Moonpetal Signet",
    "A ring carved from a single piece of moonwood, adorned with a silver-leafed flower holding a sapphire that glimmers with captured starlight. It is said to enhance the wearer's natural grace and charm.",
    "Fayr Moda",
    280.00,
    "ACCESSORY",
    "https://cdna.artstation.com/p/assets/images/images/083/614/092/large/jan-pikul-pierscienrender1.jpg?1736371985",
    "https://cdna.artstation.com/p/assets/images/images/083/614/092/large/jan-pikul-pierscienrender1.jpg?1736371985"
);
moonpetalRing.setDexBonus(1);
moonpetalRing.setChaBonus(2);
moonpetalRing.setSpecialProperties("Grants Advantage on Charisma (Persuasion) checks");
clothingItemRepository.save(moonpetalRing);

ClothingItem sunwingAmulet = new ClothingItem(
    "Amulet of the Sunwing",
    "An exquisitely crafted amulet of a Sunwing butterfly, with wings made from polished amber and mother-of-pearl set in a delicate golden frame. The amulet seems to emanate a gentle, soothing warmth.",
    "Fayr Moda",
    420.00,
    "NECK",
    "https://cdnb.artstation.com/p/assets/images/images/083/963/099/large/jan-pikul-motylek25.jpg?1737211013",
    "https://cdnb.artstation.com/p/assets/images/images/083/963/099/large/jan-pikul-motylek25.jpg?1737211013"
);
sunwingAmulet.setChaBonus(2);
sunwingAmulet.setWisBonus(1);
sunwingAmulet.setSpecialProperties("Grants Advantage on Charisma (Performance) checks");
clothingItemRepository.save(sunwingAmulet);

ClothingItem pearlBlossomChoker = new ClothingItem(
    "Choker of the Pearl Blossom",
    "A masterwork choker featuring a night-blooming flower of blackened silver with a flawless pearl at its heart. The golden leaves and pearl chain mark it as a piece of royal significance, warding the mind against lesser magics.",
    "Fayr Moda",
    580.00,
    "NECK",
    "https://cdnb.artstation.com/p/assets/images/images/084/082/687/large/jan-pikul-branzoletkafinal1.jpg?1737495243",
    "https://cdnb.artstation.com/p/assets/images/images/084/082/687/large/jan-pikul-branzoletkafinal1.jpg?1737495243"
);
pearlBlossomChoker.setChaBonus(2);
pearlBlossomChoker.setWisBonus(1);
pearlBlossomChoker.setAcBonus(1);
pearlBlossomChoker.setSpecialProperties("The wearer cannot be magically charmed");
clothingItemRepository.save(pearlBlossomChoker);

ClothingItem pathfinderTunic = new ClothingItem(
    "Pathfinder's Tunic",
    "A ranger's garb made for the wilds. The quilted leather bolero protects the vitals, while the layered belts provide ample space for tools and trophies. Designed for long treks and silent movement.",
    "Fayr Moda",
    175.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/082/310/822/large/zahra-3d-dx.jpg?1732642466",
    "https://cdna.artstation.com/p/assets/images/images/082/310/822/large/zahra-3d-dx.jpg?1732642466"
);
pathfinderTunic.setDexBonus(2);
pathfinderTunic.setConBonus(1);
pathfinderTunic.setWisBonus(1);
pathfinderTunic.setAcBonus(1);
pathfinderTunic.setSpecialProperties("Grants Advantage on Wisdom (Survival) checks to navigate the wilderness");
clothingItemRepository.save(pathfinderTunic);




        // --- ONYX (Drow-Spun) ---
ClothingItem balorEyeShield = new ClothingItem(
    "Balor's Eye Wardstone",
    "A slab of obsidian, still warm from the abyss, etched with glowing infernal runes. The fiery eye at its center is not a gem but a true portal to a realm of flame, incinerating lesser spells that target the wielder.",
    "ONYX",
    925.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/032/211/387/large/javx-rajabzade-final-preview.jpg?1605782289",
    "https://cdnb.artstation.com/p/assets/images/images/032/211/387/large/javx-rajabzade-final-preview.jpg?1605782289"
);
balorEyeShield.setIntBonus(2);
balorEyeShield.setConBonus(2);
balorEyeShield.setAcBonus(3);
balorEyeShield.setSpecialProperties("Grants resistance to fire damage and allows the wearer to cast 'Counterspell' once per day");
clothingItemRepository.save(balorEyeShield);

ClothingItem scryersEgg = new ClothingItem(
    "Scryer's Draconic Egg",
    "A fossilized dragon's egg, cracked just enough for the ever-watchful eye within to peer out. The eye swivels to follow movement, and holding the egg fills the mind with fleeting images of distant places.",
    "ONYX",
    720.00,
    "ACCESSORY",
    "https://cdna.artstation.com/p/assets/images/images/073/425/644/large/thekoswog-1-11.jpg?1709636077",
    "https://cdna.artstation.com/p/assets/images/images/073/425/644/large/thekoswog-1-11.jpg?1709636077"
);
scryersEgg.setIntBonus(2);
scryersEgg.setWisBonus(2);
scryersEgg.setSpecialProperties("Grants the ability to cast 'Clairvoyance' once per day");
clothingItemRepository.save(scryersEgg);

ClothingItem whisperingSkull = new ClothingItem(
    "Skull of the Silent Oracle",
    "The flawlessly preserved skull of a long-dead seer. It is unnervingly heavy and cold to the touch. Those who hold it claim to hear faint, cryptic whispers, offering fragmented glimpses of the future.",
    "ONYX",
    680.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/074/894/017/large/thekoswog-af68b8c9-28af-4375-be48-9276431bbc6f.jpg?1713240980",
    "https://cdnb.artstation.com/p/assets/images/images/074/894/017/large/thekoswog-af68b8c9-28af-4375-be48-9276431bbc6f.jpg?1713240980"
);
whisperingSkull.setIntBonus(2);
whisperingSkull.setWisBonus(2);
whisperingSkull.setSpecialProperties("Grants the ability to cast 'Augury' once per day");
clothingItemRepository.save(whisperingSkull);

ClothingItem thornplateArmor = new ClothingItem(
    "Armor of the Blackened Thorn",
    "A suit of masterwork plate armor covered in razor-sharp, thorn-like protrusions. The underlying chainmail is woven from darksteel, offering incredible resilience. The armor is designed not just to protect, but to punish any foe foolish enough to get close.",
    "ONYX",
    820.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/078/568/664/large/thekoswog-1-34.jpg?1722459571",
    "https://cdna.artstation.com/p/assets/images/images/078/568/664/large/thekoswog-1-34.jpg?1722459571"
);
thornplateArmor.setStrBonus(2);
thornplateArmor.setAcBonus(3);
thornplateArmor.setDexBonus(-1);
thornplateArmor.setSpecialProperties("When you are hit with a melee attack, the attacker takes 1d4 piercing damage");
clothingItemRepository.save(thornplateArmor);

ClothingItem shadowformPhilter = new ClothingItem(
    "Philter of Shadowform",
    "A swirling, violet liquid that glimmers with an internal nebula. The flask is held by a braided clasp shaped like a venomous serpent. Drinking this potion temporarily melts the user's form into pure shadow.",
    "ONYX",
    375.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/088/885/227/large/thekoswog-potin-4.jpg?1749472368",
    "https://cdnb.artstation.com/p/assets/images/images/088/885/227/large/thekoswog-potin-4.jpg?1749472368"
);
shadowformPhilter.setDexBonus(2);
shadowformPhilter.setIntBonus(1);
shadowformPhilter.setSpecialProperties("Upon drinking, become incorporeal and invisible for 1 minute or until you attack.");
clothingItemRepository.save(shadowformPhilter);

ClothingItem celestialEmperorsRobes = new ClothingItem(
    "Robes of the Celestial Emperor",
    "Masterwork silk robes, embossed with the celestial forms of a dragon and phoenix in vibrant, shimmering thread. The fabric feels impossibly light and is said to channel arcane energy with unparalleled efficiency.",
    "ONYX",
    950.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/079/990/046/large/shiva-06.jpg?1726358039",
    "https://cdna.artstation.com/p/assets/images/images/079/990/046/large/shiva-06.jpg?1726358039"
);
celestialEmperorsRobes.setIntBonus(3);
celestialEmperorsRobes.setChaBonus(2);
celestialEmperorsRobes.setAcBonus(1);
celestialEmperorsRobes.setSpecialProperties("Spells cast by the wearer that deal elemental damage ignore resistances");
clothingItemRepository.save(celestialEmperorsRobes);

ClothingItem shadowWalkersGarb = new ClothingItem(
    "Shadow-Walker's Garb",
    "A multi-layered tunic of dark, silent wool and hardened leather. The wide, embossed belt provides support and a place for tools, while the hood offers concealment in the shadows. Standard issue for infiltrators.",
    "ONYX",
    195.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/075/688/751/large/zahra-3d-w22.jpg?1715183269",
    "https://cdna.artstation.com/p/assets/images/images/075/688/751/large/zahra-3d-w22.jpg?1715183269"
);
shadowWalkersGarb.setDexBonus(2);
shadowWalkersGarb.setConBonus(1);
shadowWalkersGarb.setAcBonus(1);
shadowWalkersGarb.setSpecialProperties("Grants Advantage on Dexterity (Stealth) checks in dim light or darkness");
clothingItemRepository.save(shadowWalkersGarb);

ClothingItem inquisitorHelm = new ClothingItem(
    "Helm of the Void-Gazer",
    "A helm forged from a strange, lightweight metal that absorbs light. The intricate, web-like patterns are said to focus the wearer's mind, granting insights into the dark tapestry of fate.",
    "ONYX",
    750.00,
    "HEAD",
    "https://cdnb.artstation.com/p/assets/images/images/018/477/853/large/ringtail-studios-akunakova-render-01-dark.jpg?1559551129",
    "https://cdnb.artstation.com/p/assets/images/images/018/477/853/large/ringtail-studios-akunakova-render-01-dark.jpg?1559551129"
);
inquisitorHelm.setIntBonus(2);
inquisitorHelm.setWisBonus(1);
inquisitorHelm.setAcBonus(2);
inquisitorHelm.setSpecialProperties("Grants Advantage on Wisdom (Insight) checks to discern lies");
clothingItemRepository.save(inquisitorHelm);

ClothingItem salamanderCharm = new ClothingItem(
    "Skittering Salamander Charm",
    "A bronze amulet shaped like an elusive cavern salamander, with a vibrant turquoise inlay down its spine. The charm feels warm to the touch and seems to subtly guide the wearer's feet away from loose stones and creaking floorboards.",
    "ONYX",
    195.00,
    "NECK",
    "https://cdna.artstation.com/p/assets/images/images/084/165/734/large/jan-pikul-lizard6.jpg?1737688832",
    "https://cdna.artstation.com/p/assets/images/images/084/165/734/large/jan-pikul-lizard6.jpg?1737688832"
);
salamanderCharm.setDexBonus(2);
salamanderCharm.setSpecialProperties("Grants Advantage on Dexterity (Acrobatics) checks to maintain balance");
clothingItemRepository.save(salamanderCharm);

ClothingItem nightbladeVestments = new ClothingItem(
    "Nightblade's Vestments",
    "A quilted gambeson and tunic designed for silent movement. The wide sleeves provide freedom of motion for acrobatics, and the high collar offers concealment. Favored by spies who prefer agility over heavy armor.",
    "ONYX",
    215.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/085/834/588/large/zahra-3d-1.jpg?1741757081",
    "https://cdna.artstation.com/p/assets/images/images/085/834/588/large/zahra-3d-1.jpg?1741757081"
);
nightbladeVestments.setDexBonus(2);
nightbladeVestments.setConBonus(1);
nightbladeVestments.setAcBonus(1);
nightbladeVestments.setSpecialProperties("Reduces noise from movement, granting Advantage on Dexterity (Stealth) checks");
clothingItemRepository.save(nightbladeVestments);




        // --- Signets (Gnome-Forged) ---
ClothingItem puzzleSphere = new ClothingItem(
    "Puzzle Sphere of the Dawn",
    "A masterwork of gnomish artifice, this sphere contains the light of a captured sun. The thousands of interlocking golden chains form a complex puzzle that, when manipulated by a keen mind, can channel the sphere's immense power.",
    "Signets",
    1800.00,
    "ACCESSORY",
    "https://cdna.artstation.com/p/assets/images/images/028/183/874/large/javx-rajabzade-final-light-ball.jpg?1593709708",
    "https://cdna.artstation.com/p/assets/images/images/028/183/874/large/javx-rajabzade-final-light-ball.jpg?1593709708"
);
puzzleSphere.setIntBonus(3);
puzzleSphere.setWisBonus(2);
puzzleSphere.setAcBonus(1);
puzzleSphere.setSpecialProperties("Emits bright light and reveals all invisible creatures within a 30-foot radius");
clothingItemRepository.save(puzzleSphere);

ClothingItem arcanePowerCore = new ClothingItem(
    "Arcane Power Core",
    "A masterfully crafted containment flask holding a stabilized arcane anomaly. The intricate filigree is a complex array of conduits and heat sinks, allowing a skilled user to draw upon its immense energy to empower their own spells.",
    "Signets",
    1150.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/028/807/893/large/javx-rajabzade-ff.jpg?1595567567",
    "https://cdnb.artstation.com/p/assets/images/images/028/807/893/large/javx-rajabzade-ff.jpg?1595567567"
);
arcanePowerCore.setIntBonus(3);
arcanePowerCore.setConBonus(1);
arcanePowerCore.setAcBonus(1);
arcanePowerCore.setSpecialProperties("Increases the wearer's spell save DC by 2");
clothingItemRepository.save(arcanePowerCore);

ClothingItem mindCrystalSkull = new ClothingItem(
    "Mind-Crystal Calvaria",
    "The skull of an unknown creature, its cranial cavity completely replaced by a geode of raw, humming amethyst. The crystals resonate with psionic energy, amplifying the wearer's mental fortitude and arcane power.",
    "Signets",
    900.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/029/774/291/large/javx-rajabzade-final-colorful.jpg?1598600615",
    "https://cdnb.artstation.com/p/assets/images/images/029/774/291/large/javx-rajabzade-final-colorful.jpg?1598600615"
);
mindCrystalSkull.setIntBonus(3);
mindCrystalSkull.setWisBonus(1);
mindCrystalSkull.setSpecialProperties("Grants Advantage on all Intelligence saving throws");
clothingItemRepository.save(mindCrystalSkull);

ClothingItem titansbloodFlask = new ClothingItem(
    "Flask of Titan's Blood",
    "A masterful creation of gnomish alchemy, this flask contains a potent elixir that temporarily imbues the user with the strength and fortitude of a giant. The intricate filigree is not just decorative; it helps regulate the potion's volatile energies.",
    "Signets",
    550.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/082/148/331/large/javx-rajabzade-4.jpg?1732187549",
    "https://cdnb.artstation.com/p/assets/images/images/082/148/331/large/javx-rajabzade-4.jpg?1732187549"
);
titansbloodFlask.setStrBonus(3);
titansbloodFlask.setConBonus(1);
titansbloodFlask.setSpecialProperties("Upon drinking, the user's size category increases by one for 10 minutes, and their weapon attacks deal an extra 1d4 damage.");
clothingItemRepository.save(titansbloodFlask);

ClothingItem skyCaptainsCap = new ClothingItem(
    "Sky-Captain's Leather Coif",
    "A fleece-lined leather coif designed for the rigors of open-air piloting. It offers excellent protection from wind and weather, and the ear flaps reduce the roar of arcane engines to a manageable hum.",
    "Signets",
    140.00,
    "HEAD",
    "https://cdna.artstation.com/p/assets/images/images/065/267/062/large/shiva-00.jpg?1689931295",
    "https://cdna.artstation.com/p/assets/images/images/065/267/062/large/shiva-00.jpg?1689931295"
);
skyCaptainsCap.setConBonus(1);
skyCaptainsCap.setWisBonus(1);
skyCaptainsCap.setAcBonus(1);
skyCaptainsCap.setSpecialProperties("Grants Advantage on saves against being deafened");
clothingItemRepository.save(skyCaptainsCap);

ClothingItem catsEyeElixir = new ClothingItem(
    "Elixir of the Cat's Eye",
    "A glowing, turquoise liquid in a round flask, sealed with a gnomish maker's mark. Drinking it grants unnaturally sharp senses, allowing sight even in near-total darkness.",
    "Signets",
    150.00,
    "ACCESSORY",
    "https://cdnb.artstation.com/p/assets/images/images/088/885/231/large/thekoswog-potin-5.jpg?1749472371",
    "https://cdnb.artstation.com/p/assets/images/images/088/885/231/large/thekoswog-potin-5.jpg?1749472371"
);
catsEyeElixir.setWisBonus(1);
catsEyeElixir.setDexBonus(1);
catsEyeElixir.setSpecialProperties("Upon drinking, grants Darkvision up to 120 feet for 1 hour.");
clothingItemRepository.save(catsEyeElixir);

ClothingItem loremasterRobes = new ClothingItem(
    "Loremaster's Finery",
    "The formal robes of a master historian or arcanist. Woven from fine, durable wool with intricate brocade panels, this outfit is designed to command intellectual respect. The belt buckle is a complex astrolabe.",
    "Signets",
    450.00,
    "CHEST",
    "https://cdna.artstation.com/p/assets/images/images/076/344/019/large/shiva-006.jpg?1716796752",
    "https://cdna.artstation.com/p/assets/images/images/076/344/019/large/shiva-006.jpg?1716796752"
);
loremasterRobes.setIntBonus(2);
loremasterRobes.setWisBonus(1);
loremasterRobes.setAcBonus(1);
loremasterRobes.setSpecialProperties("The wearer can cast 'Identify' once per day");
clothingItemRepository.save(loremasterRobes);

ClothingItem scrivenerCoat = new ClothingItem(
    "Field Scrivener's Greatcoat",
    "A heavy, all-weather greatcoat designed for the wandering scholar or tinkerer. Its durable canvas is reinforced with scavenged plates, and its many pouches are perfect for holding tools, reagents, and research notes.",
    "Signets",
    325.00,
    "CHEST",
    "https://cdnb.artstation.com/p/assets/images/images/059/465/953/large/thiago-brandao-1.jpg?1676454661",
    "https://cdnb.artstation.com/p/assets/images/images/059/465/953/large/thiago-brandao-1.jpg?1676454661"
);
scrivenerCoat.setIntBonus(2);
scrivenerCoat.setConBonus(1);
scrivenerCoat.setAcBonus(1);
scrivenerCoat.setSpecialProperties("Grants resistance to acid and elemental damage");
clothingItemRepository.save(scrivenerCoat);
    }
}