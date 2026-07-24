import React, { useReducer, useState } from "react";

/**
 * CONCEPT 8: useReducer - for COMPLEX state logic
 *
 * useState is great for simple values. useReducer is better when:
 *   - the next state depends on the previous one in complicated ways
 *   - several values change together
 *   - you want all update logic in ONE place (the reducer)
 *
 * Pattern:  const [state, dispatch] = useReducer(reducer, initialState)
 *   dispatch({ type: "ADD", payload: ... })  ->  reducer decides the new state
 *
 * The reducer MUST be a pure function: (state, action) => newState
 * and must return a NEW object/array, never mutate the old one.
 */

const initialState = { todos: [], nextId: 1 };

function todoReducer(state, action) {
  switch (action.type) {
    case "ADD":
      return {
        ...state,
        todos: [
          ...state.todos,
          { id: state.nextId, text: action.payload, done: false },
        ],
        nextId: state.nextId + 1,
      };

    case "TOGGLE":
      return {
        ...state,
        todos: state.todos.map((t) =>
          t.id === action.payload ? { ...t, done: !t.done } : t
        ),
      };

    case "DELETE":
      return {
        ...state,
        todos: state.todos.filter((t) => t.id !== action.payload),
      };

    case "CLEAR_DONE":
      return { ...state, todos: state.todos.filter((t) => !t.done) };

    default:
      throw new Error("Unknown action: " + action.type);
  }
}

function TodoReducer() {
  const [state, dispatch] = useReducer(todoReducer, initialState);
  const [input, setInput] = useState("");

  const handleAdd = (e) => {
    e.preventDefault();
    if (!input.trim()) return;
    dispatch({ type: "ADD", payload: input });   // describe WHAT happened
    setInput("");
  };

  return (
    <div>
      <form onSubmit={handleAdd}>
        <input
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="What needs doing?"
        />
        <button type="submit">Add</button>
      </form>

      <ul>
        {state.todos.map((todo) => (
          <li key={todo.id}>
            <input
              type="checkbox"
              checked={todo.done}
              onChange={() => dispatch({ type: "TOGGLE", payload: todo.id })}
            />
            <span style={{ textDecoration: todo.done ? "line-through" : "none" }}>
              {todo.text}
            </span>
            <button
              className="secondary"
              onClick={() => dispatch({ type: "DELETE", payload: todo.id })}
            >
              x
            </button>
          </li>
        ))}
      </ul>

      {state.todos.length > 0 && (
        <button className="secondary" onClick={() => dispatch({ type: "CLEAR_DONE" })}>
          Clear completed
        </button>
      )}
    </div>
  );
}

export default TodoReducer;
