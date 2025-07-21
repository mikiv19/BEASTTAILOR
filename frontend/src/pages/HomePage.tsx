import React from 'react';
import { Link } from 'react-router-dom';

const HomePage: React.FC = () => {
  return (
    <div>
      <h1>Welcome to BeastTailor</h1>
      <nav>
        <Link to="/register">Go to Registration Page</Link>
      </nav>
    </div>
  );
};

export default HomePage;