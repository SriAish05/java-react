import React, { createContext, useState, useContext } from "react";

/**
 * CONCEPT 12: Context API (useContext)
 *
 * PROBLEM IT SOLVES: "Prop Drilling" - passing a prop down through 5 levels
 * of components just so the deepest one can use it.
 *
 * Context creates a "global-ish" value any descendant can read directly,
 * without it being passed through every level manually.
 *
 * Three steps:
 *   1. createContext()          -> create the channel
 *   2. <Provider value={...}>   -> broadcast a value
 *   3. useContext(TheContext)   -> any child tunes in
 */

// STEP 1: Create the context (the "radio channel")
const ThemeContext = createContext();

// STEP 2: Provider component wraps the app and broadcasts the value
export function ThemeProvider({ children }) {
  const [theme, setTheme] = useState("light");

  const toggleTheme = () => {
    setTheme((prev) => (prev === "light" ? "dark" : "light"));
  };

  // Everything inside value={} is available to all descendants
  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
}

// STEP 3: Custom hook so components can consume it cleanly
export function useTheme() {
  const context = useContext(ThemeContext);
  if (!context) {
    throw new Error("useTheme must be used inside a ThemeProvider");
  }
  return context;
}

export default ThemeContext;
