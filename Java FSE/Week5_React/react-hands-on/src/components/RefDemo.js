import React, { useState, useRef, useEffect } from "react";

/**
 * CONCEPT 7: useRef - two distinct uses
 *
 * USE A: Access a DOM element directly (focus, scroll, measure, play video)
 * USE B: Store a mutable value that PERSISTS across renders WITHOUT
 *        triggering a re-render when it changes.
 *
 * KEY DIFFERENCE FROM STATE:
 *   changing state  -> component RE-RENDERS
 *   changing a ref  -> NO re-render (it is a plain mutable box: ref.current)
 */
function RefDemo() {
  const inputRef = useRef(null);   // USE A: points at the DOM node
  const renderCount = useRef(0);   // USE B: survives renders, no re-render
  const [text, setText] = useState("");

  // Every render increments the ref - but does NOT cause a render itself
  useEffect(() => {
    renderCount.current += 1;
  });

  const focusInput = () => {
    inputRef.current.focus();               // direct DOM access
    inputRef.current.style.border = "2px solid #2e86c1";
  };

  return (
    <div>
      <input
        ref={inputRef}
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="Click the button to focus me"
      />
      <button onClick={focusInput}>Focus the input</button>
      <p style={{ fontSize: 13, color: "#666" }}>
        This component has rendered <strong>{renderCount.current}</strong> times.
        That counter lives in a ref, so updating it never causes a re-render.
      </p>
    </div>
  );
}

export default RefDemo;
