/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/templates/**/*.html", // Thymeleaf templates
    "./src/main/resources/static/**/*.html",    // Static HTML files
    "./src/main/resources/static/**/*.js",      // JS files
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}

