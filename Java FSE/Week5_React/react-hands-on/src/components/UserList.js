import React, { useState } from "react";

/**
 * CONCEPT 4: Lists & Keys + Immutable array updates
 *
 * .map() turns an array into an array of JSX elements.
 * Each element needs a UNIQUE, STABLE `key` so React can tell items apart
 * across re-renders.
 *
 * NEVER mutate state arrays (no push/splice). Always create a NEW array,
 * because React compares references to decide whether to re-render.
 */
function UserList() {
  const [users, setUsers] = useState([
    { id: 1, name: "Alice", role: "Developer" },
    { id: 2, name: "Bob", role: "Designer" },
    { id: 3, name: "Charlie", role: "Tester" },
  ]);
  const [nextId, setNextId] = useState(4);

  // ADD - spread creates a NEW array (immutable)
  const addUser = () => {
    setUsers([...users, { id: nextId, name: `User ${nextId}`, role: "Intern" }]);
    setNextId(nextId + 1);
  };

  // DELETE - filter returns a NEW array
  const removeUser = (id) => setUsers(users.filter((u) => u.id !== id));

  // UPDATE - map returns a NEW array with one item replaced
  const promote = (id) =>
    setUsers(users.map((u) => (u.id === id ? { ...u, role: "Senior " + u.role } : u)));

  return (
    <div>
      <ul>
        {users.map((user) => (
          // key MUST be a stable unique id, NOT the array index
          <li key={user.id} style={{ marginBottom: 6 }}>
            {user.name} — <em>{user.role}</em>{" "}
            <button className="secondary" onClick={() => promote(user.id)}>
              Promote
            </button>
            <button className="secondary" onClick={() => removeUser(user.id)}>
              Remove
            </button>
          </li>
        ))}
      </ul>
      <button onClick={addUser}>Add User</button>
      <p style={{ fontSize: 13, color: "#666" }}>
        Delete a middle user then add one. Because keys are stable ids (not
        indexes), React updates only what changed.
      </p>
    </div>
  );
}

export default UserList;
