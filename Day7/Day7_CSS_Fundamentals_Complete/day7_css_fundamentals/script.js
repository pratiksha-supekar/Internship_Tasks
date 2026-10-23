// Mobile navigation and demo registration form behaviour.
const menuToggle = document.getElementById("menuToggle");
const navLinks = document.getElementById("navLinks");

menuToggle.addEventListener("click", () => {
  const isOpen = navLinks.classList.toggle("open");
  menuToggle.setAttribute("aria-expanded", String(isOpen));
});

navLinks.querySelectorAll("a").forEach((link) => {
  link.addEventListener("click", () => {
    navLinks.classList.remove("open");
    menuToggle.setAttribute("aria-expanded", "false");
  });
});

const registrationForm = document.getElementById("registrationForm");
const formMessage = document.getElementById("formMessage");

registrationForm.addEventListener("submit", (event) => {
  event.preventDefault();

  if (!registrationForm.checkValidity()) {
    registrationForm.reportValidity();
    return;
  }

  const studentName = document.getElementById("fullName").value.trim();
  formMessage.textContent = `Thank you, ${studentName}! Your demo registration was submitted successfully.`;
  registrationForm.reset();
});
