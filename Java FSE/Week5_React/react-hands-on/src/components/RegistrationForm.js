import React, { useState } from "react";

/**
 * CONCEPT 5: Controlled Forms + Validation + All Input Types
 *
 * CONTROLLED INPUT = React state is the single source of truth.
 *   value={state}  +  onChange={e => setState(e.target.value)}
 *
 * Here ONE state object holds ALL fields, and ONE handler updates them
 * using the input's `name` attribute - the standard professional pattern.
 */
function RegistrationForm() {
  const [form, setForm] = useState({
    username: "",
    email: "",
    country: "",
    bio: "",
    agree: false,
  });
  const [errors, setErrors] = useState({});
  const [submitted, setSubmitted] = useState(null);

  // ONE handler for EVERY input, keyed by the `name` attribute
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) => ({
      ...prev,                                   // keep other fields
      [name]: type === "checkbox" ? checked : value,  // computed property name
    }));
  };

  const validate = () => {
    const errs = {};
    if (!form.username.trim()) errs.username = "Username is required";
    else if (form.username.length < 3) errs.username = "Minimum 3 characters";

    if (!form.email.trim()) errs.email = "Email is required";
    else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email))
      errs.email = "Enter a valid email address";

    if (!form.country) errs.country = "Please select a country";
    if (!form.agree) errs.agree = "You must accept the terms";
    return errs;
  };

  const handleSubmit = (e) => {
    e.preventDefault();          // STOP the browser's full page reload
    const errs = validate();
    setErrors(errs);
    if (Object.keys(errs).length === 0) {
      setSubmitted(form);
    } else {
      setSubmitted(null);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      {/* TEXT INPUT */}
      <div>
        <label>Username: </label>
        <input name="username" value={form.username} onChange={handleChange} />
        {errors.username && <span className="error"> {errors.username}</span>}
      </div>

      {/* EMAIL INPUT */}
      <div>
        <label>Email: </label>
        <input name="email" value={form.email} onChange={handleChange} />
        {errors.email && <span className="error"> {errors.email}</span>}
      </div>

      {/* SELECT DROPDOWN - in React, value goes on <select>, not <option> */}
      <div>
        <label>Country: </label>
        <select name="country" value={form.country} onChange={handleChange}>
          <option value="">-- Select --</option>
          <option value="IN">India</option>
          <option value="US">United States</option>
          <option value="UK">United Kingdom</option>
        </select>
        {errors.country && <span className="error"> {errors.country}</span>}
      </div>

      {/* TEXTAREA - in React, value is a prop (not inner text like HTML) */}
      <div>
        <label>Bio: </label>
        <textarea name="bio" value={form.bio} onChange={handleChange} rows="3" />
      </div>

      {/* CHECKBOX - uses `checked`, not `value` */}
      <div>
        <label>
          <input
            type="checkbox"
            name="agree"
            checked={form.agree}
            onChange={handleChange}
          />{" "}
          I accept the terms
        </label>
        {errors.agree && <span className="error"> {errors.agree}</span>}
      </div>

      <button type="submit">Register</button>

      {submitted && (
        <pre className="success" style={{ marginTop: 10 }}>
          Submitted: {JSON.stringify(submitted, null, 2)}
        </pre>
      )}
    </form>
  );
}

export default RegistrationForm;
