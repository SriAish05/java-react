import React from "react";
import RegistrationForm from "../components/RegistrationForm";
import LiftingStateDemo from "../components/LiftingStateDemo";
import ClassCounter from "../components/ClassCounter";
import ErrorBoundary, { BuggyComponent } from "../components/ErrorBoundary";

/**
 * PAGE 3: Forms, state patterns, classes and error handling.
 */
function AdvancedPage() {
  return (
    <div>
      <h2>Forms &amp; Advanced Patterns</h2>

      <div className="card">
        <h3>Controlled Forms &amp; Validation <span className="tag">forms</span></h3>
        <RegistrationForm />
      </div>

      <div className="card">
        <h3>Lifting State Up <span className="tag">state pattern</span></h3>
        <LiftingStateDemo />
      </div>

      <div className="card">
        <h3>Class Component &amp; Lifecycle <span className="tag">legacy</span></h3>
        <ClassCounter title="Legacy Class Counter" />
      </div>

      <div className="card">
        <h3>Error Boundary <span className="tag">error handling</span></h3>
        <ErrorBoundary>
          <BuggyComponent />
        </ErrorBoundary>
      </div>
    </div>
  );
}

export default AdvancedPage;
