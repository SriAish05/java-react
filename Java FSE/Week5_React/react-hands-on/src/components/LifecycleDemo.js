import React, { useState, useEffect } from "react";

/**
 * CONCEPT 6: useEffect - all three dependency patterns + CLEANUP
 *
 *   useEffect(fn)          -> runs after EVERY render
 *   useEffect(fn, [])      -> runs ONCE on mount           (componentDidMount)
 *   useEffect(fn, [x])     -> runs on mount + when x changes (componentDidUpdate)
 *   return () => {...}     -> cleanup on unmount / before re-run (componentWillUnmount)
 */
function LifecycleDemo() {
  const [seconds, setSeconds] = useState(0);
  const [running, setRunning] = useState(false);
  const [renderCount, setRenderCount] = useState(0);

  // PATTERN 1: [] -> runs ONCE when the component mounts
  useEffect(() => {
    console.log("[MOUNT] Component mounted - runs once");
    document.title = "React Hands-On";

    // CLEANUP: runs when the component unmounts
    return () => console.log("[UNMOUNT] Component removed - cleanup");
  }, []);

  // PATTERN 2: [seconds] -> runs whenever `seconds` changes
  useEffect(() => {
    console.log("[UPDATE] seconds changed to:", seconds);
  }, [seconds]);

  // PATTERN 3: a timer that must be CLEANED UP, else it leaks
  useEffect(() => {
    if (!running) return;               // do nothing when paused

    const id = setInterval(() => {
      setSeconds((s) => s + 1);         // functional update - no stale value
    }, 1000);

    // CLEANUP: clear the old interval before starting a new one / on unmount.
    // WITHOUT this, every toggle creates ANOTHER timer -> memory leak.
    return () => clearInterval(id);
  }, [running]);

  return (
    <div>
      <p>
        Timer: <strong style={{ fontSize: 20 }}>{seconds}s</strong>
      </p>
      <button onClick={() => setRunning(!running)}>
        {running ? "Pause" : "Start"}
      </button>
      <button className="secondary" onClick={() => setSeconds(0)}>Reset</button>
      <button className="secondary" onClick={() => setRenderCount(renderCount + 1)}>
        Force re-render ({renderCount})
      </button>
      <p style={{ fontSize: 13, color: "#666" }}>
        Open the browser Console (F12) to watch MOUNT / UPDATE / cleanup logs fire.
      </p>
    </div>
  );
}

export default LifecycleDemo;
