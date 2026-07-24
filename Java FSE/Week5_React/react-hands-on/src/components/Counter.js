import React, { useState } from "react";

/**
 * CONCEPT 2: State (useState) + Events + Functional Updates + Batching
 *
 * useState(initialValue) returns [currentValue, setterFunction].
 * Calling the setter schedules a re-render with the new value.
 */
function Counter() {
  const [count, setCount] = useState(0);
  const [step, setStep] = useState(1);

  // DIRECT UPDATE - fine when you don't depend on the previous value
  const increment = () => setCount(count + step);

  /**
   * FUNCTIONAL UPDATE - REQUIRED when the new value depends on the old one
   * and you update more than once in the same event.
   *
   * WRONG:  setCount(count + 1); setCount(count + 1);  -> only +1 total
   *         (both read the SAME stale `count` from this render)
   * RIGHT:  setCount(c => c + 1); setCount(c => c + 1); -> +2 total
   *         (each receives the latest pending value)
   */
  const incrementTwice = () => {
    setCount((c) => c + 1);
    setCount((c) => c + 1);
  };

  const reset = () => setCount(0);

  return (
    <div>
      <p>
        Count: <strong style={{ fontSize: 20 }}>{count}</strong>
      </p>
      <label>
        Step:{" "}
        <input
          type="number"
          value={step}
          onChange={(e) => setStep(Number(e.target.value))}
          style={{ width: 60 }}
        />
      </label>
      <div style={{ marginTop: 8 }}>
        <button onClick={increment}>+ {step}</button>
        <button onClick={() => setCount(count - step)}>- {step}</button>
        <button onClick={incrementTwice}>+2 (functional update)</button>
        <button className="secondary" onClick={reset}>
          Reset
        </button>
      </div>
      <p style={{ fontSize: 13, color: "#666" }}>
        Try "+2": it proves functional updates read the LATEST value, not the
        stale one from this render.
      </p>
    </div>
  );
}

export default Counter;
