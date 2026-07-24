import React from "react";
import { Routes, Route, NavLink } from "react-router-dom";
import BasicsPage from "./pages/BasicsPage";
import HooksPage from "./pages/HooksPage";
import AdvancedPage from "./pages/AdvancedPage";
import ApiPage from "./pages/ApiPage";
import NotFound from "./pages/NotFound";
import { useTheme } from "./context/ThemeContext";

/**
 * CONCEPT 15: React Router (client-side routing)
 *
 * In a Single Page Application the browser NEVER reloads. React Router
 * swaps which component is rendered based on the URL.
 *
 *   <Routes>   -> container that picks ONE matching route
 *   <Route>    -> maps a path to an element
 *   <NavLink>  -> like <a> but does NOT reload the page; adds an "active" class
 *   path="*"   -> catch-all for unknown URLs (404)
 *
 * Using a plain <a href> instead of Link would force a FULL page reload and
 * destroy all React state - that is the key difference.
 */
function App() {
  const { theme } = useTheme();

  return (
    <div className={theme === "dark" ? "dark" : ""} style={{ minHeight: "100vh" }}>
      <nav>
        <NavLink to="/" end>Basics</NavLink>
        <NavLink to="/hooks">Hooks</NavLink>
        <NavLink to="/advanced">Forms &amp; Advanced</NavLink>
        <NavLink to="/api">API Calls</NavLink>
      </nav>

      <div className="container">
        <h1>React Hands-On — DN 5.0 Deep Skilling</h1>
        <p style={{ color: "#666" }}>
          Every mandatory React concept, one runnable app. Open the browser
          Console (F12) to see lifecycle and render logs.
        </p>

        <Routes>
          <Route path="/" element={<BasicsPage />} />
          <Route path="/hooks" element={<HooksPage />} />
          <Route path="/advanced" element={<AdvancedPage />} />
          <Route path="/api" element={<ApiPage />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
