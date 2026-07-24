import React from "react";

/**
 * CONCEPT 1: Functional Component + Props + Default Props + children
 *
 * A component is a JS function that returns JSX.
 * PROPS are inputs from the parent. They are READ-ONLY.
 *
 * Three things shown here:
 *   - destructured props  ({ name, role })
 *   - default value via default parameter (modern replacement for defaultProps)
 *   - the special "children" prop (whatever is nested inside the tag)
 */
function Greeting({ name = "Guest", role = "Learner", children }) {
  return (
    <div style={{ padding: "8px 0" }}>
      <strong>Hello, {name}!</strong> <em>({role})</em>
      {/* children = content placed BETWEEN <Greeting> ... </Greeting> */}
      {children && <div style={{ fontSize: 14, color: "#555" }}>{children}</div>}
    </div>
  );
}

export default Greeting;
