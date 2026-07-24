import React from "react";
import Greeting from "../components/Greeting";
import Counter from "../components/Counter";
import ConditionalDemo from "../components/ConditionalDemo";
import UserList from "../components/UserList";

/**
 * PAGE 1: The four core React concepts.
 */
function BasicsPage() {
  return (
    <div>
      <h2>React Basics</h2>

      <div className="card">
        <h3>1. Components &amp; Props <span className="tag">props</span></h3>
        <Greeting name="Aish" role="Java FSE Trainee" />
        <Greeting name="Cognizant" />
        <Greeting />
        <Greeting name="With children">
          This text was passed as the special <code>children</code> prop.
        </Greeting>
      </div>

      <div className="card">
        <h3>2. State &amp; Events <span className="tag">useState</span></h3>
        <Counter />
      </div>

      <div className="card">
        <h3>3. Conditional Rendering <span className="tag">ternary / &amp;&amp;</span></h3>
        <ConditionalDemo />
      </div>

      <div className="card">
        <h3>4. Lists &amp; Keys <span className="tag">map / key</span></h3>
        <UserList />
      </div>
    </div>
  );
}

export default BasicsPage;
