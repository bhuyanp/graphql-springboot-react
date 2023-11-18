import { useEffect, useState } from "react";
import { Outlet, Link } from "react-router-dom";

const Layout = () => {
  const [darkTheme, setDarkTheme] = useState<boolean>(true);

  useEffect(() => {
    document
      .querySelector("html")
      ?.setAttribute("data-theme", darkTheme ? "dark" : "light");
  }, [darkTheme]);

  return (
    <>
      <header className="container">
        <nav>
          <ul>
            <li>
              <h2
                className="brandName"
                onClick={() => (window.location.href = "/#/")}
              >
                ABC Book Store
              </h2>
            </li>
          </ul>

          <ul>
            <li>
              <fieldset>
                <label htmlFor="switch">
                  <input
                    onChange={() => {
                      setDarkTheme(!darkTheme);
                    }}
                    type="checkbox"
                    id="switch"
                    checked={darkTheme}
                    name="switch"
                    role="switch"
                  />
                  Dark Theme
                </label>
              </fieldset>
            </li>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/authors">Authors</Link>
            </li>
            <li>
              <Link to="/publishers">Publishers</Link>
            </li>
          </ul>
        </nav>
      </header>

      <main className="container">
        <Outlet />
      </main>
    </>
  );
};

export default Layout;
