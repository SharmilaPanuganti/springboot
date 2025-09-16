import CustomNavbar from "./CustomNavbar";

const Base = ({ title = "Welcome to website", children }) => {
  return (
    <div
      className="d-flex flex-column min-vh-100"
      style={{
        // Full app background (gradient or image)
        background: "linear-gradient(135deg, #1f1c2c, #928dab)",
        // or: background: "url('/images/bg-pattern.png') no-repeat center center / cover",
        minHeight: "100vh",
      }}
    >
      {/* Navbar */}
      <CustomNavbar />

      {/* Main Content */}
      <main className="flex-fill">
        <div className="container py-4">{children}</div>
      </main>

      {/* Footer */}
      <footer
        className="text-light text-center py-3 mt-auto"
        style={{
          fontSize: "0.9rem",
          background: "linear-gradient(90deg, #0d6efd, #6c63ff)",
        }}
      >
        <p className="mb-0">
          &copy; {new Date().getFullYear()} My Website | All Rights Reserved
        </p>
      </footer>
    </div>
  );
};

export default Base;
