import React, { Component } from "react";

/**
 * CONCEPT 12: Error Boundaries
 *
 * A crash in one component would normally unmount the WHOLE React tree
 * (blank white screen). An Error Boundary catches errors from its children
 * and shows a fallback UI instead.
 *
 * IMPORTANT: Error boundaries MUST be class components - there is no hook
 * equivalent. They catch errors during rendering, in lifecycle methods, and
 * in constructors of the tree below them.
 *
 * They do NOT catch: event handler errors, async code, or server-side errors.
 */
class ErrorBoundary extends Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false, message: "" };
  }

  // Called during render phase - update state to show fallback UI
  static getDerivedStateFromError(error) {
    return { hasError: true, message: error.message };
  }

  // Called after an error - good place to log to a monitoring service
  componentDidCatch(error, errorInfo) {
    console.error("[ErrorBoundary] caught:", error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      return (
        <div className="card" style={{ borderColor: "#c0392b" }}>
          <p className="error">Something went wrong in this section.</p>
          <p style={{ fontSize: 13 }}>{this.state.message}</p>
          <button onClick={() => this.setState({ hasError: false, message: "" })}>
            Try again
          </button>
        </div>
      );
    }
    return this.props.children;
  }
}

/** A component that deliberately throws, to demo the boundary. */
export function BuggyComponent() {
  const [explode, setExplode] = React.useState(false);
  if (explode) throw new Error("I crashed on purpose!");
  return (
    <button className="secondary" onClick={() => setExplode(true)}>
      Trigger a crash
    </button>
  );
}

export default ErrorBoundary;
