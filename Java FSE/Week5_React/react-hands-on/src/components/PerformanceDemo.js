import React, { useState, useMemo, useCallback, memo } from "react";

/**
 * CONCEPT 10: Performance Optimisation
 *
 *   React.memo   -> skip re-rendering a CHILD if its props did not change
 *   useMemo      -> cache an expensive CALCULATION result
 *   useCallback  -> cache a FUNCTION so its identity stays stable
 *
 * WHY useCallback exists: functions are re-created on every render, so a new
 * function is a NEW prop value, which defeats React.memo. useCallback keeps
 * the same function reference between renders.
 *
 * WARNING: do not sprinkle these everywhere. They add memory + complexity.
 * Use them only when you have measured a real performance problem.
 */

// memo = only re-render when props actually change
const ExpensiveChild = memo(function ExpensiveChild({ onClick, label }) {
  console.log("[RENDER] ExpensiveChild rendered:", label);
  return <button onClick={onClick}>{label}</button>;
});

function slowSum(n) {
  // Simulates an expensive computation
  let total = 0;
  for (let i = 0; i <= n * 100000; i++) total += i;
  return total;
}

function PerformanceDemo() {
  const [count, setCount] = useState(1);
  const [text, setText] = useState("");

  /**
   * useMemo: slowSum only re-runs when `count` changes.
   * Without it, typing in the text box would re-run this heavy loop on
   * EVERY keystroke, because typing triggers a re-render.
   */
  const expensiveValue = useMemo(() => slowSum(count), [count]);

  /**
   * useCallback: keeps the SAME function reference across renders,
   * so memo(ExpensiveChild) can actually skip re-rendering.
   */
  const handleChildClick = useCallback(() => {
    setCount((c) => c + 1);
  }, []); // no deps -> function never changes

  return (
    <div>
      <p>
        Count: <strong>{count}</strong>
      </p>
      <p style={{ fontSize: 13 }}>
        Expensive computed value: <code>{expensiveValue}</code>
      </p>

      <input
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="Type here - stays fast thanks to useMemo"
      />

      <div style={{ marginTop: 8 }}>
        <ExpensiveChild onClick={handleChildClick} label="Increment from memo child" />
      </div>

      <p style={{ fontSize: 13, color: "#666" }}>
        Open the Console and type in the box: ExpensiveChild does NOT re-render,
        because memo + useCallback keep its props identical.
      </p>
    </div>
  );
}

export default PerformanceDemo;
