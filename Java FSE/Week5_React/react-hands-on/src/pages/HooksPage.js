import React from "react";
import LifecycleDemo from "../components/LifecycleDemo";
import RefDemo from "../components/RefDemo";
import TodoReducer from "../components/TodoReducer";
import ThemedPanel from "../components/ThemedPanel";
import PerformanceDemo from "../components/PerformanceDemo";

/**
 * PAGE 2: Every hook you need for the assessment.
 */
function HooksPage() {
  return (
    <div>
      <h2>React Hooks</h2>

      <div className="card">
        <h3>useEffect &amp; Lifecycle <span className="tag">useEffect</span></h3>
        <LifecycleDemo />
      </div>

      <div className="card">
        <h3>useRef <span className="tag">useRef</span></h3>
        <RefDemo />
      </div>

      <div className="card">
        <h3>useReducer <span className="tag">useReducer</span></h3>
        <TodoReducer />
      </div>

      <div className="card">
        <h3>useContext <span className="tag">useContext</span></h3>
        <ThemedPanel />
      </div>

      <div className="card">
        <h3>Performance <span className="tag">memo / useMemo / useCallback</span></h3>
        <PerformanceDemo />
      </div>
    </div>
  );
}

export default HooksPage;
