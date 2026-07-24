import React, { useState } from "react";

/**
 * CONCEPT 3: Conditional Rendering - all four techniques.
 */
function ConditionalDemo() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [status, setStatus] = useState("loading"); // loading | success | error

  // TECHNIQUE 3: element variable (if/else before return)
  let statusMessage;
  if (status === "loading") statusMessage = <p>Loading your data...</p>;
  else if (status === "success") statusMessage = <p className="success">Data loaded!</p>;
  else statusMessage = <p className="error">Something went wrong.</p>;

  return (
    <div>
      {/* TECHNIQUE 1: Ternary (if / else) */}
      {isLoggedIn ? (
        <p className="success">Welcome back, Aish!</p>
      ) : (
        <p className="error">Please log in to continue.</p>
      )}

      {/* TECHNIQUE 2: Logical && (render ONLY if true) */}
      {isLoggedIn && <p>You have 3 unread notifications.</p>}

      <button onClick={() => setIsLoggedIn(!isLoggedIn)}>
        {isLoggedIn ? "Log Out" : "Log In"}
      </button>

      <hr />
      {statusMessage}
      <button className="secondary" onClick={() => setStatus("loading")}>Loading</button>
      <button className="secondary" onClick={() => setStatus("success")}>Success</button>
      <button className="secondary" onClick={() => setStatus("error")}>Error</button>

      {/* TECHNIQUE 4: return null = render NOTHING */}
      <HiddenWhenEmpty items={[]} />

      <p style={{ fontSize: 13, color: "#666" }}>
        GOTCHA: with &amp;&amp;, a value of 0 RENDERS as "0" because 0 is falsy
        but still a valid React node. Use {"{count > 0 && ...}"} not {"{count && ...}"}.
      </p>
    </div>
  );
}

// TECHNIQUE 4: preventing a component from rendering at all
function HiddenWhenEmpty({ items }) {
  if (items.length === 0) return null; // renders nothing
  return <ul>{items.map((i) => <li key={i}>{i}</li>)}</ul>;
}

export default ConditionalDemo;
