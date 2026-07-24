import React from "react";
import { Link } from "react-router-dom";

/** Catch-all route for unmatched URLs. */
function NotFound() {
  return (
    <div className="card">
      <h2>404 - Page Not Found</h2>
      <Link to="/">Go back home</Link>
    </div>
  );
}

export default NotFound;
