import React from "react";
import ApiUsers from "../components/ApiUsers";

/**
 * PAGE 4: API integration.
 */
function ApiPage() {
  return (
    <div>
      <h2>Calling APIs</h2>
      <div className="card">
        <h3>fetch vs axios vs custom hook <span className="tag">API</span></h3>
        <ApiUsers />
      </div>
    </div>
  );
}

export default ApiPage;
