export interface ClothingItem {
    id: number;
    name: string;
    description: string;
    brand: string;
    basePrice: number;
    itemSlot: string;
    imageUrlDetail: string;
    imageUrlThumbnail: string;
    strBonus: number;
    dexBonus: number;
    conBonus: number;
    intBonus: number;
    wisBonus: number;
    chaBonus: number;
    acBonus: number;
    specialProperties: string | null;
}