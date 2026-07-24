import React, { useState } from "react";

/**
 * CONCEPT 14: Lifting State Up
 *
 * When two SIBLING components need to share data, the state must move UP to
 * their nearest common PARENT. The parent then passes:
 *   - the VALUE down as a prop
 *   - a SETTER function down as a prop (so the child can request a change)
 *
 * This preserves React's one-way data flow: data flows DOWN, events flow UP.
 */

// CHILD 1 - only reports changes upward, owns no state itself
function TemperatureInput({ scale, value, onChange }) {
  return (
    <div>
      <label>Temperature in {scale === "c" ? "Celsius" : "Fahrenheit"}: </label>
      <input value={value} onChange={(e) => onChange(e.target.value)} />
    </div>
  );
}

// CHILD 2 - purely presentational, derives its output from props
function BoilingVerdict({ celsius }) {
  if (Number.isNaN(celsius)) return <p>Enter a number.</p>;
  return celsius >= 100 ? (
    <p className="error">The water would boil.</p>
  ) : (
    <p className="success">The water would not boil.</p>
  );
}

function LiftingStateDemo() {
  // The SHARED state lives here, in the common parent
  const [temperature, setTemperature] = useState("");
  const [scale, setScale] = useState("c");

  const toCelsius = (f) => ((f - 32) * 5) / 9;
  const toFahrenheit = (c) => (c * 9) / 5 + 32;

  const convert = (value, converter) => {
    const num = parseFloat(value);
    if (Number.isNaN(num)) return "";
    return (Math.round(converter(num) * 1000) / 1000).toString();
  };

  const celsius = scale === "f" ? convert(temperature, toCelsius) : temperature;
  const fahrenheit = scale === "c" ? convert(temperature, toFahrenheit) : temperature;

  return (
    <div>
      <TemperatureInput
        scale="c"
        value={celsius}
        onChange={(v) => { setScale("c"); setTemperature(v); }}
      />
      <TemperatureInput
        scale="f"
        value={fahrenheit}
        onChange={(v) => { setScale("f"); setTemperature(v); }}
      />
      <BoilingVerdict celsius={parseFloat(celsius)} />
      <p style={{ fontSize: 13, color: "#666" }}>
        Two sibling inputs stay in sync because the state lives in their shared
        parent, not inside either child.
      </p>
    </div>
  );
}

export default LiftingStateDemo;
