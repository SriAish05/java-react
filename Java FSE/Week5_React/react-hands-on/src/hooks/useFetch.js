import { useState, useEffect } from "react";

/**
 * CONCEPT 14: Custom Hooks
 *
 * A custom hook is just a FUNCTION whose name starts with "use" and that
 * calls other hooks inside. It lets you EXTRACT and REUSE stateful logic
 * across many components.
 *
 * Here we extract the whole "fetch data / loading / error" pattern so any
 * component can do:   const { data, loading, error } = useFetch(url);
 *
 * NOTE: Custom hooks share LOGIC, not STATE. Each component calling
 * useFetch gets its own independent data/loading/error.
 */
function useFetch(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // AbortController lets us cancel the request if the component
    // unmounts before the response arrives (prevents memory leaks).
    const controller = new AbortController();

    async function fetchData() {
      try {
        setLoading(true);
        const response = await fetch(url, { signal: controller.signal });
        if (!response.ok) throw new Error(`HTTP error ${response.status}`);
        const json = await response.json();
        setData(json);
        setError(null);
      } catch (err) {
        // Ignore the error caused by our own abort
        if (err.name !== "AbortError") setError(err.message);
      } finally {
        setLoading(false);
      }
    }

    fetchData();

    // CLEANUP FUNCTION: runs on unmount or before the effect re-runs
    return () => controller.abort();
  }, [url]); // re-run whenever the url changes

  return { data, loading, error };
}

export default useFetch;
