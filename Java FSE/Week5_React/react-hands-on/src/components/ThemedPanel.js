import React from "react";
import { useTheme } from "../context/ThemeContext";

/**
 * CONCEPT 9: Consuming Context (useContext)
 *
 * This component is nested several levels deep inside App, yet it reads the
 * theme DIRECTLY from context. No prop was passed down through App -> Page
 * -> Panel. That is exactly the "prop drilling" problem Context solves.
 */
function ThemedPanel() {
  const { theme, toggleTheme } = useTheme();

  return (
    <div className={theme === "dark" ? "dark card" : "card"}>
      <p>
        Current theme: <strong>{theme}</strong>
      </p>
      <button onClick={toggleTheme}>Toggle theme</button>
      <p style={{ fontSize: 13 }}>
        This value came from ThemeContext, not from props. Any component in the
        tree can read it without it being threaded through every parent.
      </p>
    </div>
  );
}

export default ThemedPanel;
