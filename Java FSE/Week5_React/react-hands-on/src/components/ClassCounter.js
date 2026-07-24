import React, { Component } from "react";

/**
 * CONCEPT 11: Class Components + Lifecycle Methods (LEGACY but still examined)
 *
 * Modern React uses functions + hooks, but assessments still ask about
 * classes, so know the mapping:
 *
 *   constructor           -> useState(initialValue)
 *   componentDidMount     -> useEffect(fn, [])
 *   componentDidUpdate    -> useEffect(fn, [deps])
 *   componentWillUnmount  -> the cleanup function returned by useEffect
 *   this.state/setState   -> [value, setValue] = useState()
 *   this.props            -> function parameters (props)
 */
class ClassCounter extends Component {
  constructor(props) {
    super(props);                      // MUST call super(props) first
    this.state = { count: 0 };         // state is ONE object in classes
    this.increment = this.increment.bind(this); // bind `this` (or use arrow fn)
  }

  // Runs once after the component is inserted into the DOM
  componentDidMount() {
    console.log("[CLASS] componentDidMount");
  }

  // Runs after every update; compare prev vs current to avoid loops
  componentDidUpdate(prevProps, prevState) {
    if (prevState.count !== this.state.count) {
      console.log("[CLASS] componentDidUpdate - count is now", this.state.count);
    }
  }

  // Runs just before the component is removed - clean up here
  componentWillUnmount() {
    console.log("[CLASS] componentWillUnmount");
  }

  increment() {
    // setState MERGES into existing state (unlike useState which REPLACES)
    // Use the functional form when depending on previous state
    this.setState((prev) => ({ count: prev.count + 1 }));
  }

  render() {
    const { title = "Class Counter" } = this.props;
    return (
      <div>
        <p>
          {title}: <strong>{this.state.count}</strong>
        </p>
        <button onClick={this.increment}>Increment</button>
        {/* Arrow function auto-binds `this`, so no .bind needed */}
        <button className="secondary" onClick={() => this.setState({ count: 0 })}>
          Reset
        </button>
      </div>
    );
  }
}

export default ClassCounter;
