import React, { useState, useEffect } from "react";
import axios from "axios";
import useFetch from "../hooks/useFetch";

/**
 * CONCEPT 13: Calling APIs - three approaches side by side
 *
 *   A) fetch + async/await inside useEffect (built in, manual JSON + errors)
 *   B) axios (library: auto JSON via res.data, better errors, interceptors)
 *   C) a CUSTOM HOOK that hides all of it (the professional way)
 *
 * ALWAYS handle three UI states: loading, error, and success.
 */
const API = "https://jsonplaceholder.typicode.com/users";

function ApiUsers() {
  // ---------- A) fetch with async/await ----------
  const [fetchUsers, setFetchUsers] = useState([]);
  const [fetchLoading, setFetchLoading] = useState(true);
  const [fetchError, setFetchError] = useState(null);

  useEffect(() => {
    // NOTE: the effect callback itself cannot be async, so we define an
    // async function inside and call it.
    async function load() {
      try {
        const res = await fetch(API);
        if (!res.ok) throw new Error("HTTP " + res.status);
        const data = await res.json();     // fetch needs manual .json()
        setFetchUsers(data.slice(0, 3));
      } catch (err) {
        setFetchError(err.message);
      } finally {
        setFetchLoading(false);
      }
    }
    load();
  }, []);

  // ---------- B) axios ----------
  const [axiosUsers, setAxiosUsers] = useState([]);
  const [axiosLoading, setAxiosLoading] = useState(true);

  useEffect(() => {
    axios
      .get(API)
      .then((res) => setAxiosUsers(res.data.slice(3, 6))) // axios auto-parses JSON
      .catch((err) => console.error(err))
      .finally(() => setAxiosLoading(false));
  }, []);

  // ---------- C) custom hook (all the above, reusable in one line) ----------
  const { data: hookUsers, loading: hookLoading, error: hookError } = useFetch(API);

  return (
    <div>
      <h4>A) Using fetch + async/await</h4>
      {fetchLoading && <p>Loading...</p>}
      {fetchError && <p className="error">Error: {fetchError}</p>}
      <ul>
        {fetchUsers.map((u) => (
          <li key={u.id}>{u.name} — {u.email}</li>
        ))}
      </ul>

      <h4>B) Using axios</h4>
      {axiosLoading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {axiosUsers.map((u) => (
            <li key={u.id}>{u.name} — {u.company.name}</li>
          ))}
        </ul>
      )}

      <h4>C) Using the custom useFetch hook</h4>
      {hookLoading && <p>Loading...</p>}
      {hookError && <p className="error">Error: {hookError}</p>}
      <ul>
        {hookUsers &&
          hookUsers.slice(6, 9).map((u) => (
            <li key={u.id}>{u.name} — {u.website}</li>
          ))}
      </ul>
      <p style={{ fontSize: 13, color: "#666" }}>
        Same result three ways. Notice how the custom hook reduces a whole
        block of state + effect code to a single line.
      </p>
    </div>
  );
}

export default ApiUsers;
