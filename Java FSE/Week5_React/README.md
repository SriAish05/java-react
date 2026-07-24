# Week 5 — React (Single Page Application Framework)
## DN 5.0 Deep Skilling — Java FSE

One runnable app covering **every** React concept in the syllabus, organised into 4 pages via React Router.

---

## Project Structure

```
react-hands-on/
├── package.json
├── public/index.html
└── src/
    ├── index.js                    ← Entry point (createRoot, Router, Provider)
    ├── App.js                      ← Routes + navigation
    ├── styles.css
    │
    ├── context/
    │   └── ThemeContext.js         ← Context API (createContext/Provider/useContext)
    │
    ├── hooks/
    │   └── useFetch.js             ← Custom hook (reusable data fetching)
    │
    ├── pages/                      ← One page per topic group (React Router)
    │   ├── BasicsPage.js
    │   ├── HooksPage.js
    │   ├── AdvancedPage.js
    │   ├── ApiPage.js
    │   └── NotFound.js             ← 404 catch-all route
    │
    └── components/
        ├── Greeting.js             ← Props, default values, children
        ├── Counter.js              ← useState, events, functional updates
        ├── ConditionalDemo.js      ← All 4 conditional rendering techniques
        ├── UserList.js             ← Lists, keys, immutable array updates
        ├── RegistrationForm.js     ← Controlled forms, all input types, validation
        ├── LifecycleDemo.js        ← useEffect: all 3 dep patterns + cleanup
        ├── RefDemo.js              ← useRef: DOM access + persisting values
        ├── TodoReducer.js          ← useReducer for complex state
        ├── ThemedPanel.js          ← Consuming context
        ├── PerformanceDemo.js      ← React.memo, useMemo, useCallback
        ├── ClassCounter.js         ← Class component + lifecycle methods
        ├── ErrorBoundary.js        ← Error boundaries (class-only feature)
        ├── LiftingStateDemo.js     ← Lifting state up to a common parent
        └── ApiUsers.js             ← fetch vs axios vs custom hook
```

---

## Concept → File Map

| # | Concept | File | Page |
|---|---------|------|------|
| 1 | Components, Props, defaultProps, children | `Greeting.js` | Basics |
| 2 | State (useState), Events, functional updates | `Counter.js` | Basics |
| 3 | Conditional rendering (4 techniques) | `ConditionalDemo.js` | Basics |
| 4 | Lists, keys, immutable updates | `UserList.js` | Basics |
| 5 | useEffect + all dependency patterns + cleanup | `LifecycleDemo.js` | Hooks |
| 6 | useRef (DOM access + persisted value) | `RefDemo.js` | Hooks |
| 7 | useReducer (complex state) | `TodoReducer.js` | Hooks |
| 8 | useContext (solves prop drilling) | `ThemeContext.js`, `ThemedPanel.js` | Hooks |
| 9 | React.memo, useMemo, useCallback | `PerformanceDemo.js` | Hooks |
| 10 | Controlled forms, all input types, validation | `RegistrationForm.js` | Advanced |
| 11 | Lifting state up | `LiftingStateDemo.js` | Advanced |
| 12 | Class components + lifecycle methods | `ClassCounter.js` | Advanced |
| 13 | Error boundaries | `ErrorBoundary.js` | Advanced |
| 14 | Custom hooks | `useFetch.js` | API |
| 15 | API calls: fetch, axios, async/await | `ApiUsers.js` | API |
| 16 | React Router (routes, NavLink, 404) | `App.js`, `pages/` | all |

---

## How to Run

You need **Node.js** installed (check with `node -v`).

```bash
cd react-hands-on
npm install
npm start
```

The app opens at **http://localhost:3000**

> First `npm install` takes 2–4 minutes. Be patient.

### Build for production
```bash
npm run build
```

---

## What to Actually Do While Studying

1. **Open the browser Console (F12)** before clicking around — `LifecycleDemo`, `ClassCounter`, and `PerformanceDemo` log exactly when things mount, update, and re-render.
2. On the **Basics** page, click "+2 (functional update)" and understand *why* it adds 2 while two plain `setCount(count+1)` calls would only add 1.
3. On the **Hooks** page, start the timer then navigate away — the cleanup log proves why `clearInterval` matters.
4. On **Performance**, type in the text box and watch that `ExpensiveChild` does *not* re-render.
5. On **Advanced**, click "Trigger a crash" — the Error Boundary catches it instead of white-screening the app.
6. Install **React Developer Tools** (Chrome extension) → Components tab → click any component to inspect its live props and state.

---

## Common Errors & Fixes

| Error | Cause | Fix |
|-------|-------|-----|
| `npm: command not found` | Node.js not installed | Install from nodejs.org |
| `Objects are not valid as a React child` | Rendering an object directly | Render `obj.property`, not `obj` |
| `Each child should have a unique "key"` | Missing `key` in `.map()` | Add `key={item.id}` |
| `Too many re-renders` | Calling a setter during render | Wrap it in a handler or `useEffect` |
| `Cannot read property of undefined` | Data not loaded yet | Guard with `data && ...` or `data?.x` |
| Effect runs twice in dev | React 18 StrictMode | Normal — only in development |
