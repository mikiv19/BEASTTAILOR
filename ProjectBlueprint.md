
### **Project Blueprint: BeastTailor**

#### **1. High-Level Pitch & Concept**

**BeastTailor** is a full-stack, fantasy-themed e-commerce web shop. The name is a direct play on "Bestseller," merging the fantasy world's **"Beast"** with the fashion world's **"Tailor."** The application provides a unified storefront for a universe of fictional fashion brands, each with a unique D&D-style racial identity. Users can purchase gear with unique stats, manage their collection in a personal profile, and equip items onto a character model to see their hero's look and aggregated stats come to life. The project's unique twist is a dynamic pricing "Affinity Roll" at checkout, making every purchase a fun, game-like experience.

#### **2. User Experience & Core Features**

The user journey mimics a modern e-commerce site, but with engaging D&D mechanics.

1.  **Browse the Shop:** The user explores a unified **Shop Catalog** featuring all the fantasy brands. They can filter by brand, item slot, and other criteria.

2.  **Inspect Items:** Clicking an item opens a **Product Detail Page** showing high-quality images, its brand, D&D stats, special properties, and a "base price."

3.  **Shopping Cart:** Users add desired items to a persistent **Shopping Cart**, managed via `useContext`.

4.  **Checkout with Affinity Roll:** The checkout process is the core gameplay loop.
    *   When the user proceeds to checkout, the backend triggers the **"Affinity Roll"** for all items in the cart.
    *   This secure, server-side calculation determines a final, dynamic price based on the user's race, brand affinities, and a random D20 roll.

5.  **Profile Page & Wardrobe:** After a successful "purchase," the items are added to the user's personal **Wardrobe** on their Profile Page. This page logs a complete history of all their purchases.

6.  **Equip & Style:** The Profile Page features a persistent character model. The user can browse their Wardrobe and click items to **equip** them onto their character. As they do, the character's visual appearance and a **Live Stat Sheet** update in real-time.

#### **3. Creative World-Building: The Brand Parodies**

The shop's identity is built on clever parodies of BESTSELLER's brands, each with a defined racial identity that drives the stat system and pricing mechanics.

| D&D Parody Brand | Parody Of              | **Racial Identity & Stat Focus**                                                                            |
| :--------------- | :--------------------- | :---------------------------------------------------------------------------------------------------------- |
| **Shank & Bones**  | **Jack & Jones**       | **Goblin-Craft:** Aggressive and resourceful. Focuses on `STR`/`CON` and brutal properties.                      |
| **Fayr Moda**      | **Vero Moda**          | **Elven-Woven:** Ethereal and graceful. Focuses on `DEX`/`CHA` and magical properties.                           |
| **ONYX**           | **ONLY**               | **Drow-Spun:** Edgy and individualistic. Focuses on `Stealth`/`Deception` and shadow-themed properties.          |
| **Sected**         | **Selected Homme/Femme** | **Noble-Born:** Disciplined and honorable. Focuses on `WIS`/`AC` and commanding properties.                     |
| **Signets**        | **Pieces**             | **Gnome-Forged:** Intricate and clever. Provides focused, single-skill bonuses (`+1 Perception`, etc.).           |





### **4. Technical Architecture**

The **BeastTailor** project is built as a full-stack application, with each technology chosen to fulfill a specific, professional role.

| Category               | Technology / Tool                    | Role & Purpose in the Project                                                                                                                                                                                                                                                                      |
| :--------------------- | :----------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Frontend**           | `React with TypeScript`              | Builds the dynamic and type-safe user interface for the e-commerce shop.                                                                                                                                                                                                                               |
| **State Management**   | `React Hooks`                        | Manages all application state efficiently. <br> • **`useState`**: For local component state. <br> • **`useEffect`**: For API data fetching. <br> • **`useContext`**: For global state like the shopping cart & user session.                                                                           |
| **Backend**            | `Java (with Spring Boot)`              | Creates a secure RESTful API to handle all business logic, user authentication, and the server-side "Affinity Roll" calculation.                                                                                                                                                                           |
| **Database**           | `PostgreSQL`                         | A powerful relational database to persist all `user`, `item`, and `inventory` data.                                                                                                                                                                                                                      |
| **API Type**           | `CRUD Operations`                    | The API fully implements CRUD (Create, Read, Update, Delete) to allow the frontend to manage all application resources like user profiles and inventories.                                                                                                                                                        |
| **UI / Design System** | `Material-UI (MUI)`                  | A comprehensive component library used to rapidly build a clean, professional, and responsive user interface for the web shop.                                                                                                                                                                          |