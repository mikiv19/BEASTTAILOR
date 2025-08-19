# BeastTailor - Fantasy E-Commerce & Wardrobe Manager
## *"Bespoke Gear for the Bold."*

---

## 📖 About The Project

BeastTailor is a full-stack, fantasy-themed e-commerce application where users can browse, purchase, and manage a wardrobe of magical clothing and armor. Built with a modern tech stack, this project showcases a complete user journey from registration to a unique, interactive checkout process.

The core concept is a **"D&D-style"** shop where the gear you've equipped grants you stat bonuses. These stats, in turn, influence a **"Haggle"** mechanic at checkout, allowing users with high Charisma to get better prices on their purchases. It's a gamified e-commerce experience built from the ground up.


## ⚔️ The World of BeastTailor: Brand Parodies

The shop's identity is built on clever parodies of BESTSELLER's brands, each with a defined racial identity that drives the stat system.

| D&D Parody Brand | Parody Of              | **Racial Identity & Stat Focus**                                                                            |
| :--------------- | :--------------------- | :---------------------------------------------------------------------------------------------------------- |
| **Shank & Bones**  | **Jack & Jones**       | **Goblin-Craft:** Aggressive and resourceful. Focuses on `STR`/`CON` and brutal properties.                      |
| **Fayr Moda**      | **Vero Moda**          | **Elven-Woven:** Ethereal and graceful. Focuses on `DEX`/`CHA` and magical properties.                           |
| **ONYX**           | **ONLY**               | **Drow-Spun:** Edgy and individualistic. Focuses on `Stealth`/`Deception` and shadow-themed properties.          |
| **Sected**         | **Selected Homme/Femme** | **Noble-Born:** Disciplined and honorable. Focuses on `WIS`/`AC` and commanding properties.                     |
| **Signets**        | **Pieces**             | **Gnome-Forged:** Intricate and clever. Provides focused, single-skill bonuses (`+1 Perception`, etc.).           |


## ✨ Key Features

*   **Full User Authentication:** Secure registration and login flow using Spring Security with session management.
*   **Dynamic Product Catalog:** Browse a catalog of over 100 unique items, with the ability to filter by brand.
*   **Interactive Shopping Cart:** A persistent, database-backed shopping cart for each user.
*   **Personal Wardrobe System:** Purchased items are permanently added to a user's personal wardrobe on their profile page.
*   **Equip & Unequip System:** A full-stack feature allowing users to equip and unequip items from their wardrobe.
*   **Live Stat Sheet:** The user's profile page features a panel that dynamically calculates and displays total stat bonuses from their equipped gear in real-time.
*   **Unique "Haggle" Mechanic:** A gamified checkout process where a user's equipped stats (Charisma) influence a D20 dice roll to determine final item prices.
*   **Custom-Designed UI:** A unique, futuristic "glassmorphism" navbar with custom SVG shapes and a global dark theme.

---

## 🛠️ Tech Stack & Architecture

This project is a full-stack application built with a modern, decoupled architecture. The frontend is a single-page application (SPA) that communicates with a backend REST API via HTTP requests.

### Frontend

| Technology | Description |
| :--- | :--- |
| ![React Badge](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black) | The core of the UI, built using a **component-based architecture** with extensive use of Hooks (`useState`, `useEffect`, `useContext`, `useMemo`) for state and logic management. |
| ![TypeScript Badge](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white) | Provides **static typing** to the entire frontend codebase, ensuring type safety, catching errors during development, and improving code maintainability. |
| ![Vite Badge](https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white) | Serves as the high-performance build tool and development server, offering **Hot Module Replacement (HMR)** for a rapid development experience and optimized builds for production. |
| ![Material-UI Badge](https://img.shields.io/badge/Material--UI-007FFF?style=for-the-badge&logo=mui&logoColor=white) | The primary UI component library. Used for the responsive **Grid system**, all UI elements, and a **deeply customized global theme** (`theme.ts`) that includes custom fonts, a dark mode palette, and unique "glassmorphism" styles. |
| ![React Router Badge](https://img.shields.io/badge/React_Router-CA4245?style=for-the-badge&logo=reactrouter&logoColor=white) | Manages all **client-side routing and navigation**. `useNavigate` is used for programmatic navigation, and `useSearchParams` is used for the dynamic brand filtering feature. |
| ![Axios Badge](https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white) | The HTTP client used for all communication with the backend REST API. A **centralized `apiClient` instance** is configured with a base URL and credentials for clean, reusable code. |

### Backend

| Technology | Description |
| :--- | :--- |
| ![Java Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) | The core language for the backend API. Project is built on **JDK 21**. |
| ![Spring Boot Badge](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) | The core framework for the **REST API**, written in **Java**. It provides dependency injection, auto-configuration, and a robust web server to handle all backend logic. |
| ![Spring Security Badge](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white) | Handles all **authentication and authorization**. Manages user registration (with BCrypt password hashing), secure session-based login, and endpoint protection. |
| ![Hibernate Badge](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white) | The JPA (Java Persistence API) implementation used for **Object-Relational Mapping (ORM)**. It maps Java entity classes to database tables, allowing for database interactions without writing raw SQL. |

### Database

| Technology | Description |
| :--- | :--- |
| ![PostgreSQL Badge](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white) | The **relational database** used to store all application data, including user accounts, the clothing item catalog, user wardrobes, and the relationships between them. |

---