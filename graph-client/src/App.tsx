import "./App.scss";
import Authors from "./components/Authors";
import BookList from "./components/BookList";
import { Routes, Route, HashRouter } from "react-router-dom";
import Publishers from "./components/Publishers";
import Layout from "./components/Layout";
import BookDetails from "./components/BookDetails";

function App() {
  return (
    <>
      <HashRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<BookList />} />
            <Route path="index.html" element={<BookList />} />
            <Route path="authors" element={<Authors />} />
            <Route path="publishers" element={<Publishers />} />
            <Route path="book/:bid" element={<BookDetails />} />
            <Route path="*" element={<div>Page not found</div>} />
          </Route>
        </Routes>
      </HashRouter>
    </>
  );
}

export default App;
