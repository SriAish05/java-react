import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import App from "./App";
import { ThemeProvider } from "./context/ThemeContext";
import "./styles.css";

/**
 * ENTRY POINT of the React application.
 *
 * createRoot()  -> React 18 way of attaching React to the DOM
 * BrowserRouter -> enables client-side routing (React Router)
 * ThemeProvider -> makes theme available to EVERY component (Context API)
 * StrictMode    -> dev-only checks; intentionally double-invokes effects
 */
const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <BrowserRouter>
      <ThemeProvider>
        <App />
      </ThemeProvider>
    </BrowserRouter>
  </React.StrictMode>
);
