# BEASTTAILOR
> Bespoke Gear for the Bold.

Welcome to BeastTailor, where D&D meets high fashion! Style heroes with gear from brands like 'Shank & Bones'. A full-stack portfolio project built to showcase a fun concept with a solid tech foundation.


---

## ✨ Key Features

*   **Dynamic E-commerce Storefront:** A full shopping experience where users can browse a catalog, filter by brand or item type, and view detailed product pages.
*   **Persistent User Profiles:** Registered users have a personal profile page that serves as their hub, logging a complete history of all their purchases in a "Wardrobe."

*   **Live Character Stat System:** Equip gear from your Wardrobe onto your character model and watch as a "Live Stat Sheet" aggregates all stat bonuses in real-time.

*   **Interactive 'Affinity Roll' Checkout:** A unique, game-like checkout process where the final price is determined by a secure, server-side roll based on your character's race vs. the item's brand identity.

## ⚔️ The World of BeastTailor: Brand Parodies

The shop's identity is built on clever parodies of BESTSELLER's brands, each with a defined racial identity that drives the stat system and pricing mechanics.

| D&D Parody Brand | Parody Of              | **Racial Identity & Stat Focus**                                                                            |
| :--------------- | :--------------------- | :---------------------------------------------------------------------------------------------------------- |
| **Shank & Bones**  | **Jack & Jones**       | **Goblin-Craft:** Aggressive and resourceful. Focuses on `STR`/`CON` and brutal properties.                      |
| **Fayr Moda**      | **Vero Moda**          | **Elven-Woven:** Ethereal and graceful. Focuses on `DEX`/`CHA` and magical properties.                           |
| **ONYX**           | **ONLY**               | **Drow-Spun:** Edgy and individualistic. Focuses on `Stealth`/`Deception` and shadow-themed properties.          |
| **Sected**         | **Selected Homme/Femme** | **Noble-Born:** Disciplined and honorable. Focuses on `WIS`/`AC` and commanding properties.                     |
| **Signets**        | **Pieces**             | **Gnome-Forged:** Intricate and clever. Provides focused, single-skill bonuses (`+1 Perception`, etc.).           |

## 🛠️ Tech Stack

| Category               | Technology / Tool                    | Role & Purpose in the Project                                                                                                                                                                                                                                                                      |
| :--------------------- | :----------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Frontend**           | `React with TypeScript`              | Builds the dynamic and type-safe user interface for the e-commerce shop.                                                                                                                                                                                                                               |
| **State Management**   | `React Hooks`                        | Manages all application state efficiently. <br> • **`useState`**: For local component state. <br> • **`useEffect`**: For API data fetching. <br> • **`useContext`**: For global state like the shopping cart & user session.                                                                           |
| **Backend**            | `Java (with Spring Boot)`              | Creates a secure RESTful API to handle all business logic, user authentication, and the server-side "Affinity Roll" calculation.                                                                                                                                                                           |
| **Database**           | `PostgreSQL`                         | A powerful relational database to persist all `user`, `item`, and `inventory` data.                                                                                                                                                                                                                      |
| **API Type**           | `CRUD Operations`                    | The API fully implements CRUD (Create, Read, Update, Delete) to allow the frontend to manage all application resources like user profiles and inventories.                                                                                                                                                        |
| **UI / Design System** | `Material-UI (MUI)`                  | A comprehensive component library used to rapidly build a clean, professional, and responsive user interface for the web shop.                                                                                                                                                                          |

